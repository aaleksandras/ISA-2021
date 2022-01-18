package com.example.backend.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ComplaintDTO {

    UUID id;
    String complaint;
    String answer;
    Integer status;

}
