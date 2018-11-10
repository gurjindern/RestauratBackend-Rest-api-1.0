package com.gurjinder.tandooriBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TandooriBackendApplication {


	public static void main(String[] args) {

		//ApplicationContext context=
		SpringApplication.run(TandooriBackendApplication.class, args);
		//List<FoodCategory> foodCategories=(context.getBean(FoodItemDao.class).getAllCategories());
		//for(FoodCategory foodCategory : foodCategories){
		//	System.out.println(foodCategory.toString() +"Hi");
		//}

		//context.getBean(FoodItemDao.class).test();

	}
}
