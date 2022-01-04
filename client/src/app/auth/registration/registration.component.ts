import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationModel } from 'src/app/model/RegistrationModel';
import { MatchPassword } from '../../shared/validators/match-password';
import { RegistrationService } from '../registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  registrationForm = new FormGroup(
    {
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      phoneNumber: new FormControl('', [Validators.required]),
      typeOfUser: new FormControl('', [Validators.required]),
    },
    {
      validators: [this.matchPassword.validate],
    }
  );
  constructor(
    private matchPassword: MatchPassword,
    private registrationService: RegistrationService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    const model = new RegistrationModel(this.registrationForm.value);
    this.registrationService.create(model).subscribe((res) => {});
    console.log(this.registrationForm);
  }

  getField(field: string): FormControl {
    return this.registrationForm.get(field) as FormControl;
  }
}
