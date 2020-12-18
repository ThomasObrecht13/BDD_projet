package dao.jdbc.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VilleDaolmpl extends JdbcDao{

    public VilleDaolmpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> villes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VILLE");

            while (resultSet.next()) {
                Ville ville = new Ville();
                ville.setIdVille(resultSet.getInt("idVille"));
                ville.setNomVille(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
                villes.add(ville);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return villes;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Ville ville = new Ville();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VILLE WHERE idVille="+id);

            while (resultSet.next()) {
                ville.setIdVille(resultSet.getInt("idVille"));
                ville.setNomVille(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return ville;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement stmt= null;
        String sqlReq = "insert into VILLE(idVille,nomVille, nombreHabitants) values (?,?,?)";

        try {
            stmt = connection.prepareStatement(sqlReq);
            stmt.setInt(1, ville.getIdVille());
            stmt.setString(2, ville.getNomVille());
            stmt.setInt(3, ville.getNombreHabitsants());

            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(Entity entity) throws DaoException, SQLException {
        Ville ville = (Ville) entity;

        Statement statement = connection.createStatement();

        try {
            String sqlReq = "UPDATE VILLE SET nomVille='"+ville.getNomVille()+"',nombreHabitants="+ville.getNombreHabitsants()+" WHERE idVille="+ville.getIdVille();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("Ville : modifié");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException, SQLException {
        Ville ville = (Ville) entity;
        Statement statement = connection.createStatement();

        try {
            String sqlReq = "DELETE FROM VILLE WHERE idVille="+ville.getIdVille();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("ville : n°"+ville.getIdVille()+" supprimé");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
