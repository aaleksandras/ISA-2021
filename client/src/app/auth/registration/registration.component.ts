import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatchPassword} from "../../shared/validators/match-password";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
  }, {
    validators: [this.matchPassword.validate]
  })
  constructor(private matchPassword: MatchPassword) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.registrationForm);
  }

  getField(field: string) : FormControl {
    return this.registrationForm.get(field) as FormControl;
  }
}
