import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { WeatherRequirements } from './weather-requirements.model';
import { WeatherRequirementsService } from './weather-requirements.service';

@Component({
    selector: 'jhi-weather-requirements-detail',
    templateUrl: './weather-requirements-detail.component.html'
})
export class WeatherRequirementsDetailComponent implements OnInit, OnDestroy {

    weatherRequirements: WeatherRequirements;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private weatherRequirementsService: WeatherRequirementsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWeatherRequirements();
    }

    load(id) {
        this.weatherRequirementsService.find(id).subscribe((weatherRequirements) => {
            this.weatherRequirements = weatherRequirements;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWeatherRequirements() {
        this.eventSubscriber = this.eventManager.subscribe(
            'weatherRequirementsListModification',
            (response) => this.load(this.weatherRequirements.id)
        );
    }
}
