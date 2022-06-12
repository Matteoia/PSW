package com.example.demo.services;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.RepositoryCliente;
import com.example.demo.support.exceptions.MailEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCliente {
    @Autowired
    private RepositoryCliente repoCliente;

    @Transactional
    public Cliente registraCliente(Cliente c) throws MailEsistenteException {
        if(repoCliente.findByEmail(c.getEmail()).size()>0)
            throw new MailEsistenteException();
        return repoCliente.save(c);
    }

    @Transactional(readOnly = true)
    public Cliente getClienteById(long id){
        return repoCliente.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getClienteByNome(String nome){
        return repoCliente.findByNome(nome);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getClienteByNomeAndCognome(String nome, String cognome){
        return repoCliente.findByNomeAndCognome(nome, cognome);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getClienteByEmail(String email){
        return repoCliente.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAll() { return repoCliente.findAll();
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        repoCliente.deleteById(id);
    }
}
