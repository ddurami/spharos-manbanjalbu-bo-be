const bcrypt = "$2b$10$EE.1i9KW9faXJdeBnb31wO82bVQGuCbxAkzowHsJAmkooU2fv/d.i";

const LAST_NAMES = ["김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"];
const FIRST_NAMES = ["민준", "서연", "지호", "하은", "도윤", "서윤", "예준", "지우", "시우", "수아"];
const BASE_ADDRESSES = [
  "서울특별시 강남구 테헤란로 123",
  "서울특별시 마포구 양화로 45",
  "서울특별시 송파구 올림픽로 300",
  "경기도 성남시 분당구 판교역로 166",
  "부산광역시 해운대구 센텀중앙로 79",
];

function resolveGrade(i) {
  const mod = i % 10;
  if (mod < 6) return "WELCOME";
  if (mod < 9) return "GREEN";
  return "GOLD";
}

function resolveStatus(i) {
  const mod = i % 10;
  if (mod === 8) return "SUSPENDED";
  if (mod === 9) return "WITHDRAWN";
  return "ACTIVE";
}

function resolvePurchaseAmount(i) {
  if (i <= 10) return 0;
  if (i <= 20) return 15000;
  if (i <= 30) return 85000;
  if (i <= 40) return 250000;
  return 450000;
}

function pad(n) {
  return String(n).padStart(3, "0");
}

function birthDate(i) {
  const d = new Date(Date.UTC(1988, 0, 1 + i));
  return d.toISOString().slice(0, 10);
}

function sqlEscape(value) {
  return String(value).replace(/\\/g, "\\\\").replace(/'/g, "''");
}

const lines = [];
lines.push("-- starbucks7th 회원 더미 데이터 50명");
lines.push("-- loginId: member001 ~ member050 / password: Test1234!");
lines.push("-- Workbench에서 starbucks7th 스키마 선택 후 실행");
lines.push("");
lines.push("USE starbucks7th;");
lines.push("");
lines.push("-- 기존 더미 데이터가 있으면 삭제 (선택)");
lines.push("DELETE o FROM orders o");
lines.push("INNER JOIN member m ON m.id = o.member_id");
lines.push("WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';");
lines.push("");
lines.push("DELETE ma FROM member_address ma");
lines.push("INNER JOIN member m ON m.id = ma.member_id");
lines.push("WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';");
lines.push("");
lines.push("DELETE mp FROM member_profile mp");
lines.push("INNER JOIN member m ON m.id = mp.member_id");
lines.push("WHERE m.login_id LIKE 'member%' AND m.login_id REGEXP '^member[0-9]{3}$';");
lines.push("");
lines.push("DELETE FROM member");
lines.push("WHERE login_id LIKE 'member%' AND login_id REGEXP '^member[0-9]{3}$';");
lines.push("");
lines.push("START TRANSACTION;");
lines.push("");

for (let i = 1; i <= 50; i += 1) {
  const suffix = pad(i);
  const loginId = `member${suffix}`;
  const name = LAST_NAMES[(i - 1) % LAST_NAMES.length] + FIRST_NAMES[(i - 1) % FIRST_NAMES.length];
  const phone = `0102${String(i).padStart(7, "0")}`;
  const grade = resolveGrade(i);
  const status = resolveStatus(i);
  const warningCount = i % 10 === 8 ? 1 : 0;
  const email = `${loginId}@test.local`;
  const nickname = `nick${suffix}`;
  const marketingEmail = i % 2 === 0 ? 1 : 0;
  const marketingSms = i % 3 === 0 ? 1 : 0;
  const baseAddress = BASE_ADDRESSES[(i - 1) % BASE_ADDRESSES.length];
  const detailAddress = `${i}동 ${(i % 20) + 1}호`;
  const createdDaysAgo = i % 90;
  const createdHoursAgo = i % 12;
  const purchaseAmount = resolvePurchaseAmount(i);

  lines.push(`-- ${loginId} (${name})`);
  lines.push(`INSERT INTO member (`);
  lines.push(`  login_id, password, grade, status, warning_count, last_login_at, created_at, updated_at`);
  lines.push(`) VALUES (`);
  lines.push(`  '${loginId}',`);
  lines.push(`  '${bcrypt}',`);
  lines.push(`  '${grade}',`);
  lines.push(`  '${status}',`);
  lines.push(`  ${warningCount},`);
  lines.push(`  DATE_SUB(NOW(), INTERVAL ${Math.max(createdDaysAgo - (i % 7), 0)} DAY),`);
  lines.push(`  DATE_SUB(NOW(), INTERVAL ${createdDaysAgo} DAY) - INTERVAL ${createdHoursAgo} HOUR,`);
  lines.push(`  NOW()`);
  lines.push(`);`);
  lines.push(`SET @member_id = LAST_INSERT_ID();`);
  lines.push("");
  lines.push(`INSERT INTO member_profile (`);
  lines.push(`  member_id, name, nickname, email, phone, birth_date,`);
  lines.push(`  marketing_email_agreed, marketing_sms_agreed, updated_at`);
  lines.push(`) VALUES (`);
  lines.push(`  @member_id,`);
  lines.push(`  '${sqlEscape(name)}',`);
  lines.push(`  '${nickname}',`);
  lines.push(`  '${email}',`);
  lines.push(`  '${phone}',`);
  lines.push(`  '${birthDate(i)}',`);
  lines.push(`  ${marketingEmail},`);
  lines.push(`  ${marketingSms},`);
  lines.push(`  NOW()`);
  lines.push(`);`);
  lines.push("");
  lines.push(`INSERT INTO member_address (`);
  lines.push(`  member_id, address_name, recipient_name, zipcode, base_address, detail_address,`);
  lines.push(`  phone1, phone2, delivery_memo, is_default, created_at, updated_at`);
  lines.push(`) VALUES (`);
  lines.push(`  @member_id,`);
  lines.push(`  '집',`);
  lines.push(`  '${sqlEscape(name)}',`);
  lines.push(`  '06234',`);
  lines.push(`  '${sqlEscape(baseAddress)}',`);
  lines.push(`  '${sqlEscape(detailAddress)}',`);
  lines.push(`  '${phone}',`);
  lines.push(`  NULL,`);
  lines.push(`  '문 앞에 놓아주세요',`);
  lines.push(`  1,`);
  lines.push(`  NOW(),`);
  lines.push(`  NOW()`);
  lines.push(`);`);
  lines.push(`SET @address_id = LAST_INSERT_ID();`);
  lines.push("");

  if (purchaseAmount > 0) {
    const deliveryFee = 3000;
    const amount = purchaseAmount - deliveryFee;
    lines.push(`INSERT INTO orders (`);
    lines.push(`  member_id, member_address_id, order_no, order_name, order_status, order_type, order_category,`);
    lines.push(`  amount, delivery_fee, order_amount,`);
    lines.push(`  recipient_name, recipient_phone, recipient_zipcode, recipient_base_address, recipient_detail_address,`);
    lines.push(`  order_at, created_at, updated_at`);
    lines.push(`) VALUES (`);
    lines.push(`  @member_id,`);
    lines.push(`  @address_id,`);
    lines.push(`  'ORD-SEED-${suffix}',`);
    lines.push(`  '아메리카노 외 ${(i % 3) + 1}건',`);
    lines.push(`  'DELIVERED',`);
    lines.push(`  'DELIVERY',`);
    lines.push(`  'GENERAL',`);
    lines.push(`  ${amount},`);
    lines.push(`  ${deliveryFee},`);
    lines.push(`  ${purchaseAmount},`);
    lines.push(`  '${sqlEscape(name)}',`);
    lines.push(`  '${phone}',`);
    lines.push(`  '06234',`);
    lines.push(`  '${sqlEscape(baseAddress)}',`);
    lines.push(`  '${sqlEscape(detailAddress)}',`);
    lines.push(`  DATE_SUB(NOW(), INTERVAL ${i % 30} DAY),`);
    lines.push(`  NOW(),`);
    lines.push(`  NOW()`);
    lines.push(`);`);
    lines.push("");
  }
}

lines.push("COMMIT;");
lines.push("");
lines.push("-- 확인");
lines.push("SELECT COUNT(*) AS member_count FROM member WHERE login_id REGEXP '^member[0-9]{3}$';");

const fs = require("fs");
const path = require("path");
const outPath = path.join(__dirname, "..", "src", "main", "resources", "db", "member-seed-50.sql");
fs.writeFileSync(outPath, lines.join("\n"), "utf8");
console.log(`Generated: ${outPath}`);
