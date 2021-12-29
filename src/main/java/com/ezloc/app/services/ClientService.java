package com.ezloc.app.services;


import com.ezloc.app.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> add(Optional<Client> client);
    Optional<Client>  findById(long id);
    String update(Long id,Optional<Client> client);
    String delete(long id);
    List<Client> findByenterprise_id(Long id);
}
