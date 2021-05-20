package casadocodigo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    //    @CPF -> comentada para facilitar os testes
    @Column(unique = true)
    private String documento;

    @NotEmpty
    private String endereco;

    @NotEmpty
    private String complemento;

    @NotEmpty
    private String cidade;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cep;

    public Cliente(String nome,
                   String sobrenome,
                   String email,
                   String documento,
                   String endereco,
                   String complemento,
                   String cidade,
                   Pais pais,
                   String telefone,
                   String cep) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }

    @Deprecated
    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
