package casadocodigo.entities;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {

    }

    public String getNome() {
        return nome;
    }
}
