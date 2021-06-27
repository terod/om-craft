package com.george.om.orderservice.clients;

import com.george.om.orderservice.config.FeignConfig;
import com.george.om.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "om-product-service", configuration = FeignConfig.class)
public interface ProductServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
	public ProductDto getProductById(@PathVariable("productId") Long id);

}
