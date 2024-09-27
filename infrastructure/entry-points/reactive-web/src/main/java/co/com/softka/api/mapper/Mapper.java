package co.com.softka.api.mapper;

import co.com.softka.api.dto.request.AccountDto;
import co.com.softka.api.dto.request.MovementsDto;
import co.com.softka.api.dto.response.ReportResponse;
import co.com.softka.model.account.Account;
import co.com.softka.model.movements.Movement;
import co.com.softka.model.report.Report;
import org.mapstruct.Builder;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        imports = {LocalDateTime.class, DateTimeFormatter.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    Account toModel(AccountDto dto);
    Movement toModel(MovementsDto dto);

    AccountDto toDto(Account account);
    MovementsDto toDto(Movement movement);
    List<ReportResponse> toDto(List<Report> movement);


}
