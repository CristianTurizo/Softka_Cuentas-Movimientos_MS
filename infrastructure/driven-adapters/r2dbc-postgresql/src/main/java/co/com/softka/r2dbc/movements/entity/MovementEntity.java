package co.com.softka.r2dbc.movements.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(value = "movimiento", schema = "public")
public class MovementEntity {

    @Id
    @Column("idmovimiento")
    private Integer idMovement;
    @Column("fecha")
    private LocalDate date;
    @Column("tipomovimiento")
    private String type;
    @Column("valor")
    private Integer amount;
    @Column("saldo")
    private Integer accountBalance;
    @Column("cuentaid")
    private Integer accountNumber;
}
