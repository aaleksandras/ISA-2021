import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  @Input() label: string = '';
  @Input() type: string = 'text';
  @Input() control!: FormControl;

  constructor() { }

  ngOnInit(): void {
  }

  showErrors() {
    const { touched, errors } = this.control!;
    return  touched && errors;
  }

}
