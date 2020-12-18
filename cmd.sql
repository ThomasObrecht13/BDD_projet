DROP TABLE if exists FACTURE;
DROP TABLE if exists CONTRAT;
DROP TABLE if exists VEHICULE;
DROP TABLE if exists CLIENT;
DROP TABLE if exists AGENCE;
DROP TABLE if exists MARQUE;
DROP TABLE if exists MODELE;
DROP TABLE if exists VILLE;
DROP TABLE if exists TYPE;
DROP TABLE if exists CATEGORIE;

CREATE TABLE VILLE(
    idVille serial NOT NULL,
    nomVille varchar(100),
    nombreHabitants int,
    PRIMARY KEY (idVille)
);

CREATE TABLE AGENCE(
    idAgence serial NOT NULL,
    nbEmployés int,
    idVille int,
    PRIMARY KEY (idAgence),
    FOREIGN KEY (idVille) REFERENCES VILLE(idVille)
);

CREATE TABLE MARQUE(
    idMarque serial NOT NULL,
    nomMarque varchar(100),
    PRIMARY KEY (idMarque)
);
CREATE TABLE CLIENT(
    idClient serial NOT NULL,
    nomClient varchar(100),
    adresseClient varchar(100),
    codePostalClient int,
    idVille int,
    PRIMARY KEY (idClient),
    FOREIGN KEY (idVille) REFERENCES VILLE(idVille)
);
CREATE TABLE TYPE(
    idType serial NOT NULL,
    libelléType varchar(100),
    PRIMARY KEY (idType)
);
CREATE TABLE CATEGORIE(
    idCatégorie serial NOT NULL,
    libelléCatégorie varchar(100),
    PRIMARY KEY (idCatégorie)
);
CREATE TABLE MODELE(
    idModèle serial NOT NULL,
    dénomination varchar(100),
    puissanceFiscale int,
    PRIMARY KEY (idModèle)
);
CREATE TABLE VEHICULE(
    immatriculation serial NOT NULL,
    dateMiseEnCirculation date,
    état varchar(100),
    nbKilomètres int,
    prixParJourDeLocation int,
    idMarque int,
    idModèle int,
    idCatégorie int,
    idType int,
    idAgence int,
    PRIMARY KEY (immatriculation),
    FOREIGN KEY (idMarque) REFERENCES MARQUE(idMarque),
    FOREIGN KEY (idModèle) REFERENCES MODELE(idModèle),
    FOREIGN KEY (idCatégorie) REFERENCES CATEGORIE(idCatégorie),
    FOREIGN KEY (idType) REFERENCES TYPE(idType),
    FOREIGN KEY (idAgence) REFERENCES AGENCE(idAgence)
);

CREATE TABLE CONTRAT(
    idContrat serial NOT NULL,
    dateDeRetrait date,
    dateDeRetour date,
    kmRetrait int,
    kmRetour int,
    idClient int,
    immatriculation int,
    idAgencedeRetour int,
    PRIMARY KEY (idContrat),
    FOREIGN KEY (idClient) REFERENCES CLIENT(idClient),
    FOREIGN KEY (immatriculation) REFERENCES VEHICULE(immatriculation),
    FOREIGN KEY (idAgencedeRetour) REFERENCES AGENCE(idAgence)
);
CREATE TABLE FACTURE(
    idFacture serial NOT NULL,
    montant int,
    idContrat int,
    PRIMARY KEY (idFacture),
    FOREIGN KEY (idContrat) REFERENCES CONTRAT(idContrat)
);

INSERT INTO VILLE VALUES (0,'Montbeliard',20000);
INSERT INTO VILLE VALUES (1,'Belfort',30000);

INSERT INTO AGENCE VALUES (0,10, 0);
INSERT INTO AGENCE VALUES (1,12, 1);

INSERT INTO MARQUE VALUES (0,'peugeot');
INSERT INTO MARQUE VALUES (1,'renaud');

INSERT INTO MODELE VALUES (0,'306',0);
INSERT INTO MODELE VALUES (1,'5008',0);
INSERT INTO MODELE VALUES (2,'meganne',1);
INSERT INTO MODELE VALUES (3,'quamgou',1);

INSERT INTO TYPE VALUES (0,'essence');
INSERT INTO TYPE VALUES (1,'diesel');

INSERT INTO CATEGORIE VALUES (0,'SUV');

INSERT INTO ClIENT VALUES (0,'client1','adresse1',90000,0);

INSERT INTO VEHICULE VALUES(0,'01/11/2020','bon',10,2,0,0,0,0,0);
INSERT INTO VEHICULE VALUES(1,'02/11/2020','moyen',10,2,0,0,0,0,0);
INSERT INTO VEHICULE VALUES(2,'03/11/2020','bon',10,2,0,0,0,0,0);
INSERT INTO VEHICULE VALUES(3,'04/11/2020','mauvais',10,2,1,0,0,0,0);

INSERT INTO CONTRAT VALUES (0,'10/12/2020','12/12/2020',10,20,0,0,1);
INSERT INTO CONTRAT VALUES (1,'11/11/2020','21/11/2020',10,20,0,0,1);

INSERT INTO FACTURE VALUES (0,100,0);
INSERT INTO FACTURE VALUES (1,30,1);

/*requete 2*/
SELECT CO.idContrat, M.nomMarque, F.montant, CO.dateDeRetrait, CO.dateDeRetour  FROM CONTRAT as CO
    INNER JOIN VEHICULE as V ON V.immatriculation=CO.immatriculation
    INNER JOIN AGENCE as A ON A.idAgence=V.idAgence INNER JOIN CLIENT As CI ON CI.idClient=CO.idClient
    INNER JOIN FACTURE as F ON CO.idContrat=F.idContrat INNER JOIN MARQUE as M ON V.idMarque=M.idMarque
    WHERE V.idAgence!=CO.idAgenceDeRetour AND CO.idClient=0 AND CO.dateDeRetrait='10/12/2020' AND date_mii(CO.dateDeRetrait,-2)=CO.dateDeRetour;

/*requete 3*/
SELECT dateDeRetour FROM CONTRAT WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);

/*requete 4*/
SELECT VI.nomVille FROM VILLE as VI INNER JOIN AGENCE as A ON VI.idVille=A.idVille INNER JOIN VEHICULE as VE ON VE.idAgence=A.idAgence
    INNER JOIN CONTRAT as C ON VE.immatriculation=C.immatriculation  WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);

/*requete 5*/
SELECT A.idAgence,SUM(F.montant) as chiffreAffaires FROM FACTURE as F
    INNER JOIN CONTRAT as C ON F.idContrat=C.idContrat INNER JOIN VEHICULE as V ON V.immatriculation=C.immatriculation
    INNER JOIN AGENCE as A ON A.idAgence=V.idAgence WHERE A.idAgence=0 AND to_char(dateDeRetour, 'MM')='12' GROUP BY A.idAgence;

/*requete 6*/
SELECT M.nomMarque,COUNT(V.immatriculation) as nbVehicule FROM VEHICULE as V INNER JOIN MARQUE as M
    ON V.idMarque=M.idMarque GROUP BY M.idMarque;

/*requete 7*/
SELECT CLIENT.nomClient,  count(CONTRAT.idContrat) AS nbLocation FROM FACTURE
    INNER JOIN CONTRAT ON CONTRAT.idContrat = FACTURE.idContrat
    INNER JOIN VEHICULE ON VEHICULE.immatriculation = CONTRAT.immatriculation
    INNER JOIN AGENCE ON VEHICULE.idAgence = AGENCE.idAgence
    INNER JOIN CLIENT ON CLIENT.idClient = CONTRAT.idClient
    WHERE CONTRAT.idAgenceDeRetour =1 AND
    to_char(CONTRAT.dateDeRetrait, 'YYYY')='2020' GROUP BY CLIENT.nomclient
    order by nbLocation DESC LIMIT 1;

/*requete 8*/
SELECT SUM(FACTURE.montant) as chiffreAffaire, CATEGORIE.libelléCatégorie FROM CONTRAT
    INNER JOIN FACTURE ON CONTRAT.idContrat = FACTURE.idContrat
    INNER JOIN VEHICULE ON CONTRAT.immatriculation = VEHICULE.immatriculation
    INNER JOIN CATEGORIE ON VEHICULE.idCatégorie = CATEGORIE.idCatégorie
    GROUP BY CATEGORIE.idCatégorie;

 /*requete 9*/
SELECT SUM(FACTURE.montant) as chiffreAffaire, TYPE.libelléType FROM CONTRAT
    INNER JOIN FACTURE ON CONTRAT.idContrat = FACTURE.idContrat
    INNER JOIN VEHICULE ON CONTRAT.immatriculation = VEHICULE.immatriculation
    INNER JOIN TYPE ON VEHICULE.idType = TYPE.idType
    GROUP BY TYPE.idType;

/*requete 10*/
SELECT COUNT(VEHICULE.immatriculation)as nbVoiture,  VEHICULE.idAgence FROM VEHICULE
    WHERE VEHICULE.nbKilomètres >= 150000
    AND to_char(VEHICULE.dateMiseEnCirculation, 'YYYY')!='2019'AND
    to_char(VEHICULE.dateMiseEnCirculation, 'YYYY')!='2018'GROUP BY VEHICULE.idAgence;

/*requete 11*/
SELECT SUM(FACTURE.montant) as chiffreAffaires,  CONTRAT.idAgenceDeRetour FROM FACTURE
    INNER JOIN CONTRAT ON FACTURE.idContrat = CONTRAT.idContrat
    INNER JOIN AGENCE ON CONTRAT.idAgenceDeRetour = AGENCE.idAgence
    WHERE to_char(CONTRAT.dateDeRetrait, 'YYYY')='2020' GROUP BY CONTRAT.idAgenceDeRetour;