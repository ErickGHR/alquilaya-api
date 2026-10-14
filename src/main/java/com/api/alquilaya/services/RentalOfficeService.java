package com.api.alquilaya.services;

import org.springframework.stereotype.Service;

import com.api.alquilaya.models.RentalOffice;
import com.api.alquilaya.repositories.RentalOfficeRepository;

@Service
public class RentalOfficeService extends BaseCrudService<RentalOffice> {

    public RentalOfficeService(RentalOfficeRepository repository) {
        super(repository, "La oficina");
    }
}
