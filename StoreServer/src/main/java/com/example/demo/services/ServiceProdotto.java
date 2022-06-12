package com.example.demo.services;


import com.example.demo.entities.Prodotto;
import com.example.demo.repositories.RepositoryProdotto;
import com.example.demo.support.exceptions.ProdottoEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProdotto {
    @Autowired
    private RepositoryProdotto repoProdotto;

    @Transactional
    public Prodotto addProdotto(Prodotto p) throws ProdottoEsistenteException {
        if(repoProdotto.findById(p.getId()) != null)
            throw new ProdottoEsistenteException();
        return repoProdotto.save(p);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getAll(){
        return repoProdotto.trovaTutti();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getAll(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = repoProdotto.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getByName(String nome){
        return repoProdotto.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> getByType(String tipologia){
        return repoProdotto.findByTipologia(tipologia);
    }

    @Transactional(readOnly = true)
    public Prodotto getById(long id){
        return repoProdotto.findById(id);
    }

}
