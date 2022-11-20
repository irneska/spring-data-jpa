package com.irynapistun.controller;

import com.irynapistun.domain.Album;
import com.irynapistun.dto.AlbumDto;
import com.irynapistun.dto.assembler.AlbumDtoAssembler;
import com.irynapistun.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @Autowired
    AlbumDtoAssembler albumDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<AlbumDto>> getAllAlbums(){
        List<Album> albums = albumService.findAll();
        CollectionModel<AlbumDto> albumDtos = albumDtoAssembler.toCollectionModel(albums);
        return new ResponseEntity<>(albumDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{albumId}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable Integer albumId){
        Album album = albumService.findById(albumId);
        AlbumDto albumDto = albumDtoAssembler.toModel(album);
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AlbumDto> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumService.create(album);
        AlbumDto albumDto = albumDtoAssembler.toModel(newAlbum);
        return new ResponseEntity<>(albumDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{albumId}")
    public ResponseEntity<?> updateAlbum(@RequestBody Album album, @PathVariable Integer albumId) {
        albumService.update(albumId, album);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{albumId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer albumId) {
        albumService.delete(albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
