package mx.edu.utez.veterinaria.entity.dto;

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.entity.Patients;

public class OwnerDTO {

    private PatientOwner owner;
    private OwnerAddress ownerAddress;
    private Patients pet;

    public PatientOwner getOwner() {
        return owner;
    }

    public void setOwner(PatientOwner owner) {
        this.owner = owner;
    }

    public OwnerAddress getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(OwnerAddress ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public Patients getPet() {
        return pet;
    }

    public void setPet(Patients pet) {
        this.pet = pet;
    }
    
}
