package com.example.demo.repositories;

import com.example.demo.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryProdotto extends JpaRepository<Prodotto, Long> {
    Prodotto findById(long id);
    List<Prodotto> findByNomeContaining(String nome);
    @Query("SELECT p FROM Prodotto p WHERE p.tipologia=:tipologia")
    List<Prodotto> findByTipologia(String tipologia);

    @Query("SELECT p FROM Prodotto p")
    List<Prodotto> trovaTutti();
}
