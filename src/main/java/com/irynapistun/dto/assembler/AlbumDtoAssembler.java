package com.irynapistun.dto.assembler;

import com.irynapistun.controller.AlbumController;
import com.irynapistun.domain.Album;
import com.irynapistun.dto.AlbumDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlbumDtoAssembler implements RepresentationModelAssembler<Album, AlbumDto> {
    @Override
    public AlbumDto toModel(Album album) {
        AlbumDto albumDto = AlbumDto.builder()
                .id(album.getId())
                .name(album.getName())
                .yearOfPublishing(album.getYearOfPublishing())
                .build();
        Link selfLink = linkTo(methodOn(AlbumController.class).getAlbum(album.getId())).withSelfRel();
        albumDto.add(selfLink);
        return albumDto;
    }

    @Override
    public CollectionModel<AlbumDto> toCollectionModel(Iterable<? extends Album> albums) {
        CollectionModel<AlbumDto> albumDtos = RepresentationModelAssembler.super.toCollectionModel(albums);
        Link selfLink = linkTo(methodOn(AlbumController.class).getAllAlbums()).withSelfRel();
        albumDtos.add(selfLink);
        return albumDtos;
    }
}
