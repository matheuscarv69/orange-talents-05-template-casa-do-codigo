package casadocodigo.controllers.dto.autor;

import casadocodigo.entities.Autor;

public class DetalhesAutorDto {

    private String nome;
    private String descricao;

    public DetalhesAutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
