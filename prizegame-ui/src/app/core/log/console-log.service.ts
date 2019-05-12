import {ConsoleLogLevel} from './console-log-type';
import {isNullOrUndefined} from 'util';

export const LOG_LEVEL = 'logLevel';

export class ConsoleLog {

  static trace(message: object | string, object?: object) {
    if (ConsoleLog.getLevel() === ConsoleLogLevel.TRACE) {
      if (!isNullOrUndefined(object)) {
        console.log(message, object);
      } else {
        console.log(message);
      }
    }
  }

  static debug(message: object | string, object?: object) {
    const level = ConsoleLog.getLevel();
    if (level === ConsoleLogLevel.TRACE || level === ConsoleLogLevel.DEBUG) {
      if (!isNullOrUndefined(object)) {
        console.log(message, object);
      } else {
        console.log(message);
      }
    }
  }

  static error(message: object | string) {
    console.log(message);
  }

  static getLevel(): ConsoleLogLevel {

    const level = localStorage.getItem(LOG_LEVEL);

    if (level !== undefined && level != null) {
      switch (level.toUpperCase()) {
        case 'ERROR':
          return ConsoleLogLevel.ERROR;
        case 'TRACE':
          return ConsoleLogLevel.TRACE;
        case 'DEBUG':
          return ConsoleLogLevel.DEBUG;
      }
    }

    return ConsoleLogLevel.ERROR;
  }

}
