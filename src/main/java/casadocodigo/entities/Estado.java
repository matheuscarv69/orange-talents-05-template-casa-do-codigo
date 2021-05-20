package casadocodigo.entities;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Pais pais;

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    @Deprecated
    public Estado() {
    }

    public void perteceAoPais(Pais pais) {
        Assert.isTrue(this.pais.equals(pais), "O Estado " + this.nome + " não pertece ao Pais informado");
    }

}
