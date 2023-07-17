import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import {
  animate,
  query,
  stagger,
  style,
  transition,
  trigger,
} from '@angular/animations';
@Component({
  selector: 'app-quantity-dialog',
  templateUrl: './quantity-dialog.component.html',
  styleUrls: ['./quantity-dialog.component.css'],
  animations: [
   
    trigger('listAnimation', [
      transition('* <=> *', [
        query(
          ':enter',
          [style({ opacity: 0 }), stagger('100ms', animate('0ms ease-out', style({ opacity: 1 })))],
          { optional: true }
        ),
        query(':leave', animate('0ms', style({ opacity: 0 })), {
          optional: true,
        }),
      ]),
    ]),
  ],
})
export class QuantityDialogComponent 
{
  constructor(@Inject(MAT_DIALOG_DATA) public data:any) {} 
}
