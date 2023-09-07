//
//package com.membermanagement.Member.Management.service;
//
//
//import com.membermanagement.Member.Management.dto.*;
//import com.membermanagement.Member.Management.entity.People;
//import com.membermanagement.Member.Management.exception.PeopleNotFoundException;
//import com.membermanagement.Member.Management.repository.PeopleRepository;
//import com.membermanagement.Member.Management.service.PeopleService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class MemberServiceTest {
//
//    @Mock
//    private PeopleRepository repo;
//
//    @InjectMocks
//    private PeopleService service;
//
//    @Spy
//    private ModelMapper modelMapper;
//
//
//    People member = new People("morales", "justin", "valencia", LocalDate.of(2022, 10, 3), true);
//    //    CreatePeopleRequest memberRequest1 = new CreatePeopleRequest("mataac", "alex", "orante", new Date(2022, 10, 3), true);
////    CreatePeopleRequest memberRequest2 = new CreatePeopleRequest("flores", "michael", "cezar", new Date(2022, 10, 3), true);
//    UpdatePeopleRequest memberRequest3 = new UpdatePeopleRequest("regalado", "jerome", "garcia");
////    UpdatePeopleRequest memberRequest4 = new UpdatePeopleRequest("manghnani", "eshant", "p", new Date(2022, 10, 3), true);
////
//
//    @Test
//    @DisplayName("" +
//            "Given Members with the setup above " +
//            "When saveMember(CreateMemberRequest.class) is executed" +
//            "Then result should return member")
//    void saveMemberTest() {
//
//        //Arrange
//        CreatePeopleRequest expectedMember = new CreatePeopleRequest("morales", "justin", "valencia", LocalDate.of(2022, 10, 3));
//        when(repo.save(any(People.class))).thenReturn(member);
//
//        //Act
//        CreatePeopleResponse result = service.save(expectedMember);
//
//        //Assert
//        verify(repo).save(any(People.class));
//        assertEquals(modelMapper.map(member, CreatePeopleResponse.class), result);
//    }
//
//    @Test
//    @DisplayName("" +
//            "Given Members with the setup above " +
//            "When UpdateMemberResponse() is executed" +
//            "Then result should return updated member")
//    void UpdateMemberResponseTest() throws PeopleNotFoundException {
//        UpdatePeopleRequest update = memberRequest3;
//        update.setFirstName("John");
//        update.setLastName("Morales");
//        // Mocking update methods for service method dependency
//        when(repo.retrieveMemberByPeopleId(anyInt())).thenReturn(Optional.ofNullable(member));
//        when(repo.save(any(People.class))).thenReturn(member);
//        //ACT
//        UpdatePeopleResponse result = service.update(3, update);
//        //ASSERT
//        verify(repo).save(any(People.class));
//        assertEquals(modelMapper.map(member, UpdatePeopleResponse.class), result);
//    }
//    @Test
//    @DisplayName(" " +
//            "Given People with the setup above " +
//            "When getPeopleById(Integer) is executed " +
//            "Then result should return memberRequest5")
//    void getPeopleById() throws PeopleNotFoundException {
//        //Arrange
//        Integer expectedPeopleById =1;
//        when(repo.findById(anyInt())).thenReturn(Optional.ofNullable(member));
//        //Act
//        GetPeopleResponse result = service.find(expectedPeopleById);
//        //Assert
//        verify(repo).findById(anyInt());
//        assertEquals(modelMapper.map(member,GetPeopleResponse.class), result);
//    }
//    @Test
//    @DisplayName(" " +
//            "Given People with the setup above " +
//            "When deletePeopleById is executed " +
//            "Then Mockito should verify if the method was executed")
//    void deletePeopleById() throws PeopleNotFoundException {
//        //Arrange
//        GetPeopleRequest expected = new GetPeopleRequest(1);
//        when(repo.findById(anyInt())).thenReturn(Optional.of(member));
//        //Act
//        repo.deleteMember(1);
//        People people = repo.findById(1).orElseThrow(() -> new PeopleNotFoundException("Member not found."));
//
//        //Assert
//        verify(repo).deleteMember(1);
//    }
//}
