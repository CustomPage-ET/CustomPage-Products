package custompage.products.service;

import custompage.products.dto.CategoriaDTO;
import custompage.products.model.Categoria;
import custompage.products.repository.CategoriaRepository;
import custompage.products.service.ICategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        if (categoriaRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new RuntimeException("La categoría ya existe con el nombre: " + dto.getNombre());
        }

        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        Categoria guardada = categoriaRepository.save(categoria);
        return convertToDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> obtenerTodas() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO obtenerPorId(Long id) {
        Categoria cat = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return convertToDTO(cat);
    }

    private CategoriaDTO convertToDTO(Categoria cat) {
        return CategoriaDTO.builder()
                .idCategoria(cat.getIdCategoria())
                .nombre(cat.getNombre())
                .descripcion(cat.getDescripcion())
                .build();
    }
}