package tn.ooredoo.amir3tiers.config;

import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.ooredoo.amir3tiers.entity.*;
import tn.ooredoo.amir3tiers.enums.TicketStatus;
import tn.ooredoo.amir3tiers.enums.TicketType;
import tn.ooredoo.amir3tiers.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ActivationListener {

    @PostPersist
    public void onPostPersist(Activation activation) {
        if (activation instanceof FiberActivation || (activation instanceof FixeActivation && ((FixeActivation) activation).getActivationType().equalsIgnoreCase("outdoor"))) {
            TicketRepository ticketRepository = BeanUtil.getBean(TicketRepository.class);
            String kitCode = null;
            if (activation instanceof FiberActivation fiber) {
                kitCode = fiber.getKitCode();
            } else {
                FixeActivation fixe = (FixeActivation) activation;
                kitCode = fixe.getKitCode();
            }
            LigTicket lig = LigTicket.builder()
                    .kitCode(kitCode)
                    .build();
            Ticket ticket = Ticket.builder()
                    .reference("TCK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                    .type(TicketType.INSTALLATION)
                    .status(TicketStatus.OPEN)
                    .createdAt(LocalDateTime.now())
                    .isTreated(false)
                    .activation(activation)
                    .build();

            lig.setTicket(ticket);
            ticket.getLignes().add(lig);

            ticketRepository.save(ticket);
        }
    }
}
