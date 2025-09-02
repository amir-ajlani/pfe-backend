package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceDto;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceResponseDTO;
import tn.ooredoo.amir3tiers.entity.ParamService;

import java.math.BigDecimal;

@Component
public class ParamServiceMapper {

    public ParamService toEntity(ParamServiceDto dto) {
        ParamService entity = new ParamService();
        entity.setType(dto.type());
        entity.setOffer(dto.offre());
        entity.setTmcode(dto.tmcode());
        entity.setSncode(dto.sncode());
        entity.setPrice(dto.prix() != null ? new BigDecimal(String.valueOf(dto.prix())) : null);
        entity.setDebit(dto.debit());
        return entity;
    }

    public ParamServiceResponseDTO toResponse(ParamService entity) {
        return new ParamServiceResponseDTO(
                entity.getIdPack(),
                entity.getTmcode(),
                entity.getSncode(),
                entity.getOffer(),
                entity.getType(),
                entity.getPrice(),
                entity.getDebit()
        );
    }

    public void updateEntity(ParamService entity, ParamServiceDto dto) {
        entity.setType(dto.type());
        entity.setOffer(dto.offre());
        entity.setTmcode(dto.tmcode());
        entity.setSncode(dto.sncode());
        entity.setPrice(dto.prix() != null ? new BigDecimal(String.valueOf(dto.prix())) : null);
        entity.setDebit(dto.debit());
    }
}
