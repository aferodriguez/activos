import { Component, OnInit } from '@angular/core';
import { Activo } from '../../entities/activo';
import { ActivoService} from '../../../services/activo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activos-list',
  templateUrl: './activos-list.component.html',
  styleUrls: ['./activos-list.component.css'],
  providers:[ActivoService]
})
export class ActivosListComponent implements OnInit {

  private activos: Activo[];


  constructor(private router:Router,private activoService:ActivoService) { }

  ngOnInit() {
    this.getAllActivos();
  }
  
  getAllActivos(){
    this.activoService.findaAll().subscribe(
      activos =>{
        this.activos = activos;
      },err=>{
        console.log(err);
      }
    );
  }

}
