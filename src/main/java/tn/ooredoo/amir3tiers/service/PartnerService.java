package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.PartnerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PartnerResponseDTO;

import java.util.List;

public interface PartnerService {
    PartnerResponseDTO create(PartnerRequestDTO dto);
    PartnerResponseDTO update(Long id, PartnerRequestDTO dto);
    void delete(Long id);
    PartnerResponseDTO getById(Long id);
    List<PartnerResponseDTO> getAll();
}