package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.UserAccountRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountResponseDTO;
import tn.ooredoo.amir3tiers.entity.UserAccount;

import java.util.Date;

@Component
public class UserAccountMapper {

    public UserAccount toEntity(UserAccountRequestDTO dto) {
        return UserAccount.builder()
                .username(dto.username())
                .password(dto.password())
                .email(dto.email())
                .userType(dto.userType())
                .status(dto.status())
                .createdAt(new Date())
                .build();
    }

    public UserAccountResponseDTO toResponse(UserAccount entity) {
        return new UserAccountResponseDTO(
                entity.getUsername(),
                entity.getEmail(),
                entity.getUserType(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }

    public void updateEntity(UserAccount entity, UserAccountRequestDTO dto) {
        entity.setPassword(dto.password());
        entity.setEmail(dto.email());
        entity.setUserType(dto.userType());
        entity.setStatus(dto.status());
    }
}