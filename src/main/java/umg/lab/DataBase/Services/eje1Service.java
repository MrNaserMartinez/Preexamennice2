package umg.lab.DataBase.Services;

import umg.lab.DataBase.Dao.eje1DAO;
import umg.lab.DataBase.Model.eje1Model;

import java.sql.SQLException;
import java.util.List;

public class eje1Service {
    private eje1DAO datosDAO;

    public eje1Service() {
        this.datosDAO = new eje1DAO();
    }

    public void createDatos(eje1Model datos) throws SQLException {
        datosDAO.insert(datos);
    }

    public eje1Model getDatosByCodigo(int codigo) throws SQLException {
        return datosDAO.findByCodigo(codigo);
    }

    public List<eje1Model> getAllDatos() throws SQLException {
        return datosDAO.findAll();
    }

    public void updateDatos(eje1Model datos) throws SQLException {
        datosDAO.update(datos);
    }

    public void deleteDatos(int codigo) throws SQLException {
        datosDAO.delete(codigo);
    }

}
