package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.State;
import mx.edu.utez.veterinaria.repository.IStateRepository;

@Service
public class StateService {
    @Autowired
    private IStateRepository stateRepository;

    @Transactional(readOnly = true)
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Transactional(readOnly = true)
    public State findById(int id) {
        return stateRepository.findById(id).get();
    }

    @Transactional
    public boolean save(State obj) {
        return stateRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        State tmp = findById(id);
        if (tmp != null) {
            stateRepository.delete(tmp);
            return true;
        }
        return false;
    }
}
