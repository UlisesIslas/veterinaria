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

import mx.edu.utez.veterinaria.entity.Patients;
import mx.edu.utez.veterinaria.service.PatientsService;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private PatientsService patientsService;

    @GetMapping()
    public List<Patients> findAll() {
        return patientsService.findAll();
    }

    @GetMapping("/{id}")
    public Patients findById(@PathVariable("id") int id) {
        return patientsService.findById(id);
    }

    @GetMapping("/owner/{id}")
    public List<Patients> findByOwnerId(@PathVariable("id") int id) {
        return patientsService.findAllByOwnerId(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Patients obj) {
        return patientsService.save(obj);
    }

    @DeleteMapping("'{id}")
    public boolean delete(@PathVariable("id") int id) {
        return patientsService.delete(id);
    }

}
