package casadocodigo.controllers.form.cliente;

import casadocodigo.configs.validation.customValidation.exists.ExistsId;
import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Cliente;
import casadocodigo.entities.Estado;
import casadocodigo.entities.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ClienteForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    @Email
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotEmpty
    //    @CPF -> comentada para facilitar os testes
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;

    @NotEmpty
    private String endereco;

    @NotEmpty
    private String complemento;

    @NotEmpty
    private String cidade;

    @NotNull
    @Positive
    @ExistsId(domainClass = Pais.class, message = "{field.validation.pais.not-exists}")
    private Long idPais;

    @Positive
    @ExistsId(domainClass = Estado.class, message = "{field.validation.estado.not-exists}")
    private Long idEstado;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cep;

    public ClienteForm(String nome, String sobrenome, String email, String documento, String endereco, String complemento, String cidade, Long idPais, Long idEstado, String telefone, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente converter(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, idPais);

        Cliente cliente = new Cliente(this.nome,
                this.sobrenome,
                this.email,
                this.documento,
                this.endereco,
                this.complemento, this.cidade,
                pais,
                this.telefone,
                this.cep);

        if (idEstado != null) {
            Estado estado = entityManager.find(Estado.class, idEstado);

            estado.perteceAoPais(pais);

            cliente.setEstado(estado);
        }

        return cliente;
    }


}
