package org.geobytelogistics.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "This field cannot be blank")
    private String firstName;

    @NotBlank(message = "This field cannot be blank")
    private String lastName;

    @NotBlank(message = "This field cannot be blank")
    @Email(message = "Must be a valid email")
    private String email;

    @Size(message = "Password must be greater than 6 and less than 20", min = 6, max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,20}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, and one number")
    @NotBlank(message = "This field cannot be blank")
    private String password;
}
