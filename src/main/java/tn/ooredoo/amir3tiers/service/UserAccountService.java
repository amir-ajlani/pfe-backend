package tn.ooredoo.amir3tiers.service;

import tn.ooredoo.amir3tiers.DTOS.LoginRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.LoginResponseDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountRequestDTO;
import tn.ooredoo.amir3tiers.DTOS.UserAccountResponseDTO;

import java.util.List;

public interface UserAccountService {
    UserAccountResponseDTO create(UserAccountRequestDTO dto);
    UserAccountResponseDTO update(String username, UserAccountRequestDTO dto);
    void delete(String username);
    UserAccountResponseDTO getById(String username);
    List<UserAccountResponseDTO> getAll();
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
