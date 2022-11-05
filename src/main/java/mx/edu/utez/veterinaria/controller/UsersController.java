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
import mx.edu.utez.veterinaria.service.UsersService;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public List<Users> findAll() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public Users findById(@PathVariable("id") int id) {
        return usersService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Users obj) {
        return usersService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return usersService.delete(id);
    }
    
}
