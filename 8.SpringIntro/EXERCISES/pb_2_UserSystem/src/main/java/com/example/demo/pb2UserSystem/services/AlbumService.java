package com.example.demo.pb2UserSystem.services;

import com.example.demo.pb2UserSystem.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AlbumService {
    private AlbumRepository albumRepository;
    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public AlbumRepository getAlbumRepository() {
        return albumRepository;
    }
}
