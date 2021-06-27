package com.george.om.orderservice.clients;

import com.george.om.orderservice.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallback implements ProductServiceClient {

	@Override
	public ProductDto getProductById(Long id) {
		return null;
	}

}
