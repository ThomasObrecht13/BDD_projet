package dao.jdbc.jdbc;

import dao.exception.DaoException;
import dao.jdbc.JdbcDao;
import model.Entity;
import model.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


public class VehiculeDaolmpl extends JdbcDao{

    public VehiculeDaolmpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> vehicules = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VEHICULE");

            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("dateMiseEnCirculation"));
                vehicule.setÉtat(resultSet.getString("état"));
                vehicule.setNbKilomètres(resultSet.getInt("nbKilomètres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("prixParJourDeLocation"));
                vehicule.setIdMarque(resultSet.getInt("idMarque"));
                vehicule.setIdModèle(resultSet.getInt("idModèle"));
                vehicule.setIdCatégorie(resultSet.getInt("idCatégorie"));
                vehicule.setIdType(resultSet.getInt("idType"));
                vehicule.setIdAgence(resultSet.getInt("idAgence"));

                vehicules.add(vehicule);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return vehicules;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Vehicule vehicule = new Vehicule();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM VEHICULE WHERE immatriculation="+id);

            while (resultSet.next()) {
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("dateMiseEnCirculation"));
                vehicule.setÉtat(resultSet.getString("état"));
                vehicule.setNbKilomètres(resultSet.getInt("nbKilomètres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("prixParJourDeLocation"));
                vehicule.setIdMarque(resultSet.getInt("idMarque"));
                vehicule.setIdModèle(resultSet.getInt("idModèle"));
                vehicule.setIdCatégorie(resultSet.getInt("idCatégorie"));
                vehicule.setIdType(resultSet.getInt("idType"));
                vehicule.setIdAgence(resultSet.getInt("idAgence"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return vehicule;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement stmt= null;
        String sqlReq = "insert into VEHICULE(immatriculation,dateMiseEnCirculation, état, nbKilomètres, prixParJourDeLocation, idMarque, idModèle, idCatégorie, idType, idAgence) values (?,?,?,?,?,?,?,?,?,?)";

        try {
            Date date= Date.valueOf(vehicule.getDateMiseEnCirculation());
            stmt = connection.prepareStatement(sqlReq);
            stmt.setInt(1, vehicule.getImmatriculation());
            stmt.setDate(2, date);
            stmt.setString(3, vehicule.getÉtat());
            stmt.setInt(4, vehicule.getNbKilomètres());
            stmt.setInt(5, vehicule.getPrixParJourDeLocation());
            stmt.setInt(6, vehicule.getIdMarque());
            stmt.setInt(7, vehicule.getIdModèle());
            stmt.setInt(8, vehicule.getIdCatégorie());
            stmt.setInt(9, vehicule.getIdType());
            stmt.setInt(10, vehicule.getIdAgence());

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
        Vehicule vehicule = (Vehicule) entity;

        Statement statement = connection.createStatement();
        try {
            Date date= Date.valueOf(vehicule.getDateMiseEnCirculation());
            String sqlReq = "UPDATE VEHICULE SET dateMiseEnCirculation='"+date+"',état='"+vehicule.getÉtat()+
                    "', nbKilomètres="+vehicule.getNbKilomètres()+", prixParJourDeLocation="+vehicule.getPrixParJourDeLocation()+
                    ",idMarque="+vehicule.getIdMarque()+",idModèle="+vehicule.getIdModèle()+",idCatégorie="+vehicule.getIdCatégorie()+",idType="+vehicule.getIdType() +
                    ",idAgence="+vehicule.getIdAgence()+" WHERE immatriculation="+vehicule.getImmatriculation();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("Vehicule : modifié");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException, SQLException {
        Vehicule vehicule = (Vehicule) entity;
        Statement statement = connection.createStatement();

        try {
            String sqlReq = "DELETE FROM VEHICULE WHERE immatriculation="+vehicule.getImmatriculation();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("vehicule : n°"+vehicule.getImmatriculation()+" supprimé");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
