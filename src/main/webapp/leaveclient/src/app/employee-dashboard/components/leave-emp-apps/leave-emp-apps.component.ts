import {Component, Input} from '@angular/core';
import {ApplicationDTO} from "../../models/ApplicationDTO";
import {LeaveService} from "../../leave.service";

@Component({
  selector: 'app-leave-emp-apps',
  templateUrl: './leave-emp-apps.component.html',
  styleUrls: ['./leave-emp-apps.component.css']
})
export class LeaveEmpAppsComponent  {

  @Input()
  empApps: ApplicationDTO[];

  constructor(private leaveService: LeaveService) { }

  // ngOnInit():void {
  //   // this.leaveService.findAll().subscribe( (data: ApplicationDTO[]) => {
  //   //   return this.applications = data;
  //   })
  // }

}
