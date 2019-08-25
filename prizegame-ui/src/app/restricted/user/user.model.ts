import {Code} from '../code/code.model';

export class User {

  id?: any;
  name?: string;
  password?: string;
  phone?: string;
  email?: string;
  address?: string;
  userRole?: string;
  confirmPassword?: string;
  codes?: Code[];

  public isAdmin(): boolean {
    const allowedRoles = ['ADMIN'];
    return this.checkRole(allowedRoles);
  }

  public checkRole(allowedRoles: string[]): boolean {

    if (allowedRoles.includes(this.userRole)) {
      return true;
    }

    return false;
  }

}
