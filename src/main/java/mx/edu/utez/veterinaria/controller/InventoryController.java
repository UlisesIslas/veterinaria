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

import mx.edu.utez.veterinaria.entity.Inventory;
import mx.edu.utez.veterinaria.service.InventoryService;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping()
    public List<Inventory> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public Inventory findById(@PathVariable("id") int id) {
        return inventoryService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Inventory obj) {
        return inventoryService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return inventoryService.delete(id);
    }
    
}