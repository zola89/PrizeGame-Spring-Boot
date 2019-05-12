import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {SnackBarService} from '../shared-components/snackbar/snackbar.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Injectable()
export class AlertService {

  static INFO_MESSAGE_HEADER = 'Info-Message';
  static ERROR_MESSAGE_HEADER = 'Error-Message';
  static SUCCESS_MESSAGE_HEADER = 'Success-Message';
  static WARNING_MESSAGE_HEADER = 'Warning-Message';

  constructor(private snackBarService: SnackBarService, private spinnerService: Ng4LoadingSpinnerService) {

  }

  showSpinner() {
    this.spinnerService.show();
  }

  hideSpinner() {
    this.spinnerService.hide();
  }

  processResponse(response: any) {

    if (!response) {
      return;
    }

    let headers: HttpHeaders = response.headers;

    if (!headers) {
      return;
    }

    if (headers.get(AlertService.INFO_MESSAGE_HEADER)) {
      const message = headers.get(AlertService.INFO_MESSAGE_HEADER);
      this.info(message);
    }

    if (headers.get(AlertService.ERROR_MESSAGE_HEADER)) {
      const message = headers.get(AlertService.ERROR_MESSAGE_HEADER);
      this.error(message);
    }

    if (headers.get(AlertService.SUCCESS_MESSAGE_HEADER)) {
      const message = headers.get(AlertService.SUCCESS_MESSAGE_HEADER);
      this.success(message);
    }

    if (headers.get(AlertService.WARNING_MESSAGE_HEADER)) {
      const message = headers.get(AlertService.WARNING_MESSAGE_HEADER);
      this.warning(message);
    }
  }

  processXhrResponse(xhr: XMLHttpRequest) {

    if (xhr.getResponseHeader(AlertService.INFO_MESSAGE_HEADER)) {
      const message = xhr.getResponseHeader(AlertService.INFO_MESSAGE_HEADER);
      this.info(message);
    }

    if (xhr.getResponseHeader(AlertService.ERROR_MESSAGE_HEADER)) {
      const message = xhr.getResponseHeader(AlertService.ERROR_MESSAGE_HEADER);
      this.error(message);
    }

    if (xhr.getResponseHeader(AlertService.SUCCESS_MESSAGE_HEADER)) {
      const message = xhr.getResponseHeader(AlertService.SUCCESS_MESSAGE_HEADER);
      this.success(message);
    }

    if (xhr.getResponseHeader(AlertService.WARNING_MESSAGE_HEADER)) {
      const message = xhr.getResponseHeader(AlertService.WARNING_MESSAGE_HEADER);
      this.warning(message);
    }

  }

  info(message: string) {
    this.snackBarService.add(message, 'info');
  }

  error(message: string) {
    this.snackBarService.add(message, 'error');
  }

  success(message: string) {
    this.snackBarService.add(message, 'success');
  }

  warning(message: string) {
    this.snackBarService.add(message, 'warning');
  }

}
