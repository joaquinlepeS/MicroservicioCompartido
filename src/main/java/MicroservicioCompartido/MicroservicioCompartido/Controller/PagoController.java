package MicroservicioCompartido.MicroservicioCompartido.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import MicroservicioCompartido.MicroservicioCompartido.Model.Pago;
import MicroservicioCompartido.MicroservicioCompartido.Service.PagoService;


@RestController
@RequestMapping("/api/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> Listar() {
        List<Pago> pagos = pagoService.findAll();
        if(pagos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPagoPorId(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    @GetMapping("/nombreCliente/{nombreCliente}")
    public ResponseEntity<List<Pago>> obtenerPagosPorNombreCliente(@PathVariable String nombreCliente) {
        List<Pago> pagos = pagoService.obtenerPagosPorNombreCliente(nombreCliente);
        if (pagos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Pago> guardarPago(@RequestBody Pago pago) {
        if (pago == null) {
            return ResponseEntity.badRequest().build();
        }
        pagoService.guardarPago(pago);
        return ResponseEntity.ok(pago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        Pago pagoExistente = pagoService.obtenerPagoPorId(id);
        if (pagoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        pago.setId(id);
        pagoService.guardarPago(pago);
        return ResponseEntity.ok(pago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPagoPorId(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pago> actualizarPagoParcial(@PathVariable Long id, @RequestBody Pago pago) {
        Pago pagoExistente = pagoService.obtenerPagoPorId(id);
        if (pagoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        if (pago.getNombreCliente() != null) {
            pagoExistente.setNombreCliente(pago.getNombreCliente());
        }
        if (pago.getRut() != null) {
            pagoExistente.setRut(pago.getRut());
        }
        if (pago.getTipoClase() != null) {
            pagoExistente.setTipoClase(pago.getTipoClase());
        }
        if (pago.getMetodoPago() != null) {
            pagoExistente.setMetodoPago(pago.getMetodoPago());
        }
        if (pago.getMontoPagado() > 0) {
            pagoExistente.setMontoPagado(pago.getMontoPagado());
        }
        pagoService.guardarPago(pagoExistente);
        return ResponseEntity.ok(pagoExistente);
    }
}
