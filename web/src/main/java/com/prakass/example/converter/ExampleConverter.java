package com.prakass.example.converter;

import com.prakass.example.dto.ExampleDTO;
import com.prakass.example.entity.Example;
import org.springframework.stereotype.Component;

@Component
public class ExampleConverter {
    public Example convertExampleDtoToEntity(ExampleDTO noteDTO) {
        return Example.Builder.anExample()
                .name(noteDTO.getName())
                .content(noteDTO.getContent())
                .build();
    }

    public ExampleDTO convertExampleEntityToDto(Example example) {
        return new ExampleDTO(example.getName(), example.getContent());
    }
}
