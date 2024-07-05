package exam01.member.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLogin {
    //DTO
    private String email;
    private String password;
}
