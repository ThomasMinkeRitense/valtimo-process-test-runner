import {Injectable} from "@angular/core";
import {BehaviorSubject, catchError, Observable, of, switchMap, take, tap} from "rxjs";
import {ConfigService} from "@valtimo/config";
import {HttpClient} from "@angular/common/http";
import {TestrunDefinition} from "../models";
import {TestrunInstance} from "../models/testrun-instance";

@Injectable({providedIn: 'root'})
export class TestrunInstanceService {
  public readonly testrunInstances$ = new BehaviorSubject<TestrunInstance[]>([]);

  public readonly loading$ = new BehaviorSubject<boolean>(false);

  private valtimoEndpointUri: string;

  constructor(
    private readonly configService: ConfigService,
    private readonly http: HttpClient
  ) {
    this.valtimoEndpointUri = `${this.configService.config.valtimoApi.endpointUri}`;
  }

  public getInstancesForDefinition(definitionId: string): Observable<Array<object>> {
    console.log('Get instances for definition ' + definitionId)
    return this.http.get<Array<object>>(
      `${this.valtimoEndpointUri}management/v1/test-run/definition/${definitionId}/instance`
    );
  }
}
