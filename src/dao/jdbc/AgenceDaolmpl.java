package dao.jdbc;

import dao.exception.DaoException;
import model.Entity;
import model.Agence;
import model.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceDaolmpl extends JdbcDao{

    public AgenceDaolmpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> agences = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AGENCE");

            while (resultSet.next()) {
                Agence agence = new Agence();
                agence.setIdAgence(resultSet.getInt("idAgence"));
                agence.setNbEmployés(resultSet.getInt("nbEmployés"));
                agence.setIdVille(resultSet.getInt("idVille"));
                agences.add(agence);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agences;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Agence agence = new Agence();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AGENCE WHERE idAgence="+id);

            while (resultSet.next()) {
                agence.setIdAgence(resultSet.getInt("idAgence"));
                agence.setNbEmployés(resultSet.getInt("nbEmployés"));
                agence.setIdVille(resultSet.getInt("idVille"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agence;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement stmt= null;
        String sqlReq = "insert into AGENCE(nbEmployés, idVille) values (?,?)";

        try {
            stmt = connection.prepareStatement(sqlReq);
            stmt.setInt(1, agence.getNbEmployés());
            stmt.setInt(2, agence.getIdVille());

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
        Agence agence = (Agence) entity;

        Statement statement = connection.createStatement();

        try {
            String sqlReq = "UPDATE AGENCE SET nbEmployés="+agence.getNbEmployés()+",idVille="+agence.getIdVille()+
                    " WHERE idAgence="+agence.getIdAgence();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("Agence : modifié");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException, SQLException {
        Agence agence = (Agence) entity;
        Statement statement = connection.createStatement();

        try {
            String sqlReq = "DELETE FROM AGENCE WHERE idAgence="+agence.getIdAgence();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("agence : n°"+agence.getIdAgence()+" supprimé");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
