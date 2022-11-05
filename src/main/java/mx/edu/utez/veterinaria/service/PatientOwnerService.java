package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.repository.IPatientOwnerRepository;

@Service
public class PatientOwnerService {

    @Autowired
    private IPatientOwnerRepository patientOwnerRepository;

    @Transactional(readOnly = true)
    public List<PatientOwner> findAll() {
        return patientOwnerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PatientOwner findById(int id) {
        return patientOwnerRepository.findById(id).get();
    }

    @Transactional
    public boolean save(PatientOwner obj) {
        return patientOwnerRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        PatientOwner tmp = findById(id);
        if (tmp != null) {
            patientOwnerRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
