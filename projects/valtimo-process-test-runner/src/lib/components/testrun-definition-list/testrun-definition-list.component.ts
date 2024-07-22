import {Component, OnInit} from '@angular/core';
import {ActionItem, ColumnConfig, ViewType} from "@valtimo/components";
import {BehaviorSubject, finalize, Observable} from "rxjs";
import {TestrunDefinitionService} from "../../services";
import {TestrunDefinition} from "../../models";
import {Router} from "@angular/router";

@Component({
  selector: 'lib-testrun-definition-list',
  templateUrl: './testrun-definition-list.component.html'
})
export class TestrunDefinitionListComponent implements OnInit {
  public fields: ColumnConfig[] = [
    {
      viewType: ViewType.TEXT,
      key: 'title',
      label: 'Title',
    },
  ];

  public actionItems: ActionItem[] = [
    {
      label: 'Instances',
      callback: this.navigateToInstances.bind(this),
      type: 'normal',
    },{
      label: 'Start instance',
      callback: this.startInstance.bind(this),
      type: 'normal',
    },
  ];

  public readonly loading$: Observable<boolean> = this.testrunDefinitionService.loading$;

  public readonly showAddModal$ = new BehaviorSubject<boolean>(false);

  constructor(
    private readonly testrunDefinitionService: TestrunDefinitionService,
    private readonly router: Router
  ) {
  }

  public ngOnInit(): void {
    this.testrunDefinitionService.loadDefinitions();
  }

  public openAddModal(): void {
    this.showAddModal$.next(true);
  }

  public onAdd(data: TestrunDefinition | null): void {
    this.showAddModal$.next(false);

    if (!data) {
      return;
    }

    this.testrunDefinitionService.dispatchAction(
      this.testrunDefinitionService.addDefinition(data).pipe(
        finalize(() => {
          this.showAddModal$.next(false);
        })
      )
    );
  }

  public startInstance(definition: TestrunDefinition): void {
    console.log('Starting instance for definition ' + definition.id)
    this.testrunDefinitionService.startInstance(definition).pipe(finalize(() => {
      console.log('Started instance')
    })).subscribe({
      next: (result: any) => {
        this.navigateToInstances(definition)
      },
      error: error => {
        console.error(error);
      },
    });
  }

  public navigateToInstances(definition: TestrunDefinition): void {
    this.router.navigate([`/test-run/definition/${definition.id}/instances`]);
  }

  public onRowClick(definition: TestrunDefinition): void {
    this.router.navigate([`/test-run/definition/${definition.id}/editor`]);
  }

  public readonly testrunDefinitions: Observable<TestrunDefinition[]> = this.testrunDefinitionService.testrunDefinitions$;
}
