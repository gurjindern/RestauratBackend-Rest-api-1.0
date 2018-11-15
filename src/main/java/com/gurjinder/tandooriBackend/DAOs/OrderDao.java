package com.gurjinder.tandooriBackend.DAOs;

import com.gurjinder.tandooriBackend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public class OrderDao {

    @Autowired
    JdbcTemplate template;


    @Transactional
    public Order submitOrder(int cutomerId, Order order) {
        String insertOrder = "insert into orders(id,time_submitted,customer_id) values(?,?,?)";
        String insertItemsInOrder = "insert into items_in_order(id,order_id,item_id) values (items_in_order_seq.nextVal,?,?)";
        String getNextIdStatement = "select orders_seq.nextVal from dual";

        int newOrderId=(int)template.queryForObject(getNextIdStatement,Integer.class);
            order.setId(newOrderId);
            template.update(insertOrder, new Object[]{order.getId(), order.getTimeSubmitted(), cutomerId});


            template.batchUpdate(insertItemsInOrder, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1,order.getId());
                    ps.setInt(2,order.getFoodItems().get(i).getId());
                }

                @Override
                public int getBatchSize() {
                    return  order.getFoodItems().size();
                }
            });

        return order;

    }

    public List<Order> fetchOrderByCustomer(int customerId) {
        List<Order> orders;
        String query = "Select  * from orders where customer_id=? order by id desc";
        orders = template.query(query, new Object[]{customerId}, new BeanPropertyRowMapper<>(Order.class));
        return orders;
    }

}
