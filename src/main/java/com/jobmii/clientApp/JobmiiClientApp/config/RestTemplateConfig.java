package com.jobmii.clientApp.JobmiiClientApp.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import com.jobmii.clientApp.util.RequestInterceptor;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		// return new RestTemplate();
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors =
		restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
		interceptors = new ArrayList<>();
		}
		interceptors.add(new RequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}