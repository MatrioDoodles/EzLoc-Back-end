package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Formula;
import com.ezloc.app.services.FormulaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaServiceImpl implements FormulaService {
    @Override
    public List<Formula> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Optional<Formula> add(Optional<Formula> formula) {
        return Optional.empty();
    }

    @Override
    public Optional<Formula> findById(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public String update(Long id, Optional<Formula> formula) {
        return null;
    }

    @Override
    @Transactional
    public String delete(long id) {
        return null;
    }
}
