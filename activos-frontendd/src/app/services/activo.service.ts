import { Injectable } from '@angular/core';
import { Activo } from '../components/entities/Activo';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ActivoService {

  private apiUrl = 'http://localhost:8080/Activos';

  constructor(private http:Http) {
     
   }

   findaAll(): Observable<Activo[]>{
     return this.http.get(this.apiUrl)
     .map((res:Response) => res.json())
     .catch((error:any) => Observable.throw(error.json().error || 'Server error'));     
   };

}
