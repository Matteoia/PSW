package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="ordine")
public class Ordine {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Basic
    @Column(name="data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.MERGE)
    private List<LineaOrdine> lineeOrdini;

}
