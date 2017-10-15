import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WeatherRequirements } from './weather-requirements.model';
import { WeatherRequirementsPopupService } from './weather-requirements-popup.service';
import { WeatherRequirementsService } from './weather-requirements.service';

@Component({
    selector: 'jhi-weather-requirements-delete-dialog',
    templateUrl: './weather-requirements-delete-dialog.component.html'
})
export class WeatherRequirementsDeleteDialogComponent {

    weatherRequirements: WeatherRequirements;

    constructor(
        private weatherRequirementsService: WeatherRequirementsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.weatherRequirementsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'weatherRequirementsListModification',
                content: 'Deleted an weatherRequirements'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-weather-requirements-delete-popup',
    template: ''
})
export class WeatherRequirementsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private weatherRequirementsPopupService: WeatherRequirementsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.weatherRequirementsPopupService
                .open(WeatherRequirementsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
