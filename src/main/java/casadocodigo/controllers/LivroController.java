package casadocodigo.controllers;

import casadocodigo.controllers.form.LivroForm;
import casadocodigo.entities.Livro;
import casadocodigo.repositories.AutorRepository;
import casadocodigo.repositories.CategoriaRepository;
import casadocodigo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroForm livroForm) {
        Livro livro = livroForm.converter(categoriaRepository, autorRepository);

        livroRepository.save(livro);

        return ResponseEntity.ok().build();
    }

}
