package co.com.softka.r2dbc.account.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "cuenta", schema = "public")
public class AccountEntity {

    @Id
    @Column("numerocuenta")
    private Integer accountNumber;

    @Column("tipocuenta")
    private String tipoCuenta;

    @Column("saldoinicial")
    private Integer balance;

    private Boolean estado;

    @Column("idcliente")
    private Integer idCliente;
}
