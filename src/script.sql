DROP DATABASE IF EXISTS School;
CREATE DATABASE IF NOT EXISTS School;

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
    studentId VARCHAR(45),
    studentName VARCHAR(45),
    email TEXT,
    contact VARCHAR(20),
    address TEXT,
    nic VARCHAR(45),
    CONSTRAINT PRIMARY KEY (studentId)
    );

DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher (
    teacherId VARCHAR(45),
    name VARCHAR(45),
    nic VARCHAR(45),
    contact VARCHAR(45),
    address TEXT,
    CONSTRAINT PRIMARY KEY (teacherId)
    );

DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject (
    subjectId VARCHAR(45),
    teacherId VARCHAR(45),
    subjectName VARCHAR(45),
    credit DOUBLE,
    CONSTRAINT PRIMARY KEY (subjectId),
    CONSTRAINT FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE
    );

DROP TABLE IF EXISTS Course;
CREATE TABLE IF NOT EXISTS Course (
    courseId VARCHAR(45),
    subjectId VARCHAR(45),
    courseName VARCHAR(45),
    cost DOUBLE,
    duration VARCHAR(45),
    CONSTRAINT PRIMARY KEY (courseId),
    CONSTRAINT FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE
    );

DROP TABLE IF EXISTS Intake;
CREATE TABLE IF NOT EXISTS Intake (
    intakeId VARCHAR(45),
    courseId VARCHAR(45),
    startDate DATE,
    intekecol VARCHAR(45),
    description VARCHAR(45),
    CONSTRAINT PRIMARY KEY (intakeId),
    CONSTRAINT FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE ON UPDATE CASCADE
    );

DROP TABLE IF EXISTS Registration;
CREATE TABLE IF NOT EXISTS Registration (
    registrationId VARCHAR(45),
    studentId VARCHAR(45),
    intakeId VARCHAR(45),
    regDate DATE,
    CONSTRAINT PRIMARY KEY (registrationId),
    CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (intakeId) REFERENCES Intake(intakeId) ON DELETE CASCADE ON UPDATE CASCADE
    );

DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment (
    paymentId VARCHAR(45),
    registrationId VARCHAR(45),
    date DATE,
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (paymentId),
    CONSTRAINT FOREIGN KEY (registrationId) REFERENCES Registration(registrationId) ON DELETE CASCADE ON UPDATE CASCADE
    );
