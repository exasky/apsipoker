package fr.apside.apsipoker.championship.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.model.Tournament;
import fr.apside.apsipoker.championship.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    private final TournamentRepository repository;
    private final TournamentPlayerService tournamentPlayerService;

    public TournamentService(TournamentRepository repository, TournamentPlayerService tournamentPlayerService) {
        this.repository = repository;
        this.tournamentPlayerService = tournamentPlayerService;
    }

    public List<Tournament> createOrUpdate(Championship attachedChampionship, List<Tournament> toCreate) {
        return toCreate.stream()
                .map(tournament ->
                        Objects.isNull(tournament.getId())
                                ? create(attachedChampionship, tournament)
                                : update(tournament)
                )
                .collect(Collectors.toList());
    }

    private Tournament create(Championship attachedChampionship, Tournament toCreate) {
        Tournament attachedToCreate = repository.save(new Tournament());

        attachedToCreate.setDate(toCreate.getDate());
        attachedToCreate.setChampionship(attachedChampionship);
        attachedToCreate.setParticipants(tournamentPlayerService.createOrUpdate(attachedToCreate, toCreate.getParticipants()));

        return attachedToCreate;
    }

    private Tournament update(Tournament toUpdate) {
        Tournament attachedToUpdate = repository.getOne(toUpdate.getId());

        attachedToUpdate.setDate(toUpdate.getDate());
        attachedToUpdate.setParticipants(tournamentPlayerService.createOrUpdate(attachedToUpdate, toUpdate.getParticipants()));

        return repository.save(attachedToUpdate);
    }
}
