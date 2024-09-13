package umg.lab.DataBase.Dao;


import umg.lab.DataBase.Model.eje1Model;
import umg.lab.DataBaseConeccion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class eje1DAO {

    public void insert(eje1Model datos) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, datos.getNombre());
            pstmt.setString(2, datos.getApellido());
            pstmt.setString(3, datos.getDepartamento());
            pstmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    datos.setCodigo(generatedKeys.getInt(1));
                }
            }
        }
    }

    public eje1Model findByCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new eje1Model(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento"),
                            rs.getDate("fecha_nacimiento")
                    );
                }
            }
        }
        return null;
    }

    public List<eje1Model> findAll() throws SQLException {
        List<eje1Model> datosList = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                datosList.add(new eje1Model(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("departamento"),
                        rs.getDate("fecha_nacimiento")
                ));
            }
        }
        return datosList;
    }

    public void update(eje1Model datos) throws SQLException {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datos.getNombre());
            pstmt.setString(2, datos.getApellido());
            pstmt.setString(3, datos.getDepartamento());
            pstmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            pstmt.setInt(5, datos.getCodigo());
            pstmt.executeUpdate();
        }
    }

    public void delete(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
        }
    }
}
