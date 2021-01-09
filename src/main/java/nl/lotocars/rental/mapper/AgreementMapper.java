package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.entities.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class AgreementMapper {

    public abstract AgreementDto mapToDestination(Agreement source);

    public abstract Agreement mapToSource(AgreementDto destination);
}
