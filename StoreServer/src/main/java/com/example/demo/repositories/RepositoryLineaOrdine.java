package com.example.demo.repositories;

import com.example.demo.entities.LineaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryLineaOrdine extends JpaRepository<LineaOrdine, Long> {
    LineaOrdine findById(long id);
    List<LineaOrdine> findByOrdine(long ordine);
    List<LineaOrdine> findByProdotto(long prodotto);
}
