import { Component } from '@angular/core';
import { ListAgence } from '../../../models/list-agence.model';

@Component({
  selector: 'app-list-agence',
  templateUrl: './list-agence.component.html',
  styleUrls: ['./list-agence.component.css']
})
export class ListAgenceComponent {
 cars: ListAgence[] = [
    new ListAgence(1, "Cheverolet SUV Car","taxe"),
    new ListAgence(1, "KaryaExpress","taxe"),
    new ListAgence(1, "KaryaExpress","taxe"),
    new ListAgence(1, "KaryaExpress","taxe"),
    new ListAgence(1, "KaryaExpress","taxe"),
    new ListAgence(1, "KaryaExpress","taxe"),
    
   
  ];
}
