package com.example.demo.controllers;

import com.example.demo.entities.Prodotto;
import com.example.demo.support.MessaggioRisposta;
import com.example.demo.support.authentication.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    @GetMapping("/simple")
    public ResponseEntity checkSimple() {
        return new ResponseEntity<>("Check status ok!", HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('utente')")
    @GetMapping("/logged")
    @PreAuthorize("hasAuthority('utente')")

    public ResponseEntity checkLogged() {
        return new ResponseEntity("Check status ok, hi " /*+Utils.getEmail() + "!"*/, HttpStatus.OK);
    }
}
