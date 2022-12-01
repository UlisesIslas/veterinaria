package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.repository.IUsersRepository;
import mx.edu.utez.veterinaria.repository.IVisitReasonRepository;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.entity.VisitReason;

@Service
public class VisitReasonService {
    
    @Autowired
    private IVisitReasonRepository visitReasonRepository;
    @Autowired
    private IUsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<VisitReason> findAll() {
        return visitReasonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public VisitReason findById(int id) {
        return visitReasonRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Users> findUsersForVisitReason(int id) {
        return usersRepository.findByRole(visitReasonRepository.findById(id).get().getResponsible().getId());
    }

    @Transactional
    public boolean save(VisitReason obj) {
        return visitReasonRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        VisitReason tmp = findById(id);
        if (tmp != null) {
            visitReasonRepository.delete(tmp);
            return true;
        }
        return false;
    }
}