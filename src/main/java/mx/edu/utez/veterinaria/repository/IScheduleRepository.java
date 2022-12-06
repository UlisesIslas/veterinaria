package mx.edu.utez.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.veterinaria.entity.Schedule;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {
    
    @Query(value = "SELECT * FROM schedule s WHERE s.patient = :id ORDER BY s.visit_date ASC", nativeQuery = true)
    List<Schedule> findPetScheduleList(@Param("id") int id);

    @Query(value = "SELECT * FROM schedule s ORDER BY s.visit_date ASC", nativeQuery = true)
    List<Schedule> findOrderedSchedules();

    @Query(value = "SELECT * FROM schedule s WHERE s.doctor = :id AND s.status = 2 ORDER BY s.visit_date ASC", nativeQuery = true)
    List<Schedule> findOrderedSchedulesByUser(@Param("id") int id);

}
