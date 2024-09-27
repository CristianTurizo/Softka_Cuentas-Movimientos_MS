package co.com.softka.model.movements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Movement {
    private Integer idMovement;
    private String date;
    private String type;
    private Integer amount;
    private Integer accountBalance;
    private Integer accountNumber;
}
