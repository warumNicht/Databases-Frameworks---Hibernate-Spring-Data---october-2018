package judge.services.inretfaces;

import java.io.IOException;

public interface StrategyService {
    String importStrategies(String strategyFilePath) throws IOException;
}
