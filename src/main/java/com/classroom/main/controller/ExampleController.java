package com.classroom.main.controller;

import com.darmlabs.maestro.core.dtos.CreateExampleDTO;
import com.darmlabs.maestro.core.dtos.ExampleDTO;
import com.darmlabs.maestro.core.exceptions.MstDomainException;
import com.darmlabs.maestro.core.model.Example;
import com.darmlabs.maestro.core.service.ExampleService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/examples")
@Tag(name = "Example", description = "This is api of Example")
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @Operation(summary = "Return all examples", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all Examples",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExampleDTO.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized, unauthorized resource", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content)
    })
    // Retorna todos os exemplos em formato DTO
    @GetMapping
    public ResponseEntity<List<ExampleDTO>> getAllExamples() {
        List<Example> examples = exampleService.getAllExamples();
        List<ExampleDTO> exampleDTOS = examples.stream()
                .map(ExampleDTO::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(exampleDTOS);
    }
    @Operation(summary = "Return example filtered by id", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return filtered Example",
                    content = @Content(schema = @Schema(implementation = ExampleDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized, unauthorized resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found, Example was not found by id.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExampleDTO> getExampleById(@PathVariable Long id) {
        Example example = exampleService.getExampleById(id);

        ExampleDTO exampleDTO = ExampleDTO.convertToDTO(example);
        return ResponseEntity.ok(exampleDTO);
    }
    @Operation(summary = "Create example", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create new Example",
                    content = @Content(schema = @Schema(implementation = ExampleDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized, unauthorized resource", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict, Example is already in database.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content)
    })
    // Cria um novo exemplo e retorna 201 Created com a localização do recurso criado
    @PostMapping
    public ResponseEntity<ExampleDTO> createExample(@Valid @RequestBody CreateExampleDTO exampleDTO) {
        Example example = exampleService.createExample(exampleDTO);
        ExampleDTO responseDTO = ExampleDTO.convertToDTO(example);

        // Cria a URI do recurso criado
        URI location = URI.create(String.format("/api/v1/examples/%d", example.getId()));

        return ResponseEntity.created(location).body(responseDTO);
    }
    @Operation(summary = "Update example", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Example",
                    content = @Content(schema = @Schema(implementation = ExampleDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized, unauthorized resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found, Example was not found by id.", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict, Example is already in database.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content)
    })
    // Atualiza um exemplo existente
    @PutMapping("/{id}")
    public ResponseEntity<ExampleDTO> updateExample(@PathVariable Long id, @Valid @RequestBody CreateExampleDTO exampleDTO) {
        Example updatedExample = exampleService.updateExample(id, exampleDTO);
        ExampleDTO responseDTO = ExampleDTO.convertToDTO(updatedExample);
        return ResponseEntity.ok(responseDTO);
    }
    @Operation(summary = "Delete example", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized, unauthorized resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found, Example was not found by id.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content)
    })
    // Exclui um exemplo existente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Long id) {
        exampleService.deleteExample(id);
        return ResponseEntity.noContent().build();
    }
}
