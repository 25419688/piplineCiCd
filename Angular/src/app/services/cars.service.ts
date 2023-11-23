import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { Cars } from '../Models/cars';
import { CarDTO } from '../Models/CarDTO';
@Injectable({
  providedIn: 'root'
})
export class CarsService {
  private searchResultsSubject: BehaviorSubject<any> = new BehaviorSubject([]);
  public searchResults$: Observable<any> = this.searchResultsSubject.asObservable();
  private apiUrl = 'http://localhost:8080/api/cars';
  private searchResults: CarDTO[] = [];


  constructor(private http: HttpClient) { }

  addCarWithImage(carData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/addCar`, carData);
  }

  getCarById(carId: number): Observable<Cars> {
    return this.http.get<Cars>(`${this.apiUrl}/getCarById/${carId}`);
  }

  updateStudent(carId: number, value: any): Observable<Object> {
    return this.http.post(`${this.apiUrl}/update/${carId}`, value);
  }

  getAllCars(): Observable<CarDTO[]> {
    return this.http.get<CarDTO[]>(`${this.apiUrl}/getAllCars`);
  }

  // searchCars(searchTerm: string): Observable<any> {
  //   const url = `${this. apiUrl}/search?searchTerm=${searchTerm}`;
  //   return this.http.get(url);
  // }
  // searchCars(searchTerm: string): Observable<CarDTO[]> {
  //   const url = `${this.apiUrl}/search?searchTerm=${searchTerm}`;

  //   this.http.get(url).subscribe(
  //     (results: any) => {
  //       // Mettez à jour les résultats de la recherche
  //       this.updateSearchResults(results);
  //     },
  //     (error) => {
  //       // Gérez les erreurs ici
  //     }
  //   );
  // }

  // updateSearchResults(results: any): void {
  //   // Mettez à jour les résultats de la recherche
  //   this.searchResultsSubject.next(results);
  // }
  // searchCars(searchTerm: string): Observable<CarDTO[]> {
  //   const url = `${this.apiUrl}/search?searchTerm=${searchTerm}`;
  //   console.log(url)
  //   return this.http.get<CarDTO[]>(url).pipe(
  //     tap((results: CarDTO[]) => {
  //       // Mettez à jour les résultats de la recherche
  //       this.updateSearchResults(results);
  //     }),
  //     catchError((error) => {
  //       // Gérez les erreurs ici
  //       console.error('Erreur lors de la recherche :', error);
  //       return throwError(error);
  //     })
  //   );
  // }
  // private updateSearchResults(results: CarDTO[]): void {
  //   this.searchResults = results;
  // }
  // searchCars(searchTerm: string): Observable<CarDTO[]> {
  //   return this.http.get<CarDTO[]>(`${this.apiUrl}/search-results/${searchTerm}`);
  // }
  searchCars(searchTerm: string): Observable<CarDTO[]> {
    return this.http.get<CarDTO[]>(`${this.apiUrl}/search?searchTerm=${searchTerm}`);
  }
}
