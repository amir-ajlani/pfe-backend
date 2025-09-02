package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.FixeKitBatchCreateRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.FixeKitResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.PosProductStockMapper;
import tn.ooredoo.amir3tiers.entity.FixeKit;
import tn.ooredoo.amir3tiers.entity.KitStatus;
import tn.ooredoo.amir3tiers.entity.PointOfSale;
import tn.ooredoo.amir3tiers.entity.Product;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.repository.PosProductStockRepository;
import tn.ooredoo.amir3tiers.repository.ProductRepository;
import tn.ooredoo.amir3tiers.service.PosProductStockService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PosProductStockServiceImpl implements PosProductStockService {

    private final PosProductStockRepository repository;
    private PointOfSaleRepository pointOfSaleRepository;
    private ProductRepository productRepository;
    private final PosProductStockMapper mapper;

    @Override
    public FixeKitResponseDTO create(FixeKitRequestDTO request) {
        FixeKit entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public FixeKitResponseDTO update(String id, FixeKitRequestDTO request) {
        FixeKit existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id: " + id));

        mapper.updateEntity(existing, request);
        existing = repository.save(existing);
        return mapper.toResponse(existing);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Stock not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public FixeKitResponseDTO getById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found with id: " + id));
    }

    @Override
    public List<FixeKitResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FixeKitResponseDTO> createBatch(FixeKitBatchCreateRequestDTO request) {
        PointOfSale pos = pointOfSaleRepository.findById(request.pointOfSaleCode())
                .orElseThrow(() -> new IllegalArgumentException("PointOfSale not found"));

        Product product = productRepository.findById(request.productCode())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String prefix = request.startKit().replaceAll("[0-9]", "");
        int start = Integer.parseInt(request.startKit().replaceAll("\\D+", ""));
        int end = Integer.parseInt(request.endKit().replaceAll("\\D+", ""));

        if (start > end) throw new IllegalArgumentException("StartKit must be less than EndKit");

        List<FixeKit> entities = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            String kit = String.format("%s%04d", prefix, i);
            FixeKit entity = new FixeKit();
            entity.setKit(kit);
            entity.setPointOfSale(pos);
            entity.setProduct(product);
            entity.setStatus(new KitStatus("NEW", new Date()));
            entities.add(entity);
        }

        List<FixeKit> saved = repository.saveAll(entities);
        return saved.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<FixeKitResponseDTO> getByProductAndOptionalPOS(String productCode, String pointOfSaleCode) {
        List<FixeKit> results;

        if (pointOfSaleCode == null || pointOfSaleCode.isBlank()) {
            results = repository.findByProduct_Code(productCode);
        } else {
            results = repository.findByProduct_CodeAndPointOfSale_PointOfSaleCode(productCode, pointOfSaleCode);
        }

        return results.stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
