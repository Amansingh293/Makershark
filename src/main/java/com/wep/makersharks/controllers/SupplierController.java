package com.wep.makersharks.controllers;

import com.wep.makersharks.DTO.SupplierDTO;
import com.wep.makersharks.models.ManufacturingProcesses;
import com.wep.makersharks.models.NatureOfBusiness;
import com.wep.makersharks.services.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> getSupplierByQuery(
            @RequestParam String location,
            @RequestParam String manufacturingProcesses,
            @RequestParam String capability,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            ManufacturingProcesses process = ManufacturingProcesses.valueOf(manufacturingProcesses.toLowerCase());
            NatureOfBusiness businessCapability = NatureOfBusiness.valueOf(capability.toLowerCase());

            Page<SupplierDTO> suppliers = supplierService.getSupplier(location, process, businessCapability, page, size);

            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching: "+e.getMessage());
        }
    }
}
