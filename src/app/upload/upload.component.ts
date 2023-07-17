import { Component, EventEmitter, Output } from '@angular/core';
import { SalesCommissionService } from '../sales-commission.service';
import {
  animate,
  query,
  stagger,
  style,
  transition,
  trigger,
} from '@angular/animations';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  // animations: [
  //   trigger('listAnimation', [
  //     transition('* <=> *', [
  //       query(
  //         ':enter',
  //         [style({ opacity: 0, transform: 'translateY(-10px)' }), stagger('100ms', animate('0ms ease-out', style({ opacity: 1, transform: 'translateY(0)' })))],
  //         { optional: true }
  //       ),
  //       query(':leave', animate('0ms', style({ opacity: 0, transform: 'translateY(-10px)' })), {
  //         optional: true,
  //       }),
  //     ]),
  //   ]),
  // ],
})
export class UploadComponent {
    salesData: any;
    productData: any;
    showError: any;
    uploading = false;
    constructor(private salesCommissionService: SalesCommissionService){}

    onSalesFileSelected(event: any)
    {
      this.salesData = event.target.files[0];
    }

    onProductFileSelected(event: any)
    {
      this.productData = event.target.files[0];
    }

    
    uploadFiles()
    {
      if(!this.salesData || !this.productData){
        alert('please select both files before uploading');
        return;
      }
      this.uploading = true;
      this.salesCommissionService.uploadFiles(this.salesData,this.productData).subscribe((response)=>{
        console.log(response);
        alert('Files uploaded successfully');
        this.uploading = false;
      },
      (error) => {
        console.error(error);
        this.uploading = false;
      }
      );
    }
}


