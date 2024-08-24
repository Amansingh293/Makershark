package com.wep.makersharks.controllers;

import com.wep.makersharks.DTO.SupplierDTO;
import com.wep.makersharks.models.ManufacturingProcesses;
import com.wep.makersharks.models.NatureOfBusiness;
import com.wep.makersharks.repository.SupplierRepository;
import com.wep.makersharks.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;

    public SupplierController(SupplierRepository supplierRepository, SupplierService supplierService) {
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
    }

    @PostMapping("/search")
    public ResponseEntity getSupplierByQuery(@RequestParam String location , @RequestParam String manufacturingProcesses , @RequestParam String capability){
        try{
            ManufacturingProcesses process = ManufacturingProcesses.valueOf(manufacturingProcesses.toLowerCase());
            NatureOfBusiness businessCapability = NatureOfBusiness.valueOf(capability.toLowerCase());

            List<SupplierDTO> suppliers = supplierService.getSupplier(location , process , businessCapability);

            return ResponseEntity.ok(suppliers);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching: " + e.getMessage());
        }
    }
}
