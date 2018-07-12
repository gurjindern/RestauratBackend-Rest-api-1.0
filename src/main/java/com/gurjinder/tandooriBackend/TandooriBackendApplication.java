package com.gurjinder.tandooriBackend;

import com.gurjinder.tandooriBackend.DAOs.FoodItemAndCategoryDao;
import com.gurjinder.tandooriBackend.model.FoodCategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TandooriBackendApplication {


	public static void main(String[] args) {

		//ApplicationContext context=
		SpringApplication.run(TandooriBackendApplication.class, args);
		//List<FoodCategory> foodCategories=(context.getBean(FoodItemAndCategoryDao.class).getAllCategories());
		//for(FoodCategory foodCategory : foodCategories){
		//	System.out.println(foodCategory.toString() +"Hi");
		//}

		//context.getBean(FoodItemAndCategoryDao.class).test();

	}
}
