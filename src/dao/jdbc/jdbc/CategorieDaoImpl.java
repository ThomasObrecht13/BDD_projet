package dao.jdbc.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Catégorie;
import model.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieDaoImpl extends JdbcDao {

    public CategorieDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> catégories = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIE");

            while (resultSet.next()) {
                Catégorie catégorie = new Catégorie();
                catégorie.setIdCatégorie(resultSet.getInt("idCatégorie"));
                catégorie.setLibelléCatégorie(resultSet.getString("libelléCatégorie"));
                catégories.add(catégorie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return catégories;
    }

    @Override
    public Entity findById(int id) throws DaoException {

        Catégorie catégorie = new Catégorie();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIE WHERE idCatégorie=" + id);

            while (resultSet.next()) {
                catégorie.setIdCatégorie(resultSet.getInt("idCatégorie"));
                catégorie.setLibelléCatégorie(resultSet.getString("libelléCatégorie"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return catégorie;
    }

    public void create(Entity entity) throws DaoException {
        Catégorie catégorie = (Catégorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into CATEGORIE(idCatégorie , libelléCatégorie) values(?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, catégorie.getIdCatégorie());
            statement.setString(2, catégorie.getLibelléCatégorie());

            int res = statement.executeUpdate();

            if (res > 0) {
                System.out.println("ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void update(Entity entity) throws DaoException, SQLException {

        Catégorie catégorie = (Catégorie) entity;

        Statement statement = connection.createStatement();

        try {


            String sqlReq = "UPDATE CATEGORIE SET libelléCatégorie = " +
                    catégorie.getLibelléCatégorie() + " WHERE idCatégorie = " + catégorie.getIdCatégorie();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne modifiée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void delete(Entity entity) throws DaoException, SQLException {

        Catégorie catégorie = (Catégorie) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "DELETE FROM CATEGORIE WHERE idCatégorie = " + catégorie.getIdCatégorie();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne supprimée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}