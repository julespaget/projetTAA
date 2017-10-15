import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { WeatherRequirements } from './weather-requirements.model';
import { WeatherRequirementsService } from './weather-requirements.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-weather-requirements',
    templateUrl: './weather-requirements.component.html'
})
export class WeatherRequirementsComponent implements OnInit, OnDestroy {
weatherRequirements: WeatherRequirements[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private weatherRequirementsService: WeatherRequirementsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.weatherRequirementsService.query().subscribe(
            (res: ResponseWrapper) => {
                this.weatherRequirements = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWeatherRequirements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WeatherRequirements) {
        return item.id;
    }
    registerChangeInWeatherRequirements() {
        this.eventSubscriber = this.eventManager.subscribe('weatherRequirementsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
