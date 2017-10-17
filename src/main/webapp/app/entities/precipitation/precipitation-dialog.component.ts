import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Precipitation } from './precipitation.model';
import { PrecipitationPopupService } from './precipitation-popup.service';
import { PrecipitationService } from './precipitation.service';

@Component({
    selector: 'jhi-precipitation-dialog',
    templateUrl: './precipitation-dialog.component.html'
})
export class PrecipitationDialogComponent implements OnInit {

    precipitation: Precipitation;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private precipitationService: PrecipitationService,
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
        if (this.precipitation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.precipitationService.update(this.precipitation));
        } else {
            this.subscribeToSaveResponse(
                this.precipitationService.create(this.precipitation));
        }
    }

    private subscribeToSaveResponse(result: Observable<Precipitation>) {
        result.subscribe((res: Precipitation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Precipitation) {
        this.eventManager.broadcast({ name: 'precipitationListModification', content: 'OK'});
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
    selector: 'jhi-precipitation-popup',
    template: ''
})
export class PrecipitationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private precipitationPopupService: PrecipitationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.precipitationPopupService
                    .open(PrecipitationDialogComponent as Component, params['id']);
            } else {
                this.precipitationPopupService
                    .open(PrecipitationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
