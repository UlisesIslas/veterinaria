package mx.edu.utez.veterinaria.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "owner_address")
public class OwnerAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "street_type", nullable = false)
    private StreetType streetType;

    @Column(name = "street_name", nullable = false, length = 45)
    private String streetName;
    
    @ManyToOne
    @JoinColumn(name = "suburb_type", nullable = false)
    private SuburbType suburbType;

    @Column(name = "suburb_name", nullable = false, length = 45)
    private String suburbName;

    @Column(name = "outdoor_number", nullable = false, length = 4)
    private String outdoorNumber;

    @Column(name = "interior_number", nullable = true, length = 4)
    private String interiorNumber;

    @Column(name = "postal_code", nullable = false, length = 5)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private PatientOwner owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public StreetType getStreetType() {
        return streetType;
    }

    public void setStreetType(StreetType streetType) {
        this.streetType = streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public SuburbType getSuburbType() {
        return suburbType;
    }

    public void setSuburbType(SuburbType suburbType) {
        this.suburbType = suburbType;
    }

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public String getOutdoorNumber() {
        return outdoorNumber;
    }

    public void setOutdoorNumber(String outdoorNumber) {
        this.outdoorNumber = outdoorNumber;
    }

    public String getInteriorNumber() {
        return interiorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public PatientOwner getOwner() {
        return owner;
    }

    public void setOwner(PatientOwner owner) {
        this.owner = owner;
    }
    
}
