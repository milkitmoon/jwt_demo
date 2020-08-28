
-- 사용자정보
CREATE TABLE `USER_INFO` (
	`ID`        BIGINT       NOT NULL AUTO_INCREMENT, -- 일련번호
	`USER_ID`   VARCHAR(60)  NOT NULL, -- 사용자아이디
	`PASSWORD`  VARCHAR(256) NOT NULL, -- 비밀번호
	`USER_NM`   VARCHAR(60)  NULL,     -- 사용자명
	`AUTH_ROLE` VARCHAR(20)  NULL,     -- 권한
	`USE_YN`    CHAR(1)      NULL,     -- 사용여부
	`INST_TIME` TIMESTAMP    NULL,     -- 등록시간
	`UPD_TIME`  TIMESTAMP    NULL,     -- 갱신시간
	`INST_USER` VARCHAR(20)  NULL,     -- 등록자
	`UPD_USER`  VARCHAR(20)  NULL      -- 갱신자
);

-- 사용자정보
ALTER TABLE `USER_INFO`
	ADD CONSTRAINT `IDX_POS_USER_PK` -- IDX_POS_USER_PK
		PRIMARY KEY (
			`ID` -- 일련번호
		);

-- UIX_USER_INFO
CREATE UNIQUE INDEX `UIX_USER_INFO`
	ON `USER_INFO` ( -- 사용자정보
		`USER_ID` ASC -- 사용자아이디
	);

-- IX_USER_INFO
CREATE INDEX `IX_USER_INFO`
	ON `USER_INFO`( -- 사용자정보
		`USER_ID` ASC,   -- 사용자아이디
		`USER_NM` ASC,   -- 사용자명
		`AUTH_ROLE` ASC,   -- 권한
		`USE_YN` ASC,    -- 사용여부
		`INST_USER` ASC, -- 등록자
		`INST_TIME` ASC  -- 등록시간
	);
