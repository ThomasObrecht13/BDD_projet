DROP TABLE CONTRAT;
DROP TABLE VEHICULE;
DROP TABLE CLIENT;
DROP TABLE AGENCE;
DROP TABLE FACTURE;
DROP TABLE MARQUE;
DROP TABLE MODELE;
DROP TABLE VILLE;
DROP TABLE TYPE;

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
    idAgenceRetour int,
    PRIMARY KEY (idContrat),
    FOREIGN KEY (idClient) REFERENCES CLIENT(idClient),
    FOREIGN KEY (immatriculation) REFERENCES VEHICULE(immatriculation),
    FOREIGN KEY (idAgenceRetour) REFERENCES AGENCE(idAgence)
);
CREATE TABLE FACTURE(
    idFacture serial NOT NULL,
    montant int,
    idContrat int,
    PRIMARY KEY (idFacture),
    FOREIGN KEY (idContrat) REFERENCES CONTRAT(idContrat)
);


#2  PRESQUE
SELECT CO.idContrat, M.nomMarque, F.montant  FROM CONTRAT as CO INNER JOIN VEHICULE as V ON V.immatriculation=CO.immatriculation INNER JOIN AGENCE as A ON A.idAgence=V.idAgence 
	INNER JOIN CLIENT As CI ON CI.idClient=CO.idClient INNER JOIN FACTURE as F ON CO.idContrat=F.idContrat 
	INNER JOIN MARQUE as M ON V.idMarque=M.idMarque WHERE V.idAgence!=CO.idAgenceRetour AND CO.idClient=0 AND CO.dateDeRetrait="date donnée";

#3 OUI
SELECT dateDeRetour FROM CONTRAT WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);

#4 OUI
SELECT VI.nomVille FROM VILLE as VI INNER JOIN AGENCE as A ON VI.idVille=A.idVille INNER JOIN VEHICULE as VE ON VE.idAgence=A.idAgence
    INNER JOIN CONTRAT as C ON VE.immatriculation=C.immatriculation  WHERE idContrat = (SELECT idContrat FROM CONTRAT ORDER BY idContrat DESC LIMIT 1);

#5 PRESQUE
SELECT A.idAgence,SUM(F.montant) as chiffreAffaires FROM FACTURE as F INNER JOIN CONTRAT as C ON F.idContrat=C.idContrat
    INNER JOIN VEHICULE as V ON V.immatriculation=C.immatriculation INNER JOIN AGENCE as A ON A.idAgence=V.idAgence
    WHERE A.idAgence=0 GROUP BY A.idAgence;
#C.dateDeRetrait BETWEEN 2020-var-01 AND 2020-var-31;

#6 OUI
SELECT M.nomMarque,COUNT(V.immatriculation) as nbVehicule FROM VEHICULE as V INNER JOIN MARQUE as M 
    ON V.idMarque=M.idMarque GROUP BY idMarque;