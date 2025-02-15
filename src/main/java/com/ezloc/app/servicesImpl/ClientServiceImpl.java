package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Client;
import com.ezloc.app.repositories.ClientRepository;
import com.ezloc.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Client> add(Optional<Client> client) {
        return Optional.of(clientRepository.save(client.get()));
    }

    @Override
    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional
    public Client update(Long id, Optional<Client> client) {
        Client resource = clientRepository.getById(id);
        resource.setAdress(Optional.ofNullable(client).map(c->c.get().getAdress()).orElse(resource.getAdress()));
        resource.setLicense(Optional.ofNullable(client).map(c->c.get().getLicense()).orElse(resource.getLicense()));
        resource.setCin(Optional.ofNullable(client).map(c->c.get().getCin()).orElse(resource.getCin()));
        resource.setCode(Optional.ofNullable(client).map(c->c.get().getCode()).orElse(resource.getCode()));
        resource.setName(Optional.ofNullable(client).map(c->c.get().getName()).orElse(resource.getName()));
        resource.setSurname(Optional.ofNullable(client).map(c->c.get().getSurname()).orElse(resource.getSurname()));
        resource.setMail(Optional.ofNullable(client).map(c->c.get().getMail()).orElse(resource.getMail()));
        resource.setPhone(Optional.ofNullable(client).map(c->c.get().getPhone()).orElse(resource.getPhone()));
        resource.setPassport(Optional.ofNullable(client).map(c->c.get().getPassport()).orElse(resource.getPassport()));
        resource.setRib(Optional.ofNullable(client).map(c->c.get().getRib()).orElse(resource.getRib()));
        resource.setBlacklisted(Optional.ofNullable(client).map(c->c.get().isBlacklisted()).orElse(resource.isBlacklisted()));
        return clientRepository.save(resource);

    }

    @Override
    @Transactional
    public String delete(long id) {
        clientRepository.deleteById(id);
        return "Client N° "+ id +" Deleted Successfully";
    }

    @Override
    public List<Client> findByenterprise_id(Long id) {
        return clientRepository.findByenterprise_id(id);
    }
}
