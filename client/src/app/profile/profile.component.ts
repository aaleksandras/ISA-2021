import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegistrationService } from '../auth/registration.service';
import { RegistrationModel } from '../model/RegistrationModel';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatchPassword} from "../shared/validators/match-password"



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userId:any;
  user:any;
  registrationForm = new FormGroup({});
  is_disabled = true;

  constructor(
    private route: ActivatedRoute,
    private matchPassword: MatchPassword,
    private registrationService: RegistrationService
    ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params[`id`];
    this.registrationService.find(this.userId).subscribe((res) => { 
      this.user = res.body;
      this.registrationForm = new FormGroup(
        {
          firstName: new FormControl(this.user.firstName, [Validators.required]),
          lastName: new FormControl(this.user.lastName, [Validators.required]),
          password: new FormControl('', [Validators.required]),
          address: new FormControl(this.user.address, [Validators.required]),
          city: new FormControl(this.user.city, [Validators.required]),
          country: new FormControl(this.user.country, [Validators.required]),
          confirmPassword: new FormControl('', [Validators.required]),
          email: new FormControl({value: this.user.email, disabled: true}, [Validators.required]),
          phoneNumber: new FormControl(this.user.phoneNumber, [Validators.required]),
        },
        {
          validators: [this.matchPassword.validate],
        }
      );
    });
  }

  onSubmit() {
    const model = new RegistrationModel(this.registrationForm.value);
    this.registrationService.update(this.userId, model).subscribe((res) => { });
  }

  getField(field: string): FormControl {
    return this.registrationForm.get(field) as FormControl;
  }
}
