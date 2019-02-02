package app.services;

import app.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class IngredientService {
    private IngredientRepository ingredientRepository;
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientRepository getIngredientRepository() {
        return ingredientRepository;
    }
}
