package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Roles;
import mx.edu.utez.veterinaria.repository.IRoleRepository;

@Service
@Transactional
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<Roles> findAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Roles findRoleById(int id) {
        return roleRepository.findById(id).get();
    }

    public boolean save(Roles role) {
        return roleRepository.save(role) != null;
    }

    public boolean delete(int id) {
        boolean flag = false;
        Roles tmp = findRoleById(id);
        if (tmp != null) {
            roleRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }
    
}
