package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.PartnerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PartnerResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.PartnerMapper;
import tn.ooredoo.amir3tiers.entity.Partner;
import tn.ooredoo.amir3tiers.entity.UserAccount;
import tn.ooredoo.amir3tiers.repository.PartnerRepository;
import tn.ooredoo.amir3tiers.repository.UserAccountRepository;
import tn.ooredoo.amir3tiers.service.PartnerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepo;
    private final UserAccountRepository userAccountRepo;
    private final PartnerMapper mapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public PartnerResponseDTO create(PartnerRequestDTO dto) {

        boolean userExists = userAccountRepo.existsById(dto.userUsername());
        if (userExists) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est déjà attribué");
        }

        // Create new UserAccount
        UserAccount user = UserAccount.builder()
                .username(dto.userUsername())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .status("Active")
                .userType("PARTNER")
                .build();

        user = userAccountRepo.save(user);

        // Map PartnerRequestDTO + user to Partner entity
        Partner entity = mapper.toEntity(dto, user);

        // Save and return response
        return mapper.toResponse(partnerRepo.save(entity));
    }

    @Override
    public PartnerResponseDTO update(Long id, PartnerRequestDTO dto) {
        Partner existing = partnerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partner not found"));

        UserAccount user = userAccountRepo.findById(dto.userUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        mapper.updateEntity(existing, dto, user);
        return mapper.toResponse(partnerRepo.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!partnerRepo.existsById(id)) {
            throw new EntityNotFoundException("Partner not found");
        }
        partnerRepo.deleteById(id);
    }

    @Override
    public PartnerResponseDTO getById(Long id) {
        return partnerRepo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Partner not found"));
    }

    @Override
    public List<PartnerResponseDTO> getAll() {
        return partnerRepo.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}