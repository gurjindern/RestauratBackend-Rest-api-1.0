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

    private Object odereSubmissionLock;


    public OrderDao() {

        odereSubmissionLock = new Object();
    }

    public OrderDao(JdbcTemplate template) {
        this.template = template;
    }
    @Transactional
    public Order submitOrder(int cutomerId, Order order) {
        String insertOrder = "insert into orders(id,time_submitted,customer_id) values(?,?,?)";
        String insertItemsInOrder = "insert into items_in_order(id,order_id,item_id) values (items_in_order_seq.nextVal,?,?)";
        String getInsertedOrder = "select *  from orders where id=(select max(id) from orders";

        synchronized (odereSubmissionLock) {
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
            }

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
        String query = "Select  * from orders where customer_id=?";
        orders = template.query(query, new Object[]{customerId}, new BeanPropertyRowMapper<>(Order.class));
        return orders;
    }

}
