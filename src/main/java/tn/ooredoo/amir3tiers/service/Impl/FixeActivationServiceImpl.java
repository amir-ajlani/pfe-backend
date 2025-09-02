package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.FixeActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FixeActivationResponse;
import tn.ooredoo.amir3tiers.Mappers.FixeActivationMapper;
import tn.ooredoo.amir3tiers.entity.*;
import tn.ooredoo.amir3tiers.repository.CustomerRepository;
import tn.ooredoo.amir3tiers.repository.FixeActivationRepository;
import tn.ooredoo.amir3tiers.repository.ParamServiceRepository;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.service.FixeActivationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixeActivationServiceImpl implements FixeActivationService {

    private final FixeActivationRepository repository;
    private final FixeActivationMapper mapper;
    private final CustomerRepository customerRepository;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final ParamServiceRepository paramServiceRepository;

    @Override
    public FixeActivationResponse create(FixeActivationRequest request) {
        FixeActivation activation = mapper.toEntity(request);
        Customer customer = customerRepository.findById(request.base().customerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + request.base().customerId()));
        // Récupération du Point of Sale
        PointOfSale pointOfSale = pointOfSaleRepository.findById(request.base().pointOfSaleCode())
                .orElseThrow(() -> new EntityNotFoundException("PointOfSale not found with code: " + request.base().pointOfSaleCode()));

        // Récupération du ParamService
        ParamService paramService = paramServiceRepository.findByTmcodeAndSncode(
                request.base().tmCode(), request.base().snCode()
        ).orElseThrow(() -> new EntityNotFoundException(
                "ParamService not found with tmCode: " + request.base().tmCode() + " and snCode: " + request.base().snCode()));
        activation.setActivationType(request.activationType());
        FixeActivation saved=repository.save(activation);
        return mapper.toResponse(saved);
    }

    @Override
    public List<FixeActivationResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }




    @Override
    public FixeActivationResponse getById(UUID id) {
        try {
            return repository.findById(id)
                    .map(mapper::toResponse)
                    .orElseThrow(() -> new IllegalArgumentException("Activation with ID " + id + " not found"));
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while retrieving activation with ID " + id, ex);
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            FixeActivation activation = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Activation with ID " + id + " not found"));

            repository.delete(activation);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while deleting activation with ID " + id, ex);
        }
    }
}