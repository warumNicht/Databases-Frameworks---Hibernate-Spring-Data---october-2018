package gamestore.services;

import gamestore.domain.dto.GameAddDto;
import gamestore.domain.dto.GameViewDetailsDto;

public interface GameService {
    String addGameToCatalog(GameAddDto gameAddDto);

    String editGame(String id, String[] valuesToEdit);

    String getAllGames();

    GameViewDetailsDto getGameDetails(String param);

    String deleteGame(String id);

    boolean checkIfGameExists(String title);
}
