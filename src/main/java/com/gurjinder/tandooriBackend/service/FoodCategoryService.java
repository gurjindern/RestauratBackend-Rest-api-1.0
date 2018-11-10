package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.FoodCategoryDao;
import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FoodCategoryService {

    @Autowired
    FoodCategoryDao dao;



    public ResultResponse<List<FoodCategory>> getFoodCategories() {

        return new ResultResponse<>("success", new Date(), dao.getAllCategories());
    }
    public ResultResponse<List<FoodItem>> getItemsInCategory(int categoryId){
        return new ResultResponse<>("sucess",new Date(),dao.getItemsInCategory(categoryId));
    }



// ******************************** Admin Specific ****************************************************


    public ResultResponse<FoodCategory> addFoodCatgory(FoodCategory category) {
        return new ResultResponse<>("created", new Date(), dao.addFoodCategory(category));
    }

    public ResultResponse deleteFoodCategory(int categoryId) {

        dao.deleteFoodCategory(categoryId);
        return new ResultResponse("deleted", new Date());
    }

    public ResultResponse changeItemName(int id,String name) {

        dao.changeCategoryName(id,name);
        return new ResultResponse("success", new Date());
    }

    public ResultResponse addItemInCategory(int categoryId, int itemId) {

        dao.addItemInCategoy(categoryId, itemId);
        return new ResultResponse("success", new Date());
    }


    public ResultResponse removeItemFromCategory(int categoryId,int itemId) {
        dao.removeItemFromCategoy(categoryId, itemId);
        return new ResultResponse("success", new Date());
    }







}
