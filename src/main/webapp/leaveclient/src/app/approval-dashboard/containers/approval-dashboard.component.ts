import {Component, OnInit} from '@angular/core';
import {ApplicationTransactionDTO} from '../models/ApplicationTransactionDTO';
import {TransactionService} from '../services/transaction.service';


@Component({
  selector: 'approval-dashboard',
  styleUrls: ['approval-dashboard.component.scss'],
  template: `
    <div class="col-md-12">
      <app-leavetrans
        [transItem]="transactions"
        (approve)="handleApprove($event)"
        (confirm)="handleConfirm($event)"
        (cancel)="handleCancel($event)"
        (reject)="handleReject($event)">
        <div class="loader-bubble loader-bubble-primary d-block">انتظر</div>
      </app-leavetrans>
    </div>
  `
})

export class ApprovalDashboardComponent implements OnInit {

  transactions: ApplicationTransactionDTO[];

  constructor(private transService: TransactionService) {
  }

  ngOnInit(): void {
    this.transService.findAll().subscribe((data: ApplicationTransactionDTO[]) => {
      this.transactions = data;
      console.log(this.transactions);

    });
  }

  handleApprove(event: ApplicationTransactionDTO) {
    console.log(event);
    this.transService.approve(event).subscribe(result => {
      this.transService.findAll()
        .subscribe(((data: ApplicationTransactionDTO[]) => {
          this.transactions = data;
        }));
    });
  }

  handleConfirm(event: ApplicationTransactionDTO) {
    this.transService.confirm(event).subscribe(result => {
      this.transService.findAll()
        .subscribe(((data: ApplicationTransactionDTO[]) => {
          this.transactions = data;
        }));
    });
  }

  handleCancel(event: ApplicationTransactionDTO) {
    this.transService.cancel(event).subscribe(result => {
      this.transService.findAll()
        .subscribe(((data: ApplicationTransactionDTO[]) => {
          this.transactions = data;
        }));
    });
  }

  handleReject(event: ApplicationTransactionDTO) {
    this.transService.reject(event).subscribe(result => {
      this.transService.findAll()
        .subscribe(((data: ApplicationTransactionDTO[]) => {
          this.transactions = data;
        }));
    });
  }
}
