package casadocodigo.controllers;

import casadocodigo.controllers.dto.DetalhesLivroDto;
import casadocodigo.controllers.dto.LivroDto;
import casadocodigo.controllers.form.LivroForm;
import casadocodigo.entities.Livro;
import casadocodigo.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    EntityManager entityManager;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroForm livroForm) {
        Livro livro = livroForm.converter(entityManager);

        livroRepository.save(livro);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<LivroDto> lista(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Livro> livrosPage = livroRepository.findAll(pageable);

        return LivroDto.converter(livrosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesLivroDto> detalhar(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()) {
            return ResponseEntity.ok(new DetalhesLivroDto(livro.get()));
        }

        return ResponseEntity.notFound().build();
    }

}
