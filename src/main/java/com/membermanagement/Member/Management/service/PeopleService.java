package com.membermanagement.Member.Management.service;
import com.membermanagement.Member.Management.dto.*;
import com.membermanagement.Member.Management.entity.People;
import com.membermanagement.Member.Management.exception.ComponentAlreadyPresentException;
import com.membermanagement.Member.Management.exception.PeopleNotFoundException;
import com.membermanagement.Member.Management.repository.PeopleRepository;
import org.apache.commons.lang3.Validate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public CreatePeopleResponse save(CreatePeopleRequest memberRequest, Pageable pageable) throws ComponentAlreadyPresentException {
        List<People> peopleDuplicate = repo.findByCsvEmailOrCognizantId(memberRequest.getCsvEmail(), memberRequest.getCognizantId());
        Validate.isTrue(peopleDuplicate.isEmpty(), "should have a unique cognizantID or email");
        People people = repo.save(modelMapper.map(memberRequest, People.class));
        return modelMapper.map(people, CreatePeopleResponse.class);
    }

    @Transactional
    public UpdatePeopleResponse update(Integer peopleId, UpdatePeopleRequest memberRequest) throws PeopleNotFoundException {
        People people = repo.retrieveMemberByPeopleId(peopleId)
                .orElseThrow(() -> new PeopleNotFoundException("Member not found."));
        BeanUtils.copyProperties(memberRequest, people, getNullPropertyNames(memberRequest));
        return modelMapper.map(repo.save(people), UpdatePeopleResponse.class);
    }

    public GetPeopleResponse find(Integer peopleId) throws PeopleNotFoundException{
        List<GetPeopleResponse> people = repo.retrieveMemberWithJoin(peopleId).stream().toList();
        if (people.isEmpty()) {
            throw new PeopleNotFoundException("Member not found");
        }
        return people.get(0);
    }

    public Page<GetPeopleResponse> retrieve(String query, Pageable pageable) throws PeopleNotFoundException {
        List<GetPeopleResponse> people;
        if (query == null) {
            query = "";
            people = repo.retrieveAllOrSearchMembers(query, pageable).toList();
        } else {
            people = repo.retrieveAllOrSearchMembers(query.toLowerCase(), pageable).toList();
        }


        Page<GetPeopleResponse> peopleList = new PageImpl<>(people,pageable, people.size());
        return peopleList;
    }

    @Transactional
    public void delete(Integer peopleId) throws PeopleNotFoundException{
        People people = repo.findById(peopleId).filter(people1 -> people1.getIsActive().equals(true))
                .orElseThrow(() -> new PeopleNotFoundException("Member not found."));
        repo.deleteMember(people.getPeopleId());
    }


    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null || Objects.equals(wrappedSource.getPropertyValue(propertyName), ""))
                .toArray(String[]::new);
    }
}
