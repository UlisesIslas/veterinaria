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

import mx.edu.utez.veterinaria.service.TicketsService;
import mx.edu.utez.veterinaria.entity.Tickets;
@RestController
@RequestMapping(path = "/tickets")
public class TicketsController {
    @Autowired
    private TicketsService ticketsService;

    @GetMapping()
    public List<Tickets> findAll() {
        return ticketsService.findAll();
    }

    @GetMapping("/{id}")
    public Tickets findById(@PathVariable("id") String id) {
        return ticketsService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Tickets obj) {
        return ticketsService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") String id) {
        return ticketsService.delete(id);
    }
}
