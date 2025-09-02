package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.BoxActivationResponse;
import tn.ooredoo.amir3tiers.Mappers.BoxActivationMapper;
import tn.ooredoo.amir3tiers.entity.BoxActivation;
import tn.ooredoo.amir3tiers.entity.Customer;
import tn.ooredoo.amir3tiers.entity.ParamService;
import tn.ooredoo.amir3tiers.entity.PointOfSale;
import tn.ooredoo.amir3tiers.repository.BoxActivationRepository;
import tn.ooredoo.amir3tiers.repository.CustomerRepository;
import tn.ooredoo.amir3tiers.repository.ParamServiceRepository;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;
import tn.ooredoo.amir3tiers.service.BoxActivationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxActivationServiceImpl implements BoxActivationService {

    private final BoxActivationRepository repository;
    private final BoxActivationMapper mapper;
    private final CustomerRepository customerRepository;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final ParamServiceRepository paramServiceRepository;

    @Override
    public BoxActivationResponse create(BoxActivationRequest request) {
        BoxActivation activation = mapper.toEntity(request);
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
        activation.setAdslLogin(request.adslLogin());
        activation.setAdslPassword(request.adslPassword());
        activation.setBoxSerialNumber(request.boxSerialNumber());
        BoxActivation saved=repository.save(activation);
        return mapper.toResponse(saved);
    }

    @Override
    public List<BoxActivationResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BoxActivationResponse getById(UUID id) {
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
            BoxActivation activation = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Activation with ID " + id + " not found"));

            repository.delete(activation);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Database error while deleting activation with ID " + id, ex);
        }
    }
}
