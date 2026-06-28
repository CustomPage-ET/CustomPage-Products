package custompage.products.listener;

import custompage.products.service.IProductoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductStockListener {

    private final IProductoService productoService;

    public ProductStockListener(IProductoService productoService) {
        this.productoService = productoService;
    }

    @KafkaListener(topics = "venta-realizada-topic", groupId = "products-group")
    public void listenVentaRealizada(String message) {
        try {
            System.out.println("Mensaje recibido desde Kafka: " + message);
            String[] data = message.split(":");
            String codigoSKU = data[0];
            Integer cantidadVendida = Integer.parseInt(data[1]);

            productoService.actualizarStock(codigoSKU, cantidadVendida);
            System.out.println("Stock actualizado correctamente para el SKU: " + codigoSKU);
        } catch (Exception e) {
            System.err.println("Error procesando el evento de Kafka: " + e.getMessage());
        }
    }
}