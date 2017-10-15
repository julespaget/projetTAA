/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { WeekandgoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SportDetailComponent } from '../../../../../../main/webapp/app/entities/sport/sport-detail.component';
import { SportService } from '../../../../../../main/webapp/app/entities/sport/sport.service';
import { Sport } from '../../../../../../main/webapp/app/entities/sport/sport.model';

describe('Component Tests', () => {

    describe('Sport Management Detail Component', () => {
        let comp: SportDetailComponent;
        let fixture: ComponentFixture<SportDetailComponent>;
        let service: SportService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [WeekandgoTestModule],
                declarations: [SportDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SportService,
                    JhiEventManager
                ]
            }).overrideTemplate(SportDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SportDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SportService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Sport(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sport).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
