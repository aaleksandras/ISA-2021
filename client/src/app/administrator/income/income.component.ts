import { Component, OnInit } from '@angular/core';
import { ReportModel } from 'src/app/model/ReportModel';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css'],
})
export class IncomeComponent implements OnInit {
  currentPercentage: any;
  public newPercentage: string = '';
  public fromDate: string = '';
  public toDate: string = '';
  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getReservationPercentage().subscribe((res) => {
      this.currentPercentage = res;
    });
  }

  onChangePercentageClick = () => {
    if (this.newPercentage === '') {
      alert('You must enter a new value!');
      return;
    }
    this.adminService
      .changeReservationPercentage(this.newPercentage)
      .subscribe((res) => {
        alert('Percentage from all reservations has been changed!');
        window.location.href = '';
      });
  };

  onGenerateReportClick = () => {
    if (this.fromDate === '' || this.toDate === '') {
      alert('You must enter from and to date for report!');
      return;
    }
    const model = new ReportModel();
    model.startDate = this.fromDate;
    model.endDate = this.toDate;
    this.adminService.generateReport(model).subscribe((res: any) => {
      res.startDate = new Date(res.startDate).toLocaleDateString();
      console.log(res);
      alert(
        'Your income in period from ' +
          new Date(res.startDate).toLocaleDateString() +
          ' to ' +
          new Date(res.endDate).toLocaleDateString() +
          ' is ' +
          res.income +
          '$ on ' +
          res.percentage +
          '% percentage from all reservations.'
      );
      window.location.href = '';
    });
  };
}
