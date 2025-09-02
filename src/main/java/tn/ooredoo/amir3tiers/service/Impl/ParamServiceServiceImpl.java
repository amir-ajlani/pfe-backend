package tn.ooredoo.amir3tiers.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceDto;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.ParamServiceMapper;
import tn.ooredoo.amir3tiers.entity.ParamService;
import tn.ooredoo.amir3tiers.repository.ParamServiceRepository;
import tn.ooredoo.amir3tiers.service.ParamServiceService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ParamServiceServiceImpl implements ParamServiceService {
    private final ParamServiceRepository repository;
    private final ParamServiceMapper mapper;

    @Override
    public ParamServiceResponseDTO create(ParamServiceDto dto) {
        if (repository.existsByTmcodeAndSncode(dto.tmcode(), dto.sncode())) {
            throw new IllegalArgumentException("ParamService with given tmcode and sncode already exists");
        }
        ParamService entity = mapper.toEntity(dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<ParamServiceResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ParamServiceResponseDTO findById(Integer id) {
        ParamService entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ParamService not found with id " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public ParamServiceResponseDTO findByTmcodeAndSncode(String tmcode, String sncode) {
        ParamService entity = repository.findByTmcodeAndSncode(tmcode, sncode)
                .orElseThrow(() -> new NoSuchElementException("ParamService not found for tmcode=" + tmcode + " and sncode=" + sncode));
        return mapper.toResponse(entity);
    }

    @Override
    public ParamServiceResponseDTO update(Integer id, ParamServiceDto dto) {
        ParamService entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ParamService not found with id " + id));
        mapper.updateEntity(entity, dto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("ParamService not found with id " + id);
        }
        repository.deleteById(id);
    }

    // ✅ Extra utilities for tmcode/sncode
    @Override
    public boolean existsByTmcodeAndSncode(String tmcode, String sncode) {
        return repository.existsByTmcodeAndSncode(tmcode, sncode);
    }

    @Override
    public ParamServiceResponseDTO createWithTmcodeSncode(String tmcode, String sncode, ParamServiceDto dto) {
        if (repository.existsByTmcodeAndSncode(tmcode, sncode)) {
            throw new IllegalArgumentException("Already exists with tmcode=" + tmcode + " and sncode=" + sncode);
        }
        ParamService entity = mapper.toEntity(dto);
        entity.setTmcode(tmcode);
        entity.setSncode(sncode);
        return mapper.toResponse(repository.save(entity));
    }
}
