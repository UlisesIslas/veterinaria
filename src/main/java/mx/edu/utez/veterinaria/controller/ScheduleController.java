package mx.edu.utez.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.veterinaria.entity.Schedule;
import mx.edu.utez.veterinaria.service.ScheduleService;

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping()
    public List<Schedule> findAll() {
        return scheduleService.findOrderedSchedules();
    }

    @GetMapping("/{id}")
    public Schedule findById(@PathVariable("id") int id) {
        return scheduleService.findById(id);
    }

    @GetMapping("/pet/{id}")
    public List<Schedule> findPetScheduleList(@PathVariable("id") int id) {
        return scheduleService.findPetScheduleList(id);
    }

    @PostMapping("/pay")
    public boolean pay(@RequestBody Schedule obj) {
        return scheduleService.confirm(obj);
    }

    @PostMapping("/wallet/pay")
    public boolean payWithWallet(@RequestBody Schedule obj) {
        return scheduleService.walletPay(obj);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Schedule obj) {
        return scheduleService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return scheduleService.delete(id);
    }
    
}
