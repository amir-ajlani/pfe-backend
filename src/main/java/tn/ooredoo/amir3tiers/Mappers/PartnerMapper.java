package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.PartnerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PartnerResponseDTO;
import tn.ooredoo.amir3tiers.entity.Partner;
import tn.ooredoo.amir3tiers.entity.UserAccount;

@Component
public class PartnerMapper {

    public Partner toEntity(PartnerRequestDTO dto, UserAccount userAccount) {
        return Partner.builder()
                .name(dto.name())
                .phone(dto.phone())
                .email(dto.email())
                .userAccount(userAccount)
                .build();
    }

    public PartnerResponseDTO toResponse(Partner entity) {
        return new PartnerResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getUserAccount() != null ? entity.getUserAccount().getUsername() : null
        );
    }

    public void updateEntity(Partner entity, PartnerRequestDTO dto, UserAccount userAccount) {
        entity.setName(dto.name());
        entity.setPhone(dto.phone());
        entity.setEmail(dto.email());
        entity.setUserAccount(userAccount);
    }
}