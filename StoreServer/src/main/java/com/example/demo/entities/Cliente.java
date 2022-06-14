package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    @JsonIgnore
    private List<Ordine> ordini;
}
