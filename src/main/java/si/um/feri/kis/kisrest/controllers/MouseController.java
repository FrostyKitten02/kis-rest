package si.um.feri.kis.kisrest.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import si.um.feri.kis.kisrest.execptions.ItemNotFound;
import si.um.feri.kis.kisrest.model.Mouse;
import si.um.feri.kis.kisrest.repos.MouseRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mouse")
public class MouseController {
    private final MouseRepo mouseRepo;

    @GetMapping("/{id}")
    public Mouse getMouseById(@PathVariable Long id) {
        return mouseRepo.findById(id).orElseThrow(() -> new ItemNotFound("Mouse with id " + id + " not found"));
    }

    @GetMapping("/all")
    public Iterable<Mouse> getAllMouses() {
        return mouseRepo.findAll();
    }

    @PostMapping
    public Mouse addMouse(@RequestBody Mouse mouse, HttpServletResponse res) {
        mouse.setId(null);
        Mouse saved = mouseRepo.save(mouse);
        res.setStatus(HttpServletResponse.SC_CREATED);
        return saved;
    }

    @PutMapping("/{id}")
    public Mouse updateMouse(@PathVariable Long id, @RequestBody Mouse mouse) {
        if (!mouseRepo.existsById(id)) {
            throw new ItemNotFound("Mouse with id " + id + " not found");
        }
        mouse.setId(id);
        return mouseRepo.save(mouse);
    }

    @DeleteMapping("/{id}")
    public void deleteMouse(@PathVariable Long id, HttpServletResponse res) {
        if (!mouseRepo.existsById(id)) {
            throw new ItemNotFound("Mouse with id " + id + " not found");
        }
        mouseRepo.deleteById(id);
        res.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
