import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameShopComponent } from './game-shop.component';

describe('GameShopComponent', () => {
  let component: GameShopComponent;
  let fixture: ComponentFixture<GameShopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameShopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
