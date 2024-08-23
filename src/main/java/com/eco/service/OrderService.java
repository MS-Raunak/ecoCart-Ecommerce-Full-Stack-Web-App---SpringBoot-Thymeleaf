package com.eco.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.eco.model.OrderRequest;
import com.eco.model.ProductOrder;



public interface OrderService {

	public void saveOrder(Integer userid,OrderRequest orderRequest) throws Exception;
	
	public List<ProductOrder> getOrdersByUser(Integer userId);
	
	public ProductOrder updateOrderStatus(Integer id,String status);

	public List<ProductOrder> getAllOrders();

	public ProductOrder getOrdersByOrderId(String trim);
	
	//Pagination:
	public Page<ProductOrder> getAllOrdersPagination(Integer pageNo,Integer pageSize);

}
