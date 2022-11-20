package com.irynapistun.controller;

import com.irynapistun.domain.MusicalLabel;
import com.irynapistun.domain.Musician;
import com.irynapistun.dto.MusicalLabelDto;
import com.irynapistun.dto.MusicianDto;
import com.irynapistun.dto.assembler.MusicalLabelDtoAssembler;
import com.irynapistun.dto.assembler.MusicianDtoAssembler;
import com.irynapistun.service.MusicalLabelService;
import com.irynapistun.service.MusicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/musician")
public class MusicianController {
    @Autowired
    MusicianService musicianService;

    @Autowired
    MusicianDtoAssembler musicianDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<MusicianDto>> getAllMusicians(){
        List<Musician> musicians = musicianService.findAll();
        CollectionModel<MusicianDto> musicianDtos = musicianDtoAssembler.toCollectionModel(musicians);
        return new ResponseEntity<>(musicianDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{musicianId}")
    public ResponseEntity<MusicianDto> getMusician(@PathVariable Integer musicianId){
        Musician musician = musicianService.findById(musicianId);
        MusicianDto musicianDto = musicianDtoAssembler.toModel(musician);
        return new ResponseEntity<>(musicianDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MusicianDto> addMusician(@RequestBody Musician musician) {
        Musician newMusician =musicianService.create(musician);
        MusicianDto musicianDto = musicianDtoAssembler.toModel(newMusician);
        return new ResponseEntity<>(musicianDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{musicianId}")
    public ResponseEntity<?> updateMusician(@RequestBody Musician musician, @PathVariable Integer musicianId) {
        musicianService.update(musicianId, musician);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{musicianId}")
    public ResponseEntity<?> deleteMusician(@PathVariable Integer musicianId) {
        musicianService.delete(musicianId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
