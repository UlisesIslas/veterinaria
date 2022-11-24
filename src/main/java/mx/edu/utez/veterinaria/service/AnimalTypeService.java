package mx.edu.utez.veterinaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import mx.edu.utez.veterinaria.entity.AnimalType;
import mx.edu.utez.veterinaria.repository.IAnimalTypeRepository;

@Service
public class AnimalTypeService {
    
    @Autowired
    private IAnimalTypeRepository animalTypeRepository;

    @Transactional(readOnly = true)
    public List<AnimalType> findAll() {
        return animalTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AnimalType findById(int id) {
        return animalTypeRepository.findById(id).get();
    }

    @Transactional
    public boolean save(AnimalType obj) {
        return animalTypeRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        AnimalType tmp = findById(id);
        if (tmp != null) {
            animalTypeRepository.delete(tmp);
            return true;
        }
        return false;
    }

}
