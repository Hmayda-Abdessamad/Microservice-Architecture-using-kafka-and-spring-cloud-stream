import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AjoutVoitureDTO } from '../Models/AjoutVoitureDTO';
import { Observable, map, of } from 'rxjs';
import { voitureDTO } from '../Models/Voiture';

@Injectable({
  providedIn: 'root'
})
export class VoitureService {
 
  localUrl=" http://localhost:8089/SERVICE-VOITURE/voitures"


  constructor(private http:HttpClient) { }

  getVoitures(): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json'
    });

    return this.http.get<any>(this.localUrl, { headers });
  }

  ajouterVoiture(marque: string, matricule: string, model: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const formData = new FormData();
    formData.append('marque', marque);
    formData.append('matricule', matricule);
    formData.append('model', model);

    return this.http.post<any>(this.localUrl+"/add", formData, { headers });
  }

  voituresParClient(id: number) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
      'Content-Type': 'application/json'
    });

    return this.http.get<any>(this.localUrl+"/client/"+id, { headers });
  }
  supprimerVoiture(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<any>(`${this.localUrl}/delete?id=${id}`, { headers });
  }


}
