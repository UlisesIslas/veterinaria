package mx.edu.utez.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.veterinaria.entity.Schedule;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {
    
}
