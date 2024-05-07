import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TestrunDefinitionListComponent} from "./components/testrun-definition-list/testrun-definition-list.component";

const routes: Routes = [
  {
    path: 'x',
    component: TestrunDefinitionListComponent,
    data: {},
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TestRunnerRoutingModule { }
