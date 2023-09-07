package com.membermanagement.Member.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communityid")
    private Integer communityId;

    @Column(name = "communityname", length = 50)
    private String communityName;

    @Column(name = "communityicon", columnDefinition = "TEXT")
    private String communityIcon;

    @Column(name = "communitymgrid")
    private Integer communitymgrId;

    @Column(name = "communitydesc", length = 250)
    private String communityDesc;

    @Column(name = "isactive")
    private Boolean isActive;


}
