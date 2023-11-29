package si.um.feri.kis.kisrest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import si.um.feri.kis.kisrest.execptions.ItemNotFound;
import si.um.feri.kis.kisrest.model.Cat;
import si.um.feri.kis.kisrest.repos.CatRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatsController {
    private final CatRepo catRepo;

    @PostMapping
    public Cat addCat(@RequestBody Cat cat) {
        cat.setId(null);
        return catRepo.save(cat);
    }

    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catRepo.findById(id).orElseThrow(() -> new ItemNotFound("Cat with id " + id + " not found"));
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
    public void deleteCat(@PathVariable Long id) {
        if (!catRepo.existsById(id)) {
            throw new ItemNotFound("Cat with id " + id + " not found");
        }
        catRepo.deleteById(id);
    }

}
