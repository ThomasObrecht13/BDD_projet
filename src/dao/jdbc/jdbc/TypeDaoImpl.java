package dao.jdbc.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


public class TypeDaoImpl extends JdbcDao {

    public TypeDaoImpl(Connection connection){super(connection);}

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> types = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TYPE");

            while (resultSet.next()) {
                Type type = new Type();
                type.setIdType(resultSet.getInt("idType"));
                type.setLibelléType(resultSet.getString("libelléType"));
                types.add(type);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return types;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Type type = new Type();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TYPE WHERE idType="+id);

            while (resultSet.next()) {
                type.setIdType(resultSet.getInt("idType"));
                type.setLibelléType(resultSet.getString("libelléType"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return type;
    }

    public void create(Entity entity) throws DaoException{
        Type type = (Type) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into TYPE(idType , libelléType) values(?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getIdType());
            statement.setString(2,type.getLibelléType());

            int res = statement.executeUpdate();

            if (res > 0) {
                System.out.println("ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void update(Entity entity) throws DaoException, SQLException {

        Type type = (Type) entity;

        Statement statement = connection.createStatement();

        try {


            String sqlReq = "UPDATE TYPE SET libelléType = " + type.getLibelléType() + " WHERE idType = " + type.getIdType();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne modifiée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void delete(Entity entity) throws DaoException, SQLException {

        Type type = (Type)entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "DELETE FROM TYPE WHERE idType = " + type.getIdType();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne supprimée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
