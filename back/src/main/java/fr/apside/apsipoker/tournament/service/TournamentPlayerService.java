package fr.apside.apsipoker.tournament.service;

import fr.apside.apsipoker.tournament.model.Tournament;
import fr.apside.apsipoker.tournament.model.TournamentPlayer;
import fr.apside.apsipoker.tournament.repository.TournamentPlayerRepository;
import fr.apside.apsipoker.user.service.PokerUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TournamentPlayerService {

    private final TournamentPlayerRepository repository;
    private final PokerUserService pokerUserService;

    public TournamentPlayerService(TournamentPlayerRepository repository, PokerUserService pokerUserService) {
        this.repository = repository;
        this.pokerUserService = pokerUserService;
    }

    public List<TournamentPlayer> createOrUpdate(Tournament attachedTournament, List<TournamentPlayer> tournamentPlayers) {
        return tournamentPlayers.stream()
                .map(tournamentPlayer ->
                        Objects.isNull(tournamentPlayer.getId())
                                ? create(attachedTournament, tournamentPlayer)
                                : update(tournamentPlayer)
                )
                .collect(Collectors.toList());
    }

    private TournamentPlayer create(Tournament attachedTournament, TournamentPlayer toCreate) {
        TournamentPlayer newTP = new TournamentPlayer();

        newTP.setTournament(attachedTournament);
        newTP.setPlayer(pokerUserService.getById(toCreate.getPlayer().getId()));

        return repository.save(newTP);
    }

    private TournamentPlayer update(TournamentPlayer toUpdate) {
        return repository.getOne(toUpdate.getId());
    }


    public List<TournamentPlayer> updatePosition(List<TournamentPlayer> tournamentPlayers) {
        return tournamentPlayers.stream()
                .map(this::updatePosition)
                .collect(Collectors.toList());
    }

    private TournamentPlayer updatePosition(TournamentPlayer toUpdate) {
        TournamentPlayer bo = repository.getOne(toUpdate.getId());

        bo.setPosition(toUpdate.getPosition());

        return repository.save(bo);
    }
}
