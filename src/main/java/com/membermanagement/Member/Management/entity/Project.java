package com.membermanagement.Member.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectid")
    private Integer projectId;

    @Column(name = "projectdesc", length = 100)
    private String projectDesc;

    @Column(name = "projectcode", length = 100)
    private String projectCode;

    @Column(name = "isactive")
    private Boolean isActive = true;
}
