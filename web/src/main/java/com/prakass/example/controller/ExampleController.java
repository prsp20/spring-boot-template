package com.prakass.example.controller;

import com.prakass.example.converter.ExampleConverter;
import com.prakass.example.dto.ExampleDTO;
import com.prakass.example.entity.Example;
import com.prakass.example.service.ExampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {
    private ExampleService exampleService;
    private ExampleConverter exampleConverter;

    public ExampleController(ExampleService exampleService, ExampleConverter exampleConverter) {
        this.exampleService = exampleService;
        this.exampleConverter = exampleConverter;
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExampleDTO> getExamples() {
        List<Example> examples = exampleService.findAll();
        return examples.stream()
                .map(example -> exampleConverter.convertExampleEntityToDto(example))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/example", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveNote(@Valid @RequestBody ExampleDTO exampleDTO) {
        exampleService.save(exampleConverter.convertExampleDtoToEntity(exampleDTO));
        return new ResponseEntity<>("done", HttpStatus.CREATED);
    }
}
