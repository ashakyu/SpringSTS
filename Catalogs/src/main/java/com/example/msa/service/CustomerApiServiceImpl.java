package com.example.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CustomerApiServiceImpl implements CustomerApiService{

	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getCustomerDetailFallback")
	public String getCustomerDetail(String customerId) {
			//restTemplate = Client(Web browser가 아님) 
										//직접 요청은 loadbalancing이 아니다.(분산X), restTemplate이 분산해야한다.
												//서버 리스트를 적는다.(어플리케이션 설정에 등록)
		return restTemplate.getForObject("http://customer/customers/" + customerId, String.class);
	}
	
	public String getCustomerDetailFallback(String customerId, Throwable ex) {
		System.out.println("Error:"+ex.getMessage());
		return "고객 정보 조회가 지연되고 있습니다.";
	}

}
