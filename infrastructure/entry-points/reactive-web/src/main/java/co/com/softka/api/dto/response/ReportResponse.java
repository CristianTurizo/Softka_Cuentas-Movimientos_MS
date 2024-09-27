package co.com.softka.api.dto.response;

import co.com.softka.api.dto.request.AccountDto;
import co.com.softka.api.dto.request.MovementsDto;
import co.com.softka.model.movements.Movement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ReportResponse extends AccountDto {
    private List<MovementsDto> movementList;
}
