package com.wep.makersharks.services;

import com.wep.makersharks.DTO.SupplierDTO;
import com.wep.makersharks.models.ManufacturingProcesses;
import com.wep.makersharks.models.NatureOfBusiness;
import com.wep.makersharks.models.Supplier;
import com.wep.makersharks.repository.SupplierRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public Page<SupplierDTO> getSupplier(String location, ManufacturingProcesses manufacturingProcesses,
                                         NatureOfBusiness capability, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Supplier> suppliersPage = supplierRepository.findByLocationAndManufacturingProcessesAndNatureOfBusiness(
                    location, manufacturingProcesses, capability, pageable);

            if (suppliersPage.isEmpty()) {
                throw new RuntimeException("No supplier found with given conditions!!");
            }

            // Convert Supplier entities to SupplierDTOs
            return suppliersPage.map(supplier -> SupplierDTO.builder()
                    .supplierId(supplier.getSupplier_id())
                    .website(supplier.getWebsite())
                    .location(supplier.getLocation())
                    .companyName(supplier.getCompany_name())
                    .natureOfBusiness(supplier.getNatureOfBusiness().name())
                    .manufacturingProcesses(supplier.getManufacturingProcesses().name())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
