package org.digitinary.traninng.librarymanagmentsystem.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    private int id;
    @NotNull
    @Size(max=64)
    private String name;
    @Email(message = "email not valid : example@email.com")
    private String email;
    @Size(min = 10,max=10,message = "mobile number must be 10 digit")
    private String phoneNumber;
}
