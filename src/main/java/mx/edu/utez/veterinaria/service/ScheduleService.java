package mx.edu.utez.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.utez.veterinaria.entity.Cart;
import mx.edu.utez.veterinaria.entity.Schedule;
import mx.edu.utez.veterinaria.entity.Tickets;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.entity.Wallet;
import mx.edu.utez.veterinaria.repository.ICartRepository;
import mx.edu.utez.veterinaria.repository.IScheduleRepository;
import mx.edu.utez.veterinaria.repository.ITicketsRepository;
import mx.edu.utez.veterinaria.repository.IWalletRepository;

@Service
public class ScheduleService {

    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private IWalletRepository walletRepository;
    @Autowired
    private ITicketsRepository ticketsRepository;
    @Autowired
    private ICartRepository cartRepository;

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

    @Transactional(readOnly = true)
    public List<Schedule> findOrderedSchedules() {
        return scheduleRepository.findOrderedSchedules();
    }

    @Transactional(readOnly = true)
    public List<Schedule> findOrderedSchedulesByUser(int id) {
        return scheduleRepository.findOrderedSchedulesByUser(id);
    }

    @Transactional
    public boolean save(Schedule obj) {
        return scheduleRepository.save(obj) != null;
    }

    @Transactional
    public boolean confirm(Schedule obj, Users user) {
        if (obj.getStatus() == 2) {
            Wallet wallet = walletRepository.findByOwnerId(obj.getPatient().getOwner().getId());
            wallet.setBalance(wallet.getBalance() + (obj.getConsultory().getVisitReason().getCost() * 0.05));
            Tickets ticket = new Tickets();
            ticket.setSeller(user);
            ticket.setOwner(obj.getPatient().getOwner());
            ticket = ticketsRepository.save(ticket);
            Cart cart = new Cart();
            cart.setSchedule(obj);
            cart.setTicket(ticket);
            return scheduleRepository.save(obj) != null && walletRepository.save(wallet) != null
                    && cartRepository.save(cart) != null;
        } else if (obj.getStatus() == 0) {
            Wallet wallet = walletRepository.findByOwnerId(obj.getPatient().getOwner().getId());
            wallet.setBalance(wallet.getBalance() - (obj.getConsultory().getVisitReason().getCost() * 0.05));
            Tickets ticket = ticketsRepository.findById(cartRepository.findByScheduleId(obj.getId()).getTicket().getFolio()).get();
            ticket.setStatus(0);
            return scheduleRepository.save(obj) != null && walletRepository.save(wallet) != null
                    && ticketsRepository.save(ticket) != null;
        }
        return false;
    }

    @Transactional
    public boolean walletPay(Schedule obj, Users user) {
        if (obj.getStatus() == 2) {
            Wallet wallet = walletRepository.findByOwnerId(obj.getPatient().getOwner().getId());
            if (wallet.getBalance() > obj.getConsultory().getVisitReason().getCost()) {
                wallet.setBalance(wallet.getBalance() - obj.getConsultory().getVisitReason().getCost());
            } else {
                wallet.setBalance(0);
            }
            wallet.setBalance(wallet.getBalance() + (obj.getConsultory().getVisitReason().getCost() * 0.05));
            Tickets ticket = new Tickets();
            ticket.setSeller(user);
            ticket.setOwner(obj.getPatient().getOwner());
            ticket = ticketsRepository.save(ticket);
            Cart cart = new Cart();
            cart.setSchedule(obj);
            cart.setTicket(ticket);
            return scheduleRepository.save(obj) != null && walletRepository.save(wallet) != null
                    && cartRepository.save(cart) != null;
        }
        return false;
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
