export class TournamentUtils {
  static sortTournament = (t1, t2) => new Date(t1.date).getTime() - new Date(t2.date).getTime();
}
