package gamestore.services;

import gamestore.domain.dto.UserLoginDto;
import gamestore.domain.dto.UserLogoutDto;
import gamestore.domain.dto.UserRegisterDto;
import gamestore.domain.entities.Game;
import gamestore.domain.entities.Order;
import gamestore.domain.entities.Role;
import gamestore.domain.entities.User;
import gamestore.repositories.GameRepository;
import gamestore.repositories.UserRepository;
import gamestore.util.ConstraintViolationsExtractor;
import gamestore.util.TextConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final Validator validator;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append(TextConstants.NOT_MATCHING_PASSWORD_MESSAGE).append(System.lineSeparator());
        }

        Set<ConstraintViolation<UserRegisterDto>> constraintViolations = this.validator.validate(userRegisterDto);
        if (!constraintViolations.isEmpty()) {
            String messages = ConstraintViolationsExtractor.getConstraintViolationsMessages(constraintViolations);
            sb.append(messages).append(System.lineSeparator());
        }
        User user1 = this.userRepository.findByEmail(userRegisterDto.getEmail()).orElse(null);
        if (user1 != null) {
            sb.append(System.lineSeparator())
                    .append(TextConstants.EXISTING_USER_MESSAGE).toString().trim();
        }
        if(!sb.toString().equals("")){
            return sb.toString().trim();
        }

        User user = this.modelMapper.map(userRegisterDto, User.class);
        if (this.userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        this.userRepository.saveAndFlush(user);
        return String.format("%s was registered", userRegisterDto.getFullName());
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        Set<ConstraintViolation<UserLoginDto>> constraintViolations = this.validator.validate(userLoginDto);
        if (!constraintViolations.isEmpty()) {
            return ConstraintViolationsExtractor
                    .getConstraintViolationsMessages(constraintViolations);
        }
        User user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);
        if (user == null) {
            return TextConstants.NOT_EXISTING_USER_MESSAGE;
        }
        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            return TextConstants.NOT_MATCHING_PASSWORD_MESSAGE;
        }
        return String.format("Successfully logged in %s", user.getFullName());
    }

    @Override
    public String logoutUser(UserLogoutDto userLogoutDto) {
        User user = this.userRepository.findByEmail(userLogoutDto.getEmail()).orElse(null);
        if (user != null) {
            return String.format("User %s successfully logged out", user.getFullName());
        }
        return TextConstants.NOT_EXISTING_USER_MESSAGE;
    }

    @Override
    public boolean isAdmin(String email) {
        User user = this.userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return user.getRole() == Role.ADMIN;
        }
        return false;
    }

    @Override
    public boolean checkIfTheUserAlreadyHasTheGame(String title, String email) {
        return this.userRepository.hasUserGame(title, email);
    }

    //   @Transactional(rollbackFor = Exception.class)
    @Override
    public String buyGames(String loggedUserEmail, Set<String> gameTitles) {
        Set<Game> allByTitle = this.gameRepository.findAllByTitleIn(gameTitles);
        User user = this.userRepository.findByEmail(loggedUserEmail).orElse(null);
        if (user == null) {
            return TextConstants.NOT_EXISTING_USER_MESSAGE;
        }
        Order order = new Order();
        order.setProducts(allByTitle);
        order.setBuyer(user);

        user.getOrders().add(order);

        for (Game game : allByTitle) {
            game.getOrders().add(order);
        }
        user.getGames().addAll(allByTitle);
        this.userRepository.save(user);

        StringBuilder res = new StringBuilder();
        res.append("Successfully bought games:").append(System.lineSeparator());
        gameTitles.forEach(t -> {
            res.append(String.format("- %s", t)).append(System.lineSeparator());
        });
        return res.toString().trim();
    }

    @Override
    public String getAllGamesForLoggedUser(String email) {
        User user = this.userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return TextConstants.NOT_EXISTING_USER_MESSAGE;
        }
        StringBuilder res = new StringBuilder("Bought games:").append(System.lineSeparator());
        if (user.getGames().isEmpty()) {
            res.append("No games");
        } else {
            user.getGames()
                    .stream()
                    .map(g -> g.getTitle())
                    .forEach(t -> res.append(t).append(System.lineSeparator()));
        }
        return res.toString().trim();
    }
}
