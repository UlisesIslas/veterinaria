package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.veterinaria.entity.Consultory;
import mx.edu.utez.veterinaria.repository.IConsultoryRepository;

@Service
public class ConsultoryService {

    @Autowired
    private IConsultoryRepository consultoryRepository;

    public List<Consultory> findAll() {
        return consultoryRepository.findAll();
    }

    public Consultory findById(int id) {
        return consultoryRepository.findById(id).get();
    }

    public boolean save(Consultory obj) {
        return consultoryRepository.save(obj) != null;
    }

    public boolean delete(int id) {
        Consultory tmp = findById(id);
        if (tmp != null) {
            consultoryRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
