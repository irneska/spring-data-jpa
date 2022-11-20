package com.irynapistun.controller;

import com.irynapistun.domain.User;
import com.irynapistun.dto.UserDto;
import com.irynapistun.dto.assembler.UserDtoAssembler;
import com.irynapistun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserDtoAssembler userDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<UserDto>> getAllUsers(){
        List<User> users = userService.findAll();
        CollectionModel<UserDto> userDtos = userDtoAssembler.toCollectionModel(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        User user = userService.findById(userId);
        UserDto userDto = userDtoAssembler.toModel(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        User newUser = userService.create(user);
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer userId) {
        userService.update(userId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
