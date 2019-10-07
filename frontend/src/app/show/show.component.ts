import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import { ProductService } from '../service/product.service';
import { HttpClient } from '@angular/common/http';
import { DataSource } from '@angular/cdk/table';
import { Observable } from 'rxjs';
import {Router} from '@angular/router';


export interface Product{
  name:string;
  code:string;
  type:{
    types:string;
  }
  image:string;
  detail:string;
}


@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {

  constructor(private productService: ProductService,
    private httpClient: HttpClient,private router: Router) { }
displayedColumns: string[] = ['product','name', 'detail', 'edit','delete'];
  dataSource = new ProductDataSource(this.productService);
  product: Array<any>;
  ngOnInit() {
    this.productService.getProduct().subscribe(data =>{
      this.product=data;
      this.dataSource=new ProductDataSource(this.productService);
    })
  }
  Edit(id){
    //alert(id);
    this.router.navigate(['/edit/', id]);
  }
  delete(id) {
    this.httpClient
      .delete("http://localhost:8080/deleteproduct/"+ id).subscribe(data => {
        if(data){
          alert("ลบข้อมูลสำเร็จ");
          window.location.reload();
        }else{
          alert("ลบข้อมูลไม่สำเร็จ");
        }
        console.log("return" + data);
      });
  }


}
export class ProductDataSource extends DataSource<any> {
  constructor(private productService: ProductService){
    super();
  }
  connect():Observable<Product[]>{
    return this.productService.getProduct();
  }
  disconnect(){}
}
