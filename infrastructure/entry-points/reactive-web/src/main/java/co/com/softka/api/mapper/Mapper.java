package co.com.softka.api.mapper;

import org.mapstruct.Builder;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@org.mapstruct.Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        imports = {LocalDateTime.class, DateTimeFormatter.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {
}
