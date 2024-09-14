package umg.lab.DataBase.Services;

import umg.lab.DataBase.Dao.eje3DAO;
import umg.lab.DataBase.Model.eje3Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class eje3Service {
    private eje3DAO equipoDAO;

    public eje3Service(Connection connection) {
        this.equipoDAO = new eje3DAO(connection);
    }

    public void createEquipo(eje3Model equipo) throws SQLException {
        equipoDAO.insert(equipo);
    }

    public eje3Model getEquipoById(int id) throws SQLException {
        return equipoDAO.findById(id);
    }

    public List<eje3Model> getAllEquipos() throws SQLException {
        return equipoDAO.findAll();
    }

    public void updateEquipo(eje3Model equipo) throws SQLException {
        equipoDAO.update(equipo);
    }

    public void deleteEquipo(int id) throws SQLException {
        equipoDAO.delete(id);
    }
}