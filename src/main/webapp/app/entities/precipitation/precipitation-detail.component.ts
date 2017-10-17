import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Precipitation } from './precipitation.model';
import { PrecipitationService } from './precipitation.service';

@Component({
    selector: 'jhi-precipitation-detail',
    templateUrl: './precipitation-detail.component.html'
})
export class PrecipitationDetailComponent implements OnInit, OnDestroy {

    precipitation: Precipitation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private precipitationService: PrecipitationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPrecipitations();
    }

    load(id) {
        this.precipitationService.find(id).subscribe((precipitation) => {
            this.precipitation = precipitation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPrecipitations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'precipitationListModification',
            (response) => this.load(this.precipitation.id)
        );
    }
}
