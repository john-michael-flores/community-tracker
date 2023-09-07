//
//package com.membermanagement.Member.Management.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.membermanagement.Member.Management.dto.*;
//import com.membermanagement.Member.Management.service.PeopleService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///*@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@SpringBootTest(classes = PeopleManagementApplication.class)*/
//@WebMvcTest
//public class PeopleControllerTest {
//
//    @MockBean
//    private PeopleService service;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private WorkStateService workStateService;
//
//    @MockBean
//    private JobLevelService jobLevelService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private CreatePeopleResponse test1;
//    private UpdatePeopleResponse test2;
//    private GetPeopleResponse test3;
//
//
//    @BeforeEach
//    void setUp() {
//        LocalDate date = LocalDate.of(2019,10,10);
//        test1 = new CreatePeopleResponse();
//        test2 = new UpdatePeopleResponse();
//        test3 = new GetPeopleResponse();
//        test1.setLastName("testOne");
//        test1.setFirstName("testOne");
//        test1.setHiredDate(date);
//        test1.setIsActive(true);
//        test2.setLastName("testTwo");
//        test2.setFirstName("testTwo");
//        test3.setPeopleId(3);
//        test3.setLastName("testThree");
//        test3.setFirstName("testThree");
//        test3.setMiddleName("testThree");
//        test3.setCognizantId(3);
//        test3.setIsActive(true);
//        test3.setJobLevelDesc("software engineer");
//        test3.setProjectDesc("training");
//        test3.setCommunityName("Coffee");
//
//    }
//   // CreatePeopleResponse test1 = new CreatePeopleResponse(1,"morales", "justin", "valencia", LocalDate.of(2022, 10, 3),true);
//    CreatePeopleRequest test1Request= new CreatePeopleRequest("morales", "justin", "valencia", LocalDate.of(2022, 10, 3));
//   // UpdatePeopleResponse test2 = new UpdatePeopleResponse(2,"morales", "justin", "valencia");
//    UpdatePeopleRequest test2Request= new UpdatePeopleRequest("morales", "justin", "valencia");
//   // GetPeopleResponse test3 = new GetPeopleResponse(3,"morales", "justin", "valencia",3,true,"software engineer","training","Coffee");
//
//
//    @Test
//    @DisplayName("GIVEN test1Request with the setup above" +
//            "WHEN save() is executed" +
//            "THEN result should return test1")
//    void addPeopleTest() throws Exception {
//        //arrange
//        when(service.save(any(CreatePeopleRequest.class))).thenReturn(test1);
//        //act
//        //assert
//        mockMvc.perform(post("/api/people")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(test1Request)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.payload.lastName").value(test1.getLastName()))
//                .andExpect(jsonPath("$.payload.firstName").value(test1.getFirstName()))
//                .andExpect(jsonPath("$.payload.hiredDate").value(test1.getHiredDate().toString()))
//                .andExpect(jsonPath("$.payload.isActive").value(test1.getIsActive()));
//    }
//
//    @Test
//    @DisplayName("GIVEN test2Request with the setup above" +
//            "WHEN update() is executed" +
//            "THEN result should return test1")
//    void updatePeopleTest() throws Exception {
//        //arrange
//        test2.setFirstName("tintin");
//        when(service.update(anyInt(),any(UpdatePeopleRequest.class))).thenReturn(test2);
//        //act
//        //assert
//        mockMvc.perform(patch("/api/people/{peopleId}",2)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(test2Request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.payload.firstName").value(test2.getFirstName()));
//    }
//    @Test
//    @DisplayName("" +
//            "Given People with the setup above " +
//            "When get() is executed " +
//            "Then result should return test3")
//    void getPeopleTest() throws Exception {
//        //Arrange
//        test3.setFirstName("ADKJADS");
//        when(service.find(anyInt())).thenReturn(test3);
//        //Act
//        //Assert
//        mockMvc.perform(get("/api/people/{peopleId}", 2)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(test3)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("" +
//    "Given People with the setup above " +
//    "When delete() is executed with PeopleId " +
//    "Then result should return HttpStatus is Ok")
//    void deletePeopleIdTest() throws Exception {
//        //Arrange
//        test3.setPeopleId(2);
//        when(service.find(anyInt())).thenReturn(test3);
//
//        //Act
//
//        //Assert
//        mockMvc.perform(delete("/api/people/{peopleId}",2)
//                .contentType(MediaType.APPLICATION_JSON)
//                .contentType(objectMapper.writeValueAsString(test3)))
//                .andExpect(status().isOk());
//    }
//    //    @Test
////    @DisplayName("GIVEN People with the setup above" +
////            "WHEN find() is executed with peopleId = 3 " +
////            "THEN result should return test3")
////    void getPeopleByIdTest() throws Exception {
//
////        //arrange
////        when(service.save(any(CreatePeopleRequest.class))).thenReturn(test1);
////        //act
////        //assert
////        mockMvc.perform(post("/api/people")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(test1Request)))
////                .andExpect(status().isCreated())
////                .andExpect(jsonPath("$.payload.lastName").value(test1.getLastName()))
////                .andExpect(jsonPath("$.payload.firstName").value(test1.getFirstName()))
////                .andExpect(jsonPath("$.payload.hiredDate").value(test1.getHiredDate().toString()))
////                .andExpect(jsonPath("$.payload.isActive").value(test1.getIsActive()));
////    }
////
////    @Test
////    @DisplayName("GIVEN test2Request with the setup above" +
////            "WHEN update() is executed" +
////            "THEN result should return test1")
////    void updatePeopleTest() throws Exception {
////        //arrange
////        test2.setFirstName("tintin");
////        when(service.update(anyInt(),any(UpdatePeopleRequest.class))).thenReturn(test2);
////        //act
////        //assert
////        mockMvc.perform(patch("/api/people/{peopleId}",2)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(test2Request)))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.payload.firstName").value(test2.getFirstName()));
////    }
////    @Test
////    @DisplayName("" +
////            "Given People with the setup above " +
////            "When get() is executed " +
////            "Then result should return test3")
////    void getPeopleTest() throws Exception {
////        //Arrange
////        test3.setFirstName("ADKJADS");
////        when(service.find(anyInt())).thenReturn(test3);
////        //Act
////        //Assert
////        mockMvc.perform(get("/api/people/{peopleId}", 2)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(test3)))
////                .andExpect(status().isOk());
////    }
////
//////    @Test
//////    @DisplayName("" +
//////    "Given People with the setup above " +
//////    "When delete() is executed with PeopleId " +
//////    "Then result should return HttpStatus is Ok")
//////    void deletePeopleIdTest() throws Exception {
//////        //Arrange
//////        test3.setPeopleId(2);
//////        when(service.find(anyInt()).thenReturn(null));
//////        //Act
//////        //Assert
//////        mockMvc.perform(delete("/api/people/{peopleId",2)
//////                .contentType(MediaType.APPLICATION_JSON)
//////                .contentType(objectMapper.writeValueAsString(test3)))
//////                .andExpect(status().isOk());
//////    }
////    //    @Test
//////    @DisplayName("GIVEN People with the setup above" +
//////            "WHEN find() is executed with peopleId = 3 " +
//////            "THEN result should return test3")
//////    void getPeopleByIdTest() throws Exception {
//////        //arrange
//////        when(service.find(anyInt())).thenReturn(test3);
//////        //act
//////        // assert
//////        mockMvc.perform(get("/people/{peopleId}", 3))
//////                .andExpect(status().isOk())
//////                .andExpect(jsonPath("$.payload.peopleId").value(test3.getPeopleId()))
//////                .andExpect(jsonPath("$.payload.lastName").value(test3.getLastName()))
//////                .andExpect(jsonPath("$.payload.firstName").value(test3.getFirstName()))
//////                .andExpect(jsonPath("$.payload.middleName").value(test3.getMiddleName()))
//////                .andExpect(jsonPath("$.payload.cognizantId").value(test3.getCognizantId()))
//////                .andExpect(jsonPath("$.payload.isActive").value(test3.getIsActive()))
//////                .andExpect(jsonPath("$.payload.jobLevelDesc").value(test3.getJobLevelDesc()))
//////                .andExpect(jsonPath("$.payload.projectDesc").value(test3.getProjectDesc()))
//////                .andExpect(jsonPath("$.payload.communityName").value(test3.getCommunityName()));
//////    }
//////
//////    @Test
//////    @DisplayName("GIVEN People with the setup above " +
//////            "WHEN delete() is executed with peopleId = 2 " +
//////            "THEN result should return HttpStatus OK")
//////    void deletePeopleTest() throws Exception {
//////        //arrange
//////        doNothing().when(service).delete(anyInt());
//////        //act
//////        //assert
//////        mockMvc.perform(delete("/people/{peopleId}", 2))
//////                .andExpect(status().isOk());
//////    }
////}
