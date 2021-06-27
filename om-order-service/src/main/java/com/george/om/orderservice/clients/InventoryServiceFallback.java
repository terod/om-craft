/*
 * Author: George Ebbinason
 */

package com.george.om.orderservice.clients;

import com.george.om.orderservice.dto.ProductInventoryDto;
import org.springframework.stereotype.Component;

@Component
public class InventoryServiceFallback implements InventoryServiceClient {

	@Override
	public ProductInventoryDto getInventoryByProductId(Long productId) {
		return null;
	}

}
