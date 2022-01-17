import { Component, Input, OnInit } from '@angular/core';
import * as moment from 'moment'; //in your component

@Component({
  selector: 'app-reservation-entity-list',
  templateUrl: './reservation-entity-list.component.html',
  styleUrls: ['./reservation-entity-list.component.css'],
})
export class ReservationEntityListComponent implements OnInit {
  @Input() items: any;
  public startDate: string = '';
  public endDate: string = '';
  public address: string = '';
  public averageMark: string = '';
  public isSearchClicked: boolean = false;
  constructor() {}

  ngOnInit(): void {}

  onSearchClick = () => {
    if (this.startDate != '' && this.endDate != '' && this.startDate > this.endDate) {
      alert('You must enter valid start and end dates!');
      return;
    }
    this.isSearchClicked = true;
    let converted: any = [];
    this.items.forEach((element: any) => {
      element.availableDays.forEach((day: any) => {
        day.day = this.transformDate(new Date(day.day));
      });
      converted.push(element);
    });
    if (this.address != '') {
      this.items = converted.filter((item: any) =>
        item.reservationEntity.address.includes(this.address)
      );
      converted = this.items;
    }
    if (this.averageMark != '') {
      this.items = converted.filter(
        (item: any) => item.reservationEntity.averageMark >= this.averageMark
      );
      converted = this.items;
    }
    if (this.startDate != '') {
      let afterStart: any = [];
      for (let i = 0; i <= converted.length; i++) {
        if (converted[i]?.availableDays?.length > 0) {
          for (let j = 0; j <= converted[i]?.availableDays.length; j++) {
            if (converted[i]?.availableDays[j]?.day >= this.startDate) {
              afterStart.push(converted[i]);
              break;
            }
          }
        }
      }
      this.items = afterStart;
      converted = this.items;
    }
    if (this.endDate != '') {
      let beforeEnd: any = [];
      for (let i = 0; i <= converted.length; i++) {
        if (converted[i]?.availableDays?.length > 0) {
          for (let j = 0; j <= converted[i]?.availableDays.length; j++) {
            if (converted[i]?.availableDays[j]?.day <= this.endDate) {
              beforeEnd.push(converted[i]);
              break;
            }
          }
        }
      }
      this.items = beforeEnd;
      converted = this.items;
    }
  };

  onCancelSearchClick = () => {
    window.location.href = 'home';
  };

  transformDate = (date: Date) => {
    var mm = date.getMonth() + 1; // getMonth() is zero-based
    var dd = date.getDate();

    return [
      date.getFullYear(),
      (mm > 9 ? '' : '0') + mm,
      (dd > 9 ? '' : '0') + dd,
    ].join('-');
  };
}
