
import java.sql.*;
import java.util.Properties;

class ConnectionTest {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/projet";
        Properties props = new Properties();
        props.setProperty("user","thomas");
        props.setProperty("password","postgres");
        Connection conn = DriverManager.getConnection(url, props);

        requeteDeux(0,"2020-12-10",2,conn);
        requeteTrois(conn);
        requeteQuatre(conn);
        requeteCinq(12,conn);
        requeteSix(conn);
        requeteSept(0,"2020-12-20", conn);
        requeteHuit(conn);
        requeteNeuf(conn);
        requeteDix(conn);
        requeteOnze("2020-12-20", conn);
        conn.close();
    }

    public static void requeteDeux(int idClient, String date, int duree, Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        try {
            String sqlReq = "SELECT CO.idContrat, CI.nomClient, M.nomMarque, F.montant, CO.dateDeRetrait, CO.dateDeRetour  FROM CONTRAT as CO"+
                    " INNER JOIN VEHICULE as V ON V.immatriculation=CO.immatriculation"+
                    " INNER JOIN AGENCE as A ON A.idAgence=V.idAgence INNER JOIN CLIENT As CI ON CI.idClient=CO.idClient"+
                    " INNER JOIN FACTURE as F ON CO.idContrat=F.idContrat INNER JOIN MARQUE as M ON V.idMarque=M.idMarque"+
                    " WHERE V.idAgence!=CO.idAgenceRetour AND CO.idClient="+idClient+" AND CO.dateDeRetrait='"+date+"' AND date_mii(CO.dateDeRetrait,"+-duree+")=CO.dateDeRetour;";

            ResultSet res= statement.executeQuery(sqlReq);
            while(res.next()){
                int idContrat = res.getInt("idContrat");
                String nomClient = res.getString("nomClient");
                String nomMarque = res.getString("nomMarque");
                int montant = res.getInt("montant");
                Date dateDeRetrait = res.getDate("dateDeRetrait");
                Date dateDeRetour = res.getDate("dateDeRetour");

                System.out.println("Contrat n°:"+idContrat+" / nom client:"+nomClient+" / nom marque:"+nomMarque+" / montant:"+montant+
                        " / date retrait:"+dateDeRetrait+" / date retour:"+dateDeRetour);
            }
            res.close();
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public static void requeteTrois(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        try {
            String sqlReq ="SELECT dateDeRetour FROM CONTRAT WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);";

            ResultSet res= statement.executeQuery(sqlReq);
            while(res.next()){
                Date dateDeRetour = res.getDate("dateDeRetour");
                System.out.println(dateDeRetour+" est la date de retour du dernier véhicule loué");
            }
            res.close();
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
    public static void requeteQuatre(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        try {
            String sqlReq = "SELECT VI.nomVille, A.idAgence FROM VILLE as VI INNER JOIN AGENCE as A ON VI.idVille=A.idVille " +
                    "INNER JOIN VEHICULE as VE ON VE.idAgence=A.idAgence "+
                    "INNER JOIN CONTRAT as C ON VE.immatriculation=C.immatriculation " +
                    "WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);";

            ResultSet res= statement.executeQuery(sqlReq);
            while(res.next()){
                String nomVille = res.getString("nomVille");
                int idAgence = res.getInt("idAgence");

                System.out.println("L’établissement de la facture pour la location précédente: agence n°"+idAgence+" situé à "+nomVille);
            }
            res.close();
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
    public static void requeteCinq(int mois, Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        try {
            String sqlReq = "SELECT A.idAgence,SUM(F.montant) as chiffreAffaires FROM FACTURE as F " +
                    "INNER JOIN CONTRAT as C ON F.idContrat=C.idContrat INNER JOIN VEHICULE as V ON V.immatriculation=C.immatriculation \n" +
                    "INNER JOIN AGENCE as A ON A.idAgence=V.idAgence WHERE A.idAgence=0 AND to_char(dateDeRetour, 'MM')='"+mois+"' GROUP BY A.idAgence;";

            ResultSet res= statement.executeQuery(sqlReq);
            while(res.next()){
                String idAgence = res.getString("idAgence");
                int chiffreAffaires = res.getInt("chiffreAffaires");

                System.out.println(chiffreAffaires+"€ est le chiffre d'affaires de l'agence n°"+idAgence+" pour le "+mois+"ème mois");
            }
            res.close();
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }
    public static void requeteSix(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();

        try {
            String sqlReq ="SELECT M.nomMarque,COUNT(V.immatriculation) as nbVehicule FROM VEHICULE as V INNER JOIN MARQUE as M " +
                    "ON V.idMarque=M.idMarque GROUP BY M.idMarque;";

            ResultSet res= statement.executeQuery(sqlReq);
            while(res.next()){
                String nomMarque = res.getString("nomMarque");
                int nbVehicule = res.getInt("nbVehicule");
                System.out.println(nomMarque+" : "+nbVehicule);
            }
            res.close();
        } catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public  static  void requeteSept(int idAgence, String date,Connection conn) throws  SQLException{
        Statement statement = conn.createStatement();

        try {
            String sqlReq ="SELECT CLIENT.nomClient, MAX(COUNT(CLIENT.idClient)) as nbLocation FROM CLIENT INNER JOIN CONTRAT" +
                    "ON CLIENT.idClient = CONTRAT.idClient WHERE CONTRAT.idAgence =" + idAgence + " AND " +
                    "CONTRAT.dateRetrait LIKE " + date + "% ;";
            ResultSet res = statement.executeQuery(sqlReq);

            while (res.next()){
                String nomClient = res.getString("nomClient");
                int nbLocation = res.getInt("nbLocation");

                System.out.println(nomClient + ": " + nbLocation + " location(s)");
            }

        }catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public static void requeteHuit(Connection conn) throws SQLException{
        Statement statement = conn.createStatement();

        try {
            String sqlReq = " SELECT COUNT(FACTURE.montant) as chiffreAffaire, CATEGORIE.libelléCatégorie FROM CONTRAT" +
                    "INNER JOIN FACTURE ON CONTRAT.idFacture = FACTURE.idFacture"+
                    "INNER JOIN VEHICULE ON CONTRAT.immatriculation = VEHICULE.immatriculation" +
                    "INNER JOIN CATEGORIE ON VEHICULE.idCatégorie = CATAGORIE.idCatégorie"+
                    "GROUP BY CATEGORIE.libelléCatégorie;";
            ResultSet res = statement.executeQuery(sqlReq);

            while(res.next()){
                int chiffreAffaire = res.getInt("chiffreAffaire");
                String libelléCatégorie = res.getString("libelléCatégorie");

                System.out.println(libelléCatégorie + ": " + chiffreAffaire + "€");
            }        }catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public static void requeteNeuf(Connection conn) throws SQLException{
        Statement statement = conn.createStatement();

        try {
            String sqlReq = " SELECT COUNT(FACTURE.montant) as chiffreAffaire, TYPE.libelléType FROM CONTRAT" +
                    "INNER JOIN FACTURE ON CONTRAT.idFacture = FACTURE.idFacture"+
                    "INNER JOIN VEHICULE ON CONTRAT.immatriculation = VEHICULE.immatriculation" +
                    "INNER JOIN TYPE ON VEHICULE.idType = TYPE.idType"+
                    "GROUP BY TYPE.idType;";
            ResultSet res = statement.executeQuery(sqlReq);

            while(res.next()){
                int chiffreAffaire = res.getInt("chiffreAffaire");
                String libelléType = res.getString("libelléType");

                System.out.println(libelléType + ": " + chiffreAffaire + "€");
            }        }catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public static void requeteDix(Connection conn) throws SQLException{
        Statement statement = conn.createStatement();

        try {
            String sqlReq = " SELECT COUNT(VEHICULE.idVehicule),  VEHICULE.idAgence FROM VEHICULE" +
                    "WHERE VEHICULE.nbKilomètres >= 150000 AND (VEHICULE.dateMiseEnCirculation NOT LIKE '2020%' " +
                    "OR VEHICULE.dateMiseEnCirculation NOT LIKE '2019%')"+
                    "GROUP BY VEHICULE.idAgence;";
            ResultSet res = statement.executeQuery(sqlReq);

            while(res.next()){
                int nbVoiture= res.getInt("nbVoiture");
                int idAgence = res.getInt("idAgence");

                System.out.println(idAgence + ": " + nbVoiture + "usagées");
            }        }catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

    public static void requeteOnze(String date, Connection conn) throws SQLException{
        Statement statement = conn.createStatement();

        try {
            String sqlReq = " SELECT COUNT(FACTURE.montant),  CONTRAT.idAgence FROM FACTURE" +
                    "INNER JOIN CONTRAT ON FACTURE.idContrat = CONTRAT.idContrat"+
                    "WHERE CONTRAT.dateDeRetrait LIKE" + date +"% GROUP BY CONTRAT.idAgence;";
            ResultSet res = statement.executeQuery(sqlReq);

            while(res.next()){
                int chiffreAffaire = res.getInt("chiffreAffaire");
                int idAgence = res.getInt("idAgence");

                System.out.println(idAgence + ": " + chiffreAffaire + " en " + date);
            }        }catch(SQLException e) {
            System.err.println("Erreur SQL : " + e.getLocalizedMessage());
        }
    }

}