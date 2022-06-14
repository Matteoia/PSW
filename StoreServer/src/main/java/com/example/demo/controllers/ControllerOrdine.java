package com.example.demo.controllers;


import com.example.demo.entities.Cliente;
import com.example.demo.entities.Ordine;
import com.example.demo.repositories.RepositoryCliente;
import com.example.demo.services.ServiceCliente;
import com.example.demo.services.ServiceOrdine;
import com.example.demo.support.MessaggioRisposta;
import com.example.demo.support.exceptions.IntervalloDataErratoException;
import com.example.demo.support.exceptions.QuantitaNonDisponibileException;
import com.example.demo.support.exceptions.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ordine")
public class ControllerOrdine {
    @Autowired
    private ServiceOrdine purchasingService;

    @Autowired
    private RepositoryCliente repoCliente;

    @Autowired
    private ServiceCliente serviceCliente;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity create(@RequestBody @Valid Ordine ordine) { // è buona prassi ritornare l'oggetto inserito
        try {
            long id = repoCliente.findIdByEmail(ordine.getCliente().getEmail());
            ordine.getCliente().setId(id);
            serviceCliente.addOrdine(ordine);
            return new ResponseEntity<>(purchasingService.aggiungiOrdine(ordine), HttpStatus.OK);
        } catch (QuantitaNonDisponibileException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità non disponibile!", e); // realmente il messaggio dovrebbe essrere più esplicativo (es. specificare il prodotto di cui non vi è disponibilità)
        }
    }
    @GetMapping("/{cliente}")
    public List<Ordine> getPurchases(@RequestBody @Valid Cliente user) {
        try {
            return purchasingService.getByCliente(user);
        } catch (UtenteNonTrovatoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato!", e);
        }
    }
    @GetMapping("/{user}/{startDate}/{endDate}")
    public ResponseEntity getPurchasesInPeriod(@Valid @PathVariable("user") Cliente user, @PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date start, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        try {
            List<Ordine> result = purchasingService.getByClienteInPeriodo(user, start, end);
            if ( result.size() <= 0 ) {
                return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UtenteNonTrovatoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato", e);
        } catch (IntervalloDataErratoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La data di inizio deve essere precendete a quella di fine!!", e);
        }
    }
}
