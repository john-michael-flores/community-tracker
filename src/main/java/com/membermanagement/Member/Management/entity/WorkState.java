package com.membermanagement.Member.Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "workstate")
public class WorkState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workstateid")
    private Integer workStateId;

    @Column(name = "workstatedesc", length = 100)
    private String workStateDesc;

    @Column(name = "isactive")
    private Boolean isActive;
}
