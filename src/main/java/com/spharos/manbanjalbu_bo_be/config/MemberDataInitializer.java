package com.spharos.manbanjalbu_bo_be.config;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAddress;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberAddressRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberProfileRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.OrderStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.OrderType;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.Orders;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class MemberDataInitializer implements CommandLineRunner {

	private static final String SEED_LOGIN_PREFIX = "member";
	private static final String SEED_PASSWORD = "Test1234!";
	private static final int SEED_COUNT = 50;

	private static final List<String> LAST_NAMES = List.of(
			"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"
	);
	private static final List<String> FIRST_NAMES = List.of(
			"민준", "서연", "지호", "하은", "도윤", "서윤", "예준", "지우", "시우", "수아"
	);
	private static final List<String> BASE_ADDRESSES = List.of(
			"서울특별시 강남구 테헤란로 123",
			"서울특별시 마포구 양화로 45",
			"서울특별시 송파구 올림픽로 300",
			"경기도 성남시 분당구 판교역로 166",
			"부산광역시 해운대구 센텀중앙로 79"
	);

	private final MemberRepository memberRepository;
	private final MemberProfileRepository memberProfileRepository;
	private final MemberAddressRepository memberAddressRepository;
	private final OrderRepository orderRepository;
	private final PasswordEncoder passwordEncoder;
	private final EntityManager entityManager;

	@Override
	@Transactional
	public void run(String... args) {
		if (memberRepository.existsByLoginId(SEED_LOGIN_PREFIX + "001")) {
			log.info("회원 더미 데이터가 이미 존재합니다. (loginId={}001)", SEED_LOGIN_PREFIX);
			return;
		}

		String encodedPassword = passwordEncoder.encode(SEED_PASSWORD);
		LocalDateTime now = LocalDateTime.now();

		for (int i = 1; i <= SEED_COUNT; i++) {
			String suffix = String.format("%03d", i);
			String loginId = SEED_LOGIN_PREFIX + suffix;
			String name = LAST_NAMES.get((i - 1) % LAST_NAMES.size())
					+ FIRST_NAMES.get((i - 1) % FIRST_NAMES.size());
			String phone = "0102" + String.format("%07d", i);

			Member member = memberRepository.save(Member.builder()
					.loginId(loginId)
					.password(encodedPassword)
					.grade(resolveGrade(i))
					.status(resolveStatus(i))
					.warningCount(i % 10 == 8 ? 1 : 0)
					.build());

			memberProfileRepository.save(MemberProfile.builder()
					.member(member)
					.name(name)
					.nickname("nick" + suffix)
					.email(loginId + "@test.local")
					.phone(phone)
					.birthDate(LocalDate.of(1988, 1, 1).plusDays(i))
					.marketingEmailAgreed(i % 2 == 0)
					.marketingSmsAgreed(i % 3 == 0)
					.build());

			String baseAddress = BASE_ADDRESSES.get((i - 1) % BASE_ADDRESSES.size());
			MemberAddress address = memberAddressRepository.save(MemberAddress.create(
					member,
					"집",
					name,
					"06234",
					baseAddress,
					i + "동 " + ((i % 20) + 1) + "호",
					phone,
					null,
					"문 앞에 놓아주세요",
					true
			));

			int purchaseAmount = resolvePurchaseAmount(i);
			if (purchaseAmount > 0) {
				int deliveryFee = 3000;
				int amount = purchaseAmount - deliveryFee;
				orderRepository.save(Orders.createForSeed(
						member,
						address,
						"ORD-SEED-" + suffix,
						"아메리카노 외 " + ((i % 3) + 1) + "건",
						OrderStatus.DELIVERED,
						OrderType.DELIVERY,
						amount,
						deliveryFee,
						purchaseAmount,
						name,
						phone,
						address.getZipcode(),
						address.getBaseAddress(),
						address.getDetailAddress(),
						now.minusDays(i % 30)
				));
			}

			LocalDateTime createdAt = now.minusDays(i % 90).minusHours(i % 12);
			entityManager.createNativeQuery(
							"UPDATE member SET created_at = :createdAt, last_login_at = :lastLoginAt WHERE id = :id")
					.setParameter("createdAt", createdAt)
					.setParameter("lastLoginAt", createdAt.plusDays(i % 7))
					.setParameter("id", member.getId())
					.executeUpdate();
		}

		log.info("회원 더미 데이터 {}명 생성 완료 (loginId={}001~{}{}, password={})",
				SEED_COUNT, SEED_LOGIN_PREFIX, SEED_LOGIN_PREFIX, String.format("%03d", SEED_COUNT), SEED_PASSWORD);
	}

	private MemberGrade resolveGrade(int index) {
		int mod = index % 10;
		if (mod < 6) {
			return MemberGrade.WELCOME;
		}
		if (mod < 9) {
			return MemberGrade.GREEN;
		}
		return MemberGrade.GOLD;
	}

	private MemberStatus resolveStatus(int index) {
		return switch (index % 10) {
			case 8 -> MemberStatus.SUSPENDED;
			case 9 -> MemberStatus.WITHDRAWN;
			default -> MemberStatus.ACTIVE;
		};
	}

	private int resolvePurchaseAmount(int index) {
		if (index <= 10) {
			return 0;
		}
		if (index <= 20) {
			return 15_000;
		}
		if (index <= 30) {
			return 85_000;
		}
		if (index <= 40) {
			return 250_000;
		}
		return 450_000;
	}
}
