package custompage.products.controller;

import custompage.products.dto.CategoriaDTO;
import custompage.products.service.ICategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@Validated @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO creada = categoriaService.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }
}