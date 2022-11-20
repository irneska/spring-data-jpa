package com.irynapistun.service.impl;

import com.irynapistun.domain.MusicalLabel;
import com.irynapistun.service.MusicalLabelService;
import com.irynapistun.exception.MusicalLabelNotFoundException;
import com.irynapistun.repository.MusicalLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MusicalLabelServiceImpl implements MusicalLabelService {
    @Autowired
    MusicalLabelRepository musicalLabelRepository;
    @Override
    public List<MusicalLabel> findAll() {
        return musicalLabelRepository.findAll();
    }

    @Override
    public MusicalLabel findById(Integer id) {
        return musicalLabelRepository.findById(id).orElseThrow(() -> new MusicalLabelNotFoundException(id));
    }

    @Override
    public MusicalLabel create(MusicalLabel musicalLabel) {
        return musicalLabelRepository.save(musicalLabel);
    }

    @Override
    public void update(Integer id, MusicalLabel uMusicalLabel) {
        MusicalLabel musicalLabel = musicalLabelRepository.findById(id).orElseThrow(() -> new MusicalLabelNotFoundException(id));
        musicalLabel.setName(uMusicalLabel.getName());

        musicalLabelRepository.save(musicalLabel);
    }

    @Override
    public void delete(Integer id) {
        MusicalLabel musicalLabel = musicalLabelRepository.findById(id).orElseThrow(() -> new MusicalLabelNotFoundException(id));
        musicalLabelRepository.deleteById(id);
    }
}
