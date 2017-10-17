import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WeatherRequirements } from './weather-requirements.model';
import { WeatherRequirementsPopupService } from './weather-requirements-popup.service';
import { WeatherRequirementsService } from './weather-requirements.service';
import { Precipitation, PrecipitationService } from '../precipitation';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-weather-requirements-dialog',
    templateUrl: './weather-requirements-dialog.component.html'
})
export class WeatherRequirementsDialogComponent implements OnInit {

    weatherRequirements: WeatherRequirements;
    isSaving: boolean;

    precipitationmins: Precipitation[];

    precipitationmaxes: Precipitation[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private weatherRequirementsService: WeatherRequirementsService,
        private precipitationService: PrecipitationService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.precipitationService
            .query({filter: 'weatherrequirements-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.weatherRequirements.precipitationMinId) {
                    this.precipitationmins = res.json;
                } else {
                    this.precipitationService
                        .find(this.weatherRequirements.precipitationMinId)
                        .subscribe((subRes: Precipitation) => {
                            this.precipitationmins = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.precipitationService
            .query({filter: 'weatherrequirements-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.weatherRequirements.precipitationMaxId) {
                    this.precipitationmaxes = res.json;
                } else {
                    this.precipitationService
                        .find(this.weatherRequirements.precipitationMaxId)
                        .subscribe((subRes: Precipitation) => {
                            this.precipitationmaxes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.weatherRequirements.id !== undefined) {
            this.subscribeToSaveResponse(
                this.weatherRequirementsService.update(this.weatherRequirements));
        } else {
            this.subscribeToSaveResponse(
                this.weatherRequirementsService.create(this.weatherRequirements));
        }
    }

    private subscribeToSaveResponse(result: Observable<WeatherRequirements>) {
        result.subscribe((res: WeatherRequirements) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: WeatherRequirements) {
        this.eventManager.broadcast({ name: 'weatherRequirementsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPrecipitationById(index: number, item: Precipitation) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-weather-requirements-popup',
    template: ''
})
export class WeatherRequirementsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private weatherRequirementsPopupService: WeatherRequirementsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.weatherRequirementsPopupService
                    .open(WeatherRequirementsDialogComponent as Component, params['id']);
            } else {
                this.weatherRequirementsPopupService
                    .open(WeatherRequirementsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
