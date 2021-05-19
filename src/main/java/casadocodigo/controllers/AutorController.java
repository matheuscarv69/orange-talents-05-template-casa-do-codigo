package casadocodigo.controllers;

import casadocodigo.controllers.form.autor.AutorForm;
import casadocodigo.entities.Autor;
import casadocodigo.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping()
    public ResponseEntity<?> cadastrar(@RequestBody @Valid AutorForm autorForm) {
        Autor autor = autorForm.converter();

        autorRepository.save(autor);

        return ResponseEntity.ok().build();
    }


}
