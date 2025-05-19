package MicroservicioCompartido.MicroservicioCompartido.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MicroservicioCompartido.MicroservicioCompartido.Model.Pago;
import MicroservicioCompartido.MicroservicioCompartido.Repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }
    public Pago obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }
    public List<Pago> obtenerPagosPorNombreCliente(String nombreCliente) {
        return pagoRepository.findByNombreCliente(nombreCliente);
    }

    public void guardarPago(Pago pago) {
        pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }

}
