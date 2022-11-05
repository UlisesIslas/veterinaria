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

import mx.edu.utez.veterinaria.entity.Wallet;
import mx.edu.utez.veterinaria.service.WalletService;

@RestController
@RequestMapping(path = "/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping()
    public List<Wallet> findAll() {
        return walletService.findAll();
    }

    @GetMapping("/{id}")
    public Wallet findById(@PathVariable("id") int id) {
        return walletService.findById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Wallet obj) {
        return walletService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return walletService.delete(id);
    }
    
}
