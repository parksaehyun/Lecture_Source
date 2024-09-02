package org.choongang.member.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RequestJoin {
    @Notblank @Email
    private String email;
}

@NotBlank @Email