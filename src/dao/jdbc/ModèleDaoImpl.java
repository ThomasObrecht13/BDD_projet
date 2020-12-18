package dao.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModèleDaoImpl extends JdbcDao {

    public ModèleDaoImpl(Connection connection){super(connection);}

    @Override
    public Collection<Entity> findAll() throws DaoException {

        Collection<Entity> modèles = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MODELE");

            while (resultSet.next()) {
                Modèle modèle = new Modèle();
                modèle.setIdModèle(resultSet.getInt("idModèle"));
                modèle.setDénomination((resultSet.getString("dénomination")));
                modèle.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
                modèles.add(modèle);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return modèles;
    }
    @Override
    public Entity findById(int id) throws DaoException {
        Modèle modèle = new Modèle();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MODELE WHERE idModèle="+id);

            while (resultSet.next()) {
                modèle.setIdModèle(resultSet.getInt("idModèle"));
                modèle.setDénomination(resultSet.getString("dénomination"));
                modèle.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return modèle;
    }

    public void create(Entity entity) throws DaoException{
        Modèle modèle = (Modèle) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into MODELE(idModèle , dénomination, puissanceFiscale) values(?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modèle.getIdModèle());
            statement.setString(2,modèle.getDénomination());
            statement.setInt(3, modèle.getPuissanceFiscale());

            int res = statement.executeUpdate();

            if (res > 0) {
                System.out.println("ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }


    public void update(Entity entity) throws DaoException, SQLException {

        Modèle modèle = (Modèle) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "UPDATE MODELE SET dénomination = " + modèle.getDénomination() + ", puissanceFiscale =" +
                    modèle.getPuissanceFiscale() + " WHERE idModèle = " + modèle.getIdModèle();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne modifiée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }


    public void delete(Entity entity) throws DaoException, SQLException {

       Modèle modèle = (Modèle) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "DELETE FROM MODELE WHERE idModèle = " + modèle.getIdModèle();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne supprimée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
