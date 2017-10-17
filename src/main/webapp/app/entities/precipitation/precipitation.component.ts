import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { Precipitation } from './precipitation.model';
import { PrecipitationService } from './precipitation.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-precipitation',
    templateUrl: './precipitation.component.html'
})
export class PrecipitationComponent implements OnInit, OnDestroy {
precipitations: Precipitation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private precipitationService: PrecipitationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.precipitationService.query().subscribe(
            (res: ResponseWrapper) => {
                this.precipitations = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInPrecipitations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Precipitation) {
        return item.id;
    }
    registerChangeInPrecipitations() {
        this.eventSubscriber = this.eventManager.subscribe('precipitationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
