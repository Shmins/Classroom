package com.classroom.main.controller.dto;

import com.darmlabs.maestro.core.model.Example;
import com.darmlabs.maestro.core.model.ExampleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

// As anotações @Setter, @Getter, @AllArgsConstructor, @NoArgsConstructor, @Builder
// são geradas automaticamente pela biblioteca Lombok. Elas ajudam a reduzir o código boilerplate.

@Setter  // Gera automaticamente os setters para todos os campos
@Getter  // Gera automaticamente os getters para todos os campos
@AllArgsConstructor  // Gera automaticamente um construtor com todos os parâmetros
@NoArgsConstructor  // Gera automaticamente um construtor sem parâmetros
@Builder  // Implementa o padrão Builder, permitindo a criação de objetos de forma fluente
public class ExampleDTO {

    // Campo para o ID do exemplo
    private Long id;

    // Campo para o nome do exemplo
    private String name;

    // Campo para a descrição do exemplo
    private String description;

    // Campo para o tipo do exemplo, baseado na classe ExampleType
    private ExampleType type;

    // Campo para a data de criação. O @JsonFormat define como a data será serializada/deserializada no JSON.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    // Campo para a data de atualização. O @JsonFormat define o formato no JSON.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;

    // Método estático para converter um objeto Example (entidade) em ExampleDTO
    public static ExampleDTO convertToDTO(Example example) {
        ExampleDTO dto = new ExampleDTO();
        dto.setId(example.getId());
        dto.setName(example.getName());
        dto.setDescription(example.getDescription());
        dto.setType(example.getType());
        dto.setCreatedDate(example.getCreatedDate());
        dto.setUpdatedDate(example.getUpdatedDate());
        return dto;
    }
}
