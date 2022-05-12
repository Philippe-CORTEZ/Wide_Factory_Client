CREATE TABLE PERSON(
    PSEUDO VARCHAR(20) NOT NULL ,
    LASTNAME VARCHAR NOT NULL ,
    FIRSTNAME VARCHAR NOT NULL ,
    ROLE VARCHAR NOT NULL ,
    PRIMARY KEY (PSEUDO)
);

CREATE TABLE EXERCISE(
    NAME VARCHAR NOT NULL ,
    DESCRIPTION VARCHAR,
    PSEUDO_EDITOR VARCHAR(20) NOT NULL,
    PRIMARY KEY (NAME),
    FOREIGN KEY(PSEUDO_EDITOR) REFERENCES PERSON(PSEUDO) ON DELETE CASCADE
);

CREATE TABLE PERSONS_EXERCISES(
    PSEUDO VARCHAR(20) NOT NULL ,
    NAME_EXERCISE VARCHAR NOT NULL ,
    PRIMARY KEY (PSEUDO, NAME_EXERCISE),
    FOREIGN KEY(PSEUDO) REFERENCES PERSON(PSEUDO) ON DELETE CASCADE ,
    FOREIGN KEY (NAME_EXERCISE) REFERENCES EXERCISE(NAME) ON DELETE CASCADE
);

CREATE TABLE MOVEMENT(
    NAME VARCHAR NOT NULL ,
    DESCRIPTION VARCHAR,
    DEFAULT_REPETITION INTEGER,
    PRIMARY KEY (NAME),
    CHECK ( DEFAULT_REPETITION > 0 )
);

CREATE TABLE MOVEMENTS_EXERCISES(
    NAME_MOVEMENT VARCHAR,
    NAME_EXERCISE VARCHAR,
    PRIMARY KEY (NAME_MOVEMENT, NAME_EXERCISE),
    FOREIGN KEY (NAME_MOVEMENT) REFERENCES MOVEMENT(NAME) ON DELETE CASCADE ,
    FOREIGN KEY (NAME_EXERCISE) REFERENCES EXERCISE(NAME) ON DELETE CASCADE
);

CREATE TABLE SKELETON(
     ID BIGSERIAL NOT NULL ,
     FRAME INTEGER NOT NULL ,
     NAME_MOVEMENT VARCHAR NOT NULL ,
     PRIMARY KEY (ID),
     FOREIGN KEY (NAME_MOVEMENT) REFERENCES MOVEMENT(NAME) ON DELETE CASCADE ,
     CHECK ( FRAME >= 0 ),
     UNIQUE (FRAME, NAME_MOVEMENT)
);

CREATE TABLE JOINT(
    ID BIGSERIAL NOT NULL ,
    ID_SKELETON BIGINT NOT NULL ,
    NAME VARCHAR NOT NULL ,
    X FLOAT NOT NULL ,
    Y FLOAT NOT NULL ,
    Z FLOAT NOT NULL ,
    W FLOAT NOT NULL ,
    WX FLOAT NOT NULL ,
    WY FLOAT NOT NULL ,
    WZ FLOAT NOT NULL ,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_SKELETON) REFERENCES SKELETON(ID) ON DELETE CASCADE,
    UNIQUE (NAME, ID_SKELETON)
);