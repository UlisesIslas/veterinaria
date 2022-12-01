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

import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.entity.VisitReason;
import mx.edu.utez.veterinaria.service.VisitReasonService;

@RestController
@RequestMapping(path = "/visitReason")
public class VisitReasonController {

    @Autowired
    private VisitReasonService visitReasonService;

    @GetMapping()
    public List<VisitReason> findAll() {
        return visitReasonService.findAll();
    }

    @GetMapping("/{id}")
    public VisitReason findById(@PathVariable("id") int id) {
        return visitReasonService.findById(id);
    }

    @GetMapping("/users/{id}")
    public List<Users> findUsersForVisitReason(@PathVariable("id") int id) {
        return visitReasonService.findUsersForVisitReason(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody VisitReason obj) {
        return visitReasonService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return visitReasonService.delete(id);
    }
    
}