package umg.lab.DataBase.Services;

import umg.lab.DataBase.Dao.eje2DAO;
import umg.lab.DataBase.Model.eje2Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class eje2Service {
    private eje2DAO userDAO;

    public eje2Service(Connection connection) {
        this.userDAO = new eje2DAO(connection);
    }

    public void createUser(eje2Model user) throws SQLException {
        userDAO.insert(user);
    }

    public eje2Model getUserByCarne(String carne) throws SQLException {
        return userDAO.findByCarne(carne);
    }

    public List<eje2Model> getAllUsers() throws SQLException {
        return userDAO.findAll();
    }

    public void updateUser(eje2Model user) throws SQLException {
        userDAO.update(user);
    }

    public void deleteUser(String carne) throws SQLException {
        userDAO.delete(carne);
    }

    // Puedes agregar métodos adicionales aquí para lógica de negocio específica
    public boolean isUserActive(String carne) throws SQLException {
        eje2Model user = getUserByCarne(carne);
        return user != null && user.isActivo();
    }
}