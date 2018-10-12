package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.OrderDao;
import com.gurjinder.tandooriBackend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    public Order submitOrder(int customerId, Order order){

        order.setTimeSubmitted(System.currentTimeMillis() / 1000L);
        return orderDao.submitOrder(customerId, order);

    }


}
