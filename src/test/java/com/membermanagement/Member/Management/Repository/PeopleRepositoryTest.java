//package com.membermanagement.Member.Management.Repository;
//import com.membermanagement.Member.Management.dto.UpdatePeopleRequest;
//import com.membermanagement.Member.Management.entity.People;
//import com.membermanagement.Member.Management.repository.PeopleRepository;
//import com.membermanagement.Member.Management.service.PeopleService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace= NONE)
//public class PeopleRepositoryTest {
//    @Autowired
//    PeopleRepository memberRepository;
//
//    People test1, test2, test3;
//    @BeforeEach
//    public void setup()
//    {
//        test1 = new People();
//        test1.setLastName("Hello");
//        test2 = new People();
//        test2.setLastName("Hi");
//        test3 = new People();
//        test3.setLastName("Hiya");
//    }
//
//
//    @Test
//    void save()
//    {
//        //act
//        test1  = memberRepository.save(test1);
//        //assert
//        assertNotNull(test1);
//    }
//    @Test
//    @DisplayName("GIVEN members from SQL " +
//            "WHEN findAll() is executed " +
//            "THEN result should validate that repository is not empty")
//    public void testRepositoryIsNotEmpty() {
//        // Act
//        List<People> expectedList = new ArrayList<>();
//        test1.setPeopleId(1);
//        test2.setPeopleId(2);
//        test3.setPeopleId(3);
//        expectedList.add(test1);
//        expectedList.add(test2);
//        expectedList.add(test3);
//        List <People> peopleList = new ArrayList<>();
//        memberRepository.saveAll(expectedList).iterator().forEachRemaining(peopleList::add);
//
//        // Assert
//        assertEquals(expectedList, peopleList);
//    }
//
//    @Test
//    public void testUpdateMember()
//    {
//        //act
//        test1.setFirstName("testing");
//        List<People> expectedList = new ArrayList<>();
//        expectedList.add(test1);
//        List <People> peopleList = new ArrayList<>();
//        memberRepository.saveAll(expectedList).iterator().forEachRemaining(peopleList::add);
//
//        //assert
//        assertEquals(test1,peopleList.get(0));
//    }
//
//    @Test
//    public void testFindMemberById()
//    {
//        //act
//        test1.setPeopleId(1);
//        memberRepository.save(test1);
//        //assert
//        assertNotNull(memberRepository.retrieveMemberByPeopleId(1));
//    }
//    @Test
//    public void testDeleteMemberById() throws Exception {
//        //Act
//        memberRepository.save(test1);
//        boolean isExistBeforeDelete = memberRepository.findById(1).isPresent();
//
////        People newMember = this.memberRepository.save(test1);
//////        assertThat(this.memberRepository.existsById(test1.getPeopleId()));
//////        assertThat(test1.getIsActive());
////        Integer id = newMember.getPeopleId();
//        //Act
//        memberRepository.delete(test1);
////        this.memberRepository.deleteMember(id);
//        //Assert
//        assertTrue(isExistBeforeDelete);
//        assertFalse(memberRepository.findById(1).isEmpty());
////        Optional<People> member = this.memberRepository.findById(id);
////        assertFalse(member.get().getIsActive());
//    }
//
//}