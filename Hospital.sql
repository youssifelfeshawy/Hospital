create database hospital;
use hospital;
CREATE TABLE Medication (
    Medication_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Medication_Name VARCHAR(30) NOT NULL,
    Dosage VARCHAR(20) NOT NULL,
    Exp_Date DATE NOT NULL
);
CREATE TABLE Patient (
    Patient_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Phone_No VARCHAR(20),
    F_Name VARCHAR(30) NOT NULL,
    M_Name VARCHAR(30),
    L_Name VARCHAR(30) NOT NULL,
    Address VARCHAR(50),
    Gender VARCHAR(10),
    Age INT,
    Blood_Type VARCHAR(20),
    Medical_History TEXT
);
CREATE TABLE Prescription (
    Prescription_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Medication_ID INT,
    FOREIGN KEY (Medication_ID)
        REFERENCES Medication (Medication_ID),
    Patient_ID INT,
    FOREIGN KEY (Patient_Id)
        REFERENCES Patient (Patient_ID)
);
CREATE TABLE Medical_Records (
	Medical_Records_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Patient_ID INT,
    FOREIGN KEY (Patient_Id)
        REFERENCES Patient (Patient_ID),
    Medication_ID INT,
    FOREIGN KEY (Medication_ID)
        REFERENCES Medication (Medication_ID),
    Diagnosis VARCHAR(30),
    Treatment_History TEXT
);
CREATE TABLE Department (
    Dept_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Dept_Name VARCHAR(30) NOT NULL,
    Dept_Desc TEXT,
    Dept_Head VARCHAR(30) NOT NULL
);
CREATE TABLE Doctor (
    Doctor_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Dept_ID INT,
    FOREIGN KEY (Dept_ID)
        REFERENCES Department (Dept_ID),
    Phone_No VARCHAR(20),
    F_Name VARCHAR(30) NOT NULL,
    M_Name VARCHAR(30),
    L_Name VARCHAR(30) NOT NULL
);
CREATE TABLE Nurse (
    Nurse_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Dept_ID INT,
    FOREIGN KEY (Dept_ID)
        REFERENCES Department (Dept_ID),
    Phone_No VARCHAR(20),
    F_Name VARCHAR(30) NOT NULL,
    M_Name VARCHAR(30),
    L_Name VARCHAR(30) NOT NULL
);
CREATE TABLE Rooms (
    Room_No INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Room_Type VARCHAR(30),
    Room_Avail TINYINT NOT NULL
);
CREATE TABLE Appointment (
    Apt_ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Patient_ID INT,
    FOREIGN KEY (Patient_Id)
        REFERENCES Patient (Patient_ID),
    Doctor_ID INT,
    FOREIGN KEY (Doctor_ID)
        REFERENCES Doctor (Doctor_ID),
    Room_No INT,
    FOREIGN KEY (Room_NO)
        REFERENCES Rooms (Room_No),
    Appt_date DATE,
    Appt_time TIME
);


# Insert Data:
INSERT INTO Medication (Medication_Name, Dosage, Exp_Date) VALUES
('Aspirin', '10mg', '2023-12-31'),
('Ibuprofen', '20mg', '2023-12-31'),
('Paracetamol', '15mg', '2023-12-31'),
('Amoxicillin', '250mg', '2023-12-31'),
('Loratadine', '5mg', '2023-12-31'),
('Insulin', '15 units', '2023-12-31'),
('Aspirin', '10mg', '2023-12-31'),
('Ibuprofen', '20mg', '2023-12-31'),
('Paracetamol', '15mg', '2023-12-31'),
('Amoxicillin', '250mg', '2023-12-31'),
('Loratadine', '5mg', '2023-12-31'),
('Insulin', '15 units', '2023-12-31'),
('Morphine', '5mg', '2023-12-31'),
('Penicillin', '500mg', '2023-12-31'),
('Prednisone', '10mg', '2023-12-31'),
('Omeprazole', '20mg', '2023-12-31')
;

INSERT INTO Patient (Phone_No, F_Name, M_Name, L_Name, Address, Gender, Age, Blood_Type, Medical_History) VALUES
('123-456-7890', 'John', 'M', 'Doe', '123 Main St', 'Male', 30, 'O+', 'No significant history'),
('987-654-3210', 'Jane', 'A', 'Smith', '456 Oak St', 'Female', 25, 'A-', 'Allergic to penicillin'),
('555-123-4567', 'Michael', 'J', 'Johnson', '789 Pine St', 'Male', 45, 'B+', 'Diabetes'),
('555-789-0123', 'Sarah', 'K', 'Williams', '101 Maple St', 'Female', 35, 'AB-', 'None'),
('555-123-4567', 'Michael', 'J', 'Johnson', '789 Pine St', 'Male', 45, 'B+', 'Diabetes'),
('555-789-0123', 'Sarah', 'K', 'Williams', '101 Maple St', 'Female', 35, 'AB-', 'None'),
('555-234-5678', 'David', 'R', 'Davis', '456 Birch St', 'Male', 28, 'O-', 'Asthma'),
('555-876-5432', 'Emily', 'D', 'Anderson', '789 Oak St', 'Female', 42, 'A+', 'Hypertension'),
('555-345-6789', 'Andrew', 'T', 'Clark', '654 Elm St', 'Male', 55, 'AB+', 'Arthritis'),
('555-987-6543', 'Jessica', 'M', 'Smith', '987 Cedar St', 'Female', 31, 'B-', 'None'),
('555-456-7890', 'Daniel', 'M', 'Taylor', '321 Pine St', 'Male', 38, 'O+', 'Migraines'),
('555-567-8901', 'Olivia', 'S', 'Miller', '876 Maple St', 'Female', 29, 'A-', 'None'),
('555-678-9012', 'Matthew', 'S', 'Wilson', '543 Birch St', 'Male', 48, 'B+', 'Heart Disease'),
('555-789-0123', 'Sophia', 'T', 'Doe', '210 Oak St', 'Female', 25, 'A-', 'Allergic to penicillin')
;

INSERT INTO Prescription (Medication_ID, Patient_ID) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 4),
(6, 3),
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 4),
(6, 3),
(7, 5),
(8, 6),
(9, 5),
(10, 7)
;

INSERT INTO Medical_Records (Patient_ID, Medication_ID, Diagnosis, Treatment_History) VALUES
(1, 1, 'Headache', 'Prescribed Aspirin'),
(2, 2, 'Fever', 'Prescribed Ibuprofen'),
(3, 4, 'Type 2 Diabetes', 'Prescribed Insulin'),
(4, 5, 'Allergies', 'Prescribed Loratadine'),
(1, 1, 'Headache', 'Prescribed Aspirin'),
(2, 2, 'Fever', 'Prescribed Ibuprofen'),
(3, 3, 'Infection', 'Prescribed Paracetamol'),
(4, 4, 'Type 2 Diabetes', 'Prescribed Insulin'),
(5, 5, 'Allergies', 'Prescribed Loratadine'),
(6, 6, 'Chronic Pain', 'Prescribed Morphine'),
(7, 7, 'Infection', 'Prescribed Penicillin'),
(8, 8, 'Inflammation', 'Prescribed Prednisone'),
(9, 9, 'Acid Reflux', 'Prescribed Omeprazole'),
(10, 10, 'Headache', 'Prescribed Aspirin')
;

INSERT INTO Department (Dept_Name, Dept_Desc, Dept_Head) VALUES
('Cardiology', 'Deals with heart-related issues', 'Dr. Johnson'),
('Orthopedics', 'Deals with bone and joint issues', 'Dr. Smith'),
('Pediatrics', 'Specialized in child healthcare', 'Dr. Anderson'),
('Neurology', 'Deals with nervous system disorders', 'Dr. Williams'),
('Cardiology', 'Deals with heart-related issues', 'Dr. Johnson'),
('Orthopedics', 'Deals with bone and joint issues', 'Dr. Smith'),
('Pediatrics', 'Specialized in child healthcare', 'Dr. Anderson'),
('Neurology', 'Deals with nervous system disorders', 'Dr. Williams')
;

INSERT INTO Doctor (Dept_ID, Phone_No, F_Name, M_Name, L_Name) VALUES
(1, '555-111-2222', 'Mark', 'A', 'Jones'),
(2, '555-333-4444', 'Emily', 'B', 'Taylor'),
(3, '555-999-1111', 'Christopher', 'E', 'Moore'),
(4, '555-222-3333', 'Sophia', 'F', 'Davis'),
(1, '555-111-2222', 'Mark', 'A', 'Jones'),
(2, '555-333-4444', 'Emily', 'B', 'Taylor'),
(3, '555-999-1111', 'Christopher', 'E', 'Moore'),
(4, '555-222-3333', 'Sophia', 'F', 'Davis')
;

INSERT INTO Nurse (Dept_ID, Phone_No, F_Name, M_Name, L_Name) VALUES
(1, '555-555-6666', 'Susan', 'C', 'Miller'),
(2, '555-777-8888', 'Michael', 'D', 'Wilson'),
(3, '555-444-5555', 'Jessica', 'G', 'Brown'),
(4, '555-666-7777', 'Andrew', 'H', 'Anderson'),
(1, '555-555-6666', 'Susan', 'C', 'Miller'),
(2, '555-777-8888', 'Michael', 'D', 'Wilson'),
(3, '555-444-5555', 'Jessica', 'G', 'Brown'),
(4, '555-666-7777', 'Andrew', 'H', 'Anderson')
;

INSERT INTO Rooms (Room_Type, Room_Avail) VALUES
('Standard', 1),
('VIP', 1),
('General', 1),
('ICU', 1),
('Maternity', 1),
('Emergency', 1),
('Standard', 1),
('VIP', 1),
('General', 1),
('ICU', 1),
('Maternity', 1),
('Emergency', 1),
('Isolation', 1),
('Pediatric', 1),
('Surgery', 1),
('Private', 1),
('Isolation', 0),
('Pediatric', 0),
('Surgery', 0)
;

INSERT INTO Appointment (Patient_ID, Doctor_ID, Room_No, Appt_date, Appt_time) VALUES
(1, 1, 1, '2023-01-15', '10:00:00'),
(2, 2, 2, '2023-01-20', '14:30:00'),
(1, 3, 3, '2023-02-05', '11:15:00'),
(2, 4, 4, '2023-02-10', '15:45:00'),
(1, 1, 1, '2023-01-15', '10:00:00'),
(2, 2, 2, '2023-01-20', '14:30:00'),
(3, 3, 3, '2023-02-05', '11:15:00'),
(4, 4, 4, '2023-02-10', '15:45:00'),
(5, 1, 5, '2023-03-15', '09:30:00'),
(6, 2, 6, '2023-03-20', '13:00:00'),
(7, 3, 7, '2023-04-01', '10:45:00'),
(8, 4, 8, '2023-04-10', '16:30:00'),
(9, 1, 9, '2023-05-05', '12:15:00'),
(10, 2, 10, '2023-05-10', '14:00:00'),
(3, 3, 5, '2023-03-15', '09:30:00'),
(4, 4, 6, '2023-03-20', '13:00:00')
;


select * from appointment;
select * from department;
select * from doctor;
select * from medical_records;
select * from medication;
select * from nurse;
select * from patient;
select * from prescription;
select * from rooms;
