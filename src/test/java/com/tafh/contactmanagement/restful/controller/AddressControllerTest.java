package com.tafh.contactmanagement.restful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tafh.contactmanagement.restful.entity.Address;
import com.tafh.contactmanagement.restful.entity.Contact;
import com.tafh.contactmanagement.restful.entity.User;
import com.tafh.contactmanagement.restful.model.AddressResponse;
import com.tafh.contactmanagement.restful.model.CreateAddressRequest;
import com.tafh.contactmanagement.restful.model.UpdateAddressRequest;
import com.tafh.contactmanagement.restful.model.WebResponse;
import com.tafh.contactmanagement.restful.repository.AddressRepository;
import com.tafh.contactmanagement.restful.repository.ContactRepository;
import com.tafh.contactmanagement.restful.repository.UserRepository;
import com.tafh.contactmanagement.restful.security.BCrypt;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("Test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000L);
        userRepository.save(user);

        Contact contact = new Contact();
        contact.setId("test");
        contact.setFirstName("Test");
        contact.setLastName("Last");
        contact.setEmail("test@example");
        contact.setPhone("08123456789");
        contact.setUser(user);
        contactRepository.save(contact);

    }

    @Test
    void createAddressBadRequest() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                post("/api/contacts/test/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });

    }

    @Test
    void createAddressSuccess() throws Exception{
        CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("Basmol");
        request.setCity("Jakbar");
        request.setProvince("Jakarta");
        request.setCountry("Indonesia");
        request.setPostalCode("12345");

        mockMvc.perform(
                post("/api/contacts/test/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());
            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getCountry(), response.getData().getCountry());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/contactIdSalah/addresses/addressIdSalah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getAddressSuccess() throws Exception {

        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("testaddress");
        address.setStreet("Basmol");
        address.setCity("Jakbar");
        address.setProvince("Jakarta");
        address.setCountry("Indonesia");
        address.setPostalCode("12345");
        address.setContact(contact);
        addressRepository.save(address);

        mockMvc.perform(
                get("/api/contacts/test/addresses/testaddress")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());
            assertEquals(address.getId(), response.getData().getId());
            assertEquals(address.getStreet(), response.getData().getStreet());
            assertEquals(address.getCity(), response.getData().getCity());
            assertEquals(address.getProvince(), response.getData().getProvince());
            assertEquals(address.getCountry(), response.getData().getCountry());
            assertEquals(address.getPostalCode(), response.getData().getPostalCode());
        });

    }

    @Test
    void updateAddressBadRequest() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("testaddress");
        address.setStreet("Basmol");
        address.setCity("Jakbar");
        address.setProvince("Jakarta");
        address.setCountry("Indonesia");
        address.setPostalCode("12345");
        address.setContact(contact);
        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                put("/api/contacts/test/addresses/testaddress")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });
    }
    @Test
    void updateAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("testaddress");
        address.setContact(contact);
        address.setStreet("lama");
        address.setCity("lama");
        address.setProvince("lama");
        address.setCountry("lama");
        address.setPostalCode("lama");
        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setStreet("baru");
        request.setCity("baru");
        request.setProvince("baru");
        request.setCountry("baru");
        request.setPostalCode("0101010");

        mockMvc.perform(
                put("/api/contacts/test/addresses/testaddress")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {
            });

            assertNull(response.getErrors());

            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));

        });
    }

   @Test
   void removeAddressNotFound() throws Exception {

       mockMvc.perform(
               delete("/api/contacts/test/addresses/test")
                       .accept(MediaType.APPLICATION_JSON)
                       .contentType(MediaType.APPLICATION_JSON)
                       .header("X-API-TOKEN", "test")
       ).andExpectAll(
               status().isNotFound()
       ).andDo(result -> {
           WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
           });

           assertNotNull(response.getErrors());
       });
   }

   @Test
    void removeAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setId("testaddress");
        address.setContact(contact);
        address.setStreet("lama");
        address.setCity("lama");
        address.setProvince("lama");
        address.setCountry("lama");
        address.setPostalCode("lama");
        addressRepository.save(address);

        mockMvc.perform(
                delete("/api/contacts/test/addresses/testaddress")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNull(response.getErrors());
            assertEquals("OK", response.getData());

            assertFalse(addressRepository.existsById("test"));

        });
    }


    @Test
    void listAddressNotFound() throws Exception {

        mockMvc.perform(
                get("/api/contacts/tidakada/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void listAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        for (int i = 0; i < 5; i++) {
            address.setId("testaddress" + i);
            address.setContact(contact);
            address.setStreet("lama" + i);
            address.setCity("lama" + i);
            address.setProvince("lama" + i);
            address.setCountry("lama" + i);
            address.setPostalCode("lama + i");
            addressRepository.save(address);
        }

        mockMvc.perform(
                get("/api/contacts/test/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<List<AddressResponse>>>() {
            });

            assertNull(response.getErrors());
            assertEquals(5, response.getData().size());

        });
    }

}