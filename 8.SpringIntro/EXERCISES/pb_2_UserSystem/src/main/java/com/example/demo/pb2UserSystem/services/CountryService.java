package com.example.demo.pb2UserSystem.services;

import com.example.demo.pb2UserSystem.models.Country;
import com.example.demo.pb2UserSystem.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CountryService {
    private CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public void addCountry(Country country){
        this.countryRepository.save(country);
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }
}
