import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { clientDTO } from '../Models/Client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  localUrl=" http://localhost:8089/SERVICE-CLIENT/clients"


  constructor(private http:HttpClient) { }

  getClients(): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json'
    });

    return this.http.get<any>(this.localUrl, { headers });
  }


  ajouterClient(nom:string,prenom:string,age:string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const formData = new FormData();
    formData.append('nom', nom);
    formData.append('prenom', prenom);
    formData.append('age',age);
    

    return this.http.post<any>(this.localUrl+"/add", formData, { headers });
  }


  supprimerClient(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<any>(`${this.localUrl}/delete?id=${id}`, { headers });
  }


  allouer(clientId:string,voitureId:string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const formData = new FormData();
    formData.append('id_voiture', voitureId);
    formData.append('id_client', clientId);
   

    return this.http.post<any>(this.localUrl+"/allouer", formData, { headers });
  }
}
