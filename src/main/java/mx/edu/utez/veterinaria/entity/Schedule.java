package mx.edu.utez.veterinaria.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor", nullable = false)
    private Users doctor;

    @ManyToOne
    @JoinColumn(name = "patient", nullable = false)
    private Patients patient;

    @Column(name = "visit_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitDate;

    @Column(name = "visit_end_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitEndDate;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "visit_reason", nullable = false)
    private VisitReason visitReason;

    @ManyToOne
    @JoinColumn(name = "consultory", nullable = true)
    private Consultory consultory;

    public Schedule() {
        this.status = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getDoctor() {
        return doctor;
    }

    public void setDoctor(Users doctor) {
        this.doctor = doctor;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitEndDate() {
        return visitEndDate;
    }

    public void setVisitEndDate(Date visitEndDate) {
        this.visitEndDate = visitEndDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public VisitReason getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(VisitReason visitReason) {
        this.visitReason = visitReason;
    }

    public Consultory getConsultory() {
        return consultory;
    }

    public void setConsultory(Consultory consultory) {
        this.consultory = consultory;
    }
    
}
