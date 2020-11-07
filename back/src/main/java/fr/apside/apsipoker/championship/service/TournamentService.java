package fr.apside.apsipoker.championship.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.model.Tournament;
import fr.apside.apsipoker.championship.repository.TournamentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TournamentService {
    private final ChampionshipService championshipService;
    private final TournamentRepository repository;

    public TournamentService(ChampionshipService championshipService, TournamentRepository repository) {
        this.championshipService = championshipService;
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Tournament create(Long championshipId, Tournament toAdd) {
        Championship byId = championshipService.getById(championshipId);

        Tournament newTournament = repository.save(new Tournament());
        newTournament.setDate(toAdd.getDate());
        newTournament.setChampionship(byId);

        return newTournament;
    }
}
