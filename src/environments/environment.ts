// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
import {NgxLoggerLevel} from 'ngx-logger';
import {ROLE_ADMIN, ROLE_DEVELOPER, ROLE_USER, ValtimoConfig, UploadProvider, IncludeFunction} from '@valtimo/config';
import {authenticationKeycloak} from './auth/keycloak-config';
import {defaultDefinitionColumns} from './columns';
import {LOGO_BASE_64} from './logo';

export const environment: ValtimoConfig = {
  logoSvgBase64: LOGO_BASE_64,
  production: false,
  authentication: authenticationKeycloak,
  menu: {
    menuItems: [
      {roles: [ROLE_USER], link: ['/'], title: 'Dashboard', iconClass: 'icon mdi mdi-view-dashboard', sequence: 0},
      {roles: [ROLE_USER], title: 'Dossiers', iconClass: 'icon mdi mdi-layers', sequence: 1, children: []},
      {roles: [ROLE_USER], link: ['/tasks'], title: 'Tasks', iconClass: 'icon mdi mdi-check-all', sequence: 3},
      {roles: [ROLE_USER], link: ['/analysis'], title: 'Analysis', iconClass: 'icon mdi mdi-chart-bar', sequence: 4},
      {
        roles: [ROLE_ADMIN], title: 'Admin', iconClass: 'icon mdi mdi-tune', sequence: 5, children: [
          {title: 'Basics', textClass: 'text-dark font-weight-bold c-default', sequence: 1},
          {link: ['/processes'], title: 'Processes', sequence: 2},
          {link: ['/form-management'], title: 'Forms', sequence: 3},
          {link: ['/form-flow-management'], title: 'Form flows', sequence: 4},
          {link: ['/decision-tables'], title: 'Decision tables', sequence: 5},
          {link: ['/dossier-management'], title: 'Dossiers', sequence: 6},
          {link: ['/task-management'], title: 'Tasks', sequence: 7},
          {link: ['/object-management'], title: 'Objects', sequence: 8},
          {link: ['/plugins'], title: 'Plugins', sequence: 9},
          {link: ['/process-links'], title: 'Process links', sequence: 10},
          {link: ['/dashboard-management'], title: 'Dashboard', sequence: 11},
          {link: ['/access-control'], title: 'Access Control', sequence: 12},
          {link: ['/translation-management'], title: 'Translations', sequence: 13},
          {title: 'Other', textClass: 'text-dark font-weight-bold c-default', sequence: 14},
          {link: ['/case-migration'], title: 'Case migration (beta)', sequence: 15},
          {link: ['/process-migration'], title: 'Process migration', sequence: 16},
          {link: ['/choice-fields'], title: 'Choice fields', sequence: 17},
        ]
      },
      {
        roles: [ROLE_DEVELOPER, ROLE_ADMIN], title: 'Development', iconClass: 'icon mdi mdi-code', sequence: 6, children: [
          {link: ['/swagger'], title: 'Swagger', iconClass: 'icon mdi mdi-dot-circle', sequence: 1},
          {link: ['/test-run/definition'], title: 'Test runner', iconClass: 'icon mdi mdi-dot-circle', sequence: 2}
        ]
      }
    ]
  },
  whitelistedDomains: ['localhost:4200'],
  mockApi: {
      endpointUri: window['env']['mockApiUri'] || '/mock-api/'
  },
  valtimoApi: {
      endpointUri: window['env']['apiUri'] || '/api/'
  },
  swagger: {
      endpointUri: window['env']['swaggerUri'] || '/v3/api-docs'
  },
  logger: {
    level: NgxLoggerLevel.TRACE
  },
  definitions: {
    dossiers: []
  },
  openZaak: {
    catalogus: window['env']['openZaakCatalogusId'] || '8225508a-6840-413e-acc9-6422af120db1'
  },
  uploadProvider: UploadProvider.DOCUMENTEN_API,
  defaultDefinitionTable: defaultDefinitionColumns,
  customDefinitionTables: {
    leningen: [
      ...defaultDefinitionColumns,
      {propertyName: '$.voornaam', translationKey: 'firstName', sortable: true},
      {propertyName: 'relatedFiles', translationKey: 'files', sortable: true, viewType: 'relatedFiles'}
    ]
  },
  featureToggles: {
    showUserNameInTopBar: true,
    disableCaseCount: false,
    experimentalDmnEditing: true,
    sortFilesByDate: true,
    showPlantATreeButton: true,
    returnToLastUrlAfterTokenExpiration: true,
    allowUserThemeSwitching: true,
    enableCompactModeToggle: true,
    enableUserNameInTopBarToggle: true,
    enableObjectManagement: false
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
