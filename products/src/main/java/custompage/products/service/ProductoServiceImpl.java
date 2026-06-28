package custompage.products.service;

import custompage.products.dto.ProductoDTO;
import custompage.products.model.Categoria;
import custompage.products.model.Producto;
import custompage.products.repository.CategoriaRepository;
import custompage.products.repository.ProductoRepository;
import custompage.products.service.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = Producto.builder()
                .codigoSKU(dto.getCodigoSKU())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precioBase(dto.getPrecioBase())
                .stockActual(dto.getStockActual())
                .idEmpresa(dto.getIdEmpresa())
                .categoria(categoria)
                .build();

        Producto guardado = productoRepository.save(producto);
        return convertToDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosPorEmpresa(Long idEmpresa) {
        return productoRepository.findByIdEmpresa(idEmpresa).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto prod = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return convertToDTO(prod);
    }

    @Override
    @Transactional
    public void actualizarStock(String codigoSKU, Integer cantidadVendida) {
        Producto producto = productoRepository.findByCodigoSKU(codigoSKU)
                .orElseThrow(() -> new RuntimeException("SKU no encontrado para actualizar stock"));

        if (producto.getStockActual() < cantidadVendida) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        producto.setStockActual(producto.getStockActual() - cantidadVendida);
        productoRepository.save(producto);
    }

    private ProductoDTO convertToDTO(Producto prod) {
        return ProductoDTO.builder()
                .idProducto(prod.getIdProducto())
                .codigoSKU(prod.getCodigoSKU())
                .nombre(prod.getNombre())
                .descripcion(prod.getDescripcion())
                .precioBase(prod.getPrecioBase())
                .stockActual(prod.getStockActual())
                .idEmpresa(prod.getIdEmpresa())
                .idCategoria(prod.getCategoria().getIdCategoria())
                .nombreCategoria(prod.getCategoria().getNombre())
                .build();
    }
}