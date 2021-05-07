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
    @Column(name = "card_id")
    public String Id;
    @Column(name = "card_owner_name")
    public String cardOwnerName;
    @Column(name = "initial_balance")
    public Float initialBalance;
    @Column(name = "new_balance")
    public Float newBalance;
    @Column(name = "transport_fare")
    public Float transportFare;

}
