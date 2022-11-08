package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Tickets;
import mx.edu.utez.veterinaria.repository.ITicketsRepository;

@Service
public class TicketsService {
    @Autowired
    private ITicketsRepository ticketsRepository;

    @Transactional(readOnly = true)
    public List<Tickets> findAll() {
        return ticketsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tickets findById(String id) {
        return ticketsRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Tickets obj) {
        return ticketsRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(String id) {
        Tickets tmp = findById(id);
        if (tmp != null) {
            ticketsRepository.delete(tmp);
            return true;
        }
        return false;
    }
}