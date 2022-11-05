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

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.service.OwnerAddressService;

@RestController
@RequestMapping(path = "/address")
public class OwnerAddressController {

    @Autowired
    private OwnerAddressService ownerAddressService;

    @GetMapping()
    public List<OwnerAddress> findAll() {
        return ownerAddressService.findAll();
    }

    @GetMapping("/{id}")
    public OwnerAddress findById(@PathVariable("id") int id) {
        return ownerAddressService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody OwnerAddress obj) {
        return ownerAddressService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return ownerAddressService.delete(id);
    }
    
}
