package custompage.products.service;

import custompage.products.dto.ProductoDTO;
import java.util.List;

public interface IProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    List<ProductoDTO> obtenerTodosLosProductos();
    List<ProductoDTO> obtenerProductosPorEmpresa(Long idEmpresa);
    ProductoDTO obtenerProductoPorId(Long id);
    void actualizarStock(String codigoSKU, Integer cantidadVendida);
}