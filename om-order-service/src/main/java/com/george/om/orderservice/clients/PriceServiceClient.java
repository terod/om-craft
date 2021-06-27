package com.george.om.orderservice.clients;

import com.george.om.orderservice.dto.ProductPriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "om-cpq-service")
public interface PriceServiceClient {

	@GetMapping(value = "/prices/{productId}")
	public ProductPriceDto getPriceByProductId(@PathVariable("productId") Long productId);

}
