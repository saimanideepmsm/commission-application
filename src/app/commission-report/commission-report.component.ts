// commission-report.component.ts
import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { SalesCommissionService } from '../sales-commission.service';
import { formatDate } from '@angular/common';
import {
  animate,
  query,
  stagger,
  style,
  transition,
  trigger,
} from '@angular/animations';
import {MatSort, Sort} from '@angular/material/sort';
import {MatDialog} from '@angular/material/dialog';
import { QuantityDialogComponent } from '../quantity-dialog/quantity-dialog.component';


@Component({
  selector: 'app-commission-report',
  templateUrl: './commission-report.component.html',
  styleUrls: ['./commission-report.component.css'],
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
export class CommissionReportComponent implements OnInit {
  commissions: any[] = [];
  displayedColumns: string[] = [
    'productName',
    'salesmanName',
    'commissionId',
    'quantity',
    'salesAmount',
    'salesmanArea',
    'salesmanCommission',
    'createdDate'
    // ... other column names ...
  ];
  constructor(private salesCommissionService: SalesCommissionService, private dialog: MatDialog) {}

  ngOnInit(): void 
  {
    // this.salesCommissionService.getCommissions().subscribe((commissions) => {
    //   this.commissions = commissions;
    // });
  }

  onDateChange(date: Date): void 
  {
    const formattedDate = formatDate(date, 'dd/MM/yyyy', 'en-US');
    this.salesCommissionService.getCommissionsByDate(formattedDate).subscribe((commissions) => {
      this.commissions = commissions;
    });
  }

  openQuantityDialog(data: any): void {
    this.dialog.open(QuantityDialogComponent, {
      data: data
    });
  }
}