package it.epicode.U5W2D4practice.service;

import it.epicode.U5W2D4practice.dto.AutoreDto;
import it.epicode.U5W2D4practice.exception.AutoreNotFoundException;
import it.epicode.U5W2D4practice.model.Autore;
import it.epicode.U5W2D4practice.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private BlogPostService blogPostService;

    public Autore saveAutore(AutoreDto autoreDto){
        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        autore.setAvatar("https://www.google.com/url?q=https://ui-avatars.com/api/?name%3DMario%2BRossi&sa=D&source=editors&ust=1749560445102304&usg=AOvVaw1cbVa4xxrprdQr5bdWbxLC" + autoreDto.getNome() + " " + autoreDto.getCognome());
        return autoreRepository.save(autore);
    }

    public Autore getAutore(int id) throws AutoreNotFoundException {
        return autoreRepository.findById(id)
               .orElseThrow(() -> new AutoreNotFoundException("Autore with id " + id + " not found"));
    }

    public List<Autore> getAllAutori(){
        return autoreRepository.findAll();
    }

    public Autore updateAutore(int id, AutoreDto autoreDto) throws AutoreNotFoundException{
      Autore autoreToUpdate = getAutore(id);

        autoreToUpdate.setNome(autoreDto.getNome());
        autoreToUpdate.setCognome(autoreDto.getCognome());
        autoreToUpdate.setDataDiNascita(autoreDto.getDataDiNascita());

        return autoreRepository.save(autoreToUpdate);
    }

    public void deleteAutore(int id) throws AutoreNotFoundException{
        Autore autoreToRemove = getAutore(id);

        autoreRepository.delete(autoreToRemove);
    }

}
