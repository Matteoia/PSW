package com.example.demo.controllers;

import com.example.demo.entities.Cliente;
import com.example.demo.services.ServiceCliente;
import com.example.demo.support.MessaggioRisposta;
import com.example.demo.support.exceptions.MailEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ControllerCliente {
    @Autowired
    private ServiceCliente accountingService;


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Cliente user) {
        try {
            Cliente added = accountingService.registraCliente(user);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (MailEsistenteException e) {
            return new ResponseEntity<>(new MessaggioRisposta("EMAIL_UTENTE_ESISTENTE"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Cliente> getAll() {
        return accountingService.getAll();
    }

    @GetMapping("{id}")
    public Cliente getById(@PathVariable Long id) {
        return accountingService.getClienteById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        accountingService.deleteById(id);
    }

}
