import { Component, OnInit } from '@angular/core';
import { ProductService } from "../service/product.service";
import { HttpClient } from "@angular/common/http";
import {Router} from '@angular/router';
import {
  AngularFireStorage,
  AngularFireStorageReference,
  AngularFireUploadTask
} from "angularfire2/storage";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  downloadURL: Observable<String>;
  uploadProgress: Observable<number>;
  uploadState: Observable<string>;
  ref: AngularFireStorageReference;
  task: AngularFireUploadTask;
  types: Array<any>;
  select: any = {
    types: "",
    name: "",
    code: "",
    image: "",
    detail: ""
  };
  constructor(private productService: ProductService,
    private httpClient: HttpClient,
    private storage: AngularFireStorage,
    private router: Router, private route: ActivatedRoute
    ) { }

  ngOnInit() {
    this.productService.getType().subscribe(data => {
      this.types = data;
      console.log(this.types);
    });
  }

  save() {
    console.log("name = " + this.select.name);
    console.log("Save");
   // alert(this.select.image);
    this.httpClient
      .post("http://localhost:8080/products", JSON.stringify(this.select), {
        headers: {
          "Content-Type": "application/json"
        }
      })
      .subscribe(data => {
        console.log("return" + data);
        alert("บันทึกข้อมูลสำเร็จ");
        this.router.navigate(['']);
      });
  }
  uploadPic(event) {
    // + this.dateAsYYYYMMDDHHNNSS(new Date())
    this.ref = this.storage.ref(
      "Picture_" + this.dateAsYYYYMMDDHHNNSS(new Date())
    );
    this.ref.put(event.target.files[0]).then(result => {
      console.log(this.ref);
      if (result.state === "success") {
        alert("อัพโหลดรูปภาพสำเร็จ กรุณากดปุ่ม เพิ่ม");
        this.ref.getDownloadURL().subscribe(data => {
          console.log(data);
          this.select.image = data;

        });
      }
    });
    // this.uploadState = this.task.snapshotChanges().pipe(map(s => s.state));
    // this.uploadProgress = this.task.percentageChanges();
    this.downloadURL = this.ref.getDownloadURL();
  }
  dateAsYYYYMMDDHHNNSS(date): string {
    return (
      date.getFullYear() +
      "-" +
      this.leftpad(date.getMonth() + 1, 2) +
      "-" +
      this.leftpad(date.getDate(), 2) +
      "-" +
      this.leftpad(date.getHours(), 2) +
      "-" +
      this.leftpad(date.getMinutes(), 2) +
      "-" +
      this.leftpad(date.getSeconds(), 2)
    );
  }

  leftpad(val, resultLength = 2, leftpadChar = "0"): string {
    return (String(leftpadChar).repeat(resultLength) + String(val)).slice(
      String(val).length
    );
  }
}
