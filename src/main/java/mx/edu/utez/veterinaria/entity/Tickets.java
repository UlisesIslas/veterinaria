package mx.edu.utez.veterinaria.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tickets")
public class Tickets implements Serializable {

    @Id
    private String folio;

    @ManyToOne
    @JoinColumn(name = "seller", nullable = false)
    private Users seller;

    @Column(name = "sale_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private PatientOwner owner;

    public Tickets() {
        this.folio = UUID.randomUUID().toString();
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Users getSeller() {
        return seller;
    }

    public void setSeller(Users seller) {
        this.seller = seller;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public PatientOwner getOwner() {
        return owner;
    }

    public void setOwner(PatientOwner owner) {
        this.owner = owner;
    }
    
}
