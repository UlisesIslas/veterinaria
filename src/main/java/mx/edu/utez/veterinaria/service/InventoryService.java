package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.IInventoryRepository;
import mx.edu.utez.veterinaria.entity.Inventory;

@Service
public class InventoryService {
    
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Inventory findById(int id) {
        return inventoryRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Inventory obj) {
        return inventoryRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Inventory tmp = findById(id);
        if (tmp != null) {
            inventoryRepository.delete(tmp);
            return true;
        }
        return false;
    }
}