package com.example.demo.pb2UserSystem.services;

import com.example.demo.pb2UserSystem.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PictureService {
    private PictureRepository pictureRepository;
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public PictureRepository getPictureRepository() {
        return pictureRepository;
    }
}
