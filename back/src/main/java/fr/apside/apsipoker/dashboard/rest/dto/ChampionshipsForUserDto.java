package fr.apside.apsipoker.dashboard.rest.dto;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.model.Tournament;
import fr.apside.apsipoker.championship.model.TournamentPlayer;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static fr.apside.apsipoker.common.Utils.mapList;

@Data
public class ChampionshipsForUserDto {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PokerUserDto> participants = new ArrayList<>();
    private List<TournamentForUserDto> tournaments = new ArrayList<>();

//    public static List<ChampionshipsForUserDto> toDto(List<Championship> bos) {
//        return Objects.isNull(bos)
//                ? new ArrayList<>()
//                : bos.stream().map(ChampionshipsForUserDto::toDto).collect(Collectors.toList());
//    }

    public static ChampionshipsForUserDto toDto(Championship bo) {
        ChampionshipsForUserDto dto = new ChampionshipsForUserDto();

        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setStartDate(bo.getStartDate());
        dto.setEndDate(bo.getEndDate());
        dto.setParticipants(mapList(bo.getParticipants(), PokerUserDto::toDto));
        dto.setTournaments(mapList(bo.getTournaments(), TournamentForUserDto::toDto));

        return dto;
    }

    @Data
    static
    class TournamentForUserDto {
        private Long id;
        private Date date;
        private List<TournamentPlayerForUserDto> participants;

        public static TournamentForUserDto toDto(Tournament bo) {
            TournamentForUserDto dto = new TournamentForUserDto();

            dto.setId(bo.getId());
            dto.setDate(bo.getDate());
            dto.setParticipants(mapList(bo.getParticipants(), TournamentPlayerForUserDto::toDto));

            return dto;
        }
    }

    @Data
    static
    class TournamentPlayerForUserDto {
        private Long id;
        private PokerUserDto player;
        private Byte position;
        private Float points;

        public static TournamentPlayerForUserDto toDto(TournamentPlayer bo) {
            TournamentPlayerForUserDto dto = new TournamentPlayerForUserDto();

            dto.setId(bo.getId());
            dto.setPlayer(PokerUserDto.toDto(bo.getPlayer()));
            dto.setPosition(bo.getPosition());
            dto.setPoints(bo.getPoints());

            return dto;
        }
    }

}
