package it.epicode.U5W2D4practice.controller;

import it.epicode.U5W2D4practice.dto.AutoreDto;
import it.epicode.U5W2D4practice.exception.AutoreNotFoundException;
import it.epicode.U5W2D4practice.model.Autore;
import it.epicode.U5W2D4practice.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @PostMapping("/autori")
    @ResponseStatus(HttpStatus.CREATED)
    public Autore saveAutore(@RequestBody AutoreDto autoreDto) {
        return autoreService.saveAutore(autoreDto);
    }

    @GetMapping("/autori/{id}")
    public Autore getAutore(@PathVariable int id) throws AutoreNotFoundException {
        return autoreService.getAutore(id);
    }

    @GetMapping("/autori")
    public List<Autore> getAllAutori(){
        return autoreService.getAllAutori();
    }

    @PutMapping("/autori/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody Autore autore) throws AutoreNotFoundException{
        return autoreService.updateAutore(id, new AutoreDto());
    }

    @DeleteMapping("/autori/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutori(@PathVariable int id) throws AutoreNotFoundException{
        autoreService.deleteAutore(id);
    }


}
