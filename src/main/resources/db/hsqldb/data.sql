INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);

INSERT INTO usersdwarf(username, pass,email, active) VALUES ('rafjimfer', 'RafaAngel1','rafa@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (1, 'rafjimfer', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('serrivroa', 'SergioRivas1','ss@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (2, 'serrivroa', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('frabotrom', 'Franbotrom1','fran@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (3, 'frabotrom', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('admin1', '4dm1n','admin1@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (4, 'admin1', 'admin');

-- Inserting test game and board --
-- INSERT INTO board() -- 
-- INSERT INTO game(player1, player2, player3, order, phase, gameStatus, board) VALUES ('rafjimfer', 'serrivroa', null, [1,2,3], 'INICIO', 'IN_PROGRESS', 1) --

INSERT INTO achievements(id,last_change,condition,description) VALUES (1,'2022-01-02','total_gold=100','Consigue 100 de hierro');
INSERT INTO achievements(id,last_change,condition,description) VALUES (2,'2022-01-02','total_iron=300','Consigue 100 de oro');
INSERT INTO achievements(id,last_change,condition,description) VALUES (3,'2022-01-02','total_games=1','Consigue 100 de acero');

INSERT INTO cards(cardImage,cardType,position,title,description,effect) VALUES ('resources/cards/Alloy_steel_1_2',EXTRACCION_RECURSOS,2,'Alloy Steel','Guac',1);

-- Time Played is expressed in nanoseconds (10^-9)
INSERT INTO statistics (time_played, games_played, games_won, total_iron, total_gold, total_steel, total_object, total_medal, user_dwarf_id) VALUES ('58243000000000', 8, 5, 150, 100, 75, 28, 15, 'rafjimfer');
INSERT INTO statistics (time_played, games_played, games_won, total_iron, total_gold, total_steel, total_object, total_medal, user_dwarf_id) VALUES ('24745000000000', 7, 4, 120, 90, 77, 31, 18, 'serrivroa');
