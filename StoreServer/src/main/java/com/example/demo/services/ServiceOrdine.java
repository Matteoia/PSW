package com.example.demo.services;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.LineaOrdine;
import com.example.demo.entities.Ordine;
import com.example.demo.entities.Prodotto;
import com.example.demo.repositories.RepositoryCliente;
import com.example.demo.repositories.RepositoryLineaOrdine;
import com.example.demo.repositories.RepositoryOrdine;
import com.example.demo.support.exceptions.IntervalloDataErratoException;
import com.example.demo.support.exceptions.QuantitaNonDisponibileException;
import com.example.demo.support.exceptions.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


@Service
public class ServiceOrdine {
    @Autowired
    private RepositoryOrdine repoOrdine;
    @Autowired
    private RepositoryLineaOrdine repoLineaOrdine;
    @Autowired
    private RepositoryCliente repoCliente;
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Ordine aggiungiOrdine(Ordine ordine) throws QuantitaNonDisponibileException {
        Ordine result = repoOrdine.save(ordine);
        for ( LineaOrdine lo : result.getLineeOrdini() ) {
            lo.setOrdine(result);
            LineaOrdine justAdded = repoLineaOrdine.saveAndFlush(lo);
            entityManager.refresh(justAdded);
            Prodotto product = justAdded.getProdotto();
            int newQuantity = product.getQta() - lo.getQta();
            if ( newQuantity < 0 ) {
                throw new QuantitaNonDisponibileException();
            }
            product.setQta(newQuantity);
            entityManager.refresh(lo);
        }
        entityManager.refresh(result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Ordine> getAll() {
        return repoOrdine.findAll();
    }

    @Transactional(readOnly = true)
    public List<Ordine> getByCliente(Cliente c) throws UtenteNonTrovatoException {
        if ( !repoCliente.existsById(c.getId()) ) {
            throw new UtenteNonTrovatoException();
        }
        return repoOrdine.findByCliente(c);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getByClienteInPeriodo(Cliente c, Date dataI, Date dataF) throws UtenteNonTrovatoException, IntervalloDataErratoException {
        if ( !repoCliente.existsById(c.getId()) ) {
            throw new UtenteNonTrovatoException();
        }
        if ( dataI.compareTo(dataF) >= 0 ) {
            throw new IntervalloDataErratoException();
        }
        return repoOrdine.findByClienteInPeriodo(dataI, dataF, c);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getByData(Date d){
        return repoOrdine.findByData(d);
    }
}
