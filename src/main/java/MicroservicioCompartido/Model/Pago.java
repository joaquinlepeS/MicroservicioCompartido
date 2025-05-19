package MicroservicioCompartido.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe seleccionar un tipo de clase")
    private ClassType tipoClase;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe seleccionar un método de pago")
    private PaymentMethod metodoPago;

    @Positive
    private int montoPagado;

    private LocalDateTime fechaPago;

    @PrePersist
    public void asignarDatos() {
        if (this.fechaPago == null) {
            this.fechaPago = LocalDateTime.now();
        }
        // Se asegura que el monto pagado sea el del tipo de clase
        if (this.tipoClase != null) {
            this.montoPagado = this.tipoClase.getPrice();
        }
    }
}

//}
  //"nombreCliente": "Ana López",
  //"rut": "21.345.987-K",
  //"tipoClase": "YOGA", puede ser CALISTENIA,YOGA,MMA,ACROBACIA
  //"metodoPago": "TRANSFERENCIA",
  //"fechaHoraClase": "2025-05-20T18:00:00"
//}
