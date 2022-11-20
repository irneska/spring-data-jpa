package com.irynapistun.dto.assembler;

import com.irynapistun.controller.MusicianController;
import com.irynapistun.domain.Musician;
import com.irynapistun.dto.MusicianDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MusicianDtoAssembler implements RepresentationModelAssembler<Musician, MusicianDto> {
    @Override
    public MusicianDto toModel(Musician musician) {
        MusicianDto musicianDto = MusicianDto.builder()
                .id(musician.getId())
                .firstName(musician.getFirstName())
                .lastName(musician.getLastName())
                .build();
        Link selfLink = linkTo(methodOn(MusicianController.class).getMusician(musician.getId())).withSelfRel();
        musicianDto.add(selfLink);
        return musicianDto;
    }

    @Override
    public CollectionModel<MusicianDto> toCollectionModel(Iterable<? extends Musician> musicians) {
        CollectionModel<MusicianDto> musicianDtos = RepresentationModelAssembler.super.toCollectionModel(musicians);
        Link selfLink = linkTo(methodOn(MusicianController.class).getAllMusicians()).withSelfRel();
        musicianDtos.add(selfLink);
        return musicianDtos;
    }
}
