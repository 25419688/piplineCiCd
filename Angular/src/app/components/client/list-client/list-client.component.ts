import { Component } from '@angular/core';
import { ListClient } from '../../../models/client';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent {
  cars: ListClient[] = [
    new ListClient(1, "Ahmad Salhi",11412439),
    new ListClient(2, "Ahmad Salhi",11412439),
    new ListClient(3, "Ahmad Salhi",11412439),
    new ListClient(4, "Ahmad Salhi",11412439),
    new ListClient(5, "Ahmad Salhi",11412439),
    
   
  ];


}
