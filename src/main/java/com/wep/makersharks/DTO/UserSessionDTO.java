package com.wep.makersharks.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSessionDTO {
    @JsonIgnore
    private Long userId;

    private String email;
    private String username;
    private String password;
    private String message;
    private String token;


    public UserSessionDTO(String message) {
        this.message = message;
    }

    public void validateDTO() {

    }


}
