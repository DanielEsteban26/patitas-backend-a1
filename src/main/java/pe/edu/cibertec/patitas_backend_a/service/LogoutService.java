package pe.edu.cibertec.patitas_backend_a.service;

import pe.edu.cibertec.patitas_backend_a.dto.LogoutRequestDTO;

import java.io.IOException;

public interface LogoutService {
    void GuardarLogout(LogoutRequestDTO logoutRequestDTO) throws IOException;
}
