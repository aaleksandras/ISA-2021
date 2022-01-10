import {DefaultModel} from "../../shared/default-model";

class Picture {
  name: string;
  constructor() {
    this.name = '';
  }
}



export class ReservationEntity extends DefaultModel{
  title: string;
  averageMark: number;
  description: string;
  address: string;
  type: string;
  pictures: Picture[];

  constructor() {
    super();
    this.title = '';
    this.averageMark = 0;
    this.description = '';
    this.address = '';
    this.type = '';
    this.pictures = []
  }
}
