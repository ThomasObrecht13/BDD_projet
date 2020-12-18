package test;//package test;

import dao.Dao;
import dao.jdbc.*;
import dao.exception.DaoException;
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
    private void findAllType() {
        Dao dao = new TypeDaoImpl(connection);

        try {
            Collection<Entity> types = dao.findAll();
            for (Entity entity : types) {
                Type type = (Type) entity;

                System.out.println(type.getIdType() + " | " + type.getLibelléType());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createType(Type type) {
        try {
            dao.create(type);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifType(Type type) {
        try {
            dao.update(type);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteType(Type type) {
        try {
            dao.delete(type);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void findAllCategorie() {
        Dao dao = new CategorieDaoImpl(connection);

        try {
            Collection<Entity> catégories = dao.findAll();
            for (Entity entity : catégories) {
                Catégorie catégorie = (Catégorie) entity;

                System.out.println(catégorie.getIdCatégorie() + " | " + catégorie.getLibelléCatégorie());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createCategorie(Catégorie catégorie) {
        try {
            dao.create(catégorie);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifCategorie(Catégorie catégorie) {
        try {
            dao.update(catégorie);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteCategorie(Catégorie catégorie) {
        try {
            dao.delete(catégorie);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void findAllModèle() {
        Dao dao = new ModèleDaoImpl(connection);

        try {
            Collection<Entity> modèles = dao.findAll();
            for (Entity entity : modèles) {
                Modèle modèle = (Modèle) entity;

                System.out.println(modèle.getIdModèle() + " | " + modèle.getPuissanceFiscale()+ " | " + modèle.getDénomination());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createModèle(Modèle modèle) {
        try {
            dao.create(modèle);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifModèle(Modèle modèle) {
        try {
            dao.update(modèle);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteModèle(Modèle modèle) {
        try {
            dao.delete(modèle);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void findAllContrat() {
        Dao dao = new ContratDaoImpl(connection);

        try {
            Collection<Entity> contrats = dao.findAll();
            for (Entity entity : contrats) {
                Contrat contrat = (Contrat) entity;

                System.out.println(contrat.getIdContrat() + " | " + contrat.getDateRetrait()+ " | " + contrat.getDateRetour()+
                        " | " + contrat.getKmRetrait()+" | "+ contrat.getKmRetour()+ " | " + contrat.getIdClient()+
                        " | " + contrat.getImmatriculation()+ " | " + contrat.getIdAgenceRetour());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createContrat(Contrat contrat) {
        try {
            dao.create(contrat);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifContrat(Contrat contrat) {
        try {
            dao.update(contrat);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteContrat(Contrat contrat) {
        try {
            dao.delete(contrat);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void findAllFacture() {
        Dao dao = new FactureDaoImpl(connection);

        try {
            Collection<Entity> factures = dao.findAll();
            for (Entity entity : factures) {
                Facture facture = (Facture) entity;

                System.out.println(facture.getIdFacture() + " | " + facture.getMontant()+ " | " + facture.getIdContrat());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void createFacture(Facture facture) {
        try {
            dao.create(facture);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private void modifFacture(Facture facture) {
        try {
            dao.update(facture);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteFacture(Facture facture) {
        try {
            dao.delete(facture);
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }



    public void test() throws DaoException {
        connection = PostgresConnection.getInstance();

        System.out.println("********** Test Ville  **********");
        dao = new VilleDaolmpl(connection);
        System.out.println("***** Création d'une Ville : ");
        Ville ville = new Ville(2, "ville_test",30000);
        createVille(ville);
        System.out.println("***** Liste des Ville : ");
        findAllVille();
        System.out.println("***** Modification Ville :");
        Ville new_ville = new Ville(2,"ville_test_modif",25000);
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
        Marque marque = new Marque(2,"marque_test");
        createMarque(marque);
        System.out.println("***** Liste des Marque : ");
        findAllMarque();
        System.out.println("***** Modification Marque :");
        Marque new_marque = new Marque(2,"marque_test_modif");
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
        Client client = new Client(2,"client2","adresse_test",90000,0);
        createClient(client);
        System.out.println("***** Liste des Client : ");
        findAllClient();
        System.out.println("***** Modification Client :");
        Client new_client = new Client(2,"client2_modif","adresse_test_modif",90000,0);
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
        Vehicule vehicule = new Vehicule(4,"2020-06-24","bon",45,20,0,0,0,0,0);
        createVehicule(vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();
        System.out.println("***** Modification Vehicule :");
        Vehicule new_vehicule = new Vehicule(4,"2020-10-14","mauvais",50,25,0,0,0,0,0);
        modifVehicule(new_vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();
        System.out.println("***** Suppression Vehicule :");
        deleteVehicule(new_vehicule);
        System.out.println("***** Liste des Vehicule : ");
        findAllVehicule();

        System.out.println(" ");

        System.out.println("********** Test Type  **********");
        dao = new TypeDaoImpl(connection);
        System.out.println("***** Création d'une Type : ");
        Type type = new Type(2,"type_test");
        createType(type);
        System.out.println("***** Liste des Type : ");
        findAllType();
        System.out.println("***** Modification Type :");
        Type new_type = new Type(2,"type_test_modif");
        modifType(new_type);
        System.out.println("***** Liste des Type : ");
        findAllType();
        System.out.println("***** Suppression Type :");
        deleteType(new_type);
        System.out.println("***** Liste des Type : ");
        findAllType();

        System.out.println(" ");

        System.out.println("********** Test Catégorie  **********");
        dao = new CategorieDaoImpl(connection);
        System.out.println("***** Création d'une Catégorie : ");
        Catégorie catégorie = new Catégorie(1,"catégorie_test");
        createCategorie(catégorie);
        System.out.println("***** Liste des Catégorie : ");
        findAllCategorie();
        System.out.println("***** Modification Catégorie :");
        Catégorie new_catégorie = new Catégorie(1,"catégorie_test_modif");
        modifCategorie(new_catégorie);
        System.out.println("***** Liste des Catégorie : ");
        findAllCategorie();
        System.out.println("***** Suppression Catégorie :");
        deleteCategorie(new_catégorie);
        System.out.println("***** Liste des Catégorie : ");
        findAllCategorie();

        System.out.println(" ");

        System.out.println("********** Test Modèle  **********");
        dao = new ModèleDaoImpl(connection);
        System.out.println("***** Création d'une Modèle : ");
        Modèle modèle = new Modèle(4,"modèles_test");
        createModèle(modèle);
        System.out.println("***** Liste des Modèle : ");
        findAllModèle();
        System.out.println("***** Modification Modèle :");
        Modèle new_modèle = new Modèle(4,"modèles_test_modif");
        modifModèle(new_modèle);
        System.out.println("***** Liste des Modèle : ");
        findAllModèle();
        System.out.println("***** Suppression Modèle :");
        deleteModèle(new_modèle);
        System.out.println("***** Liste des Modèle : ");
        findAllModèle();

        System.out.println(" ");

        System.out.println("********** Test Contrat  **********");
        dao = new ContratDaoImpl(connection);
        System.out.println("***** Création d'une Contrat : ");
        Contrat contrat = new Contrat(3,"2020-08-15","2020-09-14",10,20,0,0,0);
        createContrat(contrat);
        System.out.println("***** Liste des Contrat : ");
        findAllContrat();
        System.out.println("***** Modification Contrat :");
        Contrat new_contrat = new Contrat(3,"2020-09-16","2020-11-24",11,22,0,1,1);
        modifContrat(new_contrat);
        System.out.println("***** Liste des Contrat : ");
        findAllContrat();
        System.out.println("***** Suppression Contrat :");
        deleteContrat(new_contrat);
        System.out.println("***** Liste des Contrat : ");
        findAllContrat();

        System.out.println(" ");

        System.out.println("********** Test Facture  **********");
        dao = new FactureDaoImpl(connection);
        System.out.println("***** Création d'une Facture : ");
        Facture facture = new Facture(2,100,0);
        createFacture(facture);
        System.out.println("***** Liste des Facture : ");
        findAllFacture();
        System.out.println("***** Modification Facture :");
        Facture new_facture = new Facture(2,120,0);
        modifFacture(new_facture);
        System.out.println("***** Liste des Facture ");
        findAllFacture();
        System.out.println("***** Suppression Facture :");
        deleteFacture(new_facture);
        System.out.println("***** Liste des Facture : ");
        findAllFacture();
    }



    public static void main(String[] args) throws DaoException {
        new SimpleJdbcDaoTest().test();
    }
}