package com.example.backend.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AbstractDTO {
    private UUID id;
    private Boolean deleted;
}
