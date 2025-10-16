package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Address;
import com.SoloProject.solo.repos.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    //Update existing Address
    public Optional<Address> updateAddress(UUID id, Address newData) {
        return addressRepo.findById(id).map(existing -> {
            if (newData.getLine1 () != null)existing.setLine1(newData.getLine1());
            if (newData.getLine2 () != null)existing.setLine2(newData.getLine2());
            if (newData.getCity () != null)existing.setCity(newData.getCity());
            if (newData.getState () != null)existing.setState(newData.getState());
            if (newData.getPostalCode () != null)existing.setPostalCode(newData.getPostalCode());
            return addressRepo.save(existing);
        });
    }

    //Create a new address
    public Address createAddress(Address address) {return addressRepo.save(address);}

    //Delete an address by id
    public boolean deleteAddress(UUID id) {
        if (addressRepo.existsById(id)) {
            addressRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }
}
