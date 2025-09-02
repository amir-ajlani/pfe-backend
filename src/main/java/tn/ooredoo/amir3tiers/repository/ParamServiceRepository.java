package tn.ooredoo.amir3tiers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.ooredoo.amir3tiers.entity.ParamService;

import java.util.Optional;

public interface ParamServiceRepository extends JpaRepository<ParamService,Integer> {
    Optional<ParamService> findByTmcodeAndSncode(String tmCode, String snCode);
    boolean existsByTmcodeAndSncode(String tmcode, String sncode);
}
