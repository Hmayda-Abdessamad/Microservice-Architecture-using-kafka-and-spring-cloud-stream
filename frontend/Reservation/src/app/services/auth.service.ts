import { Injectable } from '@angular/core';
import { RegisterDTO } from '../Models/Register';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Login } from '../Models/Login';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 
 
  

  localUrl= " http://localhost:8089/SERVICE-AUTH/auth"


  constructor(private http:HttpClient,public router :Router) { }


  register(formValue: RegisterDTO) {

    this.http.post(this.localUrl+"/register",formValue).subscribe({
      next:(res:any)=>
      {
        console.log("Successful registration");
        this.router.navigateByUrl("login")
        
      },
      error:(err:any)=>{
        console.log(err)
      }
    }
    );
    
  }

  login(formValue: Login): Observable<string> {
    return this.http.post(this.localUrl + "/login", formValue).pipe(
      map((res: any) => {
        return res.res as string; // Assuming 'res.res' holds the string value
      })
    );
  }
  

}
