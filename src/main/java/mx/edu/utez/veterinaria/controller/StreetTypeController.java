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

import mx.edu.utez.veterinaria.entity.StreetType;
import mx.edu.utez.veterinaria.service.StreetTypeService;

@RestController
@RequestMapping(path = "/streetType")
public class StreetTypeController {

    @Autowired
    private StreetTypeService streetTypeService;

    @GetMapping()
    public List<StreetType> findAll() {
        return streetTypeService.findAll();
    }

    @GetMapping("/{id}")
    public StreetType findById(@PathVariable("id") int id) {
        return streetTypeService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody StreetType obj) {
        return streetTypeService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return streetTypeService.delete(id);
    }
    
}