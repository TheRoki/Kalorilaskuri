/*
Luodaan tietokanta
*/
CREATE DATABASE ravinto;

/*
ravintoaineet eli eri ruoat
*/
CREATE TABLE IF NOT EXISTS ravinto.aine(
    id INT NOT NULL AUTO_INCREMENT,
    nimi VARCHAR(255) NOT NULL,
	kcal DOUBLE NOT NULL,
	proteiini DOUBLE NOT NULL,
	hiilihydraatti DOUBLE NOT NULL,
	rasva DOUBLE NOT NULL,
	

    CONSTRAINT pk_aine
        PRIMARY KEY(id)
);