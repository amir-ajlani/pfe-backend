package tn.ooredoo.amir3tiers.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.ooredoo.amir3tiers.DTOS.LoginRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.LoginResponseDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountResponseDTO;
import tn.ooredoo.amir3tiers.Mappers.UserAccountMapper;
import tn.ooredoo.amir3tiers.entity.UserAccount;
import tn.ooredoo.amir3tiers.repository.UserAccountRepository;
import tn.ooredoo.amir3tiers.service.UserAccountService;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;
    private final UserAccountMapper mapper;

    @Override
    public UserAccountResponseDTO create(UserAccountRequestDTO dto) {
        UserAccount user = mapper.toEntity(dto);
        user = repository.save(user);
        return mapper.toResponse(user);
    }

    @Override
    public UserAccountResponseDTO update(String username, UserAccountRequestDTO dto) {
        UserAccount user = repository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        mapper.updateEntity(user, dto);
        return mapper.toResponse(repository.save(user));
    }

    @Override
    public void delete(String username) {
        if (!repository.existsById(username)) {
            throw new EntityNotFoundException("User not found");
        }
        repository.deleteById(username);
    }

    @Override
    public UserAccountResponseDTO getById(String username) {
        return repository.findById(username)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserAccountResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserAccount user = repository.findByUsernameAndPassword(
                        loginRequestDTO.username(), loginRequestDTO.password())
                .orElseThrow(() -> new EntityNotFoundException("Invalid credentials"));

        // Simule un token
        String token = Base64.getEncoder().encodeToString((user.getUsername() + ":TOKEN").getBytes());

        return new LoginResponseDTO(token, user.getUserType(), user.getUsername());
    }
}