package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Client;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.services.ClientService;
import com.ezloc.app.services.ReservationService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;
    private final ReservationService reservationService;

    @Autowired
    public ClientController(ClientService clientService, ReservationService reservationService) {
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Client> findAll() {
        List<Client> allClients = clientService.findAll();

        return allClients;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Client> client = clientService.findById(id);

        if(client.isPresent()) {
            Client resource = client.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity<Object> findByReservation(@PathVariable("id") Long id) {

        Optional<Client> client = clientService.findById(id);

        if(client.isPresent()) {
            List<Reservation> reservationList = reservationService.findBycar_id(id);
            if(!reservationList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(reservationList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_RESERVATIONS_FOR_THIS_CLIENT");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Client> create(@RequestBody Optional<Client> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return clientService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<Client> resource) {

        Optional<Client> client = clientService.findById(id);
        if(client.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Client> Client = clientService.findById(id);
        if(Client.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(clientService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
    }
}
