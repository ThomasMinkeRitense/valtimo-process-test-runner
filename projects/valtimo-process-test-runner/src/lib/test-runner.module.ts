import { NgModule } from '@angular/core';
import { TestRunnerComponent } from './test-runner.component';
import { TestrunDefinitionListComponent } from './components/testrun-definition-list/testrun-definition-list.component';
import { TestRunnerRoutingModule } from './test-runner-routing.module';

@NgModule({
  declarations: [
    TestRunnerComponent,
    TestrunDefinitionListComponent
  ],
  imports: [
    TestRunnerRoutingModule
  ],
  exports: [
    TestRunnerComponent
  ]
})
export class TestRunnerModule { }
