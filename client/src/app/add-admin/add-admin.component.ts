import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../administrator/admin.service';
import { RegistrationModel } from '../model/RegistrationModel';
import { MatchPassword } from '../shared/validators/match-password';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

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
      description: new FormControl('', [Validators.required]),
    },
    {
      validators: [this.matchPassword.validate],
    }
  );
  constructor(
    private matchPassword: MatchPassword,
    private adminService: AdminService
  ) {

  }

  ngOnInit(): void { }

  onSubmit() {
    const model = new RegistrationModel(this.registrationForm.value);
    model.typeOfUser = "Administrator";
    this.adminService.addAdmin(model).subscribe(res => {
      alert("New administrator has been created");
      window.location.href = "";
    });
  }

  getField(field: string): FormControl {
    return this.registrationForm.get(field) as FormControl;
  }

}
