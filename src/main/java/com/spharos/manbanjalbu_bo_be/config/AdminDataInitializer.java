package com.spharos.manbanjalbu_bo_be.config;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminRole;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminStatus;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRoleRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberProfileRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class AdminDataInitializer implements CommandLineRunner {

	private static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN";
	private static final String ADMIN_LOGIN_ID = "admin";
	private static final String ADMIN_PASSWORD = "admin1234";

	private final AdminRoleRepository adminRoleRepository;
	private final AdminRepository adminRepository;
	private final MemberRepository memberRepository;
	private final MemberProfileRepository memberProfileRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) {
		AdminRole superAdminRole = ensureSuperAdminRole();

		memberRepository.findByLoginId(ADMIN_LOGIN_ID).ifPresentOrElse(
				member -> linkAdminIfMissing(member, superAdminRole),
				() -> createFullAdminSeed(superAdminRole)
		);
	}

	private AdminRole ensureSuperAdminRole() {
		return adminRoleRepository.findByRoleName(SUPER_ADMIN_ROLE)
				.orElseGet(() -> adminRoleRepository.save(AdminRole.builder()
						.roleName(SUPER_ADMIN_ROLE)
						.description("전체 권한 슈퍼 관리자")
						.canManageProduct(true)
						.canManageMember(true)
						.canManageOrder(true)
						.canManageSystem(true)
						.build()));
	}

	private void linkAdminIfMissing(Member member, AdminRole superAdminRole) {
		if (adminRepository.existsById(member.getId())) {
			log.info("관리자 시드 데이터가 이미 존재합니다. (loginId={})", ADMIN_LOGIN_ID);
			return;
		}
		adminRepository.save(Admin.builder()
				.member(member)
				.role(superAdminRole)
				.adminStatus(AdminStatus.ACTIVE)
				.build());
		log.info("기존 member에 admin row 연결 완료 (loginId={})", ADMIN_LOGIN_ID);
	}

	private void createFullAdminSeed(AdminRole superAdminRole) {
		Member adminMember = memberRepository.save(Member.builder()
				.loginId(ADMIN_LOGIN_ID)
				.password(passwordEncoder.encode(ADMIN_PASSWORD))
				.grade(MemberGrade.WELCOME)
				.status(MemberStatus.ACTIVE)
				.warningCount(0)
				.build());

		memberProfileRepository.save(MemberProfile.builder()
				.member(adminMember)
				.name("시스템 관리자")
				.nickname("admin")
				.email("admin@starbucks.local")
				.phone("01000000000")
				.birthDate(LocalDate.of(1990, 1, 1))
				.marketingEmailAgreed(false)
				.marketingSmsAgreed(false)
				.build());

		adminRepository.save(Admin.builder()
				.member(adminMember)
				.role(superAdminRole)
				.adminStatus(AdminStatus.ACTIVE)
				.build());

		log.info("관리자 시드 데이터 생성 완료 (loginId={}, password={})", ADMIN_LOGIN_ID, ADMIN_PASSWORD);
	}
}
