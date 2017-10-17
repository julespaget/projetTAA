/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { WeekandgoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PrecipitationDetailComponent } from '../../../../../../main/webapp/app/entities/precipitation/precipitation-detail.component';
import { PrecipitationService } from '../../../../../../main/webapp/app/entities/precipitation/precipitation.service';
import { Precipitation } from '../../../../../../main/webapp/app/entities/precipitation/precipitation.model';

describe('Component Tests', () => {

    describe('Precipitation Management Detail Component', () => {
        let comp: PrecipitationDetailComponent;
        let fixture: ComponentFixture<PrecipitationDetailComponent>;
        let service: PrecipitationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [WeekandgoTestModule],
                declarations: [PrecipitationDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PrecipitationService,
                    JhiEventManager
                ]
            }).overrideTemplate(PrecipitationDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PrecipitationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PrecipitationService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Precipitation(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.precipitation).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
