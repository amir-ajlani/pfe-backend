package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.ContractRequest;
import tn.ooredoo.amir3tiers.DTOS.ContractResponse;
import tn.ooredoo.amir3tiers.Mappers.ContractMapper;
import tn.ooredoo.amir3tiers.entity.*;
import tn.ooredoo.amir3tiers.enums.ContractStatus;
import tn.ooredoo.amir3tiers.repository.ActivationRepository;
import tn.ooredoo.amir3tiers.repository.ContractRepository;
import tn.ooredoo.amir3tiers.repository.CustomerRepository;
import tn.ooredoo.amir3tiers.service.ContractService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final ActivationRepository activationRepository;
    private final ContractMapper mapper;

    @Override
    public ContractResponse create(ContractRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Activation activation = activationRepository.findById(request.activationId())
                .orElseThrow(() -> new EntityNotFoundException("Activation not found"));

        Contract contract = Contract.builder()
                .contractNumber(UUID.randomUUID().toString())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .status(ContractStatus.ACTIVE)
                .customer(customer)
                .activation(activation)
                .build();

        // Générer le PDF avec JasperReports
        byte[] pdf = generateContractPdf(contract);
        contract.setContractPdfBlob(pdf);

        return mapper.toResponse(contractRepository.save(contract));
    }

    @Override
    public List<ContractResponse> getByActivationType(Class<? extends Activation> activationType) {
        return contractRepository.findByActivationType(activationType).stream()
                .map(mapper::toResponse)
                .toList();
    }



    @Override
    public List<ContractResponse> getAll() {
        return contractRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ContractResponse getById(UUID id) {
        return contractRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found"));
    }

    @Override
    public void delete(UUID id) {
        contractRepository.deleteById(id);
    }

    @Override
    public List<ContractResponse> getByCustomerId(UUID customerId) {
        return contractRepository.findByCustomerId(customerId)
                .stream().map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ContractResponse> getByStatus(ContractStatus status) {
        return contractRepository.findByStatus(status)
                .stream().map(mapper::toResponse)
                .toList();
    }

    private byte[] generateContractPdf(Contract contract) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Contrat #" + contract.getContractNumber(), titleFont));
            document.add(new Paragraph(" ", bodyFont)); // espace

            Customer customer = contract.getCustomer();
            if (customer instanceof BusinessCustomer) {
                BusinessCustomer business = (BusinessCustomer) customer;
                document.add(new Paragraph("Client Entreprise", titleFont));
                document.add(new Paragraph("Nom de l'entreprise: " + business.getBusinessName(), bodyFont));
                document.add(new Paragraph("Registre du commerce: " + business.getTradeRegisterNumber(), bodyFont)); // si dispo
            } else if (customer instanceof IndividualCustomer) {
                IndividualCustomer individual = (IndividualCustomer) customer;
                document.add(new Paragraph("Client Particulier", titleFont));
                document.add(new Paragraph("Nom complet: " + individual.getFirstName()+" "+individual.getLastName(), bodyFont));
                document.add(new Paragraph("CIN/Identifiant: " + individual.getIdNumber(), bodyFont)); // si dispo
            } else {
                document.add(new Paragraph("Client inconnu: " + customer.getMsisdn(), bodyFont));
            }

            document.add(new Paragraph(" ", bodyFont));
            document.add(new Paragraph("Date de début: " + contract.getStartDate(), bodyFont));
            document.add(new Paragraph("Date de fin: " + contract.getEndDate(), bodyFont));
            document.add(new Paragraph("Statut: " + contract.getStatus(), bodyFont));
            document.add(new Paragraph("Type d'activation: " + contract.getActivation().getClass().getSimpleName(), bodyFont));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Erreur lors de la génération du PDF", e);
        }
    }

}
