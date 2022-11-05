package mx.edu.utez.veterinaria.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false, length = 10, unique = true)
    private String number;

    @Column(name = "balance", nullable = false)
    private double balance;

    @OneToOne
    @JoinColumn(name = "owner", nullable = false)
    private PatientOwner owner;

    public Wallet() {
        this.balance = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PatientOwner getOwner() {
        return owner;
    }

    public void setOwner(PatientOwner owner) {
        this.owner = owner;
    }
    
}
