package gamestore.web.controllers;

import gamestore.domain.dto.*;
import gamestore.services.GameService;
import gamestore.services.UserService;
import gamestore.util.TextConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Controller
public class GameStoreController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private String loggedInUser;
    private Set<String> shoppingCart;

    @Autowired
    public GameStoreController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCart = new HashSet<>();
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputLine = scanner.nextLine();
            String[] params = inputLine.split("\\|");
            switch (params[0]) {
                case "RegisterUser": {
                    UserRegisterDto userRegisterDto = new UserRegisterDto(params[1],
                            params[2], params[3], params[4]);
                    System.out.println(this.userService.registerUser(userRegisterDto));
                }
                break;
                case "LoginUser": {
                    if (this.loggedInUser == null) {
                        UserLoginDto userLoginDto = new UserLoginDto(params[1], params[2]);
                        String result = this.userService.loginUser(userLoginDto);
                        if (result.contains("Successfully logged in ")) {
                            this.loggedInUser = userLoginDto.getEmail();
                        }
                        System.out.println(result);

                    } else {
                        System.out.println("Session is taken!");
                    }
                }
                break;
                case "LogoutUser": {
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.CANNOT_LOGOUT_MESSAGE);
                    } else {
                        String logoutResult = this.userService.logoutUser(new UserLogoutDto(this.loggedInUser));
                        this.loggedInUser = null;
                        this.shoppingCart.clear();
                        System.out.println(logoutResult);
                    }
                }
                break;
                case "AddGame": {
                    if (params.length != 8) {
                        System.out.println("Parameters length don't match!");
                        break;
                    }
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.NOT_LOGGED_IN_MESSAGE);
                    } else if (this.isAdmin()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                        GameAddDto gameToAdd = new GameAddDto(params[1],
                                new BigDecimal(params[2]), Double.parseDouble(params[3]), params[4],
                                params[5], params[6], LocalDate.parse(params[7], formatter));
                        String result = this.gameService.addGameToCatalog(gameToAdd);
                        System.out.println(result);
                    }
                }
                break;
                case "EditGame": {
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.NOT_LOGGED_IN_MESSAGE);
                    } else if (this.isAdmin()) {
                        String id = params[1];
                        String[] valuesToEdit = Arrays.stream(params).skip(2).toArray(String[]::new);
                        String result = this.gameService.editGame(id, valuesToEdit);
                        System.out.println(result);
                    }
                }
                break;
                case "AllGame": {
                    String result = this.gameService.getAllGames();
                    System.out.println(result);
                }
                break;
                case "OwnedGame": {
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.NOT_LOGGED_IN_MESSAGE);
                    } else {
                        String result = this.userService.getAllGamesForLoggedUser(this.loggedInUser);
                        System.out.println(result);
                    }
                }
                break;
                case "DetailGame": {
                    GameViewDetailsDto gameDetails = this.gameService.getGameDetails(params[1]);
                    if (gameDetails == null) {
                        System.out.println(TextConstants.NOT_EXISTING_GAME_MESSAGE);
                        break;
                    }
                    StringBuilder res = new StringBuilder();
                    res.append(String.format("Title: %s", gameDetails.getTitle()))
                            .append(System.lineSeparator());
                    res.append(String.format("Price: %.2f", gameDetails.getPrice()))
                            .append(System.lineSeparator());
                    res.append(String.format("Description: %s", gameDetails.getDescription()))
                            .append(System.lineSeparator());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                    res.append(String.format("Release date: %s", formatter.format(gameDetails.getReleaseDate())));
                    System.out.println(res.toString());
                }
                break;
                case "AddItem": {
                    String title = params[1];
                    String email = this.loggedInUser;
                    if (this.shoppingCart.contains(title)) {
                        System.out.println(String.format("%s is already added to cart.", title));
                        break;
                    }
                    if(!this.gameService.checkIfGameExists(title)){
                        System.out.println(TextConstants.NOT_EXISTING_GAME_MESSAGE);
                        break;
                    }
                    if (this.userService.checkIfTheUserAlreadyHasTheGame(title, email)) {
                        System.out.println(String.format("The user already has bought %s", title));
                        break;
                    }

                    this.shoppingCart.add(title);
                    System.out.println(String.format("%s added to cart.", title));

                }
                break;
                case "RemoveItem": {
                    String title = params[1];
                    if (this.shoppingCart.contains(title)) {
                        this.shoppingCart.remove(title);
                        System.out.println(String.format("%s removed from cart.", title));
                    } else {
                        System.out.println(String.format("%s does not exists into the cart!", title));
                    }
                }
                break;
                case "BuyItem": {
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.NOT_LOGGED_IN_MESSAGE);
                    } else if (this.shoppingCart.isEmpty()) {
                        System.out.println("The cart is empty!");
                    } else {
                        String res = this.userService.buyGames(this.loggedInUser, this.shoppingCart);
                        System.out.println(res);
                        this.shoppingCart.clear();
                    }
                }
                break;
                case "DeleteGame": {
                    if (this.loggedInUser == null) {
                        System.out.println(TextConstants.NOT_LOGGED_IN_MESSAGE);
                    } else if (this.isAdmin()) {
                        String id = params[1];
                        String result = this.gameService.deleteGame(id);
                        System.out.println(result);
                    }
                }
                break;
            }
        }
    }

    private boolean isAdmin() {
        if (!this.userService.isAdmin(this.loggedInUser)) {
            System.out.println(TextConstants.NOT_ADMINISTRATOR_MESSAGE);
            return false;
        }
        return true;
    }

}
