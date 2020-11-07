package fr.apside.apsipoker.championship.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.repository.ChampionshipRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChampionshipService {
    private final ChampionshipRepository repository;

    public ChampionshipService(ChampionshipRepository repository) {
        this.repository = repository;
    }

    public Championship getById(Long id) {
        return repository.getOne(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Championship> getAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Championship create(Championship toCreate) {
        Championship newChampionship = repository.save(new Championship(toCreate.getName(), toCreate.getStartDate(), toCreate.getEndDate()));

        newChampionship.setParticipants(toCreate.getParticipants());

        return newChampionship;
    }

}
