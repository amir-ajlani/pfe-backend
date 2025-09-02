package tn.ooredoo.amir3tiers.Mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.ActivationResponse;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationResponse;
import tn.ooredoo.amir3tiers.entity.BoxActivation;

@Component
@RequiredArgsConstructor
public class BoxActivationMapper {

    private final ActivationMapper baseMapper;

    public BoxActivation toEntity(BoxActivationRequest dto) {
        BoxActivation entity = new BoxActivation();
        baseMapper.mapBaseFields(entity, dto.base());
        entity.setBoxSerialNumber(dto.boxSerialNumber());
        entity.setAdslLogin(dto.adslLogin());
        entity.setAdslPassword(dto.adslPassword());
        return entity;
    }

    public BoxActivationResponse toResponse(BoxActivation entity) {
        ActivationResponse base = baseMapper.toResponse(entity);
        return new BoxActivationResponse(
                base,
                entity.getBoxSerialNumber(),
                entity.getAdslLogin(),
                entity.getAdslPassword()
        );
    }
}
