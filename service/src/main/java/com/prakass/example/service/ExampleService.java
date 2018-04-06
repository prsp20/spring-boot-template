package com.prakass.example.service;

import com.prakass.example.dao.ExampleRepository;
import com.prakass.example.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExampleService {
    private ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public void save(Example example) {
        exampleRepository.save(example);
    }

    public List<Example> findAll() {
        return exampleRepository.findAll();
    }
}
