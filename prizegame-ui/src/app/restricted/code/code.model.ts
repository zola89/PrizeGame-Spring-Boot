import {User} from '../user/user.model';

export class Code {

  id: number;
  prizeCode: string;
  prizeType: string;
  prizeTime: Date;
  user: User;
}
