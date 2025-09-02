package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.FiberActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.FiberActivationResponse;
import tn.ooredoo.amir3tiers.Mappers.FiberActivationMapper;
import tn.ooredoo.amir3tiers.entity.*;
import tn.ooredoo.amir3tiers.enums.ActivationStatus;
import tn.ooredoo.amir3tiers.repository.CustomerRepository;
import tn.ooredoo.amir3tiers.repository.FiberActivationRepository;
import tn.ooredoo.amir3tiers.repository.ParamServiceRepository;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.service.FiberActivationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FiberActivationServiceImpl implements FiberActivationService {

    private final FiberActivationRepository repository;
    private final FiberActivationMapper mapper;
    private final CustomerRepository customerRepository;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final ParamServiceRepository paramServiceRepository;

    @Override
    public FiberActivationResponse create(FiberActivationRequest request) {
        FiberActivation activation = mapper.toEntity(request);
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

        activation.setCustomer(customer);
        activation.setPointOfSale(pointOfSale);
        activation.setParamService(paramService);
        activation.setActivationDate(LocalDateTime.now());
        activation.setStatus(ActivationStatus.PENDING);
        activation.setOntSerialNumber(request.ontSerialNumber());

        FiberActivation saved = repository.save(activation);
        return mapper.toResponse(saved);
    }

    @Override
    public List<FiberActivationResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FiberActivationResponse getById(UUID id) {
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
            FiberActivation activation = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Activation with ID " + id + " not found"));

            repository.delete(activation);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while deleting activation with ID " + id, ex);
        }
    }

    @Override
    public List<FiberActivationResponse> findByCustomerId(UUID customerId) {
        return repository.findByCustomerId(customerId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<FiberActivationResponse> findByStatus(ActivationStatus status) {
        return repository.findByStatus(status)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}