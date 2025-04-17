package org.generation.mexico.client.service;

import org.generation.mexico.client.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.generation.mexico.client.dao.ClientRepository;
import org.generation.mexico.client.business.ClientBusiness;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    @Autowired
    private ClientBusiness clientBusiness;

    private Client testClient;@BeforeEach
    void setUp() {
        testClient = new Client();
        testClient.setId(1L);
        testClient.setName("Test User");
        testClient.setEmail("test@example.com");
        testClient.setPhoneNumber("1234567890");
    }

    @Test
    void getAllClients_ShouldReturnListOfClients() {
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(testClient));

        assertFalse(clientBusiness.findAll().isEmpty());
        assertEquals(1, clientBusiness.findAll().size());
    }

    @Test
    void getClientById_WithValidId_ShouldReturnClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(testClient));

        Client found = clientBusiness.findById(1L);
        assertNotNull(found);
        assertEquals(testClient.getId(), found.getId());
    }

    @Test
    void getClientById_WithInvalidId_ShouldThrowException() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientBusiness.findById(99L));
    }

    @Test
    void createClient_WithValidData_ShouldReturnSavedClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(testClient);

        Client created = clientBusiness.insert(testClient);
        assertNotNull(created);
        assertEquals(testClient.getName(), created.getName());
    }



}
