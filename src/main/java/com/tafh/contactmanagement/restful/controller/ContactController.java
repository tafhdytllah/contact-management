package com.tafh.contactmanagement.restful.controller;

import com.tafh.contactmanagement.restful.entity.User;
import com.tafh.contactmanagement.restful.model.ContactResponse;
import com.tafh.contactmanagement.restful.model.CreateContactRequest;
import com.tafh.contactmanagement.restful.model.UpdateContactRequest;
import com.tafh.contactmanagement.restful.model.WebResponse;
import com.tafh.contactmanagement.restful.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(
            path = "/api/contacts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request) {
        ContactResponse contactResponse = contactService.create(user, request);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(
            path = "/api/contacts/{id_contact}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<ContactResponse> get(User user, @PathVariable("id_contact") String idContact) {
        ContactResponse response = contactService.get(user, idContact);
        return WebResponse.<ContactResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/contacts/{id_contact}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<ContactResponse> update(User user,
                                                @RequestBody UpdateContactRequest request,
                                                @PathVariable("id_contact") String idContact) {
        request.setId(idContact);

        ContactResponse contactResponse = contactService.update(user, request);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts/{id_contact}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private WebResponse<String> delete(User user, @PathVariable("id_contact") String idContact) {
        contactService.delete(user, idContact);

        return WebResponse.<String>builder().data("OK").build();
    }

}
