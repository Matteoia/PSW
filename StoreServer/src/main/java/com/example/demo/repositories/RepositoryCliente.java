package com.example.demo.repositories;


import com.example.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
    Cliente findById(long id);
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeAndCognome(String nome, String cognome);
    Cliente findByEmail(String email);

    @Query("SELECT c.id FROM Cliente c WHERE c.email=:email")
    long findIdByEmail(String email);
}
