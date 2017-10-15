import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Sport } from './sport.model';
import { SportPopupService } from './sport-popup.service';
import { SportService } from './sport.service';

@Component({
    selector: 'jhi-sport-delete-dialog',
    templateUrl: './sport-delete-dialog.component.html'
})
export class SportDeleteDialogComponent {

    sport: Sport;

    constructor(
        private sportService: SportService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sportService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'sportListModification',
                content: 'Deleted an sport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sport-delete-popup',
    template: ''
})
export class SportDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sportPopupService: SportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.sportPopupService
                .open(SportDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
