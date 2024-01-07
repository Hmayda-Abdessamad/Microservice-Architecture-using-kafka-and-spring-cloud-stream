import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { SidebarComponent } from './componants/sidebar/sidebar.component';
import { NavbarComponent } from './componants/navbar/navbar.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home.component';
import { BodyComponent } from './componants/body/body.component';
import { DashboardComponent } from './componants/dashboard/dashboard.component';
import { VoituresComponent } from './componants/voitures/voitures.component';
import { ClientsComponent } from './componants/clients/clients.component';
import { AuthComponent } from './pages/auth/auth.component';
import { LoginComponent } from './componants/login/login.component';
import { RegisterComponent } from './componants/register/register.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    AdminHomeComponent,
    BodyComponent,
    DashboardComponent,
    VoituresComponent,
    ClientsComponent,
    AuthComponent,
    LoginComponent,
    RegisterComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
