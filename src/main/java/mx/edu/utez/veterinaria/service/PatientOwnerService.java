package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.entity.Patients;
import mx.edu.utez.veterinaria.entity.Wallet;
import mx.edu.utez.veterinaria.entity.dto.OwnerDTO;
import mx.edu.utez.veterinaria.entity.dto.OwnerDetailsDTO;
import mx.edu.utez.veterinaria.repository.IOwnerAddressRepository;
import mx.edu.utez.veterinaria.repository.IPatientOwnerRepository;
import mx.edu.utez.veterinaria.repository.IPatientsRepository;
import mx.edu.utez.veterinaria.repository.IWalletRepository;

@Service
public class PatientOwnerService {

    @Autowired
    private IPatientOwnerRepository patientOwnerRepository;
    @Autowired
    private IPatientsRepository patientsRepository;
    @Autowired
    private IOwnerAddressRepository ownerAddressRepository;
    @Autowired
    private IWalletRepository walletRepository;

    @Transactional(readOnly = true)
    public List<PatientOwner> findAll() {
        return patientOwnerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PatientOwner findById(int id) {
        return patientOwnerRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public OwnerDetailsDTO findDetails(int ownerId) {
        OwnerDetailsDTO details = new OwnerDetailsDTO();
        details.setOwner(findById(ownerId));
        details.setAddress(ownerAddressRepository.findByOwnerId(ownerId));
        details.setWallet(walletRepository.findByOwnerId(ownerId));
        return details;
    }

    @Transactional
    public boolean save(OwnerDTO obj) {
        boolean flag = false;
        PatientOwner owner = patientOwnerRepository.save(obj.getOwner());
        obj.getPet().setOwner(owner);
        obj.getOwnerAddress().setOwner(owner);
        Patients pet = patientsRepository.save(obj.getPet());
        OwnerAddress address = ownerAddressRepository.save(obj.getOwnerAddress());
        Wallet wallet = new Wallet();
        wallet.setOwner(owner);
        wallet.setNumber(owner.getPhone());
        if (owner != null && pet != null && address != null && walletRepository.save(wallet) != null) {
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
