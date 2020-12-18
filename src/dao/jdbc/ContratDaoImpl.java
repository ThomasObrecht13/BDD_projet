package dao.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Contrat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ContratDaoImpl extends JdbcDao {

    public ContratDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> contrats = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTRAT");

            while (resultSet.next()) {
                Contrat contrat = new Contrat();
                contrat.setIdContrat(resultSet.getInt("idContrat"));
                contrat.setDateRetrait(resultSet.getString("dateRetrait"));
                contrat.setDateRetour(resultSet.getString("dateRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));
                contrat.setKmRetour(resultSet.getInt("kmRetour"));
                contrat.setIdClient(resultSet.getInt("idClient"));
                contrat.setImmatriculation(resultSet.getInt("immatriculation"));
                contrat.setIdAgenceRetour(resultSet.getInt("idAgence"));
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contrats;
    }

    @Override
    public Entity findById(int id) throws DaoException {

        Contrat contrat = new Contrat();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTRAT WHERE idContrat=" + id);

            while (resultSet.next()) {

                contrat.setIdContrat(resultSet.getInt("idContrat"));
                contrat.setDateRetrait(resultSet.getString("dateRetrait"));
                contrat.setDateRetour(resultSet.getString("dateRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));
                contrat.setKmRetour(resultSet.getInt("kmRetour"));
                contrat.setIdClient(resultSet.getInt("idClient"));
                contrat.setImmatriculation(resultSet.getInt("immatriculation"));
                contrat.setIdAgenceRetour(resultSet.getInt("idAgence"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contrat;
    }

    public void create(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into CONTRAT(idContrat , dateDeRetrait, dateDeRetour, kmRetrait, kmRetour, idClient, immatriculation, idAgenceRetour ) values(?,?,?,?,?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getIdContrat());
            statement.setString(2, contrat.getDateRetrait());
            statement.setString(3,contrat.getDateRetour());
            statement.setInt(4, contrat.getKmRetour());
            statement.setInt(5, contrat.getKmRetrait());
            statement.setInt(6, contrat.getIdClient());
            statement.setInt(7, contrat.getImmatriculation());
            statement.setInt(8, contrat.getIdAgenceRetour());

            int res = statement.executeUpdate();

            if (res > 0) {
                System.out.println("ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void update(Entity entity) throws DaoException, SQLException {

        Contrat contrat = (Contrat) entity;

        Statement statement = connection.createStatement();

        try {


            String sqlReq = "UPDATE CONTRAT SET dateDeRetrait = " +
                    contrat.getDateRetrait() + ", dateDeRetour =" +
                    contrat.getDateRetour() + ",kmRetrait =" +
                    contrat.getDateRetrait() + ",kmRetour" +
                    contrat.getDateRetour() + "idClient = "+
                    contrat.getIdClient() + "immatriculation =" +
                    contrat.getImmatriculation() + "idAgenceDeRetour"+
                    contrat.getIdAgenceRetour() +
                    " WHERE idContrat = " + contrat.getIdContrat();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne modifiée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void delete(Entity entity) throws DaoException, SQLException {

        Contrat contrat = (Contrat) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "DELETE FROM CONTRAT WHERE idContrat = " + contrat.getIdContrat();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne supprimée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
