import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { ActivosListComponent } from './components/activos/activos-list/activos-list.component';

import {ActivoService} from './services/activo.service';

import {APP_ROUTING} from './app.routes';


import { Ng2AutoCompleteModule } from '../../node_modules/ng2-auto-complete';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { ActivosCreateComponent } from './components/activos/activos-create/activos-create.component';

@NgModule({
  declarations: [
    AppComponent,
    ActivosListComponent,
    HomeComponent,
    NavbarComponent,
    ActivosCreateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    Ng2AutoCompleteModule,
    APP_ROUTING
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
