package custompage.products.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false, unique = true, length = 50)
    private String codigoSKU;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private Integer stockActual;

    @Column(nullable = false)
    private Long idEmpresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
}