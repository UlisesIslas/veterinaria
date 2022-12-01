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

import mx.edu.utez.veterinaria.entity.Consultory;
import mx.edu.utez.veterinaria.service.ConsultoryService;

@RestController
@RequestMapping(path = "/consultory")
public class ConsultoryController {

    @Autowired
    private ConsultoryService consultoryService;

    @GetMapping()
    public List<Consultory> findAll() {
        return consultoryService.findAll();
    }

    @GetMapping("/{id}")
    public Consultory findById(@PathVariable("id") int id) {
        return consultoryService.findById(id);
    }

    @GetMapping("/visit/{id}")
    public List<Consultory> findByVisitReason(@PathVariable("id") int id) {
        return consultoryService.findByVisitReason(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Consultory obj) {
        return consultoryService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return consultoryService.delete(id);
    }
    
}
