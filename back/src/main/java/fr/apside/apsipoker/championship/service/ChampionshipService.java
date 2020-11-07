package fr.apside.apsipoker.championship.service;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.repository.ChampionshipRepository;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {
    private final ChampionshipRepository repository;
    private final UserService userService;

    public ChampionshipService(ChampionshipRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
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
        Championship newChampionship = new Championship(toCreate.getName(), toCreate.getStartDate(), toCreate.getEndDate());

        newChampionship.setParticipants(
                userService.getByIds(toCreate.getParticipants()
                        .stream()
                        .map(PokerUser::getId)
                        .collect(Collectors.toList())));

        return repository.save(newChampionship);
    }

}
