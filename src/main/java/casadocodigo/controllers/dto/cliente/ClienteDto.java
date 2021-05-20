package casadocodigo.controllers.dto.cliente;

import casadocodigo.entities.Cliente;

public class ClienteDto {

    private Long id;

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }
}
