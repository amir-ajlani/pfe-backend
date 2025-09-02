package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.IndividualCustomerResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.IndividualCustomerMapper;
import tn.ooredoo.amir3tiers.entity.IndividualCustomer;
import tn.ooredoo.amir3tiers.repository.IndividualCustomerRepository;
import tn.ooredoo.amir3tiers.service.IndividualCustomerService;

import java.util.List;
import java.util.UUID;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private final IndividualCustomerRepository repository;
    private final IndividualCustomerMapper mapper;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository repository, IndividualCustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public IndividualCustomerResponseDTO create(IndividualCustomerRequestDTO request) {
        IndividualCustomer saved=repository.save(mapper.toEntity(request));
        return mapper.toResponse(saved);
    }

    @Override
    public List<IndividualCustomerResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public IndividualCustomerResponseDTO getById(UUID id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
