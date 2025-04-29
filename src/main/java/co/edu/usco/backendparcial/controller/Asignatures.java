package co.edu.usco.backendparcial.controller;

import co.edu.usco.backendparcial.model.AsignatureEntity;
import co.edu.usco.backendparcial.model.UserEntity;
import co.edu.usco.backendparcial.service.AsignatureService;
import co.edu.usco.backendparcial.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("asignatures")
@AllArgsConstructor
public class Asignatures {
    private final UserService userService;
    private final AsignatureService asignatureService;

    @GetMapping
    public String asignatures(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean isProfessor = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("DOCENTE"));

        List<AsignatureEntity> asignatures;
        if (isProfessor) {
            asignatures = asignatureService.getAsignaturesForCurrentProfessor();
        } else {
            asignatures = asignatureService.getAllAsignatures();
        }

        model.addAttribute("asignaturas", asignatures);
        model.addAttribute("userDetails", userDetails);
        return "asignatures";
    }

    @GetMapping("/new")
    public String showAsignatureForm(Model model) {
        List<UserEntity> professors = userService.getAllProfessors();
        model.addAttribute("professores", professors);
        model.addAttribute("asignatura", new AsignatureEntity());
        return "asignature_form";
    }

    @PostMapping
    public String createAsignature(@ModelAttribute AsignatureEntity asignature) {
        asignatureService.createAsignature(asignature);
        return "redirect:/asignatures";
    }

    @GetMapping("/{id}")
    public String showAsignatureUpdateForm(@PathVariable Long id, Model model) {
        AsignatureEntity asignature = asignatureService.getAsignatureById(id);
        model.addAttribute("asignatura", asignature);
        return "asignature_update";
    }

    @GetMapping("/edit/{id}")
    public String editAsignature(@PathVariable Long id, Model model) {
        AsignatureEntity asignature = asignatureService.getAsignatureById(id);
        model.addAttribute("asignatura", asignature);
        return "asignature_update";
    }

    @PostMapping("/edit/{id}")
    public String updateAsignature(@PathVariable Long id, @ModelAttribute AsignatureEntity asignatureDetails) {
        asignatureService.updateAsignature(id, asignatureDetails);
        return "redirect:/asignatures";
    }

    @GetMapping("/delete/{id}")
    public String deleteAsignature(@PathVariable Long id) {
        asignatureService.deleteAsignature(id);
        return "redirect:/asignatures";
    }
}
