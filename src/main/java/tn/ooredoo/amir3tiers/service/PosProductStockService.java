package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.FixeKitBatchCreateRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitResponseDTO;

import java.util.List;

public interface PosProductStockService {

    FixeKitResponseDTO create(FixeKitRequestDTO request);

    FixeKitResponseDTO update(String kit, FixeKitRequestDTO request);

    void delete(String kit);

    FixeKitResponseDTO getById(String kit);

    List<FixeKitResponseDTO> getAll();

    List<FixeKitResponseDTO> createBatch(FixeKitBatchCreateRequestDTO request);
    List<FixeKitResponseDTO> getByProductAndOptionalPOS(String productCode, String pointOfSaleCode);
}