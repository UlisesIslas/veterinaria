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

import mx.edu.utez.veterinaria.service.StateService;
import mx.edu.utez.veterinaria.entity.State;

@RestController
@RequestMapping(path = "/state")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping()
    public List<State> findAll() {
        return stateService.findAll();
    }

    @GetMapping("/{id}")
    public State findById(@PathVariable("id") int id) {
        return stateService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody State obj) {
        return stateService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return stateService.delete(id);
    }
}
