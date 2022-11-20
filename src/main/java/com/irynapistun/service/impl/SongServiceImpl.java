package com.irynapistun.service.impl;

import com.irynapistun.domain.Song;
import com.irynapistun.service.SongService;
import com.irynapistun.exception.SongNotFoundException;
import com.irynapistun.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Song findById(Integer id) {
        return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    @Transactional
    public Song create(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public void update(Integer id, Song uSong) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        song.setName(uSong.getName());
        song.setDuration(uSong.getDuration());

        songRepository.save(song);
    }

    @Transactional
    public void delete(Integer id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        songRepository.deleteById(id);
    }
}
