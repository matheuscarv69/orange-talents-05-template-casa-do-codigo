package casadocodigo.controllers;

import casadocodigo.controllers.form.estado.EstadoForm;
import casadocodigo.entities.Estado;
import casadocodigo.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid EstadoForm estadoForm) {
        Estado estado = estadoForm.converter(entityManager);

        estadoRepository.save(estado);

        return ResponseEntity.ok().build();
    }


}
