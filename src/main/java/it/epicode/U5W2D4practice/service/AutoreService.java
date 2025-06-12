package it.epicode.U5W2D4practice.service;

import it.epicode.U5W2D4practice.dto.AutoreDto;
import it.epicode.U5W2D4practice.exception.AutoreNotFoundException;
import it.epicode.U5W2D4practice.model.Autore;
import it.epicode.U5W2D4practice.service.CloudinaryService;
import it.epicode.U5W2D4practice.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private EmailService emailService;

    public Autore saveAutore(AutoreDto autoreDto) {
        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        autore.setAvatar(autoreDto.getAvatar());

        return autoreRepository.save(autore);
    }


    public Autore saveAutoreConUpload(String nome, String cognome, String email, String dataDiNascita, MultipartFile avatar) throws IOException {
        String avatarUrl = cloudinaryService.uploadFile(avatar);
        AutoreDto autoreDto = new AutoreDto();
        autoreDto.setNome(nome);
        autoreDto.setCognome(cognome);
        autoreDto.setEmail(email);
        autoreDto.setDataDiNascita(LocalDate.parse(dataDiNascita)); // attenzione al formato
        autoreDto.setAvatar(avatarUrl);
        Autore autore = saveAutore(autoreDto);

        emailService.sendEmail(
                email,
                "Benvenuto " + nome + "!",
                "Il tuo profilo è stato creato con successo. Grazie per esserti unito a noi!"
        );

        return autore;
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
