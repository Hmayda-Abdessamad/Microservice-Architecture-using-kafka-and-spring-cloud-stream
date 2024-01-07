import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder,private authService:AuthService,private router:Router) {}
  IncorrectData!:boolean


  login!:FormGroup

  ngOnInit() {
    this.login = this.fb.group({
    
      email:['',[Validators.email,Validators.required]],
      password:["",[Validators.required]]
     
    });
  }

  onSubmit() {

    if (this.login.valid) {

      const formValue = this.login.value;
      
      this.authService.login(formValue).subscribe(
        {
          next:(res:string)=>{
            if (res === 'bad credential') {
            
              this.IncorrectData=true
            
            } else {
              
              localStorage.setItem("token",res);
              this.router.navigateByUrl("/home")

            }
          }
        }
      );

    } else {
      console.log("Vos données sont incorrectes");
    }
  }
}



