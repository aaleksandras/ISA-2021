package com.example.backend.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RevisionDTO {
    UUID id;
    UUID userID;
    String revision;
    Integer mark;
    Integer status;
}
