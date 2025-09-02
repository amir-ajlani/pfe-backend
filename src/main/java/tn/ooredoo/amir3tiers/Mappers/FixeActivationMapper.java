package tn.ooredoo.amir3tiers.Mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.*;
import tn.ooredoo.amir3tiers.entity.BoxActivation;
import tn.ooredoo.amir3tiers.entity.FixeActivation;

@Component
@RequiredArgsConstructor
public class FixeActivationMapper {
    private final ActivationMapper baseMapper;

    public FixeActivation toEntity(FixeActivationRequest dto) {
        FixeActivation entity = new FixeActivation();
        baseMapper.mapBaseFields(entity, dto.base());
        entity.setActivationType(dto.activationType());
        return entity;
    }

    public FixeActivationResponse toResponse(FixeActivation entity) {
        ActivationResponse base = baseMapper.toResponse(entity);
        return new FixeActivationResponse(
                base,
                entity.getActivationType()
        );
    }
}
