package com.irynapistun.dto.assembler;

import com.irynapistun.controller.SongController;
import com.irynapistun.controller.UserController;
import com.irynapistun.domain.User;
import com.irynapistun.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDtoAssembler implements RepresentationModelAssembler<User, UserDto> {
    @Override
    public UserDto toModel(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel();
        userDto.add(selfLink);
        return userDto;
    }

    @Override
    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends User> users) {
        CollectionModel<UserDto> userDtos = RepresentationModelAssembler.super.toCollectionModel(users);
        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        userDtos.add(selfLink);
        return userDtos;
    }
}
