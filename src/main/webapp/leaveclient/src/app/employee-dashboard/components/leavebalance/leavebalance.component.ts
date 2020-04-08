import {Component, Input, OnInit} from '@angular/core';
import { LeaveService } from '../../leave.service'
import { Balance } from '../../models/Balance';

@Component({
  selector: 'app-leavebalance',
  templateUrl: './leavebalance.component.html',
  styleUrls: ['./leavebalance.component.scss']
})
export class LeavebalanceComponent  {

  @Input()
  items: Balance[];

  constructor() { }



}
