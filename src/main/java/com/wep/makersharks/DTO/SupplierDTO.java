package com.wep.makersharks.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierDTO {


    private Long supplierId;

    private String companyName;


    private String website;


    private String location;

    private String natureOfBusiness;

    private String manufacturingProcesses;


    private String message;

}
