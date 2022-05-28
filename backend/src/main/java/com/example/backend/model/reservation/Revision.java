package com.example.backend.model.reservation;

import com.example.backend.enums.StatusOfRevision;
import com.example.backend.model.DefaultModel;
import com.example.backend.model.user.Client;
import com.example.backend.model.user.User;
import com.example.backend.web.dto.RevisionDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Revision extends DefaultModel {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @Column(length = 4096)
    String content;

    Integer mark;

    StatusOfRevision status;

    public Revision(RevisionDTO dto, Reservation r) {
        this.content =dto.getRevision();
        this.mark =dto.getMark();
        this.reservation = r;
        if(dto.getStatus()==null){
            this.status = StatusOfRevision.HOLD_ON;
        }else{
        this.status = dto.getStatus().equals("HOLD_ON") ? StatusOfRevision.HOLD_ON :
                dto.getStatus().equals("ACCEPTED") ? StatusOfRevision.ACCEPTED : StatusOfRevision.DECLINED;
    }}

    public Revision() {
    }
}
