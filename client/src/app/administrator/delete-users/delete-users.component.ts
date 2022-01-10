import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-delete-users',
  templateUrl: './delete-users.component.html',
  styleUrls: ['./delete-users.component.css']
})
export class DeleteUsersComponent implements OnInit {
  allUsers: any;
  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.adminService.getAllUsers().subscribe(res => {
      this.allUsers = res;
    })
  }

  onDeleteUserClick = (id: string) => {
    this.adminService.deleteUser(id).subscribe(res => {
      alert("User has been deleted!");
      window.location.href = '';
    })
  }

}
