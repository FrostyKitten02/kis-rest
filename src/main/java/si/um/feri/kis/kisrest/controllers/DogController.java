package si.um.feri.kis.kisrest.controllers;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import si.um.feri.kis.kisrest.execptions.ItemNotFound;
import si.um.feri.kis.kisrest.model.Dog;
import si.um.feri.kis.kisrest.repos.DogRepo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dog")
public class DogController {
    private final DogRepo dogRepo;

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogRepo.findById(id).orElseThrow(() -> new ItemNotFound("Dog with id " + id + " not found"));
    }

    @GetMapping("/all")
    public Iterable<Dog> getAllDogs() {
        return dogRepo.findAll();
    }

    @PostMapping
    public Dog addDog(@RequestBody Dog dog, HttpServletResponse res) {
        dog.setId(null);
        Dog saved = dogRepo.save(dog);
        res.setStatus(HttpServletResponse.SC_CREATED);
        return saved;
    }

    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        if (!dogRepo.existsById(id)) {
            throw new ItemNotFound("Dog with id " + id + " not found");
        }
        dog.setId(id);
        return dogRepo.save(dog);
    }

    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Long id, HttpServletResponse res) {
        if (!dogRepo.existsById(id)) {
            throw new ItemNotFound("Dog with id " + id + " not found");
        }
        dogRepo.deleteById(id);
        res.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
