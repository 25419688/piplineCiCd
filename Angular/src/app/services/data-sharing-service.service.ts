import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataSharingServiceService {

  // constructor() { }
  // private searchResults = new BehaviorSubject<any[]>([]);
  // searchResults$ = this.searchResults.asObservable();

  // updateSearchResults(results: any[]) {
  //   this.searchResults.next(results);
  // }

  private searchTermSubject = new BehaviorSubject<string>('');
  searchTerm$ = this.searchTermSubject.asObservable();

  updateSearchTerm(searchTerm: string) {
    this.searchTermSubject.next(searchTerm);
  }
}
