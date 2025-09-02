package tn.ooredoo.amir3tiers.Mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.ActivationRequest;
import tn.ooredoo.amir3tiers.DTOS.ActivationResponse;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.BusinessCustomer;
import tn.ooredoo.amir3tiers.entity.IndividualCustomer;
import tn.ooredoo.amir3tiers.repository.CustomerRepository;
import tn.ooredoo.amir3tiers.repository.ParamServiceRepository;
import tn.ooredoo.amir3tiers.repository.PointOfSaleRepository;

@Component
@RequiredArgsConstructor
public class ActivationMapper {

    private final CustomerRepository customerRepository;
    private final PointOfSaleRepository pointOfSaleRepository;
    private final ParamServiceRepository paramServiceRepository;

    public void mapBaseFields(Activation entity, ActivationRequest dto) {
        entity.setIccid(dto.iccid());
        entity.setActivationDate(dto.activationDate());
        entity.setStatus(dto.status());
        entity.setFileBlobIdentiteV(dto.fileBlobIdentiteV());
        entity.setFileBlobIdentiteR(dto.fileBlobIdentiteR());
        entity.setTypeFileBlobIdentite(dto.typeFileBlobIdentite());
        entity.setMsisdnVoieIp(dto.msisdnVoieIp());
        entity.setIdBscsTransaction(dto.idBscsTransaction());
        entity.setPhoneNumber(dto.phoneNumber());
        entity.setCity(dto.city());
        entity.setGovernorate(dto.governorate());
        entity.setDelegation(dto.delegation());
        entity.setKitCode(dto.kitCode());
        entity.setSnCodeCpe(dto.snCodeCpe());

        entity.setCustomer(customerRepository.findById(dto.customerId()).orElse(null));
        entity.setPointOfSale(pointOfSaleRepository.findById(dto.pointOfSaleCode()).orElse(null));
        entity.setParamService(paramServiceRepository.findByTmcodeAndSncode(dto.tmCode(), dto.snCode()).orElse(null));
    }

    public ActivationResponse toResponse(Activation entity) {
        String customerName = null;

        if (entity.getCustomer() instanceof BusinessCustomer businessCustomer) {
            customerName = businessCustomer.getBusinessName();
        } else if (entity.getCustomer() instanceof IndividualCustomer individualCustomer) {
            customerName = individualCustomer.getFirstName() + " " + individualCustomer.getLastName();
        }

        return new ActivationResponse(
                entity.getId(),
                entity.getIccid(),
                entity.getActivationDate(),
                entity.getStatus(),
                entity.getCustomer() != null ? entity.getCustomer().getId() : null,
                customerName,
                entity.getPointOfSale() != null ? entity.getPointOfSale().getPointOfSaleCode() : null,
                entity.getPointOfSale() != null ? entity.getPointOfSale().getPointOfSaleName() : null,
                entity.getMsisdnVoieIp(),
                entity.getPhoneNumber(),
                entity.getKitCode(),
                entity.getGovernorate(),
                entity.getCity(),
                entity.getDelegation(),
                entity.getSnCodeCpe(),
                entity.getParamService() != null ? entity.getParamService().getOffer() : null,
                entity.getParamService() != null ? entity.getParamService().getType() : null,
                entity.getParamService() != null ? entity.getParamService().getTmcode() : null,
                entity.getParamService() != null ? entity.getParamService().getSncode() : null
        );
    }

}