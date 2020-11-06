export class User {
  id: number;
  username: string;
  role: string;

  // Current jwt token
  token: string;
}

export const ROLE_ADMIN = 'ROLE_ADMIN';
