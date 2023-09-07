package com.membermanagement.Member.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "joblevel")
public class JobLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joblevelid")
    private Integer jobLevelId;

    @Column(name = "jobleveldesc", length = 100)
    private String jobLevelDesc;

    @Column(name = "isactive")
    private Boolean isActive = true;

//    @OneToMany(targetEntity = People.class, mappedBy = "joblevelid", orphanRemoval = false, fetch = FetchType.LAZY)
//    private Set<People> people;

}
