package app.services;

import app.repositories.BasicShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ShampooService {
    private BasicShampooRepository basicShampooRepository;

    @Autowired
    public ShampooService(BasicShampooRepository basicShampooRepository) {
        this.basicShampooRepository = basicShampooRepository;
    }

    public BasicShampooRepository getBasicShampooRepository() {
        return basicShampooRepository;
    }
}
