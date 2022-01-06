INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);

INSERT INTO usersdwarf(username, pass,email, active) VALUES ('rafjimfer', 'RafaAngel1','rafa@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (1, 'rafjimfer', 'player');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('serrivroa', 'SergioRivas1','ss@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (2, 'serrivroa', 'player');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('frabotrom', 'Franbotrom1','fran@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (3, 'frabotrom', 'player');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('admin1', 'Adm1nAdm1n','admin1@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (4, 'admin1', 'admin');

-- Inserting test game and board --
-- INSERT INTO board() -- 
-- INSERT INTO game(player1, player2, player3, order, phase, gameStatus, board) VALUES ('rafjimfer', 'serrivroa', null, [1,2,3], 'INICIO', 'IN_PROGRESS', 1) --

INSERT INTO achievements(id,last_change,condition,description,pic) VALUES (1,'2022-01-02','totalGold=100','Consigue 100 de oro','/resources/images/gold.png');
INSERT INTO achievements(id,last_change,condition,description,pic) VALUES (2,'2022-01-02','totalIron=300','Consigue 300 de hierro','/resources/images/iron.png');
INSERT INTO achievements(id,last_change,condition,description,pic) VALUES (3,'2022-01-02','gamesPlayed=1','Juega tu primera partida','/resources/images/picaxe.png');
INSERT INTO achievements(id,last_change,condition,description,pic) VALUES (4,'2022-01-07','totalGold=300','Fiebre del oro: Consigue 300 de oro','/resources/images/gold.png');

-- INSERT INTO cards(cardImage,cardType,position,title,description,effect) VALUES ('resources/cards/Alloy_steel_1_2',EXTRACCION_RECURSOS,2,'Alloy Steel','Guac',1);


-- Time Played is expressed in nanoseconds (10^-9)
INSERT INTO statistics (time_played, games_played, games_won, total_iron, total_gold, total_steel, total_object, total_medal, user_dwarf_id) VALUES ('58243000000000', 8, 5, 150, 70, 75, 28, 15, 'rafjimfer');
INSERT INTO statistics (time_played, games_played, games_won, total_iron, total_gold, total_steel, total_object, total_medal, user_dwarf_id) VALUES ('24745000000000', 7, 4, 120, 90, 77, 31, 18, 'serrivroa');
INSERT INTO statistics (time_played, games_played, games_won, total_iron, total_gold, total_steel, total_object, total_medal, user_dwarf_id) VALUES ('3600000000000', 1, 1, 150, 50, 3, 4, 6, 'frabotrom');

INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (1,0.5,'2022-01-30','rafjimfer',1);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (2,0.15,'2022-01-30','rafjimfer',2);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (3,0.35,'2022-01-30','rafjimfer',3);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (4,0.35,'2022-01-30','rafjimfer',4);

INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (5,0.15,'2022-01-30','serrivroa',1);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (6,0.85,'2022-01-30','serrivroa',2);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (7,0.34,'2022-01-30','serrivroa',3);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (8,0.35,'2022-01-30','serrivroa',4);

INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (9,0.15,'2022-01-30','frabotrom',1);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (10,0.15,'2022-01-30','frabotrom',2);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (11,0.15,'2022-01-30','frabotrom',3);
INSERT INTO user_achievements(id,progress, obtaining_date,user_dwarf_id,achievements_id) VALUES (12,0.35,'2022-01-30','frabotrom',4);
