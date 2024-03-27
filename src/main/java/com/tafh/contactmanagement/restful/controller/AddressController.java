package com.tafh.contactmanagement.restful.controller;



import com.tafh.contactmanagement.restful.entity.User;
import com.tafh.contactmanagement.restful.model.AddressResponse;
import com.tafh.contactmanagement.restful.model.CreateAddressRequest;
import com.tafh.contactmanagement.restful.model.UpdateAddressRequest;
import com.tafh.contactmanagement.restful.model.WebResponse;
import com.tafh.contactmanagement.restful.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contacts/{contact_id}/addresses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable("contact_id") String contactId) {

        request.setContactId(contactId);
        AddressResponse addressResponse = addressService.create(user, request);
        return WebResponse.<AddressResponse>builder()
                .data(addressResponse)
                .build();
    }

    @GetMapping(
            path = "/api/contacts/{contact_id}/addresses/{address_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable("contact_id") String contactId,
                                            @PathVariable("address_id") String addressId) {
        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder()
                .data(addressResponse).build();
    }

    @PutMapping(
            path = "/api/contacts/{contact_id}/addresses/{address_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(User user,
                                               @RequestBody UpdateAddressRequest request,
                                               @PathVariable("contact_id") String contactId,
                                               @PathVariable("address_id") String addressId) {
        request.setAddressId(addressId);
        request.setContactId(contactId);

        AddressResponse response = addressService.update(user, request);

        return WebResponse.<AddressResponse>builder().data(response).build();
    }

   @DeleteMapping(
           path = "/api/contacts/{contact_id}/addresses/{address_id}",
           produces = MediaType.APPLICATION_JSON_VALUE
   )
   public WebResponse<String> remove(User user,
                                     @PathVariable("contact_id") String contactId,
                                     @PathVariable("address_id") String addressId) {
       addressService.remove(user, contactId, addressId);
       return WebResponse.<String>builder().data("OK").build();
   }
    @GetMapping(
            path = "/api/contacts/{contact_id}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> list(User user,
                                      @PathVariable("contact_id") String contactId) {
        List<AddressResponse> addressResponses = addressService.list(user, contactId);
        return WebResponse.<List<AddressResponse>>builder()
                .data(addressResponses).build();
    }

}
