import { Component, OnInit } from '@angular/core';
import {ComentsService} from "../services/comments/comments.service"
import { Penalty } from '../model/PenaltyModel';
import { Comment } from '../model/CommentModel';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-view-coments',
  templateUrl: './view-coments.component.html',
  styleUrls: ['./view-coments.component.css']
})
export class ViewComentsComponent implements OnInit {

  list:any[] = [];
  constructor(private CommentsService:ComentsService, private authService:AuthService) { }

  ngOnInit(): void {
    this.CommentsService.getComments(this.authService.getId()).subscribe((res:any)=>{
      console.log(res)
      console.log("XXX")
      for(let action of res){
        this.list.push(action);
      }
    })
  }

  onSubmitComplaint(item: any) {
    if(item.reason != null && item.reason.length > 0) {
      let penalty: Penalty = {username: item.username, reason: item.reason, userComment: item.content}
      this.CommentsService.sendPenalty(this.authService.getId(),penalty).subscribe(response => {
        alert("Added penalty");
      })
    } else {
      alert("Please input reason!")
    }
  }

}
