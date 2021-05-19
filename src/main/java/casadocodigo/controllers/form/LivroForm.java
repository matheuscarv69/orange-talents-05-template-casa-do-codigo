package casadocodigo.controllers.form;

import casadocodigo.configs.validation.customValidation.exists.ExistsId;
import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Autor;
import casadocodigo.entities.Categoria;
import casadocodigo.entities.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class LivroForm {

    @NotEmpty
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "{field.validation.livro-titulo.duplicated}")
    private String titulo;

    @NotEmpty
    @Length(max = 500)
    private String resumo;

    @NotEmpty
    private String sumario;

    @Min(20)
    @Positive
    private Double preco;

    @Min(100)
    @Positive
    private Integer numeroPaginas;

    //  Comentado para facilitar os testes locais
    //  @ISBN(type = ISBN.Type.ISBN_13)
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "{field.validation.livro-isbn.duplicated}")
    private String isbn;

    @Future(message = "{field.validation.livro-data.futura}")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, message = "{field.validation.categoria.not-exists}")
    @Positive
    private Long idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class, message = "{field.validation.autor.not-exists}")
    @Positive
    private Long idAutor;

    public LivroForm(String titulo, String resumo, String sumario, Double preco, Integer numeroPaginas, String isbn, Long idCategoria, Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    // Setter para o Jackson conseguir pegar a data da requisição
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Livro converter(EntityManager entityManager) {
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        Autor autor = entityManager.find(Autor.class, idAutor);
        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas, this.isbn, this.dataPublicacao, categoria, autor);
    }
}
