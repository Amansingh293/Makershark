package com.wep.makersharks.services;

import com.wep.makersharks.DTO.SupplierDTO;
import com.wep.makersharks.models.ManufacturingProcesses;
import com.wep.makersharks.models.NatureOfBusiness;
import com.wep.makersharks.models.Supplier;
import com.wep.makersharks.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public List<SupplierDTO> getSupplier(String location , ManufacturingProcesses manufacturingProcesses , NatureOfBusiness capability){

        try{
            List<Supplier> suppliers = supplierRepository.getSuppliers(location , manufacturingProcesses,  capability);

            if(suppliers.size() == 0){
                throw  new RuntimeException("No supplier found with given conditions!!");
            }

            List<SupplierDTO> suppliersList = new ArrayList<>();

            for(Supplier supplier : suppliers){
               SupplierDTO supplierDTO = SupplierDTO.builder()
                       .supplierId(supplier.getSupplier_id())
                       .website(supplier.getWebsite())
                       .location(supplier.getLocation())
                       .companyName(supplier.getCompany_name())
                       .natureOfBusiness(supplier.getNatureOfBusiness().name())
                       .manufacturingProcesses(supplier.getManufacturingProcesses().name())
                       .build();

               suppliersList.add(supplierDTO);
            }

            return suppliersList;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
