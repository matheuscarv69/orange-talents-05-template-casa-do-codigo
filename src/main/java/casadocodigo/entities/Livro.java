package casadocodigo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column(length = 500)
    private String resumo;

    // Para o banco se responsabilizar pelo tamanho da coluna
    @Lob
    private String sumario;

    @Min(20)
    private Double preco;

    @Min(100)
    private Integer numeroPaginas;

//    @ISBN(type = ISBN.Type.ISBN_13)
    @Column(unique = true)
    private String isbn;

    @Future(message = "{field.validation.livro-data.futura}")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Autor autor;

    public Livro(String titulo, String resumo, String sumario, Double preco, Integer numeroPaginas, String isbn, LocalDate dataPublicacao, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    @Deprecated
    public Livro() {
    }

}
