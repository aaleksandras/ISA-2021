import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-delete-entities',
  templateUrl: './delete-entities.component.html',
  styleUrls: ['./delete-entities.component.css']
})
export class DeleteEntitiesComponent implements OnInit {
  allEntities: any;

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.adminService.getAllEntities().subscribe(res => {
      this.allEntities = res;
    })
  }

  onDeleteEntityClick = (id: string) => {
    this.adminService.deleteEntity(id).subscribe(res => {
      alert("Entity has been deleted!");
      window.location.href = '';
    })
  }

}
