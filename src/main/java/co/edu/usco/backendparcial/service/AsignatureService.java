package co.edu.usco.backendparcial.service;

import co.edu.usco.backendparcial.model.AsignatureEntity;
import co.edu.usco.backendparcial.model.UserEntity;
import co.edu.usco.backendparcial.repository.AsignatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsignatureService {

    private final AsignatureRepository asignatureRepository;
    private final UserService userService;

    public List<AsignatureEntity> getAllAsignatures() {
        return asignatureRepository.findAll();
    }

    public List<AsignatureEntity> getAsignaturesForCurrentProfessor() {
        UserEntity professor = userService.getCurrentUser();
        return asignatureRepository.findByProfessor(professor);
    }

    public AsignatureEntity getAsignatureById(Long id) {
        return asignatureRepository.findById(id).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void createAsignature(AsignatureEntity asignature) {
        asignatureRepository.save(asignature);
    }

    public void updateAsignature(Long id, AsignatureEntity asignatureDetails) {
        AsignatureEntity asignature = asignatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        UserEntity currentUser = userService.getCurrentUser();

        // Si es administrador o si el profesor es dueño de la asignatura
        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(rol -> rol.getRolName().equals("ADMIN"));

        boolean isOwner = asignature.getProfessor().getId().equals(currentUser.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("No tienes permisos para editar esta asignatura");
        }

        // Se actualizan los campos permitidos
        asignature.setStartTime(asignatureDetails.getStartTime());
        asignature.setEndTime(asignatureDetails.getEndTime());

        // Solo el admin puede actualizar todos los campos
        if (isAdmin) {
            asignature.setName(asignatureDetails.getName());
            asignature.setDescription(asignatureDetails.getDescription());
            asignature.setClassroom(asignatureDetails.getClassroom());
        }

        asignatureRepository.save(asignature);
    }

    public void deleteAsignature(Long id) {
        asignatureRepository.deleteById(id);
    }
}
