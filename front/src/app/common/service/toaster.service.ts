import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor(private toaster: ToastrService/*,
              private translate: TranslateService*/) {
  }

  public success(message: string): void {
    this.toaster.success(this.getMessage(message));
  }

  public warning(message: string): void {
    this.toaster.warning(this.getMessage(message));
  }

  public error(message: string): void {
    this.toaster.error(this.getMessage(message));
  }

  private getMessage(message: string): string {
    return message;
    // let toDisplay;
    // try {
    //   toDisplay = this.translate.instant(message);
    // } catch (e) {
    //   toDisplay = message;
    // }
    //
    // return toDisplay;
  }
}
