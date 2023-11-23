import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { CarDTO } from 'src/app/Models/CarDTO';
import { CarsService } from 'src/app/services/cars.service';
import { ListVoitureUserComponent } from '../cars/list-voiture-user/list-voiture-user.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  // constructor(private carsService: CarsService, private router: Router){}
  // @ViewChild(ListVoitureUserComponent) carListComponent!: ListVoitureUserComponent;
  // searchTerm: string = '';
  // @Output() searchRequested = new EventEmitter<string>();
  // onEnterSearch(event: any) {
  //   if (event.key === 'Enter') {
  //     // this.performSearch();
  //     this.searchRequested.emit(this.searchTerm);
  //     this.router.navigate(['/search-results'], { state: { searchTerm: this.searchTerm } });
  //   }
  // }
  // performSearch() {
  //   // const searchTerm = this.searchTerm;

  //   // this.carsService.searchCars(searchTerm).subscribe(
  //   //   (results: CarDTO[]) => {
  //   //     // Vous pouvez traiter les résultats ici si nécessaire
  //   //     console.log('Résultats de la recherche :', results);
  //   //     // this.carListComponent.showSearchResults(results);
  //   //     this.router.navigate(['/search-results'], { state: { searchResults: results } });
  //   //   },
  //   //   (error) => {
  //   //     // Gérez les erreurs ici
  //   //     console.error('Erreur lors de la recherche :', error);
  //   //   }
  //   // );

  //   const searchTerm = this.searchTerm;

  // this.carsService.searchCars(searchTerm).subscribe(
  //   (results: CarDTO[]) => {
  //     console.log('Résultats de la recherche :', results);
  //     this.router.navigate(['/search-results'], { state: { searchResults: results } });
  //   },
  //   (error) => {
  //     console.error('Erreur lors de la recherche :', error);
  //   }
  // );
  // }

}
