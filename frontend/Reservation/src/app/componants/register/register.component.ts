import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { RegisterDTO } from 'src/app/Models/Register';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register!: FormGroup;

  constructor(private fb: FormBuilder,private authService:AuthService) {}


  ngOnInit() {
    this.register = this.fb.group({
    
      nom:['',Validators.required],
      email:['',[Validators.email,Validators.required]],
      password:["",[Validators.required,Validators.minLength(6)]]
     
    });
  }

  onSubmit() {
    if(this.register.valid){

       const formValue=this.register.value
       this.authService.register(formValue)
      

    }else{
      console.log("email invalid")
    }
    
  }

}
