package judge.services.inretfaces;

import judge.domain.entities.Category;

import java.io.IOException;
import java.util.Optional;

public interface CategoryService {

    String importCategories(String categoriesFilePath) throws IOException;


}
