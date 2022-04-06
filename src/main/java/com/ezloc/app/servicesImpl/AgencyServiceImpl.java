package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Agency;
import com.ezloc.app.repositories.AgencyRepository;
import com.ezloc.app.services.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    @Override
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Agency> add(Optional<Agency> agency) {
        return Optional.of(agencyRepository.save(agency.get()));
    }

    @Override
    public Optional<Agency> findById(long id) {

        return agencyRepository.findById(id);
    }

    @Override
    @Transactional
    public Agency update(Long id,Optional<Agency> agency) {


        Agency resource = agencyRepository.getById(id);
        resource.setAdress(Optional.ofNullable(agency).map(c->c.get().getAdress()).orElse(resource.getAdress()));
        resource.setDescription(Optional.ofNullable(agency).map(c->c.get().getDescription()).orElse(resource.getDescription()));
        resource.setFax(Optional.ofNullable(agency).map(c->c.get().getFax()).orElse(resource.getFax()));
        resource.setLandLineNumber(Optional.ofNullable(agency).map(c->c.get().getLandLineNumber()).orElse(resource.getLandLineNumber()));
        resource.setName(Optional.ofNullable(agency).map(c->c.get().getName()).orElse(resource.getName()));
        resource.setEnterprise(Optional.ofNullable(agency).map(c->c.get().getEnterprise()).orElse(resource.getEnterprise()));
        resource.setPrimaire(Optional.ofNullable(agency).map(c->c.get().isPrimaire()).orElse(resource.isPrimaire()));
        resource.setPhone(Optional.ofNullable(agency).map(c->c.get().getPhone()).orElse(resource.getPhone()));

        return agencyRepository.save(resource);

    }

    @Override
    @Transactional
    public String delete(long id) {
        agencyRepository.deleteById(id);
        return "agency N°" +id+ " deleted successfully";
    }

    @Override
    public List<Agency> findByenterprise_id(Long id) {
        return agencyRepository.findByenterprise_id(id);
    }
}

