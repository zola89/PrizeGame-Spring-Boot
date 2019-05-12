import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material';

@Injectable()
export class SnackBarService {

  constructor(private snackBar: MatSnackBar) {

  }

  add(message: string, type: string) {

    if (!message) {
      throw new Error('uvs-snack-bar-message: No snack bar message specified!');
    }

    type = type || 'default';

    this.snackBar.open(message, null, {panelClass: 'snack-' + type + '-message'});

  }

}
