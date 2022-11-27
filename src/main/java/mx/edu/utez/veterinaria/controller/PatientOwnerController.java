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

import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.entity.dto.OwnerDTO;
import mx.edu.utez.veterinaria.service.PatientOwnerService;

@RestController
@RequestMapping(path = "/owner")
public class PatientOwnerController {

    @Autowired
    private PatientOwnerService patientOwnerService;

    @GetMapping()
    public List<PatientOwner> findAll() {
        return patientOwnerService.findAll();
    }

    @GetMapping("/{id}")
    public PatientOwner findById(@PathVariable("id") int id) {
        return patientOwnerService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody OwnerDTO obj) {
        return patientOwnerService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return patientOwnerService.delete(id);
    }
    
}
