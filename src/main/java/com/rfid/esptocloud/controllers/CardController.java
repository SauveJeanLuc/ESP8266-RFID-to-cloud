package com.rfid.esptocloud.controllers;

import com.rfid.esptocloud.domain.Card;
import com.rfid.esptocloud.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/card")
    public Card addCardDetails(@RequestBody Card card){
        return  cardRepository.save(card);
    }

    @GetMapping("/card/{id}")
    public Card getCardById(@PathVariable Long id){
        return cardRepository.findById(id)
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/card")
    public List<Card> getALlCards(){
        return cardRepository.findAll();
    }

    @PutMapping("/card/{id}")
    public ResponseEntity<Card> updateCardById(@PathVariable Long id, @RequestBody Card card){
        Optional<Card> CardData = cardRepository.findById(id);

        if( CardData.isPresent() ){
            Card _card = CardData.get();
            _card.setId(card.getId());
            _card.setCardOwnerName(card.getCardOwnerName());
            _card.setInitialBalance(card.getInitialBalance());
            _card.setNewBalance(card.getNewBalance());

            return new ResponseEntity<>(cardRepository.save(_card), HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCardById(@PathVariable Long id){
        cardRepository.deleteById(id);
    }
}
