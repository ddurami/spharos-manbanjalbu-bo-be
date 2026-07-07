package com.spharos.manbanjalbu_bo_be.domain.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberNotificationService {

	public void sendMemberEmail(String toEmail, String subject, String body) {
		log.info("[회원 메일 발송] to={}, subject={}, body={}", toEmail, subject, body);
	}
}
