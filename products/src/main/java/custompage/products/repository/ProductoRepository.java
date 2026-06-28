package custompage.products.repository;

import custompage.products.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigoSKU(String codigoSKU);
    List<Producto> findByIdEmpresa(Long idEmpresa);
}