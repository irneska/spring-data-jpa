package com.irynapistun.dto.assembler;

import com.irynapistun.controller.AlbumController;
import com.irynapistun.controller.SongController;
import com.irynapistun.domain.Song;
import com.irynapistun.dto.AlbumDto;
import com.irynapistun.dto.SongDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SongDtoAssembler implements RepresentationModelAssembler<Song, SongDto> {
    @Override
    public SongDto toModel(Song song) {
        SongDto songDto = SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .duration(song.getDuration())
                .build();
        Link selfLink = linkTo(methodOn(SongController.class).getSong(song.getId())).withSelfRel();
        songDto.add(selfLink);
        return songDto;
    }

    @Override
    public CollectionModel<SongDto> toCollectionModel(Iterable<? extends Song> songs) {
        CollectionModel<SongDto> songDtos = RepresentationModelAssembler.super.toCollectionModel(songs);
        Link selfLink = linkTo(methodOn(SongController.class).getAllSongs()).withSelfRel();
        songDtos.add(selfLink);
        return songDtos;
    }
}
