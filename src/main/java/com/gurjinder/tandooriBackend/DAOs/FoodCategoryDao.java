package com.gurjinder.tandooriBackend.DAOs;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.StringTokenizer;

@Repository
public class FoodCategoryDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<FoodCategory> getAllCategories() {
        return jdbcTemplate.query("select * from categories",
                new BeanPropertyRowMapper<FoodCategory>(FoodCategory.class));
    }

    public List<FoodItem> getItemsInCategory(int categoryId) {
        String query = "select * from food_items ,item_categories where  ITEM_CATEGORIES.food_item_id = FOOD_ITEMS.id and ITEM_CATEGORIES.category_id=? ";
        return jdbcTemplate.query(query, new Object[]{categoryId}, new BeanPropertyRowMapper<>(FoodItem.class));

    }


//**************************************** Admin Specific  *******************************************************


    public FoodCategory addFoodCategory(FoodCategory category) {
        int maxCategoryId;
        try {
            maxCategoryId = jdbcTemplate.queryForObject("select max(id) from categories", Integer.class);

        } catch (EmptyResultDataAccessException e) {
            maxCategoryId = 0;

        } catch (NullPointerException e) {
            maxCategoryId = 0;
        }
        category.setId(maxCategoryId + 1);
        String insertStatement = "insert into categories(id,name,description) values(?,?,?)";

        jdbcTemplate.update(insertStatement, new Object[]{category.getId(), category.getName(), category.getDescription()});

        return category;

    }
    public void changeCategoryName(int id,String name){
        String stmt = "update categories set name=? where id=?";
        jdbcTemplate.update(stmt, name, id);
    }


    @Transactional
    public void deleteFoodCategory(int categoryId) {
        String stmt1 = "delete from categories where id =?";
        String stmt2 = "delete from item_categories where category_id=?";
        jdbcTemplate.update(stmt1, categoryId);
        jdbcTemplate.update(stmt2, categoryId);
    }

    public void addItemInCategoy(int categoryId, int foodItemId) {
        String stmt = "insert into item_categories(category_id,food_item_id) values(?,?)";
        try {
            jdbcTemplate.update(stmt, categoryId, foodItemId);
        } catch (DataIntegrityViolationException e) {
            StringTokenizer tokenizer=new StringTokenizer(e.getCause().toString(),":");
            while(tokenizer.hasMoreTokens()){
                String token=tokenizer.nextToken().trim();
                if(token.equalsIgnoreCase("ORA-02291"))
                    System.err.println("cause of failure ::::"+ token

                    );
            }
        }
    }


    public void removeItemFromCategoy(int categoryId, int foodItemId) {
        String stmt = "delete from item_categories where category_id=? and food_item_Id=?";

        jdbcTemplate.update(stmt, categoryId, foodItemId);


    }


}