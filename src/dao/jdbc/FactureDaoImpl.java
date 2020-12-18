package dao.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureDaoImpl extends JdbcDao {

    public FactureDaoImpl(Connection connection){super(connection);}

    @Override
    public Collection<Entity> findAll() throws DaoException {

        Collection<Entity> factures = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTURE");

            while (resultSet.next()) {
                Facture facture = new Facture();
               facture.setIdFacture(resultSet.getInt("idFacture"));
                facture.setMontant((resultSet.getInt("montant")));
                facture.setIdContrat(resultSet.getInt("idContrat"));
                factures.add(facture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return factures;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Facture facture = new Facture();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTURE WHERE idFacture="+id);

            while (resultSet.next()) {
                facture.setIdFacture(resultSet.getInt("idFacture"));
                facture.setMontant((resultSet.getInt("montant")));
                facture.setIdContrat(resultSet.getInt("idContrat"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return facture;
    }

    public void create(Entity entity) throws DaoException{
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into FACTURE(idFacture, montant, idContrat) values(?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getIdFacture());
            statement.setInt(2,facture.getMontant());
            statement.setInt(3, facture.getIdContrat());

            int res = statement.executeUpdate();

            if (res > 0) {
                System.out.println("ligne insérée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void update(Entity entity) throws DaoException, SQLException {

        Facture facture = (Facture) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "UPDATE FACTURE SET montant = " + facture.getMontant() + ", idContrat=" +
                    facture.getIdContrat() + " WHERE idFacture = " + facture.getIdFacture();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne modifiée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public void delete(Entity entity) throws DaoException, SQLException {

        Facture facture = (Facture) entity;

        Statement statement = connection.createStatement();

        try {

            String sqlReq = "DELETE FROM FACTURE WHERE idFacture = " + facture.getIdFacture();

            int res = statement.executeUpdate(sqlReq);

            if (res > 0) {
                System.out.println("ligne supprimée");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }


}
