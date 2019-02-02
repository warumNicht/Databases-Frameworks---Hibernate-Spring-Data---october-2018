package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.shampoos.BasicShampoo;
import app.services.IngredientService;
import app.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private ShampooService shampooService;
    private IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        List<BasicShampoo> allBySizeOrderById = this.shampooService.
//                getBasicShampooRepository().getAllBySizeOrderById(Size.MEDIUM);
//        List<BasicShampoo> allBySizeOrderById = this.shampooService.
//               getBasicShampooRepository().getAllBySizeOrLabel_IdOrderByPriceAsc(Size.MEDIUM,10);
//
//        List<BasicShampoo> allBySizeOrderById = this.shampooService.
//                getBasicShampooRepository().getAllByPriceIsGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5));
//
//
//        for (BasicShampoo basicShampoo : allBySizeOrderById) {
//            System.out.printf("%s %s %.2flv.%n",basicShampoo.getBrand(),
//                    basicShampoo.getSize().name(),basicShampoo.getPrice());
//        }

//        List<BasicIngredient> ingredients = this.ingredientService.getIngredientRepository().getAllByNameStartingWith("M");
//        for (BasicIngredient ingredient : ingredients) {
//            System.out.println(ingredient.getName());
//        }
//
        List<BasicIngredient> ingredients = this.ingredientService.getIngredientRepository()
                .getAllByNameInOrderByPrice(Arrays.asList("Lavender"));
//        for (BasicIngredient resultIngredient : resultIngredients) {
//            System.out.println(resultIngredient.getName());
//        }
//        int count = this.shampooService.getBasicShampooRepository().countByPriceIsLessThan(BigDecimal.valueOf(8.50));
//        System.out.println(count);

//        Set<BasicIngredient> ingredients =new HashSet<>(this.ingredientService.getIngredientRepository()
//                .getAllByNameInOrderByPrice(Arrays.asList("Berry", "Mineral-Colagen")));

        Set<BasicIngredient> ingredientsSet=new HashSet<BasicIngredient>(ingredients);
        Set<BasicShampoo> allByIngredientsIn = this.shampooService.getBasicShampooRepository()
                .getAllByIngredientsIn(ingredientsSet);

        for (BasicShampoo basicShampoo : allByIngredientsIn) {
            System.out.println(basicShampoo.getId()+" "+basicShampoo.getBrand());
        }
//        List<BasicShampoo> allByIngredientsSize = this.shampooService.getBasicShampooRepository().getAllByIngredientsSize(2);
//        for (BasicShampoo basicShampoo : allByIngredientsSize) {
//            System.out.println(basicShampoo.getBrand());
//        }
//        double totalPrice = this.shampooService.getBasicShampooRepository()
//                .getSumOfIngredientsByBrand("Fresh it up!");
//        System.out.println(totalPrice);

//        this.ingredientService.getIngredientRepository().deleteIngredientByName("Berry");
 //       this.ingredientService.getIngredientRepository().updateIngredientsPrices();
 //       this.ingredientService.getIngredientRepository().updateIngredientsPricesInList(Arrays.asList("Mineral-Collagen","Aloe Vera"));

    }
}
