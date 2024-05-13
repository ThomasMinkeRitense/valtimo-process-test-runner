import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TestRunnerModule} from "../../projects/valtimo-process-test-runner/src/lib/test-runner.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TestRunnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
