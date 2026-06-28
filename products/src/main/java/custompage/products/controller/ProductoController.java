package custompage.products.controller;

import custompage.products.dto.ProductoDTO;
import custompage.products.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Validated @RequestBody ProductoDTO productoDTO) {
        ProductoDTO creado = productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<ProductoDTO>> listarPorEmpresa(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(productoService.obtenerProductosPorEmpresa(idEmpresa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorId(id));
    }
}