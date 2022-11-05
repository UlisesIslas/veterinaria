package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.repository.IOwnerAddressRepository;

@Service
public class OwnerAddressService {

    @Autowired
    private IOwnerAddressRepository ownerAddressRepository;

    @Transactional(readOnly = true)
    public List<OwnerAddress> findAll() {
        return ownerAddressRepository.findAll();        
    }

    @Transactional(readOnly = true)
    public OwnerAddress findById(int id) {
        return ownerAddressRepository.findById(id).get();
    }

    @Transactional
    public boolean save(OwnerAddress obj) {
        return ownerAddressRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        OwnerAddress tmp = findById(id);
        if (tmp != null) {
            ownerAddressRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
