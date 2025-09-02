package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.BusinessCustomerResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.BusinessCustomerMapper;
import tn.ooredoo.amir3tiers.entity.BusinessCustomer;
import tn.ooredoo.amir3tiers.repository.BusinessCustomerRepository;
import tn.ooredoo.amir3tiers.service.BusinessCustomerService;

import java.util.List;
import java.util.UUID;

@Service
public class BusinessCustomerServiceImpl implements BusinessCustomerService {

    private final BusinessCustomerRepository repository;
    private final BusinessCustomerMapper mapper;

    public BusinessCustomerServiceImpl(BusinessCustomerRepository repository, BusinessCustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BusinessCustomerResponseDTO create(BusinessCustomerRequestDTO request) {
        BusinessCustomer saved = repository.save(mapper.toEntity(request));
        return mapper.toResponse(saved);
    }

    @Override
    public List<BusinessCustomerResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public BusinessCustomerResponseDTO getById(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
