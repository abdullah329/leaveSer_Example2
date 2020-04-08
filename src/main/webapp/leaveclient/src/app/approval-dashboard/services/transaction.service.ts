import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApplicationTransactionDTO} from '../models/ApplicationTransactionDTO';
import {Observable} from 'rxjs';
import {Application} from '../models/Application';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private transUrl = '/api/v1/leave/action';

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<ApplicationTransactionDTO[]> {
    return this.http.get<ApplicationTransactionDTO[]>(`${this.transUrl}/transaction`);
  }

  public findById(id: number): Observable<Application> {
    return this.http.get<Application>(`${this.transUrl}/transaction/${id}`);
  }

  public approve(application: ApplicationTransactionDTO) {
    console.log(application);
    return this.http.post<ApplicationTransactionDTO>(`${this.transUrl}/approve`, application);
  }

  public confirm(application: ApplicationTransactionDTO) {
    return this.http.post<ApplicationTransactionDTO>(`${this.transUrl}/confirm`, application);
  }

  public cancel(application: ApplicationTransactionDTO) {
    return this.http.post<ApplicationTransactionDTO>(`${this.transUrl}/cancel`, application);
  }

  public reject(application: ApplicationTransactionDTO) {
    return this.http.post<ApplicationTransactionDTO>(`${this.transUrl}/reject`, application);
  }
}
