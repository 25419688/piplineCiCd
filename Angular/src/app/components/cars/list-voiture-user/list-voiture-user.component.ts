import { Component, OnInit } from '@angular/core';
import { CarsService } from 'src/app/services/cars.service';
import { CarDTO } from 'src/app/Models/CarDTO';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-voiture-user',
  templateUrl: './list-voiture-user.component.html',
  styleUrls: ['./list-voiture-user.component.css']
})
export class ListVoitureUserComponent implements OnInit {
  cars: CarDTO[] = [];
  searchResults: CarDTO[] = [];
  showSearchResults: boolean = false;

  constructor(private carService: CarsService, private route: ActivatedRoute, private router : Router) {}

  ngOnInit(): void {
    // Récupérez tous les véhicules à partir du service lors de l'initialisation
    this.route.paramMap.subscribe(()=>{
      this.listCars();
    });
    
  }
  listCars(){
    this.showSearchResults = this.route.snapshot.paramMap.has('keyword');
    if (this.showSearchResults){
      this.searchCars();
    }
    else{
      this.getAllCars();
    }
    
  }
  searchCars(){
    const theKeyword: string | null = this.route.snapshot.paramMap.get('keyword');

    if (theKeyword !== null) {
      this.carService.searchCars(theKeyword).subscribe((data: CarDTO[]) => {
        this.cars = data;
        // Vous pouvez effectuer d'autres opérations ici après la récupération des véhicules si nécessaire.
        
      });
    } else {
      // Traitez le cas où 'theKeyword' est nul. Vous pourriez afficher un message d'erreur ou prendre une autre action appropriée.
      console.log("null")
      
    }
  }
  getAllCars(): void {
    this.carService.getAllCars().subscribe((data: CarDTO[]) => {
      this.cars = data;
      // Vous pouvez effectuer d'autres opérations ici après la récupération des véhicules si nécessaire.
    });
  }

  getImageUrl(car: CarDTO): string {
    if (car.imageData) {
      const base64Image = 'data:image/' + car.fileType + ';base64,' + car.imageData;
      return base64Image;
    }
    return '';
  }
}
