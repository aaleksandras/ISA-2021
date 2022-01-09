import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home.component";

const routes: Routes = [{
  path: 'client/ship',
  component: HomeComponent,
  data: { roles: ['client'], entity: '0' }
}, {
  path: 'client/cottage',
  component: HomeComponent,
  data: { roles: ['client'], entity: '1'}
}, {
  path: 'client/adventure',
  component: HomeComponent,
  data: {roles: ['client'], entity: '2'}
}];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
