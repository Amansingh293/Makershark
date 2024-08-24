package com.wep.makersharks.repository;

import com.wep.makersharks.models.ManufacturingProcesses;
import com.wep.makersharks.models.NatureOfBusiness;
import com.wep.makersharks.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier , Long> {

    @Query("SELECT s FROM supplier s WHERE s.location = :location AND s.manufacturingProcesses = :manufacturer AND s.natureOfBusiness = :capability")
    public List<Supplier> getSuppliers(
            @Param("location") String location,
            @Param("manufacturer") ManufacturingProcesses manufacturer,
            @Param("capability") NatureOfBusiness capability
    );
}
