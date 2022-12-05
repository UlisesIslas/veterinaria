package mx.edu.utez.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.veterinaria.config.AuthCheckPermission;
import mx.edu.utez.veterinaria.entity.Schedule;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.service.ScheduleService;

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AuthCheckPermission authCheckPermission;

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

    @GetMapping("/self")
    public List<Schedule> findOrderedSchedulesByUser(@RequestHeader HttpHeaders headers) {
        String token = String.valueOf(headers.get("Authorization"));
        return scheduleService.findOrderedSchedulesByUser(authCheckPermission.findUserByToken(token).getId());
    }

    @PostMapping("/pay")
    public boolean pay(@RequestBody Schedule obj, @RequestHeader HttpHeaders headers) {
        String token = String.valueOf(headers.get("Authorization"));
        Users user = authCheckPermission.findUserByToken(token);
        return scheduleService.confirm(obj, user);
    }

    @PostMapping("/wallet/pay")
    public boolean payWithWallet(@RequestBody Schedule obj, @RequestHeader HttpHeaders headers) {
        String token = String.valueOf(headers.get("Authorization"));
        Users user = authCheckPermission.findUserByToken(token);
        return scheduleService.walletPay(obj, user);
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
