import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
// import { NgForm } from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LeaveService} from './employee-dashboard/leave.service';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, DatePipe} from "@angular/common";
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {LaddaModule} from "angular2-ladda";
import {NgxEchartsModule} from "ngx-echarts";
import {EmployeeDashboardModule} from "./employee-dashboard/employee-dashboard.module";
import {ApprovalDashboardModule} from "./approval-dashboard/approval-dashboard.module";
import { ToastrModule } from 'ngx-toastr';
import {AlertModule} from "./_alert";


@NgModule({
  declarations: [
    AppComponent,
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
    EmployeeDashboardModule,
    ApprovalDashboardModule,
    ToastrModule.forRoot(),
  ],
  providers: [LeaveService, DatePipe],

  bootstrap: [AppComponent]
})
export class AppModule {
}
