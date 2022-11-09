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

import mx.edu.utez.veterinaria.entity.SuburbType;
import mx.edu.utez.veterinaria.service.SuburbTypeService;

@RestController
@RequestMapping(path = "/suburbType")
public class SuburbTypeController {

    @Autowired
    private SuburbTypeService suburbTypeService;

    @GetMapping()
    public List<SuburbType> findAll() {
        return suburbTypeService.findAll();
    }

    @GetMapping("/{id}")
    public SuburbType findById(@PathVariable("id") int id) {
        return suburbTypeService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody SuburbType obj) {
        return suburbTypeService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return suburbTypeService.delete(id);
    }
    
}