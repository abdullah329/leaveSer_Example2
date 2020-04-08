import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LeaveDashboardComponent} from "./containers/leave-dashboard/leave-dashboard.component";
import {LeaveEmpAppsComponent} from "./components/leave-emp-apps/leave-emp-apps.component";
import {LeaveappComponent} from "./components/leaveapp/leaveapp.component";
import {LeavebalanceComponent} from "./components/leavebalance/leavebalance.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {LeaveService} from "./leave.service";
import {BrowserModule} from "@angular/platform-browser";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {NgxEchartsModule} from "ngx-echarts";
import {LaddaModule} from "angular2-ladda";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {ToastrModule} from 'ngx-toastr';
import { NgbdDatepickerIslamicUmalquraModule } from '../islamic-calendar/islamic-calendar.module';


@NgModule({
  declarations: [
    LeaveDashboardComponent,
    LeaveappComponent,
    LeaveEmpAppsComponent,
    LeavebalanceComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    CommonModule,
    NgbModule,
    NgxEchartsModule,
    FormsModule,
    ReactiveFormsModule,
    LaddaModule.forRoot({style: 'expand-left'}),
    NgxDatatableModule,
    ToastrModule.forRoot(),
    NgbdDatepickerIslamicUmalquraModule

  ],
  exports: [
    LeaveDashboardComponent
  ],
  providers: [
    LeaveService,
  ]
})

export class EmployeeDashboardModule {
}
