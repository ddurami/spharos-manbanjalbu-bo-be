-- starbucks7th 회원 더미 데이터 50명
-- loginId: member001 ~ member050 / password: Test1234!
-- Workbench에서 starbucks7th 스키마 선택 후 실행

USE starbucks7th;

-- 기존 더미 데이터가 있으면 삭제 (선택)
DELETE o FROM orders o
INNER JOIN member m ON m.id = o.member_id
WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';

DELETE ma FROM member_address ma
INNER JOIN member m ON m.id = ma.member_id
WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';

DELETE mp FROM member_profile mp
INNER JOIN member m ON m.id = mp.member_id
WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';

DELETE FROM member
WHERE login_id LIKE 'member%' AND login_id REGEXP '^member[0-9]{3}$';

START TRANSACTION;

-- member001 (김민준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member001',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 1 DAY) - INTERVAL 1 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '김민준',
  'nick001',
  'member001@test.local',
  '01020000001',
  '1988-01-02',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '김민준',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '1동 2호',
  '01020000001',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member002 (이서연)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member002',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 2 DAY) - INTERVAL 2 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '이서연',
  'nick002',
  'member002@test.local',
  '01020000002',
  '1988-01-03',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '이서연',
  '06234',
  '서울특별시 마포구 양화로 45',
  '2동 3호',
  '01020000002',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member003 (박지호)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member003',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 3 DAY) - INTERVAL 3 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '박지호',
  'nick003',
  'member003@test.local',
  '01020000003',
  '1988-01-04',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '박지호',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '3동 4호',
  '01020000003',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member004 (최하은)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member004',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 4 DAY) - INTERVAL 4 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '최하은',
  'nick004',
  'member004@test.local',
  '01020000004',
  '1988-01-05',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '최하은',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '4동 5호',
  '01020000004',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member005 (정도윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member005',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 5 DAY) - INTERVAL 5 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '정도윤',
  'nick005',
  'member005@test.local',
  '01020000005',
  '1988-01-06',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '정도윤',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '5동 6호',
  '01020000005',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member006 (강서윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member006',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  DATE_SUB(NOW(), INTERVAL 6 DAY) - INTERVAL 6 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '강서윤',
  'nick006',
  'member006@test.local',
  '01020000006',
  '1988-01-07',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '강서윤',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '6동 7호',
  '01020000006',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member007 (조예준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member007',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 7 DAY) - INTERVAL 7 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '조예준',
  'nick007',
  'member007@test.local',
  '01020000007',
  '1988-01-08',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '조예준',
  '06234',
  '서울특별시 마포구 양화로 45',
  '7동 8호',
  '01020000007',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member008 (윤지우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member008',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'SUSPENDED',
  1,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 8 DAY) - INTERVAL 8 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '윤지우',
  'nick008',
  'member008@test.local',
  '01020000008',
  '1988-01-09',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '윤지우',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '8동 9호',
  '01020000008',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member009 (장시우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member009',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GOLD',
  'WITHDRAWN',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 9 DAY) - INTERVAL 9 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '장시우',
  'nick009',
  'member009@test.local',
  '01020000009',
  '1988-01-10',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '장시우',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '9동 10호',
  '01020000009',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member010 (임수아)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member010',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 10 DAY) - INTERVAL 10 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '임수아',
  'nick010',
  'member010@test.local',
  '01020000010',
  '1988-01-11',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '임수아',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '10동 11호',
  '01020000010',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

-- member011 (김민준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member011',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 11 DAY) - INTERVAL 11 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '김민준',
  'nick011',
  'member011@test.local',
  '01020000011',
  '1988-01-12',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '김민준',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '11동 12호',
  '01020000011',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-011',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '김민준',
  '01020000011',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '11동 12호',
  DATE_SUB(NOW(), INTERVAL 11 DAY),
  NOW(),
  NOW()
);

-- member012 (이서연)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member012',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 12 DAY) - INTERVAL 0 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '이서연',
  'nick012',
  'member012@test.local',
  '01020000012',
  '1988-01-13',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '이서연',
  '06234',
  '서울특별시 마포구 양화로 45',
  '12동 13호',
  '01020000012',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-012',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '이서연',
  '01020000012',
  '06234',
  '서울특별시 마포구 양화로 45',
  '12동 13호',
  DATE_SUB(NOW(), INTERVAL 12 DAY),
  NOW(),
  NOW()
);

-- member013 (박지호)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member013',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  DATE_SUB(NOW(), INTERVAL 13 DAY) - INTERVAL 1 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '박지호',
  'nick013',
  'member013@test.local',
  '01020000013',
  '1988-01-14',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '박지호',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '13동 14호',
  '01020000013',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-013',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '박지호',
  '01020000013',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '13동 14호',
  DATE_SUB(NOW(), INTERVAL 13 DAY),
  NOW(),
  NOW()
);

-- member014 (최하은)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member014',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 14 DAY) - INTERVAL 2 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '최하은',
  'nick014',
  'member014@test.local',
  '01020000014',
  '1988-01-15',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '최하은',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '14동 15호',
  '01020000014',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-014',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '최하은',
  '01020000014',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '14동 15호',
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  NOW(),
  NOW()
);

-- member015 (정도윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member015',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 15 DAY) - INTERVAL 3 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '정도윤',
  'nick015',
  'member015@test.local',
  '01020000015',
  '1988-01-16',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '정도윤',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '15동 16호',
  '01020000015',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-015',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '정도윤',
  '01020000015',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '15동 16호',
  DATE_SUB(NOW(), INTERVAL 15 DAY),
  NOW(),
  NOW()
);

-- member016 (강서윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member016',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 16 DAY) - INTERVAL 4 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '강서윤',
  'nick016',
  'member016@test.local',
  '01020000016',
  '1988-01-17',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '강서윤',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '16동 17호',
  '01020000016',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-016',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '강서윤',
  '01020000016',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '16동 17호',
  DATE_SUB(NOW(), INTERVAL 16 DAY),
  NOW(),
  NOW()
);

-- member017 (조예준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member017',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 17 DAY) - INTERVAL 5 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '조예준',
  'nick017',
  'member017@test.local',
  '01020000017',
  '1988-01-18',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '조예준',
  '06234',
  '서울특별시 마포구 양화로 45',
  '17동 18호',
  '01020000017',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-017',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '조예준',
  '01020000017',
  '06234',
  '서울특별시 마포구 양화로 45',
  '17동 18호',
  DATE_SUB(NOW(), INTERVAL 17 DAY),
  NOW(),
  NOW()
);

-- member018 (윤지우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member018',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'SUSPENDED',
  1,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 18 DAY) - INTERVAL 6 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '윤지우',
  'nick018',
  'member018@test.local',
  '01020000018',
  '1988-01-19',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '윤지우',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '18동 19호',
  '01020000018',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-018',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '윤지우',
  '01020000018',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '18동 19호',
  DATE_SUB(NOW(), INTERVAL 18 DAY),
  NOW(),
  NOW()
);

-- member019 (장시우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member019',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GOLD',
  'WITHDRAWN',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 19 DAY) - INTERVAL 7 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '장시우',
  'nick019',
  'member019@test.local',
  '01020000019',
  '1988-01-20',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '장시우',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '19동 20호',
  '01020000019',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-019',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '장시우',
  '01020000019',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '19동 20호',
  DATE_SUB(NOW(), INTERVAL 19 DAY),
  NOW(),
  NOW()
);

-- member020 (임수아)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member020',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  DATE_SUB(NOW(), INTERVAL 20 DAY) - INTERVAL 8 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '임수아',
  'nick020',
  'member020@test.local',
  '01020000020',
  '1988-01-21',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '임수아',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '20동 1호',
  '01020000020',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-020',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  12000,
  3000,
  15000,
  '임수아',
  '01020000020',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '20동 1호',
  DATE_SUB(NOW(), INTERVAL 20 DAY),
  NOW(),
  NOW()
);

-- member021 (김민준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member021',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 21 DAY) - INTERVAL 9 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '김민준',
  'nick021',
  'member021@test.local',
  '01020000021',
  '1988-01-22',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '김민준',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '21동 2호',
  '01020000021',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-021',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '김민준',
  '01020000021',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '21동 2호',
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  NOW(),
  NOW()
);

-- member022 (이서연)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member022',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 22 DAY) - INTERVAL 10 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '이서연',
  'nick022',
  'member022@test.local',
  '01020000022',
  '1988-01-23',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '이서연',
  '06234',
  '서울특별시 마포구 양화로 45',
  '22동 3호',
  '01020000022',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-022',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '이서연',
  '01020000022',
  '06234',
  '서울특별시 마포구 양화로 45',
  '22동 3호',
  DATE_SUB(NOW(), INTERVAL 22 DAY),
  NOW(),
  NOW()
);

-- member023 (박지호)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member023',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 23 DAY) - INTERVAL 11 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '박지호',
  'nick023',
  'member023@test.local',
  '01020000023',
  '1988-01-24',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '박지호',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '23동 4호',
  '01020000023',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-023',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '박지호',
  '01020000023',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '23동 4호',
  DATE_SUB(NOW(), INTERVAL 23 DAY),
  NOW(),
  NOW()
);

-- member024 (최하은)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member024',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 24 DAY) - INTERVAL 0 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '최하은',
  'nick024',
  'member024@test.local',
  '01020000024',
  '1988-01-25',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '최하은',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '24동 5호',
  '01020000024',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-024',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '최하은',
  '01020000024',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '24동 5호',
  DATE_SUB(NOW(), INTERVAL 24 DAY),
  NOW(),
  NOW()
);

-- member025 (정도윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member025',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 25 DAY) - INTERVAL 1 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '정도윤',
  'nick025',
  'member025@test.local',
  '01020000025',
  '1988-01-26',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '정도윤',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '25동 6호',
  '01020000025',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-025',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '정도윤',
  '01020000025',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '25동 6호',
  DATE_SUB(NOW(), INTERVAL 25 DAY),
  NOW(),
  NOW()
);

-- member026 (강서윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member026',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 26 DAY) - INTERVAL 2 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '강서윤',
  'nick026',
  'member026@test.local',
  '01020000026',
  '1988-01-27',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '강서윤',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '26동 7호',
  '01020000026',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-026',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '강서윤',
  '01020000026',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '26동 7호',
  DATE_SUB(NOW(), INTERVAL 26 DAY),
  NOW(),
  NOW()
);

-- member027 (조예준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member027',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 21 DAY),
  DATE_SUB(NOW(), INTERVAL 27 DAY) - INTERVAL 3 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '조예준',
  'nick027',
  'member027@test.local',
  '01020000027',
  '1988-01-28',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '조예준',
  '06234',
  '서울특별시 마포구 양화로 45',
  '27동 8호',
  '01020000027',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-027',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '조예준',
  '01020000027',
  '06234',
  '서울특별시 마포구 양화로 45',
  '27동 8호',
  DATE_SUB(NOW(), INTERVAL 27 DAY),
  NOW(),
  NOW()
);

-- member028 (윤지우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member028',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'SUSPENDED',
  1,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 28 DAY) - INTERVAL 4 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '윤지우',
  'nick028',
  'member028@test.local',
  '01020000028',
  '1988-01-29',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '윤지우',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '28동 9호',
  '01020000028',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-028',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '윤지우',
  '01020000028',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '28동 9호',
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  NOW(),
  NOW()
);

-- member029 (장시우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member029',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GOLD',
  'WITHDRAWN',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 29 DAY) - INTERVAL 5 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '장시우',
  'nick029',
  'member029@test.local',
  '01020000029',
  '1988-01-30',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '장시우',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '29동 10호',
  '01020000029',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-029',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '장시우',
  '01020000029',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '29동 10호',
  DATE_SUB(NOW(), INTERVAL 29 DAY),
  NOW(),
  NOW()
);

-- member030 (임수아)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member030',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 30 DAY) - INTERVAL 6 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '임수아',
  'nick030',
  'member030@test.local',
  '01020000030',
  '1988-01-31',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '임수아',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '30동 11호',
  '01020000030',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-030',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  82000,
  3000,
  85000,
  '임수아',
  '01020000030',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '30동 11호',
  DATE_SUB(NOW(), INTERVAL 0 DAY),
  NOW(),
  NOW()
);

-- member031 (김민준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member031',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 31 DAY) - INTERVAL 7 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '김민준',
  'nick031',
  'member031@test.local',
  '01020000031',
  '1988-02-01',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '김민준',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '31동 12호',
  '01020000031',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-031',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '김민준',
  '01020000031',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '31동 12호',
  DATE_SUB(NOW(), INTERVAL 1 DAY),
  NOW(),
  NOW()
);

-- member032 (이서연)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member032',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 32 DAY) - INTERVAL 8 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '이서연',
  'nick032',
  'member032@test.local',
  '01020000032',
  '1988-02-02',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '이서연',
  '06234',
  '서울특별시 마포구 양화로 45',
  '32동 13호',
  '01020000032',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-032',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '이서연',
  '01020000032',
  '06234',
  '서울특별시 마포구 양화로 45',
  '32동 13호',
  DATE_SUB(NOW(), INTERVAL 2 DAY),
  NOW(),
  NOW()
);

-- member033 (박지호)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member033',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 33 DAY) - INTERVAL 9 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '박지호',
  'nick033',
  'member033@test.local',
  '01020000033',
  '1988-02-03',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '박지호',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '33동 14호',
  '01020000033',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-033',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '박지호',
  '01020000033',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '33동 14호',
  DATE_SUB(NOW(), INTERVAL 3 DAY),
  NOW(),
  NOW()
);

-- member034 (최하은)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member034',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 28 DAY),
  DATE_SUB(NOW(), INTERVAL 34 DAY) - INTERVAL 10 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '최하은',
  'nick034',
  'member034@test.local',
  '01020000034',
  '1988-02-04',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '최하은',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '34동 15호',
  '01020000034',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-034',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '최하은',
  '01020000034',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '34동 15호',
  DATE_SUB(NOW(), INTERVAL 4 DAY),
  NOW(),
  NOW()
);

-- member035 (정도윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member035',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 35 DAY) - INTERVAL 11 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '정도윤',
  'nick035',
  'member035@test.local',
  '01020000035',
  '1988-02-05',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '정도윤',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '35동 16호',
  '01020000035',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-035',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '정도윤',
  '01020000035',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '35동 16호',
  DATE_SUB(NOW(), INTERVAL 5 DAY),
  NOW(),
  NOW()
);

-- member036 (강서윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member036',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 36 DAY) - INTERVAL 0 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '강서윤',
  'nick036',
  'member036@test.local',
  '01020000036',
  '1988-02-06',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '강서윤',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '36동 17호',
  '01020000036',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-036',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '강서윤',
  '01020000036',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '36동 17호',
  DATE_SUB(NOW(), INTERVAL 6 DAY),
  NOW(),
  NOW()
);

-- member037 (조예준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member037',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 37 DAY) - INTERVAL 1 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '조예준',
  'nick037',
  'member037@test.local',
  '01020000037',
  '1988-02-07',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '조예준',
  '06234',
  '서울특별시 마포구 양화로 45',
  '37동 18호',
  '01020000037',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-037',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '조예준',
  '01020000037',
  '06234',
  '서울특별시 마포구 양화로 45',
  '37동 18호',
  DATE_SUB(NOW(), INTERVAL 7 DAY),
  NOW(),
  NOW()
);

-- member038 (윤지우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member038',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'SUSPENDED',
  1,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 38 DAY) - INTERVAL 2 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '윤지우',
  'nick038',
  'member038@test.local',
  '01020000038',
  '1988-02-08',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '윤지우',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '38동 19호',
  '01020000038',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-038',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '윤지우',
  '01020000038',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '38동 19호',
  DATE_SUB(NOW(), INTERVAL 8 DAY),
  NOW(),
  NOW()
);

-- member039 (장시우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member039',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GOLD',
  'WITHDRAWN',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 39 DAY) - INTERVAL 3 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '장시우',
  'nick039',
  'member039@test.local',
  '01020000039',
  '1988-02-09',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '장시우',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '39동 20호',
  '01020000039',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-039',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '장시우',
  '01020000039',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '39동 20호',
  DATE_SUB(NOW(), INTERVAL 9 DAY),
  NOW(),
  NOW()
);

-- member040 (임수아)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member040',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 40 DAY) - INTERVAL 4 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '임수아',
  'nick040',
  'member040@test.local',
  '01020000040',
  '1988-02-10',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '임수아',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '40동 1호',
  '01020000040',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-040',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  247000,
  3000,
  250000,
  '임수아',
  '01020000040',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '40동 1호',
  DATE_SUB(NOW(), INTERVAL 10 DAY),
  NOW(),
  NOW()
);

-- member041 (김민준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member041',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 35 DAY),
  DATE_SUB(NOW(), INTERVAL 41 DAY) - INTERVAL 5 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '김민준',
  'nick041',
  'member041@test.local',
  '01020000041',
  '1988-02-11',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '김민준',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '41동 2호',
  '01020000041',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-041',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '김민준',
  '01020000041',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '41동 2호',
  DATE_SUB(NOW(), INTERVAL 11 DAY),
  NOW(),
  NOW()
);

-- member042 (이서연)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member042',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 42 DAY) - INTERVAL 6 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '이서연',
  'nick042',
  'member042@test.local',
  '01020000042',
  '1988-02-12',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '이서연',
  '06234',
  '서울특별시 마포구 양화로 45',
  '42동 3호',
  '01020000042',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-042',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '이서연',
  '01020000042',
  '06234',
  '서울특별시 마포구 양화로 45',
  '42동 3호',
  DATE_SUB(NOW(), INTERVAL 12 DAY),
  NOW(),
  NOW()
);

-- member043 (박지호)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member043',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 43 DAY) - INTERVAL 7 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '박지호',
  'nick043',
  'member043@test.local',
  '01020000043',
  '1988-02-13',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '박지호',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '43동 4호',
  '01020000043',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-043',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '박지호',
  '01020000043',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '43동 4호',
  DATE_SUB(NOW(), INTERVAL 13 DAY),
  NOW(),
  NOW()
);

-- member044 (최하은)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member044',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 44 DAY) - INTERVAL 8 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '최하은',
  'nick044',
  'member044@test.local',
  '01020000044',
  '1988-02-14',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '최하은',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '44동 5호',
  '01020000044',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-044',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '최하은',
  '01020000044',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '44동 5호',
  DATE_SUB(NOW(), INTERVAL 14 DAY),
  NOW(),
  NOW()
);

-- member045 (정도윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member045',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 45 DAY) - INTERVAL 9 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '정도윤',
  'nick045',
  'member045@test.local',
  '01020000045',
  '1988-02-15',
  0,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '정도윤',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '45동 6호',
  '01020000045',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-045',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '정도윤',
  '01020000045',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '45동 6호',
  DATE_SUB(NOW(), INTERVAL 15 DAY),
  NOW(),
  NOW()
);

-- member046 (강서윤)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member046',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 46 DAY) - INTERVAL 10 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '강서윤',
  'nick046',
  'member046@test.local',
  '01020000046',
  '1988-02-16',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '강서윤',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '46동 7호',
  '01020000046',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-046',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '강서윤',
  '01020000046',
  '06234',
  '서울특별시 강남구 테헤란로 123',
  '46동 7호',
  DATE_SUB(NOW(), INTERVAL 16 DAY),
  NOW(),
  NOW()
);

-- member047 (조예준)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member047',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 47 DAY) - INTERVAL 11 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '조예준',
  'nick047',
  'member047@test.local',
  '01020000047',
  '1988-02-17',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '조예준',
  '06234',
  '서울특별시 마포구 양화로 45',
  '47동 8호',
  '01020000047',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-047',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '조예준',
  '01020000047',
  '06234',
  '서울특별시 마포구 양화로 45',
  '47동 8호',
  DATE_SUB(NOW(), INTERVAL 17 DAY),
  NOW(),
  NOW()
);

-- member048 (윤지우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member048',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GREEN',
  'SUSPENDED',
  1,
  DATE_SUB(NOW(), INTERVAL 42 DAY),
  DATE_SUB(NOW(), INTERVAL 48 DAY) - INTERVAL 0 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '윤지우',
  'nick048',
  'member048@test.local',
  '01020000048',
  '1988-02-18',
  1,
  1,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '윤지우',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '48동 9호',
  '01020000048',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-048',
  '아메리카노 외 1건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '윤지우',
  '01020000048',
  '06234',
  '서울특별시 송파구 올림픽로 300',
  '48동 9호',
  DATE_SUB(NOW(), INTERVAL 18 DAY),
  NOW(),
  NOW()
);

-- member049 (장시우)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member049',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'GOLD',
  'WITHDRAWN',
  0,
  DATE_SUB(NOW(), INTERVAL 49 DAY),
  DATE_SUB(NOW(), INTERVAL 49 DAY) - INTERVAL 1 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '장시우',
  'nick049',
  'member049@test.local',
  '01020000049',
  '1988-02-19',
  0,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '장시우',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '49동 10호',
  '01020000049',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-049',
  '아메리카노 외 2건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '장시우',
  '01020000049',
  '06234',
  '경기도 성남시 분당구 판교역로 166',
  '49동 10호',
  DATE_SUB(NOW(), INTERVAL 19 DAY),
  NOW(),
  NOW()
);

-- member050 (임수아)
INSERT INTO member (
  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at
) VALUES (
  'member050',
  '$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i',
  'WELCOME',
  'ACTIVE',
  0,
  DATE_SUB(NOW(), INTERVAL 49 DAY),
  DATE_SUB(NOW(), INTERVAL 50 DAY) - INTERVAL 2 HOUR,
  NOW()
);
SET @member_id = LAST_INSERT_ID();

INSERT INTO member_profile (
  member_id, name, nickname, email, phone, birth_date,
  marketing_email_agreed, marketing_sms_agreed, updated_at
) VALUES (
  @member_id,
  '임수아',
  'nick050',
  'member050@test.local',
  '01020000050',
  '1988-02-20',
  1,
  0,
  NOW()
);

INSERT INTO member_address (
  member_id, address_name, recipient_name, zipcode, base_address, detail_address,
  phone1, phone2, delivery_memo, is_default, created_at, updated_at
) VALUES (
  @member_id,
  '집',
  '임수아',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '50동 11호',
  '01020000050',
  NULL,
  '문 앞에 놓아주세요',
  1,
  NOW(),
  NOW()
);
SET @address_id = LAST_INSERT_ID();

INSERT INTO orders (
  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,
  amount, delivery_fee, order_amount,
  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,
  order_at, created_at, updated_at
) VALUES (
  @member_id,
  @address_id,
  'ORD-SEED-050',
  '아메리카노 외 3건',
  'DELIVERED',
  'DELIVERY',
  'GENERAL',
  447000,
  3000,
  450000,
  '임수아',
  '01020000050',
  '06234',
  '부산광역시 해운대구 센텀중앙로 79',
  '50동 11호',
  DATE_SUB(NOW(), INTERVAL 20 DAY),
  NOW(),
  NOW()
);

COMMIT;

-- 확인
SELECT COUNT(*) AS member_count FROM member WHERE login_id REGEXP '^member[0-9]{3}$';