package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Ordine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositoryOrdine extends JpaRepository<Ordine, Long> {
    Ordine findById(long id);
    List<Ordine> findByCliente(Cliente c);
    List<Ordine> findByData(Date data);
    @Query("SELECT o FROM Ordine o WHERE o.data >= :dataI AND o.data <= :dataF AND o.cliente = :c")
    List<Ordine> findByClienteInPeriodo(Date dataI, Date dataF, Cliente c);
}
