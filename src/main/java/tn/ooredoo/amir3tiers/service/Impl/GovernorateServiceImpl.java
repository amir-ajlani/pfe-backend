package tn.ooredoo.amir3tiers.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.entity.Governorate;
import tn.ooredoo.amir3tiers.repository.GovernorateRepository;
import tn.ooredoo.amir3tiers.service.GovernorateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GovernorateServiceImpl implements GovernorateService {

    private final GovernorateRepository governorateRepository;

    @Override
    public List<Governorate> getAllGovernorate() {
        return governorateRepository.findAll();
    }
}
