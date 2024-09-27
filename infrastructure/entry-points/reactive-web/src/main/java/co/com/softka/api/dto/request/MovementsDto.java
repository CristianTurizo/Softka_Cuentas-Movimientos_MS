package co.com.softka.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class MovementsDto {
    private Integer idMovement;
    private String date;
    private String type;
    private Integer amount;
    private Integer accountBalance;
    private Integer accountNumber;
}
