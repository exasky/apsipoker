export class Championship {
  id?: number;
  name: string;
  startDate: string;
  endDate: string;
  participants: Player[];
  tournaments: Tournament[];

  constructor() {
    this.participants = [];
    this.tournaments = [];
  }
}

export class Tournament {
  id?: number;
  date: string;
  participants: TournamentPlayer[];

  constructor() {
    this.participants = [];
  }
}

export class TournamentPlayer {
  id?: number;
  player: Player;
  position: number;
  points: number;
}

export class Player {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  agency: string;
  role: string;
}
