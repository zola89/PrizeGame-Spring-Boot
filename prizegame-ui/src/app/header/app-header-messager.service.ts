import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable()
export class AppHeaderMessengerService {

  private messageSource: BehaviorSubject<boolean> = new BehaviorSubject(false);

  public message = this.messageSource.asObservable();

  public refresh(value: boolean) {
    this.messageSource.next(value);
  }

}
