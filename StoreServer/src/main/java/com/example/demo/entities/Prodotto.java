package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="prodotto")
public class Prodotto {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "nome", length = 40)
    private String nome;

    @Basic
    @Column(name = "qta")
    private int qta;

    @Basic
    @Column(name = "tipologia")
    private String tipologia;

    @Basic
    @Column(name = "prezzo")
    private float prezzo;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JsonIgnore
    private List<LineaOrdine> lineeOrdini;
}
