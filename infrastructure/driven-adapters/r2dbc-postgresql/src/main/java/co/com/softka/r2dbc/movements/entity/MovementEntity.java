package co.com.softka.r2dbc.movements.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "movimiento", schema = "public")
public class MovementEntity {

    @Id
    @Column("idmovimiento")
    private Integer idMovement;
    @Column("fecha")
    private String date;
    @Column("tipomovimiento")
    private String type;
    @Column("valor")
    private Integer amount;
    @Column("saldo")
    private Integer accountBalance;
    @Column("cuentaid")
    private Integer accountNumber;
}
