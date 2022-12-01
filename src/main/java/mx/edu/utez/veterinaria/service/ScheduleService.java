package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Schedule;
import mx.edu.utez.veterinaria.repository.IScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Schedule findById(int id) {
        return scheduleRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Schedule> findPetScheduleList(int id) {
        return scheduleRepository.findPetScheduleList(id);
    }

    @Transactional
    public boolean save(Schedule obj) {
        return scheduleRepository.save(obj) != null;
    }

    @Transactional
    public boolean delete(int id) {
        Schedule tmp = findById(id);
        if (tmp != null) {
            scheduleRepository.delete(tmp);
            return true;
        }
        return false;
    }
    
}
