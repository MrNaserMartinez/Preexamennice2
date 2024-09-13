package umg.lab.DataBase.Dao;

import umg.lab.DataBase.Model.eje2Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class eje2DAO {
    private Connection connection; // Asume que tienes una conexión a la base de datos

    public eje2DAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(eje2Model user) throws SQLException {
        String sql = "INSERT INTO tb_user (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getCarne());
            pstmt.setString(2, user.getNombre());
            pstmt.setString(3, user.getCorreo());
            pstmt.setString(4, user.getSeccion());
            pstmt.setString(5, user.getTelegramId());
            pstmt.setBoolean(6, user.isActivo());
            pstmt.executeUpdate();
        }
    }

    public eje2Model findByCarne(String carne) throws SQLException {
        String sql = "SELECT * FROM tb_user WHERE carne = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, carne);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new eje2Model(
                            rs.getString("carne"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("seccion"),
                            rs.getString("telegramid"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return null;
    }

    public List<eje2Model> findAll() throws SQLException {
        List<eje2Model> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new eje2Model(
                        rs.getString("carne"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("seccion"),
                        rs.getString("telegramid"),
                        rs.getBoolean("activo")
                ));
            }
        }
        return users;
    }

    public void update(eje2Model user) throws SQLException {
        String sql = "UPDATE tb_user SET nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE carne = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getCorreo());
            pstmt.setString(3, user.getSeccion());
            pstmt.setString(4, user.getTelegramId());
            pstmt.setBoolean(5, user.isActivo());
            pstmt.setString(6, user.getCarne());
            pstmt.executeUpdate();
        }
    }

    public void delete(String carne) throws SQLException {
        String sql = "DELETE FROM tb_user WHERE carne = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, carne);
            pstmt.executeUpdate();
        }
    }

}
