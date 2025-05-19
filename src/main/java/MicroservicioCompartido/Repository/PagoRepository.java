package MicroservicioCompartido.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MicroservicioCompartido.Model.Pago;
import java.time.LocalDateTime;


@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByNombreCliente(String nombreCliente);
    List<Pago> findByRut(String rut);
    List<Pago> findByFechaPago(LocalDateTime fechaPago);
    List<Pago> findByTipoClase(String tipoClase);
    List<Pago> findByMetodoPago(String metodoPago);
    List<Pago> findByMontoPagado(int montoPagado);
    

     

}
