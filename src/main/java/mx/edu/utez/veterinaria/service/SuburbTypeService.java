package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.ISuburbTypeRepository;
import mx.edu.utez.veterinaria.entity.SuburbType;

@Service
public class SuburbTypeService {
    
    @Autowired
    private ISuburbTypeRepository suburbTypeRepository;

    @Transactional(readOnly = true)
    public List<SuburbType> findAll() {
        return suburbTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SuburbType findById(int id) {
        return suburbTypeRepository.findById(id).get();
    }

    @Transactional
    public boolean save(SuburbType obj) {
        return suburbTypeRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        SuburbType tmp = findById(id);
        if (tmp != null) {
            suburbTypeRepository.delete(tmp);
            return true;
        }
        return false;
    }
}