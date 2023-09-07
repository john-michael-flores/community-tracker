package com.membermanagement.Member.Management.repository;
import com.membermanagement.Member.Management.dto.GetPeopleResponse;
import com.membermanagement.Member.Management.entity.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PeopleRepository extends CrudRepository<People, Integer> {
    @Query("SELECT p FROM People p WHERE p.peopleId = :peopleId and p.isActive = true")
    Optional<People> retrieveMemberByPeopleId(@Param("peopleId") Integer peopleId);

    @Modifying
    @Query("UPDATE People p SET p.isActive = false WHERE p.peopleId = :peopleId")
    void deleteMember(@Param("peopleId") Integer peopleId);

    @Query(value = "SELECT new com.membermanagement.Member.Management.dto.GetPeopleResponse(p.peopleId, p.lastName, p.firstName, p.middleName, p.cognizantId, p.isActive, j.jobLevelDesc, pr.projectDesc, c.communityName) FROM People p JOIN p.jobLevelId j JOIN p.projectId pr JOIN p.communityId c WHERE p.peopleId = :peopleId AND p.isActive = true")
    List<GetPeopleResponse> retrieveMemberWithJoin(@Param("peopleId") Integer peopleId);


    @Query(value = "SELECT new com.membermanagement.Member.Management.dto.GetPeopleResponse(p.peopleId, p.lastName, p.firstName, p.middleName, p.cognizantId, p.isActive, j.jobLevelDesc, pr.projectDesc, c.communityName) FROM People p JOIN p.jobLevelId j JOIN p.projectId pr JOIN p.communityId c WHERE p.isActive = true AND (lower(p.firstName) LIKE :query% OR lower(p.middleName) LIKE :query% OR lower(p.lastName) LIKE :query%)")
    Page<GetPeopleResponse> retrieveAllOrSearchMembers(String query, Pageable pageable);

    List<People> findByCsvEmailOrCognizantId(String csvEmail, Integer cognizantId);

    List<People> findAll();

}
