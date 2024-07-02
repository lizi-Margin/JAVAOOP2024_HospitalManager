LOAD DATA INFILE 'C:/Users/33149/Desktop/toursite_table.csv'
INTO TABLE toursite_table
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'          
LINES TERMINATED BY '\n' 
IGNORE 2 LINES           
(index_, name_ , introduction, place_num , likes );

Create table toursite_table
(id   INT AUTO_INCREMENT PRIMARY KEY,
index_ int not null, 
name_ varchar(100), 
introduction varchar(200), 
place_num  int not null, 
likes  int not null);
