package fr.apside.apsipoker.tournament.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.common.exception.ValidationCheckException;
import fr.apside.apsipoker.tournament.model.Tournament;
import fr.apside.apsipoker.tournament.repository.TournamentRepository;
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

    public Tournament getById(Long id) {
        return repository.getOne(id);
    }

    public Tournament updateAndCalculatePoints(Long id, Tournament toUpdate) {
        Tournament attachedToUpdate = repository.getOne(toUpdate.getId());

        if (attachedToUpdate.getParticipants().size() != toUpdate.getParticipants().size()) {
            ValidationCheckException.throwError(Constant.Errors.TOURNAMENT.PARTICIPANT_SIZE_MISMATCH);
        }

        attachedToUpdate.setParticipants(tournamentPlayerService.updatePosition(toUpdate.getParticipants()));

        int nbParticipants = attachedToUpdate.getParticipants().size();
        attachedToUpdate.getParticipants().forEach(participant ->
                participant.setPoints(calculatePoints(nbParticipants, participant.getPosition()))
        );

        return repository.save(attachedToUpdate);
    }

    private static float calculatePoints(int nbParticipant, int position) {
        return (float) Math.round(
                (100 * (1 - (float) position / (float) nbParticipant) * Math.cbrt((float) nbParticipant / (float) position)
                ) * 10) / 10;
    }
}
