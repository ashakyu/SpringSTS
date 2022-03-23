package com.example.msa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //Server쪽의 설정을 갱신할 수 있게 도와주는 어노테이션
public class ConfigClientController {

	@Value("${config.profile}")
	private String profile;
	@Value("${config.message}")
	private String message;
	
	@GetMapping("/config/profile")
	public String profile() {
		return profile;
	}
	@GetMapping("/config/message")
	public String message() {
		return message;
	}
}
