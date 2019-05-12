export class User {

  id: number;
  username: string;
  email: string;
  role: string;
  password: string;
  confirmPassword: string;

  public isAdmin(): boolean {
    let allowedRoles = ['ADMIN'];
    return this.checkRole(allowedRoles);
  }

  public isAnalyst(): boolean {
    let allowedRoles = ['ADMIN', 'ANALYST'];
    return this.checkRole(allowedRoles);
  }

  public checkRole(allowedRoles: string[]): boolean {

    if (allowedRoles.includes(this.role)) {
      return true;
    }

    return false;
  }





}
