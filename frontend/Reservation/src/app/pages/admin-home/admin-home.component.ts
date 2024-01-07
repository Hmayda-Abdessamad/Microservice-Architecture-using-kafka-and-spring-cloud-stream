import { Component } from '@angular/core';
import { SideNavToggle } from 'src/app/Models/SideNavToggle';
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {

  isSideBarCollapsed=false
  screenWidth=0


  onToggleSideNav(data: SideNavToggle) :void{
    this.isSideBarCollapsed=data.collapsed
    this.screenWidth=data.screenWidth
    }

}
