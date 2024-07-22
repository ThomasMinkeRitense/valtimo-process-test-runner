/*
 * Copyright 2015-2024 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {ChangeDetectionStrategy, Component, OnDestroy, OnInit} from '@angular/core';
import {AccessControlService} from '@valtimo/access-control-management';
import {BehaviorSubject, switchMap, take, tap} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {
  EditorModel,
  PageHeaderService,
  PageTitleService,
} from '@valtimo/components';
import {NotificationService} from 'carbon-components-angular';
import {TranslateService} from '@ngx-translate/core';
import {TestrunDefinitionService} from "../../services";

@Component({
  templateUrl: './testrun-definition-editor.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./testrun-definition-editor.component.scss'],
  providers: [NotificationService],
})
export class TestrunDefinitionEditorComponent implements OnInit, OnDestroy {
  public readonly model$ = new BehaviorSubject<EditorModel | null>(null);
  public readonly saveDisabled$ = new BehaviorSubject<boolean>(true);
  public readonly editorDisabled$ = new BehaviorSubject<boolean>(false);
  public readonly moreDisabled$ = new BehaviorSubject<boolean>(true);
  public readonly showDeleteModal$ = new BehaviorSubject<boolean>(false);
  public readonly showEditModal$ = new BehaviorSubject<boolean>(false);
  public readonly selectedRowKeys$ = new BehaviorSubject<Array<string> | null>(null);
  public readonly compactMode$ = this.pageHeaderService.compactMode$;

  private _roleKey!: string;
  private readonly _updatedModelValue$ = new BehaviorSubject<string>('');

  constructor(
    private readonly accessControlService: AccessControlService,
    private readonly route: ActivatedRoute,
    private readonly pageTitleService: PageTitleService,
    private readonly router: Router,
    private readonly notificationService: NotificationService,
    private readonly translateService: TranslateService,
    private readonly pageHeaderService: PageHeaderService,
    private readonly testrunDefinitionService: TestrunDefinitionService
  ) {}

  public ngOnInit(): void {
    this.getDefinition();
  }

  public ngOnDestroy(): void {
    this.pageTitleService.enableReset();
  }

  public onValid(valid: boolean): void {
    this.saveDisabled$.next(valid === false);
  }

  public onValueChange(value: string): void {
    this._updatedModelValue$.next(value);
  }

  public updateDefinition(): void {
    this.disableEditor();
    this.disableSave();
    this.disableMore();

    this._updatedModelValue$
      .pipe(
        take(1),
        switchMap(updatedModelValue =>
          this.testrunDefinitionService.updateDefinition(
            JSON.parse(updatedModelValue)
          )
        )
      )
      .subscribe({
        next: result => {
          this.enableMore();
          this.enableSave();
          this.enableEditor();
          this.showSuccessMessage(this._roleKey);
          this.setModel(result);
        },
        error: () => {
          this.enableMore();
          this.enableSave();
          this.enableEditor();
        },
      });
  }

  private getDefinition(): void {
    this.route.params
      .pipe(
        tap(params => {
          this.pageTitleService.setCustomPageTitle(params?.id);
          this.selectedRowKeys$.next([params?.id]);
        }),
        switchMap(params => this.testrunDefinitionService.getDefinition(params?.id))
      )
      .subscribe(definition => {
        this.enableMore();
        this.enableSave();
        this.enableEditor();
        this.setModel(definition);
      });
  }

  private setModel(definition: object): void {
    this.model$.next({
      value: JSON.stringify(definition),
      language: 'json',
    });
  }

  private disableMore(): void {
    this.moreDisabled$.next(true);
  }

  private enableMore(): void {
    this.moreDisabled$.next(false);
  }

  private disableSave(): void {
    this.saveDisabled$.next(true);
  }

  private enableSave(): void {
    this.saveDisabled$.next(false);
  }

  private disableEditor(): void {
    this.editorDisabled$.next(true);
  }

  private enableEditor(): void {
    this.editorDisabled$.next(false);
  }

  private showSuccessMessage(id: string): void {
    this.notificationService.showToast({
      caption: this.translateService.instant('accessControl.roles.savedSuccessTitleMessage', {
        id,
      }),
      type: 'success',
      duration: 4000,
      showClose: true,
      title: this.translateService.instant('accessControl.roles.savedSuccessTitle'),
    });
  }
}
