package fr.apside.apsipoker.championship.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.repository.ChampionshipRepository;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.service.PokerUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static fr.apside.apsipoker.common.Utils.getCurrentUser;

@Service
public class ChampionshipService {
    private final ChampionshipRepository repository;
    private final PokerUserService pokerUserService;
    private final TournamentService tournamentService;

    public ChampionshipService(ChampionshipRepository repository,
                               PokerUserService pokerUserService,
                               TournamentService tournamentService) {
        this.repository = repository;
        this.pokerUserService = pokerUserService;
        this.tournamentService = tournamentService;
    }

    public Championship getById(Long id) {
        return repository.getOne(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Championship> getAll() {
        return repository.findAll();
    }

    public List<Championship> getAllForCurrentUser() {
        return repository.getAllForUser(getCurrentUser().getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Championship create(Championship toCreate) {
        Championship newChampionship = new Championship(toCreate.getName(), toCreate.getStartDate(), toCreate.getEndDate());

        newChampionship.setParticipants(
                pokerUserService.getByIds(toCreate.getParticipants()
                        .stream()
                        .map(PokerUser::getId)
                        .collect(Collectors.toList())));

        return repository.save(newChampionship);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Championship update(Long id, Championship toUpdate) {
        Championship championship = repository.getOne(id);

        championship.setName(toUpdate.getName());
        championship.setStartDate(toUpdate.getStartDate());
        championship.setEndDate(toUpdate.getEndDate());

        championship.setParticipants(
                pokerUserService.getByIds(toUpdate.getParticipants()
                        .stream()
                        .map(PokerUser::getId)
                        .collect(Collectors.toList())));

        championship.setTournaments(tournamentService.createOrUpdate(championship, toUpdate.getTournaments()));

        return repository.save(championship);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        repository.delete(repository.getOne(id));
    }
}
