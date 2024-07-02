
DROP TABLE IF EXISTS `arrange`;
DROP TABLE IF EXISTS `information`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `record`;



-- admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE admin (
    a_id INT PRIMARY KEY,
    a_password VARCHAR(50) NOT NULL,
    a_name VARCHAR(100) NOT NULL,
    a_gender VARCHAR(10) NOT NULL,
    a_card VARCHAR(20) NOT NULL,
    a_phone VARCHAR(20) NOT NULL,
    a_address VARCHAR(200) NOT NULL
);
INSERT INTO admin (a_id, a_password, a_name, a_gender, a_card, a_phone, a_address) VALUES
(1, 'password1', 'Admin1', 'Male', '1234567890', '123-456-7890', '123 Admin Street'),
(2, 'password2', 'Admin2', 'Female', '1234567891', '123-456-7891', '124 Admin Street'),
(3, 'password3', 'Admin3', 'Male', '1234567892', '123-456-7892', '125 Admin Street'),
(4, 'password4', 'Admin4', 'Female', '1234567893', '123-456-7893', '126 Admin Street'),
(5, 'password5', 'Admin5', 'Male', '1234567894', '123-456-7894', '127 Admin Street');

-- patient
DROP TABLE IF EXISTS `patient`;
CREATE TABLE patient (
    p_id INT PRIMARY KEY,
    p_password VARCHAR(50) NOT NULL,
    p_name VARCHAR(100) NOT NULL,
    p_gender VARCHAR(10) NOT NULL,
    p_card VARCHAR(20) NOT NULL,
    p_phone VARCHAR(20) NOT NULL,
    p_address VARCHAR(200) NOT NULL,
    p_age INT NOT NULL,
    p_history TEXT NOT NULL 
);
INSERT INTO patient (p_id, p_password, p_name, p_gender, p_card, p_phone, p_address, p_age, p_history) VALUES
(1, 'pass1', 'Patient1', 'Male', '2345678901', '234-567-8901', '234 Patient Road', 30, 'None'),
(2, 'pass2', 'Patient2', 'Female', '2345678902', '234-567-8902', '235 Patient Road', 25, 'Asthma'),
(3, 'pass3', 'Patient3', 'Male', '2345678903', '234-567-8903', '236 Patient Road', 40, 'Diabetes'),
(4, 'pass4', 'Patient4', 'Female', '2345678904', '234-567-8904', '237 Patient Road', 35, 'Hypertension'),
(5, 'pass5', 'Patient5', 'Male', '2345678905', '234-567-8905', '238 Patient Road', 50, 'Heart Disease');

-- doctor
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE doctor (
    d_id INT PRIMARY KEY,
    d_password VARCHAR(50) NOT NULL,
    d_name VARCHAR(100) NOT NULL,
    d_gender VARCHAR(10) NOT NULL,
    d_card VARCHAR(20) NOT NULL,
    d_phone VARCHAR(20) NOT NULL,
    d_address VARCHAR(200) NOT NULL,

    d_post VARCHAR(50) NOT NULL,
    d_introduction TEXT NOT NULL,
    d_section VARCHAR(100) NOT NULL,
    d_state VARCHAR(20) NOT NULL,
    d_price DECIMAL(10, 2) NOT NULL,
    d_hospital VARCHAR(100) NOT NULL
);
INSERT INTO doctor (d_id, d_password, d_name, d_gender, d_card, d_phone, d_address, d_post, d_introduction, d_section, d_state, d_price, d_hospital) VALUES
(1, 'docpass1', 'Doctor1', 'Male', '3456789012', '345-678-9012', '345 Doctor Lane', 'Chief', 'Experienced cardiologist', 'Cardiology', 'Active', 150.00, 'General Hospital'),
(2, 'docpass2', 'Doctor2', 'Female', '3456789013', '345-678-9013', '346 Doctor Lane', 'Attending', 'Specialist in endocrinology', 'Endocrinology', 'Active', 120.00, 'City Hospital'),
(3, 'docpass3', 'Doctor3', 'Male', '3456789014', '345-678-9014', '347 Doctor Lane', 'Resident', 'Expert in pediatrics', 'Pediatrics', 'Active', 100.00, 'Children Hospital'),
(4, 'docpass4', 'Doctor4', 'Female', '3456789015', '345-678-9015', '348 Doctor Lane', 'Senior', 'Renowned neurologist', 'Neurology', 'Active', 200.00, 'Neuro Hospital'),
(5, 'docpass5', 'Doctor5', 'Male', '3456789016', '345-678-9016', '349 Doctor Lane', 'Consultant', 'Leading oncologist', 'Oncology', 'Active', 250.00, 'Cancer Center');



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
    o_price_state VARCHAR(50),
    FOREIGN KEY (p_id) REFERENCES patient(p_id),
    FOREIGN KEY (d_id) REFERENCES doctor(d_id)
);
INSERT INTO orders (o_id,p_id, d_id, o_history, o_start, o_end, o_state, o_total_price, o_price_state) VALUES
(1,1, 1, 'History1', '2023-06-01 09:00:00', '2023-06-01 09:30:00', 'Completed', 200.00, 'Paid'),
(2,2, 2, 'History2', '2023-06-02 10:00:00', '2023-06-02 10:30:00', 'Pending', 150.00, 'Unpaid'),
(3,3, 3, 'History3', '2023-06-03 11:00:00', '2023-06-03 11:30:00', 'Completed', 180.00, 'Paid'),
(4,4, 4, 'History4', '2023-06-04 12:00:00', '2023-06-04 12:30:00', 'Canceled', 200.00, 'Unpaid'),
(5,5, 5, 'History5', '2023-06-05 13:00:00', '2023-06-05 13:30:00', 'Completed', 220.00, 'Paid');








-- information (门诊信息)
DROP TABLE IF EXISTS `information`;
CREATE TABLE information (
    info_id INT PRIMARY KEY AUTO_INCREMENT,
    info_date DATE NOT NULL,
    info_price INT NOT NULL,
    d_id INT,
    info_cycle BOOLEAN NOT NULL,
    info_state VARCHAR(50),
    FOREIGN KEY (d_id) REFERENCES doctor(d_id)
);
INSERT INTO information (info_id,info_date, info_price, d_id, info_cycle, info_state) VALUES
(1,'2023-06-01', 50.00, 1, FALSE, 'Approved'),
(2,'2023-06-02', 60.00, 2, TRUE, 'Pending'),
(3,'2023-06-03', 55.00, 3, FALSE, 'Approved'),
(4,'2023-06-04', 70.00, 4, TRUE, 'Rejected'),
(5,'2023-06-05', 65.00, 5, FALSE, 'Approved');


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
('2023-06-01 09:00:00', 1, 1, 1),
('2023-06-02 10:00:00', 2, 2, 2),
('2023-06-03 11:00:00', 3, 3, 3),
('2023-06-04 12:00:00', 4, 4, 4),
('2023-06-05 13:00:00', 5, 5, 5);



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
