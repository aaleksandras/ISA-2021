import { Component, OnInit } from '@angular/core';
import { LoyaltyProgramModel } from '../../../src/app/model/LoyaltyProgramModel';
import { LoyaltyProgramService } from '../services/loyalty-program/loyalty-program.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css'],
})
export class LoyaltyProgramComponent implements OnInit {

  existProgram: any;
  programForm = new FormGroup({});

  constructor(private loyaltyProgramService: LoyaltyProgramService) { }

  ngOnInit(): void {
    this.loyaltyProgramService.getLoyaltyProgram().subscribe((res) => {
      this.existProgram = res.body;
      this.programForm = new FormGroup(
        {
          pointsToRegular: new FormControl(this.existProgram.pointsToRegular, [Validators.required,]),
          pointsToSilver: new FormControl(this.existProgram.pointsToSilver, [Validators.required]),
          pointsToGold: new FormControl(this.existProgram.pointsToGold, [Validators.required]),

          percentRegular: new FormControl(this.existProgram.percentRegular, [Validators.required]),
          percentSilver: new FormControl(this.existProgram.percentSilver, [Validators.required]),
          percentGold: new FormControl(this.existProgram.percentGold, [Validators.required]),

          clientPointsForReservation: new FormControl(this.existProgram.clientPointsForReservation, [Validators.required]),
          
        }
        );
      });
  }

  onSubmit() {
    const model = new LoyaltyProgramModel(this.programForm.value);
    this.loyaltyProgramService.defineProgram(model).subscribe((res) => {});
    alert("You defined loyalty program!")
  }

  getField(field: string): FormControl {
    return this.programForm.get(field) as FormControl;
  }
}


