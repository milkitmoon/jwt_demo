
INSERT INTO USER_INFO(
    USER_ID, 
    PASSWORD, 
    USER_NM, 
    AUTH_ROLE,
    USE_YN, 
    INST_TIME, 
    UPD_TIME, 
    INST_USER, 
    UPD_USER
) values(
    'admin',
    'ecc8515652bb0ef58bcf88e5bc8693352614053a0622ce213f644bcc0ffa0331d16e6b0b02385683', 
    '관리자', 
    'ROLE_ADMIN', 
    'Y', 
    now(), 
    now(), 
    'admin', 
    'admin'
);

INSERT INTO USER_INFO(
    USER_ID, 
    PASSWORD, 
    USER_NM, 
    AUTH_ROLE,
    USE_YN, 
    INST_TIME, 
    UPD_TIME, 
    INST_USER, 
    UPD_USER
) values(
    'test',
    'ecc8515652bb0ef58bcf88e5bc8693352614053a0622ce213f644bcc0ffa0331d16e6b0b02385683', 
    '김복돌', 
    'ROLE_MEMBER', 
    'Y', 
    now(), 
    now(), 
    'admin', 
    'admin'
);


