package com.gurjinder.tandooriBackend.DAOs;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.model.FoodItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class
FoodItemDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<FoodItem> getAllFoodIItems() {

        return jdbcTemplate.query("select * from FOOD_ITEMS ft left join ITEM_CATEGORIES  ic on  ft.ID=ic.FOOD_ITEM_ID order by ft.id asc",
                new ResultSetExtractor<List<FoodItem>>() {
                    @Override
                    public List<FoodItem> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<FoodItem> foodItems = new ArrayList<>();
                        FoodItem foodItem = null;

                        while (rs.next()) {
                            if (foodItem == null) {

                                foodItem = new FoodItem();

                            }
                            if (foodItem.getId() != rs.getInt("id")) {
                                int itemId = rs.getInt("id");
                                String itemName = rs.getString("name");
                                Double price = rs.getDouble("price");
                                String description = rs.getString("description");
                                int availability = rs.getInt("availability");
                                int categoryId = rs.getInt("category_id");

                                foodItem.setId(itemId);
                                foodItem.setName(itemName);
                                foodItem.setPrice(price);
                                foodItem.setDescription(description);

                                if(categoryId!=0){
                                    foodItem.setCategoryIds(new ArrayList<>());
                                    foodItem.getCategoryIds().add(categoryId);}
                                if (availability == 0) {
                                    foodItem.setAvailabily(false);
                                } else {
                                    foodItem.setAvailabily(true);
                                }
                                try {
                                    foodItems.add((FoodItem) foodItem.clone());
                                } catch (CloneNotSupportedException c) {

                                }
                            } else if (foodItem.getId() == rs.getInt("id")) {
                                foodItem.getCategoryIds().add(rs.getInt("category_id"));
                            }


                        }
                        return foodItems;
                    }
                });

    }

    public FoodItem getFoodItemById(int id){
        String query="select * from FOOD_ITEMS where id=?";
        return (FoodItem) jdbcTemplate.queryForObject(query,new Object[]{id},
               new FoodItemRowMapper());
       // return (FoodItem) jdbcTemplate.queryForObject(query,new Object[]{id},
         //       new BeanPropertyRowMapper<>(FoodItem.class));
    }







    // admin specific


    @Transactional
    public FoodItem addFoodItem(FoodItem foodItem) {

        int maxItemId;
        try {
            maxItemId = jdbcTemplate.queryForObject("select max(id) from food_items", Integer.class);

        } catch (EmptyResultDataAccessException e) {
            maxItemId = 0;

        } catch (NullPointerException e) {
            maxItemId = 0;
        }
        foodItem.setId(maxItemId + 1);
        String insertStatement = "insert into food_Items(id,name,price,description) values(?,?,?,?)";
        jdbcTemplate.update(insertStatement, new Object[]{foodItem.getId(), foodItem.getName(),
                foodItem.getPrice(), foodItem.getDescription()});
        insertStatement = "insert into item_categories(food_item_id,category_id) values(?,?)";
       try{
        jdbcTemplate.batchUpdate(insertStatement, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, foodItem.getId());
                ps.setInt(2, foodItem.getCategoryIds().get(i));
            }

            @Override
            public int getBatchSize() {
                return foodItem.getCategoryIds().size();
            }
        });}
        catch(NullPointerException e){

        }
        foodItem.setAvailabily(true);
        return foodItem;
    }

    @Transactional
    public void deleteFoddItem(int itemId) {
        String stmt1 = "delete from food_items where id =?";
        String stmt2 = "delete from item_categories where food_item_id=?";
        jdbcTemplate.update(stmt1, itemId);
        jdbcTemplate.update(stmt2, itemId);
    }




    public void toggleAvailabilty(int itemId, int availibility) {

        String makeUnavailableStmt = "update food_Items set availability=0 where id=?";
        String makeAvailableStmt = "update food_Items set availability=1 where id=?";
        if (availibility == 0)
            jdbcTemplate.update(makeUnavailableStmt, itemId);
        else
            jdbcTemplate.update(makeAvailableStmt, itemId);

    }

    public void changePrice(int itemId, double price) {

        String stmt = "update food_Items set price=? where id=?";

        jdbcTemplate.update(stmt, price, itemId);

    }

    public void changeName(int itemId, String name) {

        String stmt = "update food_Items set name=? where id=?";

        jdbcTemplate.update(stmt, name, itemId);

    }

    public void changeDescription(int itemId, String description) {

        String stmt = "update food_Items set description=? where id=?";

        jdbcTemplate.update(stmt, description, itemId);

    }
}
