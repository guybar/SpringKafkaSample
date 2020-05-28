CREATE table USERS (ID number(10) not null unique, NAME varchar2(50), AGE number(3));
INSERT INTO USERS (ID, NAME, AGE) values (1, 'Neta', 46);
INSERT INTO USERS (ID, NAME, AGE) values (2, 'Alice', 22);
INSERT INTO USERS (ID, NAME, AGE) values (3, 'Ofer', 34);
INSERT INTO USERS (ID, NAME, AGE) values (4, 'Naomi', 50);
INSERT INTO USERS (ID, NAME, AGE) values (5, 'Noa', 32);
INSERT INTO USERS (ID, NAME, AGE) values (6, 'Yoel', 29);
COMMIT;

--SLECT * FROM USERS;

--DROP table USERS;

--jdbc:oracle:thin:@hfaexadbadm05:1611:iqpet7