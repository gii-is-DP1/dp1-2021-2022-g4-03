-- noinspection SqlNoDataSourceInspectionForFile

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



-- Cards
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 1-1.png','EXTRACCION_RECURSOS',0,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 1-3.png','EXTRACCION_RECURSOS',2,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 1-2.png','EXTRACCION_RECURSOS',1,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 2-2.png','EXTRACCION_RECURSOS',4,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 2-3.png','EXTRACCION_RECURSOS',5,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 3-2.png','EXTRACCION_RECURSOS',7,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Alloy Steel 3-3.png','EXTRACCION_RECURSOS',8,'Alloy Steel','Return 3 iron to the supply then take 2 steel.','-3i,+2s',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 2-1.png','EXTRACCION_RECURSOS',3,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 1-1.png','EXTRACCION_RECURSOS',0,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 2-2.png','EXTRACCION_RECURSOS',4,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 3-1.png','EXTRACCION_RECURSOS',6,'Gold Seam','Take 1 gold from the supply.','+1g',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 3-2.png','EXTRACCION_RECURSOS',7,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 3-3.png','EXTRACCION_RECURSOS',8,'Gold Seam','Take 1 gold from the supply.','+1g',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-1.png','EXTRACCION_RECURSOS',0,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-2.png','EXTRACCION_RECURSOS',1,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-3.png','EXTRACCION_RECURSOS',2,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-1.png','EXTRACCION_RECURSOS',3,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-2.png','EXTRACCION_RECURSOS',4,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-3.png','EXTRACCION_RECURSOS',5,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 3-1.png','EXTRACCION_RECURSOS',6,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 3-2.png','EXTRACCION_RECURSOS',7,'Iron Seam','Take 3 iron from the supply.','+3i',TRUE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 3-3.png','EXTRACCION_RECURSOS',8,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);

INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Knockers 3-1.png','DEFENSA',6,'Knockers','If undefended at the end of the round, each player must return 1 iron to the supply.','-1i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Knockers 3-2.png','DEFENSA',7,'Knockers','If undefended at the end of the round, each player must return 1 iron to the supply.','-1i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Knockers_2_2.png','DEFENSA',4,'Knockers','If undefended at the end of the round, each player must return 1 iron to the supply.','-1i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Orc Raiders 1-3.png','DEFENSA',2,'Orc Raiders','If undefended at the end of the round, players cannot take any mining actions.','*',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Orc Raiders 2-1.png','DEFENSA',3,'Orc Raiders','If undefended at the end of the round, players cannot take any mining actions.','*',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Orc_Raiders_1_2.png','DEFENSA',1,'Orc Raiders','If undefended at the end of the round, players cannot take any mining actions.','*',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Sidhe 1-1.png','DEFENSA',0,'Sidhe','If undefended at the end of the round, each player must replace two gold they possess with two iron.','?',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Sidhe 1-2.png','DEFENSA',1,'Sidhe','If undefended at the end of the round, each player must replace two gold they possess with two iron.','?',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Dragon 2-1.png','DEFENSA',3,'Dragon','If undefended at the end of the round, each player must return 1 gold to the supply.','-1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Dragon 3-2.png','DEFENSA',7,'Dragon','If undefended at the end of the round, each player must return 1 gold to the supply.','-1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Dragon 3-3.png','DEFENSA',8,'Dragon','If undefended at the end of the round, each player must return 1 gold to the supply.','-1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Great Dragon 1-3.png','DEFENSA',2,'Great Dragon','If undefended at the end of the round, each player must return all gold they possess to the supply.','-100g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Great Dragon 2-1.png','DEFENSA',3,'Great Dragon','If undefended at the end of the round, each player must return all gold they possess to the supply.','-100g',FALSE);


INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Armour 2-3.png','FORJA',5,'Forge Armour','Return 2 steel and 1 gold to the supply then take 1 item.','-2s,-2g,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge axe 2-1.png','FORJA',3,'Forge Axe','Return 2 steel to the supply then take 1 item.','-2s,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Crown 2-3.png','FORJA',5,'Forge Crown','Return 3 gold to the supply then take 1 item.','-3g,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Dagger 3-3.png','FORJA',8,'Forge Dagger','Return 1 iron and 2 steel to the supply then take 1 item.','-1i,-2s,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Diadem 1-3.png','FORJA',2,'Forge Diadem','Return 1 iron, 1 steel and 1 gold to the supply then take 1 item.','-1i,-1s,-1g,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Helm 3-2.png','FORJA',7,'Forge Helm','Return 1 steel and 2 gold to the supply then take 1 item.','-1s,-2g,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Mace 3-1.png','FORJA',6,'Forge Mace','Return 2 steel and 1 gold to the supply then take 1 item.','-2s,-1g,+1o',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Forge Sword 1-3.png','FORJA',2,'Forge Sword','Return 3 steel to the supply then take 1 item.','-3s,+1o',FALSE);

INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Get Help 1-3.png','RECIBIR_AYUDA',2,'Get Help','You may place 2 additional workers this round. If you are 1st player, pass the 1st player marker to your left.','help',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Get Help 2-2.png','RECIBIR_AYUDA',4,'Get Help','You may place 2 additional workers this round. If you are 1st player, pass the 1st player marker to your left.','help',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Get Help 2-3.png','RECIBIR_AYUDA',5,'Get Help','You may place 2 additional workers this round. If you are 1st player, pass the 1st player marker to your left.','help',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Get Help 3-1.png','RECIBIR_AYUDA',6,'Get Help','You may place 2 additional workers this round. If you are 1st player, pass the 1st player marker to your left.','help',FALSE);

INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Muster an Army.png','ESPECIAL',null,'Muster an Army','When resolving actions this round, treat all defensive locations as if they are occupied.','muster',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/hold a council.png','ESPECIAL',null,'Hold a Council','Remove the top card from each location (leaving at least one card) and shuffle them back into The Mountain.','hold',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/sell an item.png','ESPECIAL',null,'Sell an Item','Exchange 1 of your items for 5 resources of your choice.','sell',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/past glories.png','ESPECIAL',null,'Past Glories','Name a card previously on top in any location and return it to the top of that location.','past',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Special Order.png','ESPECIAL',null,'Special Order','Return 5 resources of your choice, including at least 1 of each type, to the supply and take 1 item.','special',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/turn back.png','ESPECIAL',null,'Turn Back','Remove the top card from 1 location and shuffle it back into the mountain. Immediately place 1 worker on that location.','turn',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/apprentice.png','ESPECIAL',null,'Apprentice','Place one worker on a location occupied by the other player.','apprentice',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/collapse the shafts.png','ESPECIAL',null,'Collapse the Shafts','Remove the top card from each location and place it on the bottom of that stack.','collapse',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/run amok.png','ESPECIAL',null,'Run Amok','Collect all of the cards from each location, in turn, shuffle them and return them to that location.','run',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 3-1.png','EXTRACCION_RECURSOS',6,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Gold Seam 3-3.png','EXTRACCION_RECURSOS',8,'Gold Seam','Take 1 gold from the supply.','+1g',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-1.png','EXTRACCION_RECURSOS',0,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-2.png','EXTRACCION_RECURSOS',1,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 1-3.png','EXTRACCION_RECURSOS',2,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-1.png','EXTRACCION_RECURSOS',3,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-2.png','EXTRACCION_RECURSOS',4,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 2-3.png','EXTRACCION_RECURSOS',5,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);
INSERT INTO cards(cardimage,cardtype,position,title,description,effect,initial) VALUES ('/resources/cards/Iron Seam 3-2.png','EXTRACCION_RECURSOS',7,'Iron Seam','Take 3 iron from the supply.','+3i',FALSE);

