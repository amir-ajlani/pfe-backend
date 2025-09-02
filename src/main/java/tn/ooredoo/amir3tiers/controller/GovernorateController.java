package tn.ooredoo.amir3tiers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.ooredoo.amir3tiers.entity.Governorate;
import tn.ooredoo.amir3tiers.service.GovernorateService;

import java.util.List;

@RestController
@RequestMapping("/api/governorate")
@RequiredArgsConstructor
public class GovernorateController {

    private final GovernorateService governorateService;

    @GetMapping
    public ResponseEntity<List<Governorate>> getAllGovernorate(){
        return new ResponseEntity<>(governorateService.getAllGovernorate(),HttpStatus.OK);
    }
}
