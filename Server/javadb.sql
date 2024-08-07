DROP TABLE IF EXISTS `arrange`;
DROP TABLE IF EXISTS `information`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `record`;
SET GLOBAL max_connections = 30000;



-- admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE admin (
    a_id INT PRIMARY KEY AUTO_INCREMENT,
    a_password VARCHAR(50) DEFAULT '000000',
    a_name VARCHAR(100) NOT NULL,
    a_gender VARCHAR(10) NOT NULL,
    a_card VARCHAR(20) NOT NULL,
    a_phone VARCHAR(20) NOT NULL,
    a_address VARCHAR(200) NOT NULL,
    a_state BOOLEAN DEFAULT FALSE,
    a_password_modified BOOLEAN DEFAULT FALSE
);
INSERT INTO admin (  a_name, a_gender, a_card, a_phone, a_address, a_state) VALUES
(  'Admin1', 'Male', '1234567890', '123-456-7890', '123 Admin Street', TRUE),
(  'Admin2', 'Female', '1234567891', '123-456-7891', '124 Admin Street',True),
(  'Admin3', 'Male', '1234567892', '123-456-7892', '125 Admin Street',FALSE),
(  'Admin4', 'Female', '1234567893', '123-456-7893', '126 Admin Street',TRUE),
(  'Admin5', 'Male', '1234567894', '123-456-7894', '127 Admin Street',FALSE);

-- patient
DROP TABLE IF EXISTS `patient`;
CREATE TABLE patient (
    p_id INT PRIMARY KEY AUTO_INCREMENT,
    p_password VARCHAR(50) NOT NULL,
    p_name VARCHAR(100) NOT NULL,
    p_gender VARCHAR(10) NOT NULL,
    p_card VARCHAR(20) NOT NULL,
    p_phone VARCHAR(20) NOT NULL,
    p_address VARCHAR(200) NOT NULL,
    p_age INT NOT NULL,
    p_history TEXT NOT NULL ,
    p_state BOOLEAN DEFAULT FALSE
);
INSERT INTO patient (p_password, p_name, p_gender, p_card, p_phone, p_address, p_age, p_history, p_state) VALUES
( 'pass1', 'Patient1', 'Male', '2345678901', '234-567-8901', '234 Patient Road', 30, 'None',True),
( 'pass2', 'Patient2', 'Female', '2345678902', '234-567-8902', '235 Patient Road', 25, 'Asthma',TRUE),
( 'pass3', 'Patient3', 'Male', '2345678903', '234-567-8903', '236 Patient Road', 40, 'Diabetes',False),
( 'pass4', 'Patient4', 'Female', '2345678904', '234-567-8904', '237 Patient Road', 35, 'Hypertension',True),
( 'pass5', 'Patient5', 'Male', '2345678905', '234-567-8905', '238 Patient Road', 50, 'Heart Disease',FALSE);
INSERT INTO patient (p_password, p_name, p_gender, p_card, p_phone, p_address, p_age, p_history, p_state) VALUES
('pass6', 'Patient6', 'Male', '2345678906', '234-567-8906', '239 Patient Road', 45, 'None', TRUE),
('pass7', 'Patient7', 'Female', '2345678907', '234-567-8907', '240 Patient Road', 55, 'Asthma', FALSE),
('pass8', 'Patient8', 'Male', '2345678908', '234-567-8908', '241 Patient Road', 60, 'Diabetes', TRUE),
('pass9', 'Patient9', 'Female', '2345678909', '234-567-8909', '242 Patient Road', 28, 'Hypertension', FALSE),
('pass10', 'Patient10', 'Male', '2345678910', '234-567-8910', '243 Patient Road', 35, 'Heart Disease', TRUE),
('pass11', 'Patient11', 'Female', '2345678911', '234-567-8911', '244 Patient Road', 48, 'None', TRUE),
('pass12', 'Patient12', 'Male', '2345678912', '234-567-8912', '245 Patient Road', 52, 'Asthma', TRUE),
('pass13', 'Patient13', 'Female', '2345678913', '234-567-8913', '246 Patient Road', 29, 'Diabetes', FALSE),
('pass14', 'Patient14', 'Male', '2345678914', '234-567-8914', '247 Patient Road', 34, 'Hypertension', TRUE),
('pass15', 'Patient15', 'Female', '2345678915', '234-567-8915', '248 Patient Road', 40, 'Heart Disease', FALSE);
-- And so on until 'Patient200'
-- doctor
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE doctor (
    d_id INT PRIMARY KEY AUTO_INCREMENT,
    d_password VARCHAR(50) NOT NULL,
    d_name VARCHAR(100) NOT NULL,
    d_gender VARCHAR(10) NOT NULL,
    d_card VARCHAR(20) NOT NULL,
    d_phone VARCHAR(20) NOT NULL,
    d_address VARCHAR(200) NOT NULL,

    d_post VARCHAR(50) NOT NULL,
    d_introduction TEXT NOT NULL,
    d_section VARCHAR(100) NOT NULL,
    
    d_price DECIMAL(10, 2) DEFAULT 50.0,
    d_hospital VARCHAR(100) NOT NULL,
    d_state BOOLEAN DEFAULT FALSE
);
INSERT INTO doctor (d_id, d_password, d_name, d_gender, d_card, d_phone, d_address, d_post, d_introduction, d_section, d_state, d_price, d_hospital) VALUES
(1, 'docpass1', 'Doctor1', 'Male', '3456789012', '345-678-9012', '345 Doctor Lane', 'Chief', 'Experienced cardiologist', 'Cardiology', TRUE, 150.00, 'General Hospital'),
(2, 'docpass2', 'Doctor2', 'Female', '3456789013', '345-678-9013', '346 Doctor Lane', 'Attending', 'Specialist in endocrinology','Pediatrics', FALSE,  120.00, 'City Hospital'),
(3, 'docpass3', 'Doctor3', 'Male', '3456789014', '345-678-9014', '347 Doctor Lane', 'Resident', 'Expert in pediatrics', 'Pediatrics', TRUE, 100.00, 'Children Hospital'),
(4, 'docpass4', 'Doctor4', 'Female', '3456789015', '345-678-9015', '348 Doctor Lane', 'Senior', 'Renowned neurologist', 'Neurology', TRUE, 200.00, 'Neuro Hospital'),
(5, 'docpass5', 'Doctor5', 'Male', '3456789016', '345-678-9016', '349 Doctor Lane', 'Consultant', 'Leading oncologist', 'Oncology', TRUE, 250.00, 'Cancer Center');



-- orders (挂号订单)
DROP TABLE IF EXISTS `orders`;
CREATE TABLE orders (
    o_id INT PRIMARY KEY AUTO_INCREMENT,
    p_id INT,
    d_id INT,
    o_history TEXT NOT NULL,
    o_start DATETIME NOT NULL,
    o_end DATETIME NOT NULL,
    o_state VARCHAR(50),
    o_total_price INT NOT NULL,
    o_price_state BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (p_id) REFERENCES patient(p_id),
    FOREIGN KEY (d_id) REFERENCES doctor(d_id)
);
INSERT INTO orders (p_id, d_id, o_history, o_start, o_end, o_state, o_total_price, o_price_state) VALUES
(1, 1, 'History1', '2024-07-03 09:00:00', '2023-06-01 09:30:00', 'Completed', 200.00, TRUE),
(2, 2, 'History2', '2024-07-04 10:00:00', '2023-06-02 10:30:00', 'Pending', 150.00, FALSE),
(3, 3, 'History3', '2024-07-03 11:00:00', '2023-06-03 11:30:00', 'Completed', 180.00, TRUE),
(4, 4, 'History4', '2024-07-04 12:00:00', '2023-06-04 12:30:00', 'Canceled', 200.00, FALSE),
(5, 5, 'History5', '2024-07-05 13:00:00', '2023-06-05 13:30:00', 'Completed', 220.00, TRUE);








-- information (门诊信息)
DROP TABLE IF EXISTS `information`;
CREATE TABLE information (
    info_id INT PRIMARY KEY AUTO_INCREMENT,
    info_date DATE NOT NULL,
    info_price INT NOT NULL,
    d_id INT,
    info_cycle BOOLEAN NOT NULL,
    info_state BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (d_id) REFERENCES doctor(d_id)
);
INSERT INTO information (info_id,info_date, info_price, d_id, info_cycle, info_state) VALUES
(1,'2024-07-05', 50.00, 1, FALSE, TRUE),
(2,'2024-07-04', 60.00, 2, TRUE, FALSE),
(3,'2024-07-06', 55.00, 3, FALSE, TRUE),
(4,'2024-07-04', 70.00, 4, TRUE, TRUE),
(5,'2024-07-05', 65.00, 5, FALSE,TRUE);


-- arrange (出诊安排)
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE arrange (
    ar_id INT PRIMARY KEY AUTO_INCREMENT,
    ar_time DATETIME NOT NULL,
    d_id INT,
    p_id INT,
    info_id INT,
    FOREIGN KEY (d_id) REFERENCES doctor(d_id),
    FOREIGN KEY (p_id) REFERENCES patient(p_id),
    FOREIGN KEY (info_id) REFERENCES information(info_id)
);
INSERT INTO arrange (ar_time, d_id, p_id, info_id) VALUES
('2024-07-05 09:00:00', 1, 1, 1),
('2024-07-04 10:00:00', 2, 2, 2),
('2024-07-06 11:00:00', 3, 3, 3),
('2024-07-04 12:00:00', 4, 4, 4),
('2024-07-05 13:00:00', 5, 5, 5);



-- record (操作记录)
DROP TABLE IF EXISTS `record`;
CREATE TABLE record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    record_time DATETIME NOT NULL,
    record_object INT NOT NULL,
    record_type INT NOT NULL
);





SHOW TABLES;
SELECT * FROM admin;
SELECT * FROM patient;
SELECT * FROM doctor;
SELECT * FROM orders;
SELECT * FROM arrange;
SELECT * FROM information;
