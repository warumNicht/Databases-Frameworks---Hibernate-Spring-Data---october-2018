package judge.web.controllers;

import judge.services.inretfaces.CategoryService;
import judge.services.inretfaces.ContestService;
import judge.services.inretfaces.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HomeController implements CommandLineRunner {
    private final static String CATEGORY_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\categories.json";
    private final static String STRATEGIES_FILE_PATH = "src\\main\\resources\\files\\strategies.json";
    private final static String CONTESTS_FILE_PATH = "src\\main\\resources\\files\\contests.json";

    private CategoryService categoryService;
    private StrategyService strategyService;
    private ContestService contestService;

    @Autowired
    public HomeController(CategoryService categoryService, StrategyService strategyService, ContestService contestService) {
        this.categoryService = categoryService;
        this.strategyService = strategyService;

        this.contestService = contestService;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.importCategories();
        //this.importStrategies();
        this.importContests();
    }

    private void importCategories() throws IOException {
        System.out.println(this.categoryService.importCategories(CATEGORY_FILE_PATH));
    }

    private void importStrategies() throws IOException {
        System.out.println(this.strategyService.importStrategies(STRATEGIES_FILE_PATH));
    }
    private void importContests() throws IOException {
        System.out.println(this.contestService.importContests(CONTESTS_FILE_PATH));
    }
}
