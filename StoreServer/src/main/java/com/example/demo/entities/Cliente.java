package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "nome", length = 40)
    private String nome;

    @Basic
    @Column(name = "cognome", length = 40)
    private String cognome;

    @Basic
    @Column(name = "email", length = 50)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.MERGE)
    private List<Ordine> ordini;
}
