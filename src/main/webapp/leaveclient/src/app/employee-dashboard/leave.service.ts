import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {Balance} from './models/Balance';
import {ApplicationDTO} from './models/ApplicationDTO';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {
  private leaveUrl = '/api/v1/leave/application';
  private addrequestUrl = '/api/v1/leave/application/addLeave';

  constructor(private http: HttpClient) {
  }

  public findBalance(type: number): Observable<Balance[]> {
    return this.http.get<Balance[]>(`${this.leaveUrl}/balance?type=${type}`)
      .pipe(catchError(err => this.handleError));
  }

  public addApplication(application: ApplicationDTO) {
    console.log(application);
    return this.http
      .post<ApplicationDTO>(this.addrequestUrl, application)
      .pipe(catchError(this.handleError));
  }

  public findAll(): Observable<ApplicationDTO[]> {
    return this.http
      .get<ApplicationDTO[]>(`${this.leaveUrl}/leaves`)
      .pipe(catchError(err => this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    // console.log(error.error.message)
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.message}`;
    }
    if (error.error.status === 400) {
      errorMessage = error.error.message;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.error.message}`;
    }
    window.alert(errorMessage);
    return throwError(error.error.message);
  }
}
