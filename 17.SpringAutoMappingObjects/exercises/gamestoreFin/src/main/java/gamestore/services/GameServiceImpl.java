package gamestore.services;

import gamestore.domain.dto.GameAddDto;
import gamestore.domain.dto.GameEditDto;
import gamestore.domain.dto.GameViewDetailsDto;
import gamestore.domain.entities.Game;
import gamestore.domain.entities.Order;
import gamestore.domain.entities.User;
import gamestore.repositories.GameRepository;
import gamestore.repositories.OrderRepository;
import gamestore.repositories.UserRepository;
import gamestore.util.ConstraintViolationsExtractor;
import gamestore.util.TextConstants;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final Validator validator;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    @Override
    public String addGameToCatalog(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> constraintViolations = this.validator.validate(gameAddDto);
        if (constraintViolations.isEmpty()) {
            Game existingGame = this.gameRepository.getByTitle(gameAddDto.getTitle()).orElse(null);
            if (existingGame == null) {
                Game game = this.modelMapper.map(gameAddDto, Game.class);
                this.gameRepository.saveAndFlush(game);
                return String.format("Added %s", gameAddDto.getTitle());
            } else {
                return TextConstants.EXISTING_GAME_MESSAGE;
            }
        }
        String violationsMessages = ConstraintViolationsExtractor
                .getConstraintViolationsMessages(constraintViolations);
        return violationsMessages;
    }

    @Override
    public String editGame(String id, String[] valuesToEdit) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if (game == null) {
            return TextConstants.NOT_EXISTING_GAME_MESSAGE;
        }
        GameEditDto gameEditDto = new GameEditDto();
        this.fillEditGameDto(gameEditDto, valuesToEdit);
        Set<ConstraintViolation<GameEditDto>> constraintViolations = this.validator.validate(gameEditDto);
        String oldName=game.getTitle(); //in case of edited title
        if(constraintViolations.isEmpty()){
            this.modelMapper.map(gameEditDto,game);
            this.gameRepository.save(game);
            return String.format("Edited %s",oldName);

        }else {
            String messages = ConstraintViolationsExtractor.getConstraintViolationsMessages(constraintViolations);
            return messages;
        }
    }

    @Override
    public String getAllGames() {
        List<Game> allGames = this.gameRepository.findAll();
        if(allGames.isEmpty()){
            return "There are no games into the database!";
        }
        StringBuilder res=new StringBuilder();
        this.gameRepository.findAll()
                .stream()
                .map(g -> String.format("%s %.2f", g.getTitle(), g.getPrice()))
                .forEach(x->res.append(x).append(System.lineSeparator()));
        return res.toString().trim();
    }

    @Override
    public GameViewDetailsDto getGameDetails(String title) {
        Game game = this.gameRepository.getByTitle(title).orElse(null);
        if(game==null){
            return null;
        }
        GameViewDetailsDto detailGame = this.modelMapper.map(game, GameViewDetailsDto.class);
        return detailGame;
    }

    @Transactional()
    @Override
    public String deleteGame(String id) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if(game==null){
            return TextConstants.NOT_EXISTING_GAME_MESSAGE;
        }
        Set<User> allUsersContainingGame = this.userRepository.getAllContainingGame(game);
        for (User user : allUsersContainingGame) {
            user.getGames().remove(game);
            this.userRepository.saveAndFlush(user);
        }
        Set<Order> allOrdersContainingGame = this.orderRepository.getAllContainingGame(game);
        for (Order order : allOrdersContainingGame) {
            order.getProducts().remove(game);
            this.orderRepository.saveAndFlush(order);
        }
        this.gameRepository.delete(game);
        return String.format("Deleted %s",game.getTitle());
    }

    @Override
    public boolean checkIfGameExists(String title) {
        Game game = this.gameRepository.getByTitle(title).orElse(null);
        return game!=null;
    }

    private void fillEditGameDto(GameEditDto gameEditDto, String[] valuesToEdit) {

        for (String value : valuesToEdit) {
            String[] tokens = value.split("=");
            switch (tokens[0]){
                case "title":{
                    gameEditDto.setTitle(tokens[1]);
                }break;
                case "price":{
                    gameEditDto.setPrice(new BigDecimal(tokens[1]));
                }break;
                case "size":{
                    gameEditDto.setSize(Double.parseDouble(tokens[1]));
                }break;
                case "trailer":{
                    gameEditDto.setTrailer(tokens[1]);
                }break;
                case "description":{
                    gameEditDto.setDescription(tokens[1]);
                }break;
                case "thumbnailUrl":{
                    gameEditDto.setThumbnailUrl(tokens[1]);
                }break;
                case "releaseDate":{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                    gameEditDto.setReleaseDate(LocalDate.parse(tokens[1],formatter));
                }break;
            }
        }
    }
}
