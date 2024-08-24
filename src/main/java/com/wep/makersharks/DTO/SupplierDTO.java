package com.wep.makersharks.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    @JsonProperty("supplier_id")
    private Long supplierId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("website")
    private String website;

    @JsonProperty("location")
    private String location;

    @JsonProperty("nature_of_business")
    private String natureOfBusiness;

    @JsonProperty("manufacturing_processes")
    private String manufacturingProcesses;

}
