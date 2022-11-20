package com.irynapistun.service.impl;

import com.irynapistun.domain.Musician;
import com.irynapistun.service.MusicianService;
import com.irynapistun.exception.MusicianNotFoundException;
import com.irynapistun.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class MusicianServiceImpl implements MusicianService {
    @Autowired
    MusicianRepository musicianRepository;

    public List<Musician> findAll() {
        return musicianRepository.findAll();
    }

    public Musician findById(Integer id) {
        return musicianRepository.findById(id).orElseThrow(() -> new MusicianNotFoundException(id));
    }

    @Transactional
    public Musician create(Musician musician) {
        return musicianRepository.save(musician);
    }

    @Transactional
    public void update(Integer id, Musician uMusician) {
        Musician musician = musicianRepository.findById(id).orElseThrow(() -> new MusicianNotFoundException(id));
        musician.setFirstName(uMusician.getFirstName());
        musician.setLastName(uMusician.getLastName());

        musicianRepository.save(musician);
    }

    @Transactional
    public void delete(Integer id) {
        Musician musician = musicianRepository.findById(id).orElseThrow(() -> new MusicianNotFoundException(id));
        musicianRepository.deleteById(id);
    }
}
