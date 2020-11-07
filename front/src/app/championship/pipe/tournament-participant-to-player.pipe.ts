import {Pipe, PipeTransform} from '@angular/core';
import {Player, TournamentPlayer} from '../model/championship';

@Pipe({name: 'toPlayer'})
export class TournamentParticipantToPlayerPipe implements PipeTransform {
  transform(tournamentPlayers: TournamentPlayer[]): Player[] {
    return tournamentPlayers.map(tp => tp.player);
  }

}

