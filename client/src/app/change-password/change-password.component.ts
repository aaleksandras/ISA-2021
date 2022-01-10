import { Component, OnInit } from '@angular/core';
import { AdminService } from '../administrator/admin.service';
import { AuthService } from '../auth/auth.service';
import { ResetPasswordModel } from '../model/ResetPasswordModel';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  public newPassword: string = '';
  public repeatePassword: string = '';
  public passwordsMatch: boolean = true;
  constructor(private adminService: AdminService, private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit = () => {
    this.passwordsMatch = this.newPassword === this.repeatePassword;
    if (this.passwordsMatch) {
      const model = new ResetPasswordModel();
      model.id = this.authService.getId() as string;
      model.newPassword = this.newPassword;
      this.adminService.resetPassword(model).subscribe(res => {
        alert("Your password has been updated!");
        this.authService.doLogout();
        window.location.href = "/login";
      });
    }
  }

}
