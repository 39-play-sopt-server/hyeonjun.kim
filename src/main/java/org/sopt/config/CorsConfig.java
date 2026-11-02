package org.sopt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins(
				"http://hyeonjun-sopt.o-r.kr",
				"https://hyeonjun-sopt.o-r.kr"
			)
			.allowedMethods("*")
			.allowedHeaders("Authorization", "Content-Type")
			.maxAge(3600);
	}
}
