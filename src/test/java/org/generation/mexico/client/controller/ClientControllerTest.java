package org.generation.mexico.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generation.mexico.client.business.ClientBusiness;
import org.generation.mexico.client.exception.*;
import org.generation.mexico.client.model.Client;
import org.generation.mexico.client.web.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientBusiness clientBusiness;

    private ObjectMapper objectMapper;
    private Client testClient;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        ClientController clientController = new ClientController(clientBusiness);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        testClient = new Client();
        testClient.setId(1L);
        testClient.setName("Perenganito");
        testClient.setEmail("pere@outlook.com");
        testClient.setPhoneNumber("2287936548");
    }

    @Test
    void getAllClients_ShouldReturnListOfClients() throws Exception {
        when(clientBusiness.findAll()).thenReturn(Arrays.asList(testClient));

        mockMvc.perform(get("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testClient.getId()))
                .andExpect(jsonPath("$[0].name").value(testClient.getName()))
                .andExpect(jsonPath("$[0].email").value(testClient.getEmail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(testClient.getPhoneNumber()));
    }

    @Test
    void getClientById_WithValidId_ShouldReturnClient() throws Exception {
        when(clientBusiness.findById(1L)).thenReturn(testClient);

        mockMvc.perform(get("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testClient.getId()))
                .andExpect(jsonPath("$.name").value(testClient.getName()))
                .andExpect(jsonPath("$.email").value(testClient.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(testClient.getPhoneNumber()));
    }

    @Test
    void createClient_WithValidData_ShouldReturnCreatedClient() throws Exception {

        when(clientBusiness.insert(any(Client.class))).thenReturn(testClient);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClient)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testClient.getId()))
                .andExpect(jsonPath("$.name").value(testClient.getName()))
                .andExpect(jsonPath("$.email").value(testClient.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(testClient.getPhoneNumber()));
    }

    @Test
    void deleteClient_WithValidId_ShouldReturnNoContent() throws Exception {
        doNothing().when(clientBusiness).deleteById(1L);

        mockMvc.perform(delete("/api/clients/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getClientById_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(clientBusiness.findById(99L))
                .thenThrow(new ClientNotFoundException("Client not found with id: 99"));

        mockMvc.perform(get("/api/clients/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}