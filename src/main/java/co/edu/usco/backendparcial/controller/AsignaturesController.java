package co.edu.usco.backendparcial.controller;

import co.edu.usco.backendparcial.model.AsignatureEntity;
import co.edu.usco.backendparcial.service.AsignatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AsignaturesController {
    private final AsignatureService asignatureService;

    // Este sigue devolviendo todas las asignaturas (admin)
    @GetMapping("/api/asignatures")
    public List<AsignatureEntity> getAllAsignatures() {
        return asignatureService.getAllAsignatures();
    }

    // ESTE ES EL NUEVO para docentes
    @GetMapping("/api/asignatures/professor")
    public List<AsignatureEntity> getAsignaturesForCurrentProfessor() {
        return asignatureService.getAsignaturesForCurrentProfessor();
    }
}
