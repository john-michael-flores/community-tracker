package com.membermanagement.Member.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPeopleResponse {
    private Integer peopleId;

    private String lastName;

    private String firstName;

    private String middleName;

    private Integer cognizantId;

    private Boolean isActive;

    private String jobLevelDesc;

    private String projectDesc;

    private String communityName;
}
