package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.IStreetTypeRepository;
import mx.edu.utez.veterinaria.entity.StreetType;

@Service
public class StreetTypeService {
    
    @Autowired
    private IStreetTypeRepository streetTypeRepository;

    @Transactional(readOnly = true)
    public List<StreetType> findAll() {
        return streetTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StreetType findById(int id) {
        return streetTypeRepository.findById(id).get();
    }

    @Transactional
    public boolean save(StreetType obj) {
        return streetTypeRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        StreetType tmp = findById(id);
        if (tmp != null) {
            streetTypeRepository.delete(tmp);
            return true;
        }
        return false;
    }
}