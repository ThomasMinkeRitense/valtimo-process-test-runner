import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import {TestrunDefinitionListComponent} from "./components/testrun-definition-list/testrun-definition-list.component";
import {
  TestrunInstanceListComponent
} from "./components/testrun-instance-list/testrun-instance-list.component";
import {
  TestrunDefinitionEditorComponent
} from "./components/testrun-definition-editor/testrun-definition-editor.component";

const routes: Routes = [
  {
    path: 'test-run/definition/:id/instances',
    component: TestrunInstanceListComponent,
    data: {title: 'Testrun definition'},
  },
  {
    path: 'test-run/definition/:id/editor',
    component: TestrunDefinitionEditorComponent,
    data: {title: 'Testrun definition'},
  },
  {
    path: 'test-run/definition',
    component: TestrunDefinitionListComponent,
    data: {title: 'Testrun definitions'},
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TestRunnerRoutingModule {
  constructor(private router: Router) {
    this.router.errorHandler = (error: any) => {
      console.log(error)
    };
  }
}
