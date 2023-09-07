package com.membermanagement.Member.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "communityadminandmanager")
public class CommunityAdminAndManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communityadminandmanagerid")
    private Integer communityAdminAndManagerId;

    @Column(name = "communityadminandmanagername", length = 100)
    private String communityAdminAndManagerName;

    @Column(name = "cognizantid", length = 10)
    private Integer cognizantId;

    @Column(name = "csvemail", length = 50)
    private String csvEmail;

    @Column(length = 100)
    private String password;

    @Column(name = "roletype", length = 10)
    private String roleType;

    @Column(name = "isactive")
    private Boolean isActive;
}
