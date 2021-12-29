package com.ezloc.app.servicesImpl;


import com.ezloc.app.entities.Enterprise;
import com.ezloc.app.repositories.EnterpriseRepository;
import com.ezloc.app.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {


    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Optional<Enterprise> add(Optional<Enterprise> enterprise) {
        return Optional.of(enterpriseRepository.save(enterprise.get()));
    }

    @Override
    public Optional<Enterprise> findById(long id) {
        return enterpriseRepository.findById(id);
    }

    @Override
    public String update(Long id, Optional<Enterprise> enterprise) {
        Enterprise resource = enterpriseRepository.getById(id);
        resource.setLogo(Optional.ofNullable(enterprise).map(c->c.get().getLogo()).orElse(resource.getLogo()));
        resource.setName(Optional.ofNullable(enterprise).map(c->c.get().getName()).orElse(resource.getName()));
        resource.setDescription(Optional.ofNullable(enterprise).map(c->c.get().getDescription()).orElse(resource.getDescription()));
        resource.setPhone(Optional.ofNullable(enterprise).map(c->c.get().getPhone()).orElse(resource.getPhone()));
        resource.setBank(Optional.ofNullable(enterprise).map(c->c.get().getBank()).orElse(resource.getBank()));
        resource.setFax(Optional.ofNullable(enterprise).map(c->c.get().getFax()).orElse(resource.getFax()));
        resource.setLandLineNumber(Optional.ofNullable(enterprise).map(c->c.get().getLandLineNumber()).orElse(resource.getLandLineNumber()));
        resource.setWebsite(Optional.ofNullable(enterprise).map(c->c.get().getWebsite()).orElse(resource.getWebsite()));
        resource.setMail(Optional.ofNullable(enterprise).map(c->c.get().getMail()).orElse(resource.getMail()));
        resource.setRegistryNumber(Optional.ofNullable(enterprise).map(c->c.get().getRegistryNumber()).orElse(resource.getRegistryNumber()));
        resource.setFiscalId(Optional.ofNullable(enterprise).map(c->c.get().getFiscalId()).orElse(resource.getFiscalId()));
        resource.setCity(Optional.ofNullable(enterprise).map(c->c.get().getCity()).orElse(resource.getCity()));
        resource.setImmatriculation(Optional.ofNullable(enterprise).map(c->c.get().getImmatriculation()).orElse(resource.getImmatriculation()));


        enterpriseRepository.save(resource);
        return "Enterprise N° "+id+" updated successfully";
    }

    @Override
    public String delete(long id) {
        enterpriseRepository.deleteById(id);
        return "Enterprise N° "+id+" Deleted Successfully";
    }
}
