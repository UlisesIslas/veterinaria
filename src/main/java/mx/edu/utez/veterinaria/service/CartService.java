package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.ICartRepository;
import mx.edu.utez.veterinaria.entity.Cart;

@Service
public class CartService {
    
    @Autowired
    private ICartRepository cartRepository;

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cart findById(int id) {
        return cartRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Cart obj) {
        return cartRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Cart tmp = findById(id);
        if (tmp != null) {
            cartRepository.delete(tmp);
            return true;
        }
        return false;
    }
}
