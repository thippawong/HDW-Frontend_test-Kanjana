import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Product } from '../show/show.component';
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  public API = '//localhost:8080';
  constructor(private http: HttpClient) {}

  getType(): Observable<any> {
    return this.http.get(this.API + '/types');
  }
  getProduct():Observable<Product[]>{
    return this.http.get<Product[]>(this.API +'/product');
  }
  getProduct1(id): Observable<any> {
    return this.http.get(this.API + '/product/' + id);
  }
}
