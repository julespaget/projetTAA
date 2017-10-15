import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WeatherRequirements } from './weather-requirements.model';
import { WeatherRequirementsPopupService } from './weather-requirements-popup.service';
import { WeatherRequirementsService } from './weather-requirements.service';

@Component({
    selector: 'jhi-weather-requirements-dialog',
    templateUrl: './weather-requirements-dialog.component.html'
})
export class WeatherRequirementsDialogComponent implements OnInit {

    weatherRequirements: WeatherRequirements;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private weatherRequirementsService: WeatherRequirementsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
