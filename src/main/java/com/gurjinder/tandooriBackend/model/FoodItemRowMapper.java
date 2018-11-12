package com.gurjinder.tandooriBackend.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodItemRowMapper implements RowMapper<FoodItem> {
    @Override
    public FoodItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        FoodItem item=new FoodItem();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getDouble("price"));
        item.setDescription(rs.getString("description"));
        if(rs.getInt("availability")==1)
            item.setAvailabily(true);
        return item;
    }
}
