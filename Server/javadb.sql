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
DROP TABLE IF EXISTS `doctors`;
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
