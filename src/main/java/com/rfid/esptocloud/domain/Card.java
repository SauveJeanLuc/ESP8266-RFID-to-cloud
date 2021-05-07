package com.rfid.esptocloud.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "card")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column("card_id")
    public Long Id;
    @Column("card_owner_name")
    public String cardOwnerName;
    @Column("initial_balance")
    public Float initialBalance;
    @Column("new_balance")
    public Float newBalance;
    @Column("transport_fare")
    public Float transportFare;

}
