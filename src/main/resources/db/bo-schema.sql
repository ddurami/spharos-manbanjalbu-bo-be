-- BO 전용 테이블 (starbucks7th 공유 DB)
CREATE TABLE IF NOT EXISTS admin_role (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '권한 식별자',
    role_name           VARCHAR(50)  NOT NULL UNIQUE COMMENT 'SUPER_ADMIN, PRODUCT_MD, CS_MANAGER 등',
    description         VARCHAR(255) COMMENT '권한 설명',
    can_manage_product  BOOLEAN DEFAULT FALSE COMMENT '상품 등록/수정/삭제',
    can_manage_member   BOOLEAN DEFAULT FALSE COMMENT '회원 조회/등급/정지',
    can_manage_order    BOOLEAN DEFAULT FALSE COMMENT '주문 조회/취소/환불',
    can_manage_system   BOOLEAN DEFAULT FALSE COMMENT '약관, 추천검색어, 배너 등',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='관리자 역할 RBAC';

CREATE TABLE IF NOT EXISTS admin (
    member_id           BIGINT PRIMARY KEY COMMENT 'member.id 1:1',
    role_id             BIGINT NOT NULL COMMENT 'admin_role.id',
    admin_status        ENUM('ACTIVE','SUSPENDED','RESIGNED') DEFAULT 'ACTIVE',
    last_admin_login_at DATETIME COMMENT 'BO 마지막 로그인',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id)   REFERENCES admin_role(id),
    INDEX idx_admin_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='관리자 계정';

CREATE TABLE IF NOT EXISTS admin_audit_log (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    admin_member_id  BIGINT NOT NULL COMMENT 'admin.member_id',
    action_type      VARCHAR(50)  NOT NULL COMMENT 'MEMBER_SUSPEND, PRODUCT_UPDATE 등',
    target_id        BIGINT COMMENT '대상 데이터 ID',
    description      VARCHAR(255) NOT NULL,
    ip_address       VARCHAR(50),
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_member_id) REFERENCES admin(member_id) ON DELETE CASCADE,
    INDEX idx_admin_audit_log_admin_member_id (admin_member_id),
    INDEX idx_admin_audit_log_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='관리자 감사 로그';
