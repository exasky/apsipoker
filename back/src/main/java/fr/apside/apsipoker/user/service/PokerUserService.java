package fr.apside.apsipoker.user.service;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.common.exception.ValidationCheckException;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.repository.PokerUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PokerUserService {
    private final PokerUserRepository pokerUserRepository;
    private final LoginService loginService;

    public PokerUserService(PokerUserRepository pokerUserRepository,
                            LoginService loginService) {
        this.pokerUserRepository = pokerUserRepository;
        this.loginService = loginService;
    }

    public List<PokerUser> getAll() {
        return pokerUserRepository.findAll();
    }

    public PokerUser getById(Long id) {
        return pokerUserRepository.getOne(id);
    }

    public List<PokerUser> getByIds(List<Long> ids) {
        return pokerUserRepository.findAllById(ids);
    }

    @Transactional
    public PokerUser create(PokerUser toCreate) {
        PokerUser attachedUser = loginService.register(toCreate.getUsername(), toCreate.getPassword());

        attachedUser.setEmail(toCreate.getEmail());
        attachedUser.setFirstName(toCreate.getFirstName());
        attachedUser.setLastName(toCreate.getLastName());
        attachedUser.setRole(toCreate.getRole());
        attachedUser.setAgency(toCreate.getAgency());

        return attachedUser;
    }

    @Transactional
    public PokerUser update(Long id, PokerUser toUpdate) {
        PokerUser attachedUser = pokerUserRepository.getOne(id);

        //noinspection ConstantConditions
        if (Objects.isNull(attachedUser)) {
            ValidationCheckException.throwError(HttpStatus.NOT_FOUND, Constant.Errors.USER.NOT_FOUND);
        }

        attachedUser.setUsername(toUpdate.getUsername());
        attachedUser.setEmail(toUpdate.getEmail());
        attachedUser.setFirstName(toUpdate.getFirstName());
        attachedUser.setLastName(toUpdate.getLastName());
        attachedUser.setRole(toUpdate.getRole());
        attachedUser.setAgency(toUpdate.getAgency());

        return attachedUser;
    }

    @Transactional
    public PokerUser updatePassword(Long id, PokerUser toUpdate) {
        PokerUser attachedUser = pokerUserRepository.getOne(id);

        //noinspection ConstantConditions
        if (Objects.isNull(attachedUser)) {
            ValidationCheckException.throwError(HttpStatus.NOT_FOUND, Constant.Errors.USER.NOT_FOUND);
        }

        attachedUser.setPassword(loginService.encorePassword(toUpdate.getPassword()));

        return attachedUser;
    }

    @Transactional
    public void delete(Long id) {
        PokerUser attachedUser = pokerUserRepository.getOne(id);

        //noinspection ConstantConditions
        if (Objects.isNull(attachedUser)) {
            ValidationCheckException.throwError(HttpStatus.NOT_FOUND, Constant.Errors.USER.NOT_FOUND);
        }

        pokerUserRepository.delete(attachedUser);
    }
}
