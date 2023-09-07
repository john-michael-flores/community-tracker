package com.membermanagement.Member.Management.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePeopleResponse {

    private Integer peopleId;

    private String lastName;

    private String firstName;

    private String middleName;

}
