package com.irynapistun.dto.assembler;

import com.irynapistun.controller.MusicalLabelController;
import com.irynapistun.domain.MusicalLabel;
import com.irynapistun.dto.MusicalLabelDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MusicalLabelDtoAssembler implements RepresentationModelAssembler<MusicalLabel, MusicalLabelDto> {
    @Override
    public MusicalLabelDto toModel(MusicalLabel musicalLabel) {
        MusicalLabelDto musicalLabelDto = MusicalLabelDto.builder()
                .id(musicalLabel.getId())
                .name(musicalLabel.getName())
                .build();
        Link selfLink = linkTo(methodOn(MusicalLabelController.class).getMusicalLabel(musicalLabel.getId())).withSelfRel();
        musicalLabelDto.add(selfLink);
        return musicalLabelDto;
    }

    @Override
    public CollectionModel<MusicalLabelDto> toCollectionModel(Iterable<? extends MusicalLabel> musicalLabels) {
        CollectionModel<MusicalLabelDto> musicalLabelDtos = RepresentationModelAssembler.super.toCollectionModel(musicalLabels);
        Link selfLink = linkTo(methodOn(MusicalLabelController.class).getAllMusicalLabels()).withSelfRel();
        musicalLabelDtos.add(selfLink);
        return musicalLabelDtos;
    }
}
