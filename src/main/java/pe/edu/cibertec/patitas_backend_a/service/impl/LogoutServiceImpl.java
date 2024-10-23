package pe.edu.cibertec.patitas_backend_a.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend_a.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backend_a.service.LogoutService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class LogoutServiceImpl implements LogoutService {

    @Value("${logout.file.path}")
    private String filePath;

    @Override
    public void GuardarLogout(LogoutRequestDTO logoutRequestDTO) throws IOException {
        // Define el formato de fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Obtiene la fecha y hora actual formateada como una cadena
        String fechaHoraActual = LocalDateTime.now().format(formatter);

        // Crea un objeto File para la ruta especificada
        File file = new File(filePath);
        // Verifica si el directorio padre existe, si no, lo crea
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // Verifica si el archivo existe, si no, lo crea
        if (!file.exists()) {
            file.createNewFile();
        }

        // Usa un BufferedWriter para escribir en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Escribe la información de logout en el archivo
            writer.write(logoutRequestDTO.tipoDocumento() + ";" + logoutRequestDTO.numeroDocumento() + ";" + fechaHoraActual + "\n");
        } catch (IOException e) {
            // Lanza una IOException con un mensaje personalizado si ocurre un error
            throw new IOException("Error al escribir en logout.txt", e);
        }
    }
}
