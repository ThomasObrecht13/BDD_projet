package dao.jdbc;

import dao.exception.DaoException;
import model.Entity;
import model.Client;
import model.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaolmpl extends JdbcDao{

    public ClientDaolmpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT");

            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setNomClient(resultSet.getString("nomClient"));
                client.setAdresseClient(resultSet.getString("adresseClient"));
                client.setCodePostalClient(resultSet.getInt("codePostalClient"));
                client.setIdVille(resultSet.getInt("idVille"));
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Client client = new Client();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT WHERE idClient="+id);

            while (resultSet.next()) {
                client.setIdClient(resultSet.getInt("idClient"));
                client.setNomClient(resultSet.getString("nomClient"));
                client.setAdresseClient(resultSet.getString("adresseClient"));
                client.setCodePostalClient(resultSet.getInt("codePostalClient"));
                client.setIdVille(resultSet.getInt("idVille"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement stmt= null;
        String sqlReq = "insert into ClIENT(nomClient, adresseClient, codePostalClient, idVille) values (?,?,?,?)";

        try {
            stmt = connection.prepareStatement(sqlReq);
            stmt.setString(1, client.getNomClient());
            stmt.setString(2, client.getAdresseClient());
            stmt.setInt(3, client.getCodePostalClient());
            stmt.setInt(4, client.getIdVille());

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
        Client client = (Client) entity;

        Statement statement = connection.createStatement();

        try {
            String sqlReq = "UPDATE CLIENT SET nomClient='"+client.getNomClient()+"',adresseClient='"+client.getAdresseClient()+
                    "', codePostalClient="+client.getCodePostalClient()+", idVille="+client.getIdVille()+" WHERE idClient="+client.getIdClient();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("Client : modifié");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException, SQLException {
        Client client = (Client) entity;
        Statement statement = connection.createStatement();

        try {
            String sqlReq = "DELETE FROM CLIENT WHERE idClient="+client.getIdClient();
            int res = statement.executeUpdate(sqlReq);
            if (res > 0) {
                System.out.println("agence : n°"+client.getIdVille()+" supprimé");
            }
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
}
