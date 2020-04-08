import {Component, OnInit} from '@angular/core';
import {ApplicationDTO} from '../../models/ApplicationDTO';
import {LeaveService} from '../../leave.service';
import {
  NgbCalendar,
  NgbCalendarIslamicUmalqura,
  NgbDate,
  NgbDateParserFormatter,
  NgbDatepickerI18n,
  NgbDateStruct
} from '@ng-bootstrap/ng-bootstrap';
import {Balance} from '../../models/Balance';
import {AlertService} from '../../../_alert';
import {IslamicI18n} from 'src/app/islamic-calendar/islamic-calendar.component';

@Component({
  selector: 'leave-dashboard',
  styleUrls: ['leave-dashboard.component.scss'],
  template: `
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-6">
          <app-leaveapp
            [editable]="application"
            [disabledDates]="disabledDates"
            (edit)="handleEdit($event)">
            <div class="loader-bubble loader-bubble-primary d-block">انتظر</div>
          </app-leaveapp>
        </div>
        <div class="col-md-6">
          <app-leavebalance [items]="balances">
            <div class="loader-bubble loader-bubble-primary d-block">انتظر</div>
          </app-leavebalance>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <app-leave-emp-apps [empApps]="applications">
            <div class="loader-bubble loader-bubble-primary d-block">انتظر</div>
          </app-leave-emp-apps>
        </div>
      </div>
    </div>
  `,
  providers: [
    {provide: NgbCalendar, useClass: NgbCalendarIslamicUmalqura},
    {provide: NgbDatepickerI18n, useClass: IslamicI18n}

  ]
})
export class LeaveDashboardComponent implements OnInit {
  applications: ApplicationDTO[];

  balances: Balance[];

  application: ApplicationDTO;

  disabledDates: NgbDateStruct[] = [];

  errorMsg = '';

  options = {
    autoClose: false,
    keepAfterRouteChange: false
  };

  constructor(
    private leaveService: LeaveService,
    private parserFormatter: NgbDateParserFormatter,
    protected alertService: AlertService,
    private calendar: NgbCalendar,
  ) {
    this.application = new ApplicationDTO();
  }

  ngOnInit() {

    this.getEmpApplications();

    this.getEmpBalance(1);
  }

  handleEdit(event: ApplicationDTO) {
    this.leaveService.addApplication(event).subscribe(() => {
        this.getEmpApplications();
        this.getEmpBalance(1);
        this.disabledDate(this.applications);
      },
      (error: string) => {
        this.errorMsg = error;
        this.alertService.error(this.errorMsg, this.options);
      }
    );


    //   .subscribe(
    //   result =>
    //     this.leaveService.findAll().subscribe((dat: ApplicationDTO[]) => {
    //       this.disabledDate(dat);
    //       return (this.applications = dat);
    //     }),

    // this.toastr.error(this.errorMsg, '--<>--', {progressBar: true});
    //   }
    // );
    // console.log(this.errorMsg);
    // this.toastr.success('تم إرسال طلبك', '--<>--', {progressBar: true});
    this.alertService.success('تم ارسال الطلب بنجاح', this.options);
    this.application = new ApplicationDTO();
  }


  getEmpApplications() {
    this.leaveService.findAll().subscribe((dat: ApplicationDTO[]) => {
      this.disabledDate(dat);
      return (this.applications = dat);
    });
  }

  getEmpBalance(type: number) {
    this.leaveService.findBalance(type).subscribe((data: Balance[]) => {
      // console.log(data);
      return (this.balances = data);
    });
  }


  disabledDate(appList: ApplicationDTO[]) {
    console.log(appList);
    appList.forEach(x => {
      // console.log(x.startDateAh);
      this.calculateDate(x.startDateAh, x.duration);
    });
  }

  calculateDate(startDate: NgbDate, duration: number) {

    const startdt = new Date(startDate + '');
    let ngbDateStruct = {
      year: startdt.getFullYear(),
      month: startdt.getMonth() + 1,
      day: startdt.getDate()
    };
    // console.log(startdt);
    for (let i = 0; i < duration; i++) {


      // console.log(ngbDateStruct);
      this.disabledDates.push(ngbDateStruct);
      ngbDateStruct = this.calendar.getNext(NgbDate.from(ngbDateStruct));
    }

  }
}
