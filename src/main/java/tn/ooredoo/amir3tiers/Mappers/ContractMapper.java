package tn.ooredoo.amir3tiers.Mappers;

import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.DTOS.ContractRequest;
import tn.ooredoo.amir3tiers.DTOS.ContractResponse;
import tn.ooredoo.amir3tiers.entity.Activation;
import tn.ooredoo.amir3tiers.entity.Contract;
import tn.ooredoo.amir3tiers.entity.Customer;

@Component
public class ContractMapper {

    public Contract toEntity(ContractRequest request, Customer customer, Activation activation) {
        return Contract.builder()
                .contractNumber(request.contractNumber())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(request.status())
                .customer(customer)
                .activation(activation)
                .build();
    }

    public ContractResponse toResponse(Contract contract) {
        return new ContractResponse(
                contract.getId(),
                contract.getContractNumber(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getStatus(),
                contract.getCustomer().getId(),
                contract.getActivation().getId(),
                contract.getContractPdfBlob(),
                contract.getContractPdfMimeType()
        );
    }
}