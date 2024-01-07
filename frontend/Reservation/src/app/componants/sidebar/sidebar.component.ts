import { Component, EventEmitter, HostListener, OnInit, Output } from '@angular/core';
import { navData } from './navData';
import { SideNavToggle } from 'src/app/Models/SideNavToggle';




@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})


export class SidebarComponent implements OnInit {


  @HostListener("window:resize",['$event'])
  onResize(event:any){
    this.screenWidth=window.innerWidth;
    if(this.screenWidth<=768){
      this.collapsed=false
      this.onToggleSideNav.emit({collapsed: this.collapsed,screenWidth: this.screenWidth})
    }
  }

  ngOnInit(): void {
      this.screenWidth=window.innerWidth;
  }

  @Output()  onToggleSideNav: EventEmitter<SideNavToggle>= new EventEmitter();

  collapsed=false;
  screenWidth=0

  navData=navData;


closeSidenav() {
this.collapsed=false;
this.onToggleSideNav.emit({collapsed: this.collapsed,screenWidth: this.screenWidth})
}

closeSidenavInMd() {
  
  if(this.screenWidth<=768){
    this.collapsed=false;
    this.onToggleSideNav.emit({collapsed: this.collapsed,screenWidth: this.screenWidth})
  }
 
  }

toggleCollapse() {
  this.collapsed=!this.collapsed
  this.onToggleSideNav.emit({collapsed: this.collapsed,screenWidth: this.screenWidth})
}

  

}
