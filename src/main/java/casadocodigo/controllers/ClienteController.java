package casadocodigo.controllers;

import casadocodigo.controllers.dto.cliente.ClienteDto;
import casadocodigo.controllers.form.cliente.ClienteForm;
import casadocodigo.entities.Cliente;
import casadocodigo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm clienteForm) {
        Cliente cliente = clienteForm.converter(entityManager);

        clienteRepository.save(cliente);

        return ResponseEntity.ok(new ClienteDto(cliente));
    }


}
