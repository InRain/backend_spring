package com.backend.spring.backend.controller;

import com.backend.spring.backend.exception.ResourceNotFoundException;
import com.backend.spring.backend.model.Client;
import com.backend.spring.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository employeeRepository) {
        this.clientRepository = employeeRepository;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllEmployees(){
        return clientRepository.findAll();
    }

    @RequestMapping(value = "/clients/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client save (@RequestBody Client e){
        return clientRepository.save(e);
    }

    @RequestMapping(value = "/clients/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getEmployeeById(@PathVariable long id){
        Client client =  clientRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not exists"));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/id/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteById(@PathVariable long id){
        Client client =  clientRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not exists"));
        clientRepository.deleteById(client.getId());
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/id/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateEmployee(@PathVariable Long id, @RequestBody Client client){
        Client e = clientRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee not exists"));
        e.setFullName(client.getFullName());
        e.setAddress(client.getAddress());
        e.setCommunicationType(client.getCommunicationType());
        e.setDebtAmount(client.getDebtAmount());
        e.setIndividualTaxNumber(client.getIndividualTaxNumber());
        e.setOrganizationalLegalForm(client.getOrganizationalLegalForm());

        Client updated = clientRepository.saveAndFlush(e);
        return ResponseEntity.ok(updated);
    }
}
