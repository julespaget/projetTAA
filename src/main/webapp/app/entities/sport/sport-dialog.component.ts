import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Sport } from './sport.model';
import { SportPopupService } from './sport-popup.service';
import { SportService } from './sport.service';
import { WeatherRequirements, WeatherRequirementsService } from '../weather-requirements';
import { Place, PlaceService } from '../place';
import { Person, PersonService } from '../person';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-sport-dialog',
    templateUrl: './sport-dialog.component.html'
})
export class SportDialogComponent implements OnInit {

    sport: Sport;
    isSaving: boolean;

    weatherrequirements: WeatherRequirements[];

    places: Place[];

    people: Person[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private sportService: SportService,
        private weatherRequirementsService: WeatherRequirementsService,
        private placeService: PlaceService,
        private personService: PersonService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.weatherRequirementsService.query()
            .subscribe((res: ResponseWrapper) => { this.weatherrequirements = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.placeService.query()
            .subscribe((res: ResponseWrapper) => { this.places = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.personService.query()
            .subscribe((res: ResponseWrapper) => { this.people = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sport.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sportService.update(this.sport));
        } else {
            this.subscribeToSaveResponse(
                this.sportService.create(this.sport));
        }
    }

    private subscribeToSaveResponse(result: Observable<Sport>) {
        result.subscribe((res: Sport) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Sport) {
        this.eventManager.broadcast({ name: 'sportListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackWeatherRequirementsById(index: number, item: WeatherRequirements) {
        return item.id;
    }

    trackPlaceById(index: number, item: Place) {
        return item.id;
    }

    trackPersonById(index: number, item: Person) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-sport-popup',
    template: ''
})
export class SportPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private sportPopupService: SportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.sportPopupService
                    .open(SportDialogComponent as Component, params['id']);
            } else {
                this.sportPopupService
                    .open(SportDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
