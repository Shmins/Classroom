package com.classroom.main.service;

import com.darmlabs.maestro.core.dtos.CreateExampleDTO;
import com.darmlabs.maestro.core.exceptions.MstDomainException;
import com.darmlabs.maestro.core.model.Example;
import com.darmlabs.maestro.core.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Autowired
    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    public Example getExampleById(Long id) {
        Optional<Example> example = exampleRepository.findById(id);
        if (example.isEmpty()) {
            throw MstDomainException.notFound();
        }
        return example.get();
    }

    public Example createExample(CreateExampleDTO exampleDTO) {
        Example example = new Example();
        example.setName(exampleDTO.getName());
        example.setDescription(exampleDTO.getDescription());
        example.setType(exampleDTO.getType());

        return exampleRepository.save(example);
    }

    public Example updateExample(Long id, CreateExampleDTO exampleDTO) {
        Optional<Example> example = exampleRepository.findById(id);
        if (example.isEmpty()) {
            throw MstDomainException.notFound();
        }
        example.get().setName(exampleDTO.getName());
        example.get().setDescription(exampleDTO.getDescription());
        example.get().setType(exampleDTO.getType());

        return exampleRepository.save(example.get());
    }

    public void deleteExample(Long id) {
        exampleRepository.deleteById(id);
    }
}
