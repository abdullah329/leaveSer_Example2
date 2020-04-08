import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ApplicationTransactionDTO} from '../../models/ApplicationTransactionDTO';

@Component({
  selector: 'app-leavetrans',
  templateUrl: './leavetrans.component.html',
  styleUrls: ['./leavetrans.component.css']
})
export class LeavetransComponent {

  @Input()
  transItem: ApplicationTransactionDTO[];
  // application: Application[];

  @Output()
  approve: EventEmitter<ApplicationTransactionDTO> = new EventEmitter();

  @Output()
  confirm: EventEmitter<ApplicationTransactionDTO> = new EventEmitter();

  @Output()
  cancel: EventEmitter<ApplicationTransactionDTO> = new EventEmitter();

  @Output()
  reject: EventEmitter<ApplicationTransactionDTO> = new EventEmitter();

  constructor() {
  }


  onApprove(event: ApplicationTransactionDTO) {
    this.approve.emit(event);
  }

  onConfirm(event: ApplicationTransactionDTO) {
    this.confirm.emit(event);
  }

  onCancel(event: ApplicationTransactionDTO) {
    this.cancel.emit(event);
  }

  onReject(event: ApplicationTransactionDTO) {
    this.reject.emit(event);
  }


}
