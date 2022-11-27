package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.entity.Patients;
import mx.edu.utez.veterinaria.entity.dto.OwnerDTO;
import mx.edu.utez.veterinaria.repository.IOwnerAddressRepository;
import mx.edu.utez.veterinaria.repository.IPatientOwnerRepository;
import mx.edu.utez.veterinaria.repository.IPatientsRepository;

@Service
public class PatientOwnerService {

    @Autowired
    private IPatientOwnerRepository patientOwnerRepository;
    @Autowired
    private IPatientsRepository patientsRepository;
    @Autowired
    private IOwnerAddressRepository ownerAddressRepository;

    @Transactional(readOnly = true)
    public List<PatientOwner> findAll() {
        return patientOwnerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PatientOwner findById(int id) {
        return patientOwnerRepository.findById(id).get();
    }

    /* @Transactional
    public boolean save(PatientOwner obj) {
        return patientOwnerRepository.save(obj) != null;
    } */

    @Transactional
    public boolean save(OwnerDTO obj) {
        boolean flag = false;
        PatientOwner owner = patientOwnerRepository.save(obj.getOwner());
        obj.getPet().setOwner(owner);
        obj.getOwnerAddress().setOwner(owner);
        Patients pet = patientsRepository.save(obj.getPet());
        OwnerAddress address = ownerAddressRepository.save(obj.getOwnerAddress());
        if (owner != null && pet != null && address != null) {
            flag = true;
        }
        return flag;
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
