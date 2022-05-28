package com.example.backend.web.dto;

import com.example.backend.model.reservation.Revision;
import lombok.Data;


@Data
public class CommentsDTO {

    private String username;
    private String reservationName;
    private String content;

    public CommentsDTO(Revision r){
        System.out.println("dto je"+r.getUser().getUsername());
        this.username = r.getUser().getUsername();
        this.content = r.getContent();
        this.reservationName = r.getReservation().getReservation().getName();
    }

}
