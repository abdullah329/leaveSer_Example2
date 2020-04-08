CREATE TABLE GROUP_PERMISSIONS(
                                  ID NUMBER  auto_increment,
                                  GROUP_NAME VARCHAR(100),
                                  EMPLOYEES VARCHAR(500),
                                  primary key (id)
);



CREATE TABLE WORKFLOW(
                         ID NUMBER auto_increment,
                         PRIORITY NUMBER ,
                         GROUP_ID NUMBER,
                         ACTION varchar(2),
                         primary key (id, PRIORITY)
);


ALTER TABLE WORKFLOW
    ADD FOREIGN KEY (GROUP_ID)
        REFERENCES GROUP_PERMISSIONS(ID);


CREATE TABLE APPLICATION_TRANSACTION(
                                        ID NUMBER auto_increment ,
                                        PRIORITY_ORDER NUMBER ,
                                        USER_NID NUMBER NOT NULL,
                                        START_DATE_AG DATE NOT NULL,
                                        START_DATE_AH VARCHAR(20),
                                        DURATION NUMBER,
                                        REQUEST_DATE TIMESTAMP,
                                        WORKFLOW NUMBER,
                                        TYPE NUMBER,
                                        STATUS number,
                                        createdBy varchar(50),
                                        createdOn timestamp,
                                        modifiedBy varchar(50),
                                        modifiedOn timestamp,
                                        version number,
                                        primary key (id, PRIORITY_ORDER)
);

-- ALTER TABLE APPLICATION_TRANSACTION
--     ADD PRIMARY KEY (ID, PRIORITY_ORDER);
ALTER TABLE APPLICATION_TRANSACTION
    ADD FOREIGN KEY (WORKFLOW,PRIORITY_ORDER)
        REFERENCES WORKFLOW(ID,PRIORITY);
-- ALTER TABLE APPLICATION_TRANSACTION
--     ADD FOREIGN KEY (PRIORITY_ORDER)
--         REFERENCES WORKFLOW(PRIORITY);


CREATE TABLE BALANCE_TRANSACTION(
                                    ID NUMBER auto_increment ,
                                    APPLICATION_ID NUMBER,
                                    USER_NID NUMBER,
                                    YEAR NUMBER,
                                    START_EFCT_DATE_AG DATE,
                                    END_EFCT_DATE_AG DATE ,
                                    START_EFCT_DATE_AH VARCHAR(20),
                                    END_EFCT_DATE_AH VARCHAR(20),
                                    TYPE NUMBER ,
                                    BALANCE NUMBER,
                                    CREDIT_OR_DEBIT NUMBER,
                                    STATUS NUMBER,
                                    primary key (ID)
);

-- ALTER TABLE BALANCE_TRANSACTION ADD PRIMARY KEY (ID);
-- ALTER TABLE BALANCE_TRANSACTION ADD FOREIGN KEY (APPLICATION_ID) REFERENCES APPLICATION_TRANSACTION(ID);





CREATE TABLE APPROVAL_PATH(
                              USER_NID NUMBER ,
                              WORKFLOW NUMBER ,
                              primary key (USER_NID,WORKFLOW)
);

-- ALTER TABLE APPROVAL_PATH
--     ADD PRIMARY KEY(USER_NID,WORKFLOW);
-- ALTER TABLE APPROVAL_PATH
-- ADD FOREIGN KEY (WORKFLOW)
-- REFERENCES WORKFLOW(workflow_ID);
