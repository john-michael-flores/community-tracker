package com.membermanagement.Member.Management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.membermanagement.Member.Management.entity.*;
import com.membermanagement.Member.Management.validator.CustomDateValidator;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CreatePeopleRequest {

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 50, message = "Maximum characters is 50")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 40, message = "Maximum characters is 40")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z-\\sñ]+")
    @Size(min = 2, max = 40, message = "Maximum characters is 40")
    private String middleName;

    private String fullName;

    @CustomDateValidator
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate hiredDate;

    @Pattern(regexp = "^[A-Za-z\\+]*\\.+([A-Za-z]+)*@softvision.com")
    @Column(unique = true)
    private String csvEmail;

    private JobLevel jobLevelId;


    @Positive
    @Column(unique = true)
    private Integer cognizantId;

    private Community communityId;

    private Project projectId;

    private WorkState workStateId;

    public CreatePeopleRequest(String lastName, String firstName, String middleName, LocalDate hiredDate, String csvEmail, JobLevel jobLevelId, WorkState workStateId, Integer cognizantId, Community communityId, Project projectId) {
        if (lastName != null || firstName != null || middleName != null) {
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
            this.fullName = this.firstName + " " + this.middleName + " " + this.lastName;
            this.hiredDate = hiredDate;
            this.jobLevelId = jobLevelId;
            this.workStateId = workStateId;
            this.cognizantId = cognizantId;
            this.csvEmail = csvEmail;
            this.communityId = communityId;
            this.projectId = projectId;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field must not be null.");
        }
    }
}

