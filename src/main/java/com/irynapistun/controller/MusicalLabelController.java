package com.irynapistun.controller;

import com.irynapistun.domain.MusicalLabel;
import com.irynapistun.dto.MusicalLabelDto;
import com.irynapistun.dto.assembler.MusicalLabelDtoAssembler;
import com.irynapistun.service.MusicalLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/musicalLabel")
public class MusicalLabelController {
    @Autowired
    MusicalLabelService musicalLabelService;

    @Autowired
    MusicalLabelDtoAssembler musicalLabelDtoAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<MusicalLabelDto>> getAllMusicalLabels(){
        List<MusicalLabel> musicalLabels = musicalLabelService.findAll();
        CollectionModel<MusicalLabelDto> musicalLabelDtos = musicalLabelDtoAssembler.toCollectionModel(musicalLabels);
        return new ResponseEntity<>(musicalLabelDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{musicalLabelId}")
    public ResponseEntity<MusicalLabelDto> getMusicalLabel(@PathVariable Integer musicalLabelId){
        MusicalLabel musicalLabel = musicalLabelService.findById(musicalLabelId);
        MusicalLabelDto musicalLabelDto = musicalLabelDtoAssembler.toModel(musicalLabel);
        return new ResponseEntity<>(musicalLabelDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MusicalLabelDto> addMusicalLabel(@RequestBody MusicalLabel musicalLabel) {
        MusicalLabel newMusicalLabel = musicalLabelService.create(musicalLabel);
        MusicalLabelDto musicalLabelDto = musicalLabelDtoAssembler.toModel(newMusicalLabel);
        return new ResponseEntity<>(musicalLabelDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{musicalLabelId}")
    public ResponseEntity<?> updateMusicalLabel(@RequestBody MusicalLabel musicalLabel, @PathVariable Integer musicalLabelId) {
        musicalLabelService.update(musicalLabelId, musicalLabel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{musicalLabelId}")
    public ResponseEntity<?> deleteMusicalLabel(@PathVariable Integer musicalLabelId) {
        musicalLabelService.delete(musicalLabelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
