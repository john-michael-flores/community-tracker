package com.membermanagement.Member.Management.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePeopleRequest {

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 50, message = "Maximum characters is 50")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 40, message = "Maximum characters is 40")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 40, message = "Maximum characters is 40")
    private String middleName;

    public void setLastName(String lastName) {
        if (lastName != null) {
            if (lastName.contains("-")) {
                this.lastName = lastName.trim().replaceAll(" +", "")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            } else {
                this.lastName = lastName.trim().replaceAll(" +", " ")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field must not be null.");
        }
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            if (firstName.contains("-")) {
                this.firstName = firstName.trim().replaceAll(" +", "")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            } else {
                this.firstName = firstName.trim().replaceAll(" +", " ")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field must not be null.");
        }
    }

    public void setMiddleName(String middleName) {
        if (middleName != null) {
            if (middleName.contains("-")) {
                this.middleName = middleName.trim().replaceAll(" +", "")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            } else {
                this.middleName = middleName.trim().replaceAll(" +", " ")
                        .replaceAll("^-+", "")
                        .replaceAll("-+$", "")
                        .replaceAll("-+", "-");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field must not be null.");
        }
    }
}