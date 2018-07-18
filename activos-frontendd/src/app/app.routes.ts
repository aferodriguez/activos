import {Component} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivosListComponent } from './components/activos/activos-list/activos-list.component';
import { HomeComponent } from './components/home/home.component';


const APP_ROUTES: Routes = [
    {path: 'home', component: HomeComponent},
    {path: 'activos', component: ActivosListComponent}
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);