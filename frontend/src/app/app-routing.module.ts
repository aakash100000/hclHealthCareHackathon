import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { StaffComponent } from './staff/staff.component';
import { ShiftComponent } from './shift/shift.component';
import { ScheduleComponent } from './schedule/schedule.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'staff', component: StaffComponent},
  {path: 'shift', component: ShiftComponent},
  {path: 'schedule', component: ScheduleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
