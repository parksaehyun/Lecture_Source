package exam01.member.controllers;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RequestJoin {
    //DTO
        private String email;
        private String password;
        private String confirmPassword;
        private String userName;
        private LocalDate RegDt;
}
