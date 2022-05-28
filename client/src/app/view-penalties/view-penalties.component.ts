import { Component, OnInit } from '@angular/core';
import {ComentsService} from "../services/comments/comments.service"
import { Penalty } from '../model/PenaltyModel';
import { Comment } from '../model/CommentModel';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-view-penalties',
  templateUrl: './view-penalties.component.html',
  styleUrls: ['./view-penalties.component.css']
})
export class ViewPenaltiesComponent implements OnInit {

  list:any[] = [];
  constructor(private CommentsService:ComentsService, private authService:AuthService) { }

  ngOnInit(): void {
    this.CommentsService.getPenalties(this.authService.getId()).subscribe((res:any)=>{
      for(let action of res){
        this.list.push(action);
      }
    })
  }

  approve(item: any) {
      this.CommentsService.approvePenalty(this.authService.getId(),item.id).subscribe(response => {
        alert("Penalty approved");
      })
  }
  delete(item: any) {
    this.CommentsService.deletePenalty(this.authService.getId(),item.id).subscribe(response => {
      alert("Penalty deleted");
    })
}

}
