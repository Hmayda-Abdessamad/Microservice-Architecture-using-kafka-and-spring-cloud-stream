import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { clientDTO } from 'src/app/Models/Client';

import { voitureDTO } from 'src/app/Models/Voiture';
import { ClientService } from 'src/app/services/client.service';
import { VoitureService } from 'src/app/services/voiture.service';
@Component({
  selector: 'app-voitures',
  templateUrl: './voitures.component.html',
  styleUrls: ['./voitures.component.css']
})
export class VoituresComponent implements OnInit{


  SelectedVoitureID!:number
  allocationForm!: FormGroup<any>;
  voitures!:voitureDTO[]
  clients!:clientDTO[]
  selectedClient: number = 0; 
  AddForm!:FormGroup
  selectedMarque: string = '0';
  state: string = '0';
  model:string="0"

  constructor(private fb:FormBuilder,private VoitureService:VoitureService,private clientService:ClientService){}

  ngOnInit(): void {

    this.getAllCar()
    this.getClients()

   this.AddForm=this.fb.group({
    marque : ["",Validators.required],
    matricule:["",Validators.required],
    model :["",Validators.required] 
 
   })

   this.allocationForm=this.fb.group({
    client_id : ["",Validators.required],
    
   })

  }
     
  Allouer() {
 

  if (this.allocationForm && this.allocationForm.valid) {
    const formValue = this.allocationForm.value;
    const clientId = formValue.client_id
    const voitureId=this.SelectedVoitureID
    this.clientService.allouer(clientId,voitureId.toString()).subscribe({
      next:(res)=>{
        window.location.reload()
      }
    })
    
    
  } else {
    console.log('Form is invalid or allocationForm is undefined/null.');
  }
       }


    setVoiture(id: number) {
  this.SelectedVoitureID=id
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

  filterCarsByModel() {

    if(this.model=='0'){
      this.getAllCar();
    }else{
      
      this.voitures = this.voitures.filter((v: voitureDTO) => v.model === this.model);
    }
  }
  
  filterCarsByMarque() {
    if(this.selectedMarque=='0'){
      this.getAllCar();
    }else{
      
      this.voitures = this.voitures.filter((v: voitureDTO) => v.marque === this.selectedMarque);
    }
   
  }
  
  filterCarsByState() {
    if(this.state=='0'){
      this.getAllCar();
    }else if(this.state=="1"){
      
      this.voitures = this.voitures.filter((v: voitureDTO) => v.id_client ==0);
    }else{
      
      this.voitures = this.voitures.filter((v: voitureDTO) => v.id_client !=0);
    }

    }
    


  onSubmit() {

    if (this.AddForm.valid) {
      const { marque, matricule, model } = this.AddForm.value;
  
      this.VoitureService.ajouterVoiture(marque, matricule, model).subscribe(
        (response) => {
         
          window.location.reload()
        },
        (error) => {
        console.log(error)
        }
      );
    }
  }
  
  getAllCar(){
    this.VoitureService.getVoitures().subscribe(
      (response) => {
      
        this.voitures=response
      
      
      },
      (error) => {
        console.error('Error fetching cars:', error);
     
      }
        );
  }


  SuppVoiture(id: number) {
    this.VoitureService.supprimerVoiture(id).subscribe(
      (response) => {
        // On success, you may want to update the list of cars after deletion
        this.getAllCar();
      },
      (error) => {
        console.log('Error deleting car:', error);
      }
    );
  }


}
