package gamestore.services;

import gamestore.domain.dto.UserLoginDto;
import gamestore.domain.dto.UserLogoutDto;
import gamestore.domain.dto.UserRegisterDto;

import java.util.Set;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser(UserLogoutDto userLogoutDto);

    boolean isAdmin(String email);

    boolean checkIfTheUserAlreadyHasTheGame(String title, String email);

    String buyGames(String loggedUserEmail, Set<String> gameTitles);

    String getAllGamesForLoggedUser(String email);
}
