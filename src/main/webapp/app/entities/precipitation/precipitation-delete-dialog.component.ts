import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Precipitation } from './precipitation.model';
import { PrecipitationPopupService } from './precipitation-popup.service';
import { PrecipitationService } from './precipitation.service';

@Component({
    selector: 'jhi-precipitation-delete-dialog',
    templateUrl: './precipitation-delete-dialog.component.html'
})
export class PrecipitationDeleteDialogComponent {

    precipitation: Precipitation;

    constructor(
        private precipitationService: PrecipitationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.precipitationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'precipitationListModification',
                content: 'Deleted an precipitation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-precipitation-delete-popup',
    template: ''
})
export class PrecipitationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private precipitationPopupService: PrecipitationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.precipitationPopupService
                .open(PrecipitationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
