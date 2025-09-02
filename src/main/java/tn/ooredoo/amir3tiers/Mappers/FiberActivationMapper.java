package tn.ooredoo.amir3tiers.Mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.*;
import tn.ooredoo.amir3tiers.entity.FiberActivation;


@Component
@RequiredArgsConstructor
public class FiberActivationMapper {
    private final ActivationMapper baseMapper;

    public FiberActivation toEntity(FiberActivationRequest dto) {
        FiberActivation entity = new FiberActivation();
        baseMapper.mapBaseFields(entity, dto.base());
        entity.setOntSerialNumber(dto.ontSerialNumber());
        return entity;
    }

    public FiberActivationResponse toResponse(FiberActivation entity) {
        ActivationResponse base = baseMapper.toResponse(entity);
        return new FiberActivationResponse(
                base,
                entity.getOntSerialNumber()
        );
    }
}
