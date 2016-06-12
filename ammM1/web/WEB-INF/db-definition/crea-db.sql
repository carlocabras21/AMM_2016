CREATE TABLE cliente(
	username varchar(128),
	password varchar(128),
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	nome varchar(128),
	cognome varchar(128),
	codice_fiscale varchar(20),
	data_nascita DATE,
	saldo DOUBLE	
)

CREATE TABLE venditore(
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	nome varchar(128),
	cognome varchar(128),
	codice_fiscale varchar(20),
	data_nascita DATE,
	saldo DOUBLE,
	username varchar(128),
	password varchar(128)
)

CREATE TABLE oggetto(
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	nome varchar(128),
	descrizione varchar(128),
	url_immagine varchar(20),
	quantita INTEGER,
	prezzo DOUBLE,
	id_venditore INTEGER,
	FOREIGN KEY (id_venditore) REFERENCES venditore(id)
)

INSERT INTO cliente (id, nome, cognome, codice_fiscale, data_nascita, saldo, username, password)
VALUES (default, 'Carlo', 'Cabras', 'CBRCRL93S26V234A','1993-11-26',1000,'carlocabras','0'),
	   (default, 'Federica','Locci','LCCFDR82R55S971F','1985-03-15',0,'federicalocci','0');

INSERT INTO venditore (id, nome, cognome, codice_fiscale, data_nascita, saldo, username, password)
VALUES (default, 'Mario', 'Puddu', 'PDDMRA65A04E324Q','1965-04-25',0,'mariopuddu','0'),
	   (default, 'Fabrizio','Murru','MRRFBR81A25S740Z','1981-04-25',0,'fabriziomurru','0');
	   
INSERT INTO oggetto(id, nome, descrizione, url_immagine, quantita, prezzo, id_venditore)
VALUES	(default, 'Babolat Pure Strike 100', 'racchetta', 'images/babolat.png', 3, 68.9, 1),
		(default, 'Roger Zoom Vapor 9.5 Tour Clay', 'scarpe', 'images/nike.png', 11, 103.9, 1),
		(default, 'Dunlop Fort All Court 3er', 'palline', 'images/dunlop.png', 21, 4.9, 1),
		(default, 'Luxilon Alu Power Feel 220m',  'corde', 'images/luxilon.png', 6, 233.9, 2),
		(default, 'Wilson Pro Staff 97LS (besaitet)', 'racchetta', 'images/wilson.png', 17, 179.9, 2);