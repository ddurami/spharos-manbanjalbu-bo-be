package com.spharos.manbanjalbu_bo_be.global.mail;

import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MailService {

	private final JavaMailSender mailSender;
	private final String from;
	private final String fromName;

	public MailService(
			JavaMailSender mailSender,
			@Value("${app.mail.from}") String from,
			@Value("${app.mail.from-name}") String fromName
	) {
		this.mailSender = mailSender;
		this.from = from;
		this.fromName = fromName;
	}

	public void send(String to, String subject, String body) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false, StandardCharsets.UTF_8.name());
			helper.setFrom(buildFromAddress());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, false);
			mailSender.send(message);
			log.info("메일 발송 완료 (to={}, subject={})", to, subject);
		} catch (Exception e) {
			log.error("메일 발송 실패 (to={}, subject={})", to, subject, e);
			throw new BusinessException(ErrorCode.EMAIL_SEND_FAILED);
		}
	}

	private InternetAddress buildFromAddress() throws UnsupportedEncodingException {
		return new InternetAddress(from, fromName, StandardCharsets.UTF_8.name());
	}
}
