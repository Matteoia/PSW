import { HomeComponent } from './home/home.component';
import { RegOKComponent } from './reg-ok/reg-ok.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "reg-ok", component: RegOKComponent}
];

@NgModule({
imports: [BrowserModule, RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }

