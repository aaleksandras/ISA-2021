import {DefaultModel} from "../../shared/default-model";

export class ReservationEntity extends DefaultModel{
  title: string;
  averageMark: number;
  description: string;
  address: string;
  type: string;

  constructor() {
    super();
    this.title = '';
    this.averageMark = 0;
    this.description = '';
    this.address = '';
    this.type = '';
  }
}
