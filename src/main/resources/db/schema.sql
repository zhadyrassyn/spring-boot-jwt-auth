DROP TABLE IF EXISTS USER_ROLE;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USER;

CREATE TABLE IF NOT EXISTS ROLE(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR2(100)
);

CREATE TABLE IF NOT EXISTS USER(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR2(100),
    PASSWORD VARCHAR2(100),
    FIRSTNAME VARCHAR2(100),
    LASTNAME VARCHAR2(100)
);

create TABLE IF NOT EXISTS USER_ROLE(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USER_ID INTEGER REFERENCES USER(ID),
    ROLE_ID INTEGER REFERENCES ROLE(ID)
);