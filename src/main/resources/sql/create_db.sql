CREATE TYPE ROLETYPE AS ENUM ('UTILISATEUR', 'COACH');
CREATE TYPE JOINTTYPE AS ENUM('K4ABT_JOINT_PELVIS',
    'K4ABT_JOINT_SPINE_NAVEL',
    'K4ABT_JOINT_SPINE_CHEST',
    'K4ABT_JOINT_NECK',
    'K4ABT_JOINT_CLAVICLE_LEFT',
    'K4ABT_JOINT_SHOULDER_LEFT',
    'K4ABT_JOINT_ELBOW_LEFT',
    'K4ABT_JOINT_WRIST_LEFT',
    'K4ABT_JOINT_HAND_LEFT',
    'K4ABT_JOINT_HANDTIP_LEFT',
    'K4ABT_JOINT_THUMB_LEFT',
    'K4ABT_JOINT_CLAVICLE_RIGHT',
    'K4ABT_JOINT_SHOULDER_RIGHT',
    'K4ABT_JOINT_ELBOW_RIGHT',
    'K4ABT_JOINT_WRIST_RIGHT',
    'K4ABT_JOINT_HAND_RIGHT',
    'K4ABT_JOINT_HANDTIP_RIGHT',
    'K4ABT_JOINT_THUMB_RIGHT',
    'K4ABT_JOINT_HIP_LEFT',
    'K4ABT_JOINT_KNEE_LEFT',
    'K4ABT_JOINT_ANKLE_LEFT',
    'K4ABT_JOINT_FOOT_LEFT',
    'K4ABT_JOINT_HIP_RIGHT',
    'K4ABT_JOINT_KNEE_RIGHT',
    'K4ABT_JOINT_ANKLE_RIGHT',
    'K4ABT_JOINT_FOOT_RIGHT',
    'K4ABT_JOINT_HEAD',
    'K4ABT_JOINT_NOSE',
    'K4ABT_JOINT_EYE_LEFT',
    'K4ABT_JOINT_EAR_LEFT',
    'K4ABT_JOINT_EYE_RIGHT',
    'K4ABT_JOINT_EAR_RIGHT');

CREATE TABLE PERSON(
    PSEUDO VARCHAR(20) NOT NULL ,
    LASTNAME VARCHAR NOT NULL ,
    FIRSTNAME VARCHAR NOT NULL ,
    ROLE ROLETYPE NOT NULL ,
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
    PRIMARY KEY (NAME)
);

CREATE TABLE MOVEMENTS_EXERCISES(
    NAME_MOVEMENT VARCHAR,
    NAME_EXERCISE VARCHAR,
    DEFAULT_REPETITION INTEGER,
    PRIMARY KEY (NAME_EXERCISE, NAME_MOVEMENT),
    FOREIGN KEY (NAME_MOVEMENT) REFERENCES MOVEMENT(NAME) ON DELETE CASCADE ,
    FOREIGN KEY (NAME_EXERCISE) REFERENCES EXERCISE(NAME) ON DELETE CASCADE ,
    CHECK ( DEFAULT_REPETITION > 0 )
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
    NAME JOINTTYPE NOT NULL ,
    X FLOAT NOT NULL ,
    Y FLOAT NOT NULL ,
    Z FLOAT NOT NULL ,
    W FLOAT NOT NULL ,
    WX FLOAT NOT NULL ,
    WY FLOAT NOT NULL ,
    WZ FLOAT NOT NULL ,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_SKELETON) REFERENCES SKELETON(ID) ON DELETE CASCADE
);