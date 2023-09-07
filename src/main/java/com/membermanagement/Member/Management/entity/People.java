package com.membermanagement.Member.Management.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peopleid")
    private Integer peopleId;

    @Column(name = "cognizantid", unique = true)
    private Integer cognizantId = 0;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Column(name = "firstname", length = 40)
    private String firstName;

    @Column(name = "middlename", length = 40)
    private String middleName;

    @Column(name = "fullname", length = 130)
    private String fullName;

    @Column(name = "csvemail", length = 50, unique = true)
    private String csvEmail = "sample@sample.com";

    @Column(name = "hireddate")
    private LocalDate hiredDate;

    @ManyToOne
    @JoinColumn(name = "communityid", updatable = false)
    private Community communityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communityadminandmanagerid", updatable = false)
    private CommunityAdminAndManager communityAdminAndManagerId;

    @ManyToOne
    @JoinColumn(name = "joblevelid", updatable = false)
    private JobLevel jobLevelId;

    @ManyToOne
    @JoinColumn(name = "projectid", updatable = false)
    private Project projectId;

    @ManyToOne
    @JoinColumn(name = "workstateid", updatable = false)
    private WorkState workStateId;

    @Column(name = "isprobationary")
    private Boolean isProbationary = false;

    @Column(name = "isactive")
    private Boolean isActive = true;

    public People(String lastName, String firstName, String middleName, LocalDate hiredDate, Boolean isActive) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.hiredDate = hiredDate;
        this.isActive = isActive;
    }
}