package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Patients;
import mx.edu.utez.veterinaria.repository.IPatientsRepository;

@Service
public class PatientsService {

    @Autowired
    private IPatientsRepository patientsRepository;

    @Transactional(readOnly = true)
    public List<Patients> findAll() {
        return patientsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Patients findById(int id) {
        return patientsRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Patients> findAllByOwnerId(int id) {
        return patientsRepository.findByOwnerId(id);
    }

    @Transactional
    public boolean save(Patients obj) {
        return patientsRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Patients tmp = findById(id);
        if (tmp != null) {
            patientsRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
