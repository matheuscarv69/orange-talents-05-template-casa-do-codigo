package casadocodigo.controllers.form;

import casadocodigo.configs.validation.customValidation.exists.Exist;
import casadocodigo.configs.validation.customValidation.uniqueValue.UniqueValue;
import casadocodigo.entities.Autor;
import casadocodigo.entities.Categoria;
import casadocodigo.entities.Livro;
import casadocodigo.repositories.AutorRepository;
import casadocodigo.repositories.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

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
    private Double preco;

    @Min(100)
    private Integer numeroPaginas;

    //    @ISBN(type = ISBN.Type.ISBN_13)
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "{field.validation.livro-isbn.duplicated}")
    private String isbn;

    @Future(message = "{field.validation.livro-data.futura}")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @Exist(domainClass = Categoria.class, message = "{field.validation.categoria.not-exists}")
    private Long categoria;

    @NotNull
    @Exist(domainClass = Autor.class, message = "{field.validation.autor.not-exists}")
    private Long autor;

    public LivroForm(String titulo, String resumo, String sumario, Double preco, Integer numeroPaginas, String isbn, Long categoria, Long autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autor = autor;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Livro converter(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        Categoria categoria = categoriaRepository.findById(Long.valueOf(this.categoria)).get();
        Autor autor = autorRepository.findById(Long.valueOf(this.autor)).get();
        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas, this.isbn, this.dataPublicacao, categoria, autor);
    }
}
