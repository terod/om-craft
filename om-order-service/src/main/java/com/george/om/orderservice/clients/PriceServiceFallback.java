package com.george.om.orderservice.clients;

import com.george.om.orderservice.dto.ProductPriceDto;
import org.springframework.stereotype.Component;

@Component
public class PriceServiceFallback implements PriceServiceClient {

	@Override
	public ProductPriceDto getPriceByProductId(Long productId) {
		return null;
	}

}
