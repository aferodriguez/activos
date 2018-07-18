import { Component, OnInit } from '@angular/core';
import { Activo } from '../../entities/activo';
import { ActivoService} from '../../../services/activo.service';
import {Location} from '@angular/common';
import {FormControl, FormGroup, Validators, FormBuilder} from '@angular/forms';
import { ActivatedRoute,Router } from '../../../../../node_modules/@angular/router';


@Component({
  selector: 'app-activos-create',
  templateUrl: './activos-create.component.html',
  styleUrls: ['./activos-create.component.css'],
  providers:[ActivoService]
})
export class ActivosCreateComponent implements OnInit {

  activoForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private location: Location,
  private route:ActivatedRoute,private router: Router, private activoService: ActivoService ) {
    
   }

  ngOnInit() {
  
  }

}
