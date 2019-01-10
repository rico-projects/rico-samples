import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';

import { RicoAngularModule } from 'rico-angular';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RicoAngularModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
