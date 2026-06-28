package custompage.products.service;

import custompage.products.dto.CategoriaDTO;
import java.util.List;

public interface ICategoriaService {
    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);
    List<CategoriaDTO> obtenerTodas();
    CategoriaDTO obtenerPorId(Long id);
}