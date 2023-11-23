import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  constructor(){}
  ngOnInit(): void{

  }
  minDate= new Date(2023, 9, 9);
  maxDate= new Date(2023, 9, 29);
}
