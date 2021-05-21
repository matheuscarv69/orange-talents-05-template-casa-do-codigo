package casadocodigo.controllers.form.cliente;

import casadocodigo.configs.validation.customValidation.estadoInPais.EstadoInPais;
import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.controllers.form.localidade.LocalidadeForm;
import casadocodigo.entities.Cliente;
import casadocodigo.entities.Estado;
import casadocodigo.entities.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

    @EstadoInPais(message = "{field.validation.estado.not-exists-in-pais}")
    private LocalidadeForm localidade;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cep;

    public ClienteForm(String nome, String sobrenome, String email, String documento, String endereco, String complemento, String cidade, LocalidadeForm localidade, String telefone, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.localidade = localidade;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente converter(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, localidade.getIdPais());

        Cliente cliente = new Cliente(this.nome,
                this.sobrenome,
                this.email,
                this.documento,
                this.endereco,
                this.complemento, this.cidade,
                pais,
                this.telefone,
                this.cep);

        if (localidade.getIdEstado() != null) {
            Estado estado = entityManager.find(Estado.class, localidade.getIdEstado());

            estado.perteceAoPais(pais);

            cliente.setEstado(estado);
        }

        return cliente;
    }
}
