import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SalesCommissionService {

  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  uploadFiles(salesData: File, productData: File): Observable<any>
  {
    const formData = new FormData();
    formData.append('sales',salesData,salesData.name);
    formData.append('products',productData, productData.name);
    return this.http.post(`${this.apiUrl}/sales`,formData);
  }

  getCommissions():Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/sales`);
  }

  getCommissionsByDate(date : string): Observable<any[]>
  {
    return this.http.get<any[]>(`${this.apiUrl}/sales/date`, {params: {date: date}});
  }
}