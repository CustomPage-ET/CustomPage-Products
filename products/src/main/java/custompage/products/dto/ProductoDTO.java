package custompage.products.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long idProducto;

    @NotBlank(message = "El código SKU es obligatorio")
    private String codigoSKU;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal precioBase;

    @NotNull(message = "El stock inicial es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stockActual;

    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long idEmpresa;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;

    private String nombreCategoria;
}