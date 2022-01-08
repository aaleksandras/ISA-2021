import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputComponent } from './input/input.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    InputComponent,
  ],
    exports: [
        InputComponent,
    ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  schemas: []
})
export class SharedModule { }
