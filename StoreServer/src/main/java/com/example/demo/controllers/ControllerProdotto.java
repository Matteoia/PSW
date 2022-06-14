package com.example.demo.controllers;

import com.example.demo.entities.Prodotto;
import com.example.demo.services.ServiceProdotto;
import com.example.demo.support.MessaggioRisposta;
import com.example.demo.support.exceptions.ProdottoEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/prodotto")
public class ControllerProdotto {
    @Autowired
    private ServiceProdotto productService;

    @PostMapping
    public ResponseEntity<MessaggioRisposta> create(@RequestBody @Valid Prodotto product) {
        try {
            productService.addProdotto(product);
        } catch (ProdottoEsistenteException e) {
            return new ResponseEntity<>(new MessaggioRisposta("CODICE_PRODOTTO_ESISTENTE"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunto con successo!"), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Prodotto>> mostraTutti(){
        return new ResponseEntity<List<Prodotto>>(productService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/paged")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        List<Prodotto> result = productService.getAll(pageNumber, pageSize, sortBy);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getById(@PathVariable long id){
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/search/by_name")
    public ResponseEntity getByName(@RequestParam(required = false) String name) {
        List<Prodotto> result = productService.getByName(name);
        if ( result.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("No results!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
