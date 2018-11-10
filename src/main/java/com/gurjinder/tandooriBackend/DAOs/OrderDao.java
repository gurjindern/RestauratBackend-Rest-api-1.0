package com.gurjinder.tandooriBackend.DAOs;

import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class OrderDao {

    @Autowired
    JdbcTemplate template;

    private Object myLock;


    public OrderDao() {

        myLock = new Object();
    }

    public OrderDao(JdbcTemplate template) {
        this.template = template;
    }

    public Order submitOrder(int cutomerId, Order order) {
        String insertOrder = "insert into orders(id,time_submitted,customer_id) values(?,?,?)";
        String insertItemsInOrder = "insert into items_in_order(id,order_id,item_id) values (items_in_order_seq.nextVal,?,?)";
        String getInseredOrder = "select *  from orders where id=(select max(id) from orders";

        synchronized (myLock) {
            int lastOrderId;
            try {
                lastOrderId = (int) template.queryForObject("select max(id) from orders", Integer.class);
            } catch (EmptyResultDataAccessException e) {
                lastOrderId = 0;
            } catch (NullPointerException e) {
                lastOrderId = 0;
            }
            order.setId(lastOrderId + 1);
            template.update(insertOrder, new Object[]{order.getId(), order.getTimeSubmitted(), cutomerId});
            for (FoodItem foodItem : order.getFoodItems()) {
                template.update(insertItemsInOrder, new Object[]{order.getId(), foodItem.getId()});
            }


        }
        return order;

    }

    public List<Order> fetchOrderByCustomer(int customerId) {
        List<Order> orders;
        String query = "Select  * from orders where customer_id=?";
        orders = template.query(query, new Object[]{customerId}, new BeanPropertyRowMapper<>(Order.class));
        return orders;
    }

}
