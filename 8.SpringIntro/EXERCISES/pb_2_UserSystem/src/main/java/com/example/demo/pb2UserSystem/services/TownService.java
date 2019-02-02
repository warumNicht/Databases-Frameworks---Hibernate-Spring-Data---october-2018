package com.example.demo.pb2UserSystem.services;

import com.example.demo.pb2UserSystem.models.Town;
import com.example.demo.pb2UserSystem.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TownService {
    private TownRepository townRepository;
    @Autowired
    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }
    public void register(Town town){
        this.townRepository.save(town);
    }

    public TownRepository getTownRepository() {
        return townRepository;
    }
}
