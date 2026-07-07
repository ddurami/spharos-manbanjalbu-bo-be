package com.spharos.manbanjalbu_bo_be.global.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.upload")
public class UploadProperties {

	private String baseDir = "uploads";
	private String publicUrlPrefix = "http://localhost:8081/uploads";
}
