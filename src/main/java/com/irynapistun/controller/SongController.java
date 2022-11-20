package com.irynapistun.controller;

import com.irynapistun.domain.Song;
import com.irynapistun.dto.SongDto;
import com.irynapistun.dto.assembler.SongDtoAssembler;
import com.irynapistun.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/song")
public class SongController {
    @Autowired
    SongService songService;

    @Autowired
    SongDtoAssembler songDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<SongDto>> getAllSongs(){
        List<Song> songs = songService.findAll();
        CollectionModel<SongDto> songDtos = songDtoAssembler.toCollectionModel(songs);
        return new ResponseEntity<>(songDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{songId}")
    public ResponseEntity<SongDto> getSong(@PathVariable Integer songId){
        Song song = songService.findById(songId);
        SongDto songDto = songDtoAssembler.toModel(song);
        return new ResponseEntity<>(songDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SongDto> addSong(@RequestBody Song song) {
        Song newSong = songService.create(song);
        SongDto songDto = songDtoAssembler.toModel(newSong);
        return new ResponseEntity<>(songDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{songId}")
    public ResponseEntity<?> updateSong(@RequestBody Song song, @PathVariable Integer songId) {
        songService.update(songId, song);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable Integer songId) {
        songService.delete(songId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
