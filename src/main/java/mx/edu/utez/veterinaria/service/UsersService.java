package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.repository.IUsersRepository;

@Service
public class UsersService {

    @Autowired
    private IUsersRepository usersRepository;

    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users findById(int id) {
        return usersRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Users obj) {
        return usersRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Users tmp = findById(id);
        if (tmp != null) {
            usersRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
