package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.ICityRepository;
import mx.edu.utez.veterinaria.entity.City;

@Service
public class CityService {
    
    @Autowired
    private ICityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<City> findByStateId(int id) {
        return cityRepository.findByStateId(id);
    }

    @Transactional(readOnly = true)
    public City findById(int id) {
        return cityRepository.findById(id).get();
    }

    @Transactional
    public boolean save(City obj) {
        return cityRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        City tmp = findById(id);
        if (tmp != null) {
            cityRepository.delete(tmp);
            return true;
        }
        return false;
    }
}