package com.irynapistun.service.impl;

import com.irynapistun.service.AlbumService;
import com.irynapistun.domain.Album;
import com.irynapistun.exception.AlbumNotFoundException;
import com.irynapistun.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public Album findById(Integer id) {
        return albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @Transactional
    public Album create(Album album) {
        return albumRepository.save(album);
    }

    @Transactional
    public void update(Integer id, Album uAlbum) {
       Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
        album.setName(uAlbum.getName());
        album.setYearOfPublishing(uAlbum.getYearOfPublishing());

        albumRepository.save(album);

    }

    @Transactional
    public void delete(Integer id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
       albumRepository.deleteById(id);
    }
}
