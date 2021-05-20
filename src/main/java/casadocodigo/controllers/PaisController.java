package casadocodigo.controllers;

import casadocodigo.controllers.form.pais.PaisForm;
import casadocodigo.entities.Pais;
import casadocodigo.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid PaisForm paisForm) {
        Pais pais = paisForm.converter();

        paisRepository.save(pais);

        return ResponseEntity.ok().build();
    }


}
