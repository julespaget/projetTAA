/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { WeekandgoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { WeatherRequirementsDetailComponent } from '../../../../../../main/webapp/app/entities/weather-requirements/weather-requirements-detail.component';
import { WeatherRequirementsService } from '../../../../../../main/webapp/app/entities/weather-requirements/weather-requirements.service';
import { WeatherRequirements } from '../../../../../../main/webapp/app/entities/weather-requirements/weather-requirements.model';

describe('Component Tests', () => {

    describe('WeatherRequirements Management Detail Component', () => {
        let comp: WeatherRequirementsDetailComponent;
        let fixture: ComponentFixture<WeatherRequirementsDetailComponent>;
        let service: WeatherRequirementsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [WeekandgoTestModule],
                declarations: [WeatherRequirementsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    WeatherRequirementsService,
                    JhiEventManager
                ]
            }).overrideTemplate(WeatherRequirementsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WeatherRequirementsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WeatherRequirementsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new WeatherRequirements(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.weatherRequirements).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
