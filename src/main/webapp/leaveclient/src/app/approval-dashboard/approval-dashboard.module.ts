import {NgModule} from '@angular/core';
import {LeavetransComponent} from "./components/leavetrans/leavetrans.component";
import {HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";
import {CommonModule} from "@angular/common";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {NgxEchartsModule} from "ngx-echarts";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LaddaModule} from "angular2-ladda";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {ApprovalDashboardComponent} from "./containers/approval-dashboard.component";
import {TransactionService} from "./services/transaction.service";


@NgModule({
  declarations: [
    LeavetransComponent,
    ApprovalDashboardComponent
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
  ],
  exports: [
    ApprovalDashboardComponent,
  ],
  providers: [
    TransactionService
  ]
})

export class ApprovalDashboardModule{}
