import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminHomeComponent} from "./pages/admin-home/admin-home.component";
import { DashboardComponent } from './componants/dashboard/dashboard.component';
import { VoituresComponent } from './componants/voitures/voitures.component';
import { ClientsComponent } from './componants/clients/clients.component';
import { AuthComponent } from './pages/auth/auth.component';
import { LoginComponent } from './componants/login/login.component';
import { RegisterComponent } from './componants/register/register.component';

const routes: Routes = [
  {path:"home",component:AdminHomeComponent,children:[
    {path:"",component:DashboardComponent},
    {path:"dashboard",component:DashboardComponent},
    {path:"voitures",component:VoituresComponent},
    {path:"clients",component:ClientsComponent}
  ]},
  {path:"",component:AuthComponent,children:[
    {path:"",component:LoginComponent},
    {path:"login",component:LoginComponent},
    {path:"register",component:RegisterComponent},
  
  ]},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
