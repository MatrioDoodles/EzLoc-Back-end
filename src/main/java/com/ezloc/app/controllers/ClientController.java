package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Client;
import com.ezloc.app.services.ClientService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private ClientService ClientService;

    @Autowired
    public ClientController(ClientService ClientService) {
        this.ClientService = ClientService;
    }

    @GetMapping
    public CollectionModel<Client> findAll() {
        List<Client> allClients = ClientService.findAll();
        for (Client Client : allClients) {
            Long ClientId = Client.getId();
            Link selfLink = linkTo(ClientController.class).slash(ClientId).withSelfRel();
            Client.add(selfLink);
            if(Client.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(Client.getEnterprise().getId()).withRel("Enterprise");
                Client.add(enterpriseLink);}

        }


        Link link = linkTo(ClientController.class).withSelfRel();
        CollectionModel<Client> result = CollectionModel.of(allClients, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Client> Client = ClientService.findById(id);

        if(Client.isPresent()) {
            Client resource = Client.get();
            Link selfLink = linkTo(ClientController.class).slash(id).withSelfRel();
            EntityModel<Client> result = EntityModel.of(resource,selfLink);
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel("Enterprise");
                result.add(enterpriseLink);}
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Client> create(@RequestBody Optional<Client> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return ClientService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Client> resource) {

        Optional<Client> Client = ClientService.findById(id);
        if(Client.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ClientService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Client> Client = ClientService.findById(id);
        if(Client.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ClientService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CLIENT_NOT_FOUND);
    }
}
