import { NgModule } from '@angular/core';
import { TestRunnerComponent } from './test-runner.component';
import { TestrunDefinitionListComponent } from './components/testrun-definition-list/testrun-definition-list.component';
import { TestRunnerRoutingModule } from './test-runner-routing.module';
import {AsyncPipe, CommonModule} from "@angular/common";
import {TranslateModule} from "@ngx-translate/core";
import {
  CarbonListModule,
  ConfirmationModalModule,
  EditorModule,
  RenderInPageHeaderDirectiveModule
} from "@valtimo/components";
import {
  TestrunDefinitionMetadataModal
} from "./components/testrun-definition-metadata-modal/testrun-definition-metadata-modal";
import {
  ButtonModule,
  DialogModule,
  IconModule,
  InputModule,
  LoadingModule,
  ModalModule
} from "carbon-components-angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {
  TestrunInstanceListComponent
} from "./components/testrun-instance-list/testrun-instance-list.component";
import {TestrunDefinitionEditorComponent} from "./components/testrun-definition-editor/testrun-definition-editor.component";

@NgModule({
  declarations: [
    TestRunnerComponent,
    TestrunDefinitionListComponent,
    TestrunDefinitionMetadataModal,
    TestrunInstanceListComponent,
    TestrunDefinitionEditorComponent,
  ],
  imports: [
    TestRunnerRoutingModule,
    AsyncPipe,
    TranslateModule,
    CarbonListModule,
    CommonModule,
    EditorModule,
    ModalModule,
    IconModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    InputModule,
    RenderInPageHeaderDirectiveModule,
    ConfirmationModalModule,
    LoadingModule,
    DialogModule,
  ]
})
export class TestRunnerModule { }
