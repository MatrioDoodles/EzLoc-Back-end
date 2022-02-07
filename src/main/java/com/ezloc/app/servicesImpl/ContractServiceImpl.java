package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Contract;
import com.ezloc.app.repositories.ContractRepository;
import com.ezloc.app.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Optional<Contract> add(Optional<Contract> contract) {
        return Optional.of(contractRepository.save(contract.get()));
    }

    @Override
    public Optional<Contract> findById(long id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract update(Long id, Optional<Contract> contract) {
        Contract resource = contractRepository.getById(id);
        resource.setCode(Optional.ofNullable(contract).map(c->c.get().getCode()).orElse(resource.getCode()));
        resource.setContractFile(Optional.ofNullable(contract).map(c->c.get().getContractFile()).orElse(resource.getContractFile()));
        resource.setContractFileName(Optional.ofNullable(contract).map(c->c.get().getContractFileName()).orElse(resource.getContractFileName()));
        resource.setDateOfCreation(Optional.ofNullable(contract).map(c->c.get().getDateOfCreation()).orElse(resource.getDateOfCreation()));

        return contractRepository.save(resource);

    }

    @Override
    public String delete(long id) {
        contractRepository.deleteById(id);
        return "Contact N° "+id+" Deleted Successfully";
    }
}
