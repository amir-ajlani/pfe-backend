package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.PointOfSaleMapper;
import tn.ooredoo.amir3tiers.entity.Governorate;
import tn.ooredoo.amir3tiers.entity.Partner;
import tn.ooredoo.amir3tiers.entity.PointOfSale;
import tn.ooredoo.amir3tiers.repository.GovernorateRepository;
import tn.ooredoo.amir3tiers.repository.PartnerRepository;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.service.PointOfSaleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointOfSaleServiceImpl implements PointOfSaleService {

    private final PointOfSaleRepository repository;
    private final PartnerRepository partnerRepository;
    private final GovernorateRepository governorateRepository;
    private final PointOfSaleMapper mapper;

    @Override
    public PointOfSaleResponseDTO create(PointOfSaleRequestDTO dto) {
        Partner partner = partnerRepository.findById(dto.responsableId())
                .orElseThrow(() -> new EntityNotFoundException("Partner not found"));
        Governorate governorate = governorateRepository.findById(dto.governorateCode())
                .orElseThrow(() -> new EntityNotFoundException("Governorate not found"));
        long count = repository.countByGovernorate_Code(dto.governorateCode());
        String newCode = dto.governorateCode() + String.format("%03d", count + 1);
        PointOfSaleRequestDTO updatedDto = new PointOfSaleRequestDTO(
                newCode,
                dto.pointOfSaleName(),
                dto.address(),
                dto.city(),
                dto.telephone(),
                dto.saleType(),
                dto.status(),
                dto.responsableId(),
                dto.governorateCode()
        );
        PointOfSale entity = mapper.toEntity(updatedDto, partner, governorate);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public PointOfSaleResponseDTO update(String code, PointOfSaleRequestDTO dto) {
        PointOfSale existing = repository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("POS not found"));

        Partner partner = partnerRepository.findById(dto.responsableId())
                .orElseThrow(() -> new EntityNotFoundException("Partner not found"));
        Governorate governorate = governorateRepository.findById(dto.governorateCode())
                .orElseThrow(() -> new EntityNotFoundException("Governorate not found"));

        mapper.updateEntity(existing, dto, partner, governorate);
        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public void delete(String code) {
        if (!repository.existsById(code)) {
            throw new EntityNotFoundException("POS not found");
        }
        repository.deleteById(code);
    }

    @Override
    public PointOfSaleResponseDTO getByCode(String code) {
        return repository.findById(code)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("POS not found"));
    }

    @Override
    public List<PointOfSaleResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}