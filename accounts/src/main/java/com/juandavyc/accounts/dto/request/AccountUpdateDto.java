package com.juandavyc.accounts.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AccountUpdateDto {

    @NotEmpty(message = "Username cannot be null or empty")
    private String username;

    @NotEmpty(message = "Email cannot be null or empty")
    @Pattern(
            regexp = "^([a-z0-9_\\.-]+\\@[\\da-z\\.-]+\\.[a-z\\.]{2,6})$",
            message = "Invalid email format"
    )
    private String email;
}
