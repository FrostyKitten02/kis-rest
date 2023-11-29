package si.um.feri.kis.kisrest.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import si.um.feri.kis.kisrest.execptions.ItemNotFound;
import si.um.feri.kis.kisrest.model.Cat;
import si.um.feri.kis.kisrest.repos.CatRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cat")
public class CatController {
    private final CatRepo catRepo;

    @PostMapping
    public Cat addCat(@RequestBody Cat cat, HttpServletResponse res) {
        cat.setId(null);
        Cat saved = catRepo.save(cat);
        res.setStatus(HttpServletResponse.SC_CREATED);
        return saved;
    }

    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catRepo.findById(id).orElseThrow(() -> new ItemNotFound("Cat with id " + id + " not found"));
    }

    @GetMapping("/all")
    public Iterable<Cat> getAllCats() {
        return catRepo.findAll();
    }

    @PutMapping("/{id}")
    public Cat updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        if (!catRepo.existsById(id)) {
            throw new ItemNotFound("Cat with id " + id + " not found");
        }
        cat.setId(id);
        return catRepo.save(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id, HttpServletResponse res) {
        if (!catRepo.existsById(id)) {
            throw new ItemNotFound("Cat with id " + id + " not found");
        }
        catRepo.deleteById(id);
        res.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
