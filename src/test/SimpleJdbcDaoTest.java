package test;//package test;

import dao.Dao;
import dao.jdbc.*;
import dao.exception.DaoException;
//import dao.jdbc.EtudiantDaoImpl;
import model.*;
import sql.PostgresConnection;

import java.nio.file.FileSystemAlreadyExistsException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class SimpleJdbcDaoTest {
    private Connection connection;
    private Dao dao;


    private void findAllVille() {
        Dao dao = new VilleDaolmpl(connection);

        try {
            Collection<Entity> etudiants = dao.findAll();
            for (Entity entity : etudiants) {
                Ville ville = (Ville) entity;

                System.out.println(ville.getIdVille() + " | " + ville.getNomVille()+ " | " + ville.getNombreHabitsants());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createVille(Ville ville) {
        try {
            dao.create(ville);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifVille(Ville ville) {
        try {
            dao.update(ville);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteVille(Ville ville) {
        try {
            dao.delete(ville);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void findAllAgence() {
        Dao dao = new AgenceDaolmpl(connection);

        try {
            Collection<Entity> agences = dao.findAll();
            for (Entity entity : agences) {
                Agence agence = (Agence) entity;

                System.out.println(agence.getIdAgence() + " | " + agence.getNbEmployés()+ " | " + agence.getIdVille());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createAgence(Agence agence) {
        try {
            dao.create(agence);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifAgence(Agence agence) {
        try {
            dao.update(agence);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteAgence(Agence agence) {
        try {
            dao.delete(agence);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void findAllMarque() {
        Dao dao = new MarqueDaolmpl(connection);

        try {
            Collection<Entity> marques = dao.findAll();
            for (Entity entity : marques) {
                Marque marque = (Marque) entity;

                System.out.println(marque.getIdMarque() + " | " + marque.getNomMarque());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createMarque(Marque marque) {
        try {
            dao.create(marque);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifMarque(Marque marque) {
        try {
            dao.update(marque);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteMarque(Marque marque) {
        try {
            dao.delete(marque);
        } catch (DaoException | SQLException e ) {
            e.printStackTrace();
        }
    }
    private void findAllClient() {
        Dao dao = new ClientDaolmpl(connection);

        try {
            Collection<Entity> clients = dao.findAll();
            for (Entity entity : clients) {
                Client client = (Client) entity;

                System.out.println(client.getIdClient() + " | " + client.getNomClient()+ " | " + client.getAdresseClient()+ " | " + client.getCodePostalClient()+ " | " + client.getIdVille());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createClient(Client client) {
        try {
            dao.create(client);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifClient(Client client) {
        try {
            dao.update(client);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteClient(Client client) {
        try {
            dao.delete(client);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void findAllVehicule() {
        Dao dao = new VehiculeDaolmpl(connection);

        try {
            Collection<Entity> vehicules = dao.findAll();
            for (Entity entity : vehicules) {
                Vehicule vehicule = (Vehicule) entity;

                System.out.println(vehicule.getImmatriculation() + " | " + vehicule.getDateMiseEnCirculation()+ " | " + vehicule.getÉtat()+
                        " | " + vehicule.getNbKilomètres()+ " | " + vehicule.getPrixParJourDeLocation()+ " | " + vehicule.getIdMarque()+
                        " | " + vehicule.getIdModèle()+ " | " + vehicule.getIdCatégorie()+ " | " + vehicule.getIdType()+ " | " + vehicule.getIdAgence());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createVehicule(Vehicule vehicule) {
        try {
            dao.create(vehicule);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifVehicule(Vehicule vehicule) {
        try {
            dao.update(vehicule);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteVehicule(Vehicule vehicule) {
        try {
            dao.delete(vehicule);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void test() throws DaoException {
        connection = PostgresConnection.getInstance();

        System.out.println("********** Test Ville  **********");
        dao = new VilleDaolmpl(connection);
        System.out.println("***** Création d'une Ville : ");
        Ville ville = new Ville(2, "bart",30000);
        createVille(ville);
        System.out.println("***** Liste des Ville : ");
        findAllVille();
        System.out.println("***** Modification Ville :");
        Ville new_ville = new Ville(2,"veauj",25000);
        modifVille(new_ville);
        System.out.println("***** Liste des Ville : ");
        findAllVille();
        System.out.println("***** Suppression Ville :");
        deleteVille(new_ville);
        System.out.println("***** Liste des Ville : ");
        findAllVille();

        System.out.println("");

        System.out.println("********** Test Agence  **********");
        dao = new AgenceDaolmpl(connection);
        System.out.println("***** Création d'une Agence : ");
        Agence agence = new Agence(2, 11,0);
        createAgence(agence);
        System.out.println("***** Liste des Agence : ");
        findAllAgence();
        System.out.println("***** Modification Agence :");
        Agence new_agence = new Agence(2, 16,0);
        modifAgence(new_agence);
        System.out.println("***** Liste des Agence : ");
        findAllAgence();
        System.out.println("***** Suppression Agence :");
        deleteAgence(new_agence);
        System.out.println("***** Liste des Agence : ");
        findAllAgence();

        System.out.println("");

        System.out.println("********** Test Marque  **********");
        dao = new MarqueDaolmpl(connection);
        System.out.println("***** Création d'une Marque : ");
        Marque marque = new Marque(2,"citroen");
        createMarque(marque);
        System.out.println("***** Liste des Marque : ");
        findAllMarque();
        System.out.println("***** Modification Marque :");
        Marque new_marque = new Marque(2,"ds");
        modifMarque(new_marque);
        System.out.println("***** Liste des Marque : ");
        findAllMarque();
        System.out.println("***** Suppression Marque :");
        deleteMarque(new_marque);
        System.out.println("***** Liste des Marque : ");
        findAllMarque();

        System.out.println("");

        System.out.println("********** Test Client  **********");
        dao = new ClientDaolmpl(connection);
        System.out.println("***** Création d'une Client : ");
        Client client = new Client(1,"client2","adresse2",90000,0);
        createClient(client);
        System.out.println("***** Liste des Client : ");
        findAllClient();
        System.out.println("***** Modification Client :");
        Client new_client = new Client(1,"client2_modif","adresse2_modif",90000,0);
        modifClient(new_client);
        System.out.println("***** Liste des Client : ");
        findAllClient();
        System.out.println("***** Suppression Client :");
        deleteClient(new_client);
        System.out.println("***** Liste des Client : ");
        findAllClient();

        System.out.println("");

        System.out.println("********** Test Vehicule  **********");
        dao = new VehiculeDaolmpl(connection);
        System.out.println("***** Création d'une Vehicule : ");
        Vehicule vehicule = new Vehicule(4,"2020-12-10","bon",45,20,0,0,0,0,0);
        createVehicule(vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();
        System.out.println("***** Modification Vehicule :");
        Vehicule new_vehicule = new Vehicule(4,"2020-12-20","mauvais",50,25,0,0,0,0,0);
        modifVehicule(new_vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();
        System.out.println("***** Suppression Vehicule :");
        deleteVehicule(new_vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();

    }

    public static void main(String[] args) throws DaoException {
        new SimpleJdbcDaoTest().test();
    }
}