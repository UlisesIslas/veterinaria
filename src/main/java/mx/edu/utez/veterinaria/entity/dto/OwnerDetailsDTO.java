package mx.edu.utez.veterinaria.entity.dto;

import mx.edu.utez.veterinaria.entity.OwnerAddress;
import mx.edu.utez.veterinaria.entity.PatientOwner;
import mx.edu.utez.veterinaria.entity.Wallet;

public class OwnerDetailsDTO {

    private PatientOwner owner;
    private OwnerAddress address;
    private Wallet wallet;
    public PatientOwner getOwner() {
        return owner;
    }
    public void setOwner(PatientOwner owner) {
        this.owner = owner;
    }
    public OwnerAddress getAddress() {
        return address;
    }
    public void setAddress(OwnerAddress address) {
        this.address = address;
    }
    public Wallet getWallet() {
        return wallet;
    }
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    
}
