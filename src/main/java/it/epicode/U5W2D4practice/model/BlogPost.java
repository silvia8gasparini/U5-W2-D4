package it.epicode.U5W2D4practice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private int id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;


    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

}
