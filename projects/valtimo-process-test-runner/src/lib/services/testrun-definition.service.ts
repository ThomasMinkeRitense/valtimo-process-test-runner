import {Injectable} from "@angular/core";
import {BehaviorSubject, catchError, Observable, of, switchMap, take, tap} from "rxjs";
import {ConfigService} from "@valtimo/config";
import {HttpClient} from "@angular/common/http";
import {TestrunDefinition} from "../models";
import {TestrunInstance} from "../models/testrun-instance";

@Injectable({providedIn: 'root'})
export class TestrunDefinitionService {
  public readonly testrunDefinitions$ = new BehaviorSubject<TestrunDefinition[]>([]);

  public readonly loading$ = new BehaviorSubject<boolean>(false);

  private valtimoEndpointUri: string;

  private get getDefinitions(): Observable<TestrunDefinition[]> {
    return this.http.get<TestrunDefinition[]>(`${this.valtimoEndpointUri}management/v1/test-run/definition`);
  }

  public getDefinition(id: string): Observable<Array<object>> {
    return this.http.get<Array<object>>(
      `${this.valtimoEndpointUri}management/v1/test-run/definition/${id}`
    );
  }

  public updateDefinition(updatedDefinition: object): Observable<object> {
    return this.http.put<object>(
      `${this.valtimoEndpointUri}management/v1/test-run/definition`,
      updatedDefinition
    );
  }

  constructor(
    private readonly configService: ConfigService,
    private readonly http: HttpClient
  ) {
    this.valtimoEndpointUri = `${this.configService.config.valtimoApi.endpointUri}`;
  }

  public loadDefinitions(): void {
    this.getDefinitions
      .pipe(
        tap(() => {
          this.loading$.next(true);
        }),
        take(1)
      )
      .subscribe({
        next: (items: TestrunDefinition[]) => {
          this.testrunDefinitions$.next(items);
          this.loading$.next(false);
        },
        error: error => {
          console.error(error);
        },
      });
  }

  public addDefinition(role: TestrunDefinition): Observable<TestrunDefinition> {
    return this.http.post<TestrunDefinition>(`${this.valtimoEndpointUri}management/v1/test-run/definition`, role);
  }

  public dispatchAction(actionResult: Observable<TestrunDefinition | null>): void {
    actionResult
      .pipe(
        tap(() => {
          this.loading$.next(true);
        }),
        switchMap(() => this.getDefinitions),
        take(1),
        catchError(error => of(error))
      )
      .subscribe({
        next: (roles: TestrunDefinition[]) => {
          this.testrunDefinitions$.next(roles);
          this.loading$.next(false);
        },
        error: error => {
          console.error(error);
        },
      });
  }

  public startInstance(definition: TestrunDefinition): Observable<TestrunInstance> {
    console.log('executing POST request to start an instance')
    return this.http.post<TestrunInstance>(`${this.valtimoEndpointUri}management/v1/test-run/definition/${definition.id}/start`, "");
  }
}
