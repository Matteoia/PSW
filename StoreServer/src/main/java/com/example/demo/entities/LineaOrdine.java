package com.example.demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "lineaordine")
public class LineaOrdine {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ordine")
    @ToString.Exclude
    private Ordine ordine;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prodotto")
    private Prodotto prodotto;

    @Basic
    @Column(name = "qta")
    private int qta;

    @Basic
    @Column(name = "prezzo")
    private double prezzo;
}
