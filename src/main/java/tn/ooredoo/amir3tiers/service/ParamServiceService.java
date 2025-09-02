package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.ParamServiceDto;
import tn.ooredoo.amir3tiers.DTOS.ParamServiceResponseDTO;

import java.util.List;

public interface ParamServiceService {

    ParamServiceResponseDTO create(ParamServiceDto dto);

    List<ParamServiceResponseDTO> findAll();

    ParamServiceResponseDTO findById(Integer id);

    ParamServiceResponseDTO findByTmcodeAndSncode(String tmcode, String sncode);

    ParamServiceResponseDTO update(Integer id, ParamServiceDto dto);

    void delete(Integer id);

    // ✅ Extra utilities for tmcode/sncode
    boolean existsByTmcodeAndSncode(String tmcode, String sncode);

    ParamServiceResponseDTO createWithTmcodeSncode(String tmcode, String sncode, ParamServiceDto dto);
}
