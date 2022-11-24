package mx.edu.utez.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.veterinaria.service.AnimalTypeService;
import mx.edu.utez.veterinaria.entity.AnimalType;

@RestController
@RequestMapping(path = "/animal")
public class AnimalTypeController {

    @Autowired
    private AnimalTypeService animalService;

    @GetMapping()
    public List<AnimalType> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public AnimalType findById(@PathVariable("id") int id) {
        return animalService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody AnimalType obj) {
        return animalService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return animalService.delete(id);
    }
}
