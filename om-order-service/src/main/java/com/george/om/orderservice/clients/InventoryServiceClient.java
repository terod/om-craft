/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.clients;


import com.george.om.orderservice.dto.ProductInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "om-inventory-service")
public interface InventoryServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventories/{productId}")
	public ProductInventoryDto getInventoryByProductId(@PathVariable("productId") Long productId);

}
