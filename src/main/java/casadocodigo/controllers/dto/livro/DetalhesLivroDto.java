package casadocodigo.controllers.dto.livro;

import casadocodigo.controllers.dto.autor.DetalhesAutorDto;
import casadocodigo.entities.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DetalhesLivroDto {

    private String titulo;
    private String resumo;
    private String sumario;
    private Double preco;
    private String isbn;
    private Integer numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    private DetalhesAutorDto autor;
    private String categoria;

    public DetalhesLivroDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.autor = new DetalhesAutorDto(livro.getAutor());
        this.categoria = livro.getCategoria().getNome();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public DetalhesAutorDto getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }
}
