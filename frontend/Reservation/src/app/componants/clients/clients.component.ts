import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { clientDTO } from 'src/app/Models/Client';
import { voitureDTO } from 'src/app/Models/Voiture';
import { ClientService } from 'src/app/services/client.service';
import { VoitureService } from 'src/app/services/voiture.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {


  clients!:clientDTO[]
  AddForm!:FormGroup
  selectedClient!:clientDTO
  voitures!:voitureDTO[]

  constructor(private clientService:ClientService,private fb:FormBuilder,private voitureService:VoitureService){}

  ngOnInit(): void {

    this.getClients()

    this.AddForm=this.fb.group({
      nom:['',Validators.required],
      prenom:['',Validators.required],
      age:["",Validators.required]

    })
  }

  getClients(){
    this.clientService.getClients().subscribe(
      (response) => {
      
        this.clients=response
        console.log(response)
      
      
      },
      (error) => {
        console.error('Error fetching cars:', error);
     
      }
        );
  }

  voitureParClient(id: number) {
   
      const res=this.clients.filter(client => client.id==id)
      this.selectedClient=res[0]
      this.voitureService.voituresParClient(this.selectedClient.id).subscribe({
        next:(res)=>{
          this.voitures=res;
       
        },error:(err)=>{
          
        }
      })

    }

  onSubmit(){
    if(this.AddForm.valid){
      const { nom, prenom, age } = this.AddForm.value;
      this.clientService.ajouterClient(nom,prenom,age).subscribe({
        next:(res)=>{
          window.location.reload()
        },error:(err)=>{
          console.log(err)
        }
      })
      
    }
  }


  SuppClient(id: number) {
    
    this.clientService.supprimerClient(id).subscribe(
      (response) => {
        // On success, you may want to update the list of cars after deletion
        this.getClients();
      },
      (error) => {
        console.log('Error deleting car:', error);
      }
    );
   
  }

 


 

}
