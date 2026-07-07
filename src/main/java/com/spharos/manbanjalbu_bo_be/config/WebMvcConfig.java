package com.spharos.manbanjalbu_bo_be.config;

import com.spharos.manbanjalbu_bo_be.global.storage.UploadProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final UploadProperties uploadProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadPath = Path.of(uploadProperties.getBaseDir()).toAbsolutePath().normalize();
		String location = uploadPath.toUri().toString();
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations(location.endsWith("/") ? location : location + "/");
	}
}
