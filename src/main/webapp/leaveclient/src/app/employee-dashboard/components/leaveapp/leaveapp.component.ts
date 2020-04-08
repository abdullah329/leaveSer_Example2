import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LeaveService} from '../../leave.service';
import {ApplicationDTO} from '../../models/ApplicationDTO';
import {
  NgbCalendar,
  NgbCalendarIslamicUmalqura,
  NgbDate,
  NgbDateParserFormatter,
  NgbDatepickerI18n,
  NgbDateStruct
} from '@ng-bootstrap/ng-bootstrap';
import {IslamicI18n} from '../../../islamic-calendar/islamic-calendar.component';


@Component({
  selector: 'app-leaveapp',
  templateUrl: './leaveapp.component.html',
  styleUrls: ['./leaveapp.component.scss'],
  providers: [
    {provide: NgbCalendar, useClass: NgbCalendarIslamicUmalqura},
    {provide: NgbDatepickerI18n, useClass: IslamicI18n}
  ]
})
export class LeaveappComponent implements OnInit {

  @Input()
  editable: ApplicationDTO;

  // @Input()
  // listOfApps: ApplicationDTO[];

  @Output()
  edit: EventEmitter<ApplicationDTO> = new EventEmitter<ApplicationDTO>();

  stDate: NgbDateStruct;

  model: NgbDateStruct;
  date: { year: number, month: number };

  @Input()
  disabledDates: NgbDateStruct[];

  durationError = false;


  constructor(
    private parserFormatter: NgbDateParserFormatter,
    private calendar: NgbCalendar,
    private leaveService: LeaveService) {
  }

  ngOnInit(): void {
    // this.disabledDate();
  }


  onSubmit() {
    // this.editable.startDateAh = NgbDate.from(this.stDate);
    this.edit.emit(this.editable);
  }

  intersectionLeave(event: any) {
    const endDate = this.calendar.getNext(this.editable.startDateAh, 'd', Number(event.target.value));
    this.durationError = this.disabledDates.filter(x => NgbDate.from(x).after(this.editable.startDateAh)
      && NgbDate.from(x).before(NgbDate.from(endDate))).length > 0;
  }

  isDisabled = (date: NgbDate) => {
    return !!this.disabledDates.find(x => NgbDate.from(x).equals(date)) ||
      this.calendar.getWeekday(date) === 5 || this.calendar.getWeekday(date) === 6;

  };

  isDisabledStyle = (date: NgbDateStruct) => {
    return !!this.disabledDates.find(x => NgbDate.from(x).equals(date));

  };

  isWeekend(date: NgbDate) {
    // const d = new Date(date.year, date.month - 1, date.day);
    return this.calendar.getWeekday(date) === 5 || this.calendar.getWeekday(date) === 6;
  }

}
