INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);

INSERT INTO usersdwarf(username, pass,email, active) VALUES ('rafjimfer', 'rafa','rafa@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (1, 'rafjimfer', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('serrivroa', 'sergio','ss@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (2, 'serrivroa', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('frabotrom', 'fran','fran@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (3, 'frabotrom', 'admin');

-- Inserting test game and board --
-- INSERT INTO board() -- 
-- INSERT INTO game(player1, player2, player3, order, phase, gameStatus, board) VALUES ('rafjimfer', 'serrivroa', null, [1,2,3], 'INICIO', 'IN_PROGRESS', 1) --

INSERT INTO achievements(id,last_change,condition,description) VALUES (1,'2022-01-02','total_gold=100','Consigue 100 de hierro');
INSERT INTO achievements(id,last_change,condition,description) VALUES (2,'2022-01-02','total_iron=300','Consigue 100 de oro');
INSERT INTO achievements(id,last_change,condition,description) VALUES (3,'2022-01-02','total_games=1','Consigue 100 de acero');
