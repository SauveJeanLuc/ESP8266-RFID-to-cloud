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

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/card")
    public Card addCardDetails(@RequestBody Card card){
        return  cardRepository.save(card);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/card/{id}")
    public Card getCardById(@PathVariable String id){
        return cardRepository.findById(id)
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/card")
    public List<Card> getALlCards(){
        return cardRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/card/{id}")
    public ResponseEntity<Card> updateCardById(@PathVariable String id, @RequestBody Card card){
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

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/card/{id}")
    public void deleteCardById(@PathVariable String id){
        cardRepository.deleteById(id);
    }


}
