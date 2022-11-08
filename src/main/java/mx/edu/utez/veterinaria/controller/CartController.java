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

import mx.edu.utez.veterinaria.service.CartService;
import mx.edu.utez.veterinaria.entity.Cart;


@RestController
@RequestMapping(path = "/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @GetMapping()
    public List<Cart> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public Cart findById(@PathVariable("id") int id) {
        return cartService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Cart obj) {
        return cartService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") int id) {
        return cartService.delete(id);
    }
}
