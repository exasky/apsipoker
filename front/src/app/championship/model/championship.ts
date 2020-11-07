export class Championship {
  id?: number;
  name: string;
  startDate: Date;
  endDate: Date;
  participants: Player[];

  constructor() {
    this.participants = [];
  }
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
