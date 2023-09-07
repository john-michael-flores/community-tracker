package com.membermanagement.Member.Management.controller;
import com.membermanagement.Member.Management.common.dto.ApiResponse;
import com.membermanagement.Member.Management.dto.*;
import com.membermanagement.Member.Management.exception.ComponentAlreadyPresentException;
import com.membermanagement.Member.Management.exception.PeopleNotFoundException;
import com.membermanagement.Member.Management.service.PeopleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("/api/people")
@Validated
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<CreatePeopleResponse>> addPeople(@RequestBody @Valid CreatePeopleRequest people, Pageable pageable) throws ComponentAlreadyPresentException {
        return new ResponseEntity<>(new ApiResponse<>(true, "Member Successfully created.",
                peopleService.save(people, pageable)), HttpStatus.CREATED);
    }

    @PatchMapping("/{peopleId}")
    public ResponseEntity<ApiResponse<UpdatePeopleResponse>> updatePeople(@PathVariable Integer peopleId, @RequestBody @Valid UpdatePeopleRequest people) throws PeopleNotFoundException {
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Member Successfully updated.",
                        peopleService.update(peopleId, people)), HttpStatus.OK);
    }

    @GetMapping("/{peopleId}")
    public ResponseEntity<ApiResponse<GetPeopleResponse>> getPeople(@PathVariable @Valid Integer peopleId) throws PeopleNotFoundException {
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Member Successfully retrieved.",
                        peopleService.find(peopleId)), HttpStatus.OK);
    }
    @ApiOperation(value = "Retrieve All or Search Members")
    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<GetPeopleResponse>>> retrieveAllOrSearchMembers(
            @RequestParam(value = "query", required = false) @Pattern(regexp = "[a-zA-Z-\\sñ]+", message = "Invalid Search Keyword") String query,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) throws PeopleNotFoundException {
        return new ResponseEntity<>(

                new ApiResponse<>(true, "Member Successfully retrieved",
                        peopleService.retrieve(query, pageable)),HttpStatus.OK);


    }

    @DeleteMapping("/{peopleId}")
    public ResponseEntity<ApiResponse<String>> deletePeople(@PathVariable @Valid Integer peopleId) throws PeopleNotFoundException {
        peopleService.delete(peopleId);
        return new ResponseEntity<>(
                new ApiResponse<>(true, "Member Successfully Deleted.", null), HttpStatus.OK);
    }

}