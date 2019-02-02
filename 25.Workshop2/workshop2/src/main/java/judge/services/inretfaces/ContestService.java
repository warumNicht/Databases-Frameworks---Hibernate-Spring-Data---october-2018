package judge.services.inretfaces;

import java.io.IOException;

public interface ContestService {
    String importContests(String contestFilePath) throws IOException;
}
