package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Schedule;
import mx.edu.utez.veterinaria.entity.Wallet;
import mx.edu.utez.veterinaria.repository.IScheduleRepository;
import mx.edu.utez.veterinaria.repository.IWalletRepository;

@Service
public class ScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private IWalletRepository walletRepository;

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
    public boolean confirm(Schedule obj) {
        Wallet wallet = walletRepository.findByOwnerId(obj.getPatient().getId());
        wallet.setBalance(wallet.getBalance() + (obj.getConsultory().getVisitReason().getCost() * 0.05));
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
