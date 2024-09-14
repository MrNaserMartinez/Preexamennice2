package umg.lab.DataBase.Dao;

import umg.lab.DataBase.Model.eje3Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class eje3DAO {
    private Connection connection;

    public eje3DAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(eje3Model equipo) throws SQLException {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, " +
                "web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());
            pstmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    equipo.setIdEquipo(generatedKeys.getInt(1));
                }
            }
        }
    }

    public eje3Model findById(int id) throws SQLException {
        String sql = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new eje3Model(
                            rs.getInt("id_equipo"),
                            rs.getString("nombre"),
                            rs.getString("pais"),
                            rs.getString("ciudad"),
                            rs.getString("estadio"),
                            rs.getInt("fundacion"),
                            rs.getString("entrenador"),
                            rs.getString("web_oficial"),
                            rs.getString("facebook"),
                            rs.getString("twitter"),
                            rs.getString("instagram"),
                            rs.getString("patrocinador_principal"),
                            rs.getTimestamp("creado_en")
                    );
                }
            }
        }
        return null;
    }

    public List<eje3Model> findAll() throws SQLException {
        List<eje3Model> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos_champions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                equipos.add(new eje3Model(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("estadio"),
                        rs.getInt("fundacion"),
                        rs.getString("entrenador"),
                        rs.getString("web_oficial"),
                        rs.getString("facebook"),
                        rs.getString("twitter"),
                        rs.getString("instagram"),
                        rs.getString("patrocinador_principal"),
                        rs.getTimestamp("creado_en")
                ));
            }
        }
        return equipos;
    }

    public void update(eje3Model equipo) throws SQLException {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, " +
                "entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? " +
                "WHERE id_equipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());
            pstmt.setInt(12, equipo.getIdEquipo());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
