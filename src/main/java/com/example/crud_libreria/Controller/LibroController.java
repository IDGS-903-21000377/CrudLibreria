package com.example.crud_libreria.Controller;

import com.example.crud_libreria.Model.Libro;
import com.example.crud_libreria.Service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    // READ - Listar todos
    @GetMapping
    public List<Libro> listar() {
        return service.listar();
    }

    // READ - Buscar por código
    @GetMapping("/{codigo}")
    public Libro buscarPorCodigo(@PathVariable String codigo) {
        return service.buscarPorCodigo(codigo);
    }

    // CREATE
    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        return service.guardar(libro);
    }

    // UPDATE
    @PutMapping("/{codigo}")
    public Libro actualizar(
            @PathVariable String codigo,
            @RequestBody Libro libro) {

        Libro existente = service.buscarPorCodigo(codigo);

        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setGenero(libro.getGenero());
        existente.setAnio(libro.getAnio());

        return service.guardar(existente);
    }

    // DELETE
    @DeleteMapping("/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        service.eliminarPorCodigo(codigo);
    }

    // BUSCAR por título, autor o género
    @GetMapping("/buscar")
    public List<Libro> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String genero) {

        return service.buscar(titulo, autor, genero);
    }
}