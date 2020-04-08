import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LeaveappComponent } from './employee-dashboard/components/leaveapp/leaveapp.component';
import { LeavebalanceComponent } from './employee-dashboard/components/leavebalance/leavebalance.component';


const routes: Routes = [
  { path: 'leaveapp', component: LeaveappComponent },
  { path: 'balance', component: LeavebalanceComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
