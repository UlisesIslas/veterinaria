package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Product;
import mx.edu.utez.veterinaria.repository.IProductRepository;


@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Product obj) {
        return productRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Product tmp = findById(id);
        if (tmp != null) {
            productRepository.delete(tmp);
            return true;
        }
        return false;
    }
}
