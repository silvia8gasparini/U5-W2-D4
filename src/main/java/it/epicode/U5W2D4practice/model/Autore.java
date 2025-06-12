package it.epicode.U5W2D4practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Autore {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;


    @OneToMany(mappedBy = "autore")
    private List<BlogPost> blogPosts;


}
