package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.PointOfSaleRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.PointOfSaleResponseDTO;

import java.util.List;

public interface PointOfSaleService {
    PointOfSaleResponseDTO create(PointOfSaleRequestDTO request);
    PointOfSaleResponseDTO update(String code, PointOfSaleRequestDTO request);
    void delete(String code);
    PointOfSaleResponseDTO getByCode(String code);
    List<PointOfSaleResponseDTO> getAll();
}