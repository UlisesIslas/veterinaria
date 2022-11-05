package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Wallet;
import mx.edu.utez.veterinaria.repository.IWalletRepository;

@Service
public class WalletService {

    @Autowired
    private IWalletRepository walletRepository;

    @Transactional(readOnly = true)
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Wallet findById(int id) {
        return walletRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Wallet obj) {
        return walletRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Wallet tmp = findById(id);
        if (tmp != null) {
            walletRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
