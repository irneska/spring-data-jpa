package com.irynapistun.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "musician", collectionRelation = "musicians")
public class MusicianDto extends RepresentationModel<MusicianDto> {
    private final Integer id;

    private final String firstName;

    private final String lastName;
}
