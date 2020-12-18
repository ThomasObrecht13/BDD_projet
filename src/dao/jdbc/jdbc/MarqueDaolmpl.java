package dao.jdbc.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MarqueDaolmpl extends JdbcDao{

    public MarqueDaolmpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> marques = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MARQUE");

            while (resultSet.next()) {
                Marque marque = new Marque();
                marque.setIdMarque(resultSet.getInt("idMarque"));
                marque.setNomMarque(resultSet.getString("nomMarque"));
                marques.add(marque);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marques;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Marque marque = new Marque();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MARQUE WHERE idMarque="+id);

            while (resultSet.next()) {
                marque.setIdMarque(resultSet.getInt("idMarque"));
                marque.setNomMarque(resultSet.getString("nomMarque"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marque;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement stmt= null;
        String sqlReq = "insert into Marque(idMarque,nomMarque) values (?,?)";

        try {
            stmt = connection.prepareStatement(sqlReq);
            stmt.setInt(1, marque.getIdMarque());
            stmt.setString(2, marque.getNomMarque());

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
        Marque marque = (Marque) entity;

        Statement statement = connection.createStatement();

        try {
            String sqlReq = "UPDATE MARQUE SET nomMarque='"+marque.getNomMarque()+"' WHERE idMarque="+marque.getIdMarque();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("Marque : modifié");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException, SQLException {
        Marque marque = (Marque) entity;
        Statement statement = connection.createStatement();

        try {
            String sqlReq = "DELETE FROM MARQUE WHERE idMarque="+marque.getIdMarque();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("marque : n°"+marque.getIdMarque()+" supprimé");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
