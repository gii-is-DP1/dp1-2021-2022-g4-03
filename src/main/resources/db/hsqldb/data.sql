INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);



INSERT INTO usersdwarf(username, pass,email, active) VALUES ('rafjimfer', 'rafa','rafa@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (1, 'rafjimfer', 'admin');
INSERT INTO usersdwarf(username, pass,email, active) VALUES ('serrivroa', 'sergio','ss@gmail.com', TRUE);
INSERT INTO authorities(id,username, authority) VALUES (2, 'serrivroa', 'admin');