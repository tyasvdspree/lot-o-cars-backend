package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.entities.Agreement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AgreementMapper {

    public abstract AgreementDto mapToDestination(Agreement source);

    public abstract Agreement mapToSource(AgreementDto destination);
}
