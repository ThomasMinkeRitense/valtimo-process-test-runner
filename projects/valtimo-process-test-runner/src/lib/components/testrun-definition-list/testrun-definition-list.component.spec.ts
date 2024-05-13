import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestrunDefinitionListComponent } from './testrun-definition-list.component';

describe('TestrunDefinitionListComponent', () => {
  let component: TestrunDefinitionListComponent;
  let fixture: ComponentFixture<TestrunDefinitionListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestrunDefinitionListComponent]
    });
    fixture = TestBed.createComponent(TestrunDefinitionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
