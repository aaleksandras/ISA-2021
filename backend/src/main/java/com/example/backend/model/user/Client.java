package com.example.backend.model.user;

import com.example.backend.enums.LoyaltyCategory;
import com.example.backend.model.penalty.Penalty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "clients")
@Where(clause = "deleted = 'false'")
public class Client extends User implements Serializable {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Penalty> listOfPenalties;

    @Column(name = "penalty")
    private int penalty;

    @Column(name = "points")
    private int points;

    @Column(name = "loyaltyCategory")
    private LoyaltyCategory loyaltyCategory;

    public List<Penalty> getListOfPenalties() {
        return listOfPenalties;
    }

    public void setListOfPenalties(List<Penalty> listOfPenalties) {
        this.listOfPenalties = listOfPenalties;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LoyaltyCategory getLoyaltyCategory() {
        return loyaltyCategory;
    }

    public void setLoyaltyCategory(LoyaltyCategory loyaltyCategory) {
        this.loyaltyCategory = loyaltyCategory;
    }
}