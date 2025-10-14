package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Address;
import com.SoloProject.solo.repos.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    //Update existing Address
    public Optional<Address> updateAddress(UUID is, Address newData) {
        return addressRepo.findById(id).map(existing -> {
            existing.setLine1(newData.getLine1());
            existing.setLine2(newData.getLine2());
            existing.setCity(newData.getCity());
            existing.setState(newData.getState());
            existing.setPostalCode(newData.getPostalCode());
        });
    }

    //Create a new address
    public Address createAddress(Address address) {
        return addressRepo.save(address);
    }
    //Delete an address by id
    public boolean deleteAddress(UUID id) {
        if (addressRepo.existById(id)) {
            addressRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
