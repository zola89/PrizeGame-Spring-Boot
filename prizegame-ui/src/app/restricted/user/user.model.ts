import {Code} from '../code/code.model';

export class User {

  id?: number;
  name?: string;
  password?: string;
  phone?: string;
  email?: string;
  address?: string;
  userRole?: string;
  confirmPassword?: string;
  codes?: Code[];

  public isAdmin(): boolean {
    let allowedRoles = ['ADMIN'];
    return this.checkRole(allowedRoles);
  }

  public isAnalyst(): boolean {
    let allowedRoles = ['ADMIN', 'ANALYST'];
    return this.checkRole(allowedRoles);
  }

  public checkRole(allowedRoles: string[]): boolean {

    if (allowedRoles.includes(this.userRole)) {
      return true;
    }

    return false;
  }

}
