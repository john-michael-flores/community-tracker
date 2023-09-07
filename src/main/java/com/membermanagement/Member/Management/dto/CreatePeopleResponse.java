package com.membermanagement.Member.Management.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreatePeopleResponse {

    private Integer peopleId;

    private String lastName;

    private String firstName;

    private String middleName;

    private LocalDate hiredDate;

    private Boolean isActive;
}
