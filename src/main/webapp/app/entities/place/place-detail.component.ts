import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Place } from './place.model';
import { PlaceService } from './place.service';
import {google} from "@agm/core/services/google-maps-types";
import {Http, Response} from "@angular/http";
import {SERVER_API_URL} from "../../app.constants";

@Component({
    selector: 'jhi-place-detail',
    templateUrl: './place-detail.component.html'
})
export class PlaceDetailComponent implements OnInit, OnDestroy {

    place: Place;
    lat: number = 0 ;
    lng: number = 0 ;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    private resourceUrl = SERVER_API_URL + 'api/locations';

    constructor(
        private eventManager: JhiEventManager,
        private placeService: PlaceService,
        private route: ActivatedRoute,
        private http: Http
    ) {
    }

    ngOnInit() {
    /*    function addMapsScript() {
            if (!document.querySelectorAll(`[src="${googleMapsUrl}"]`).length) {
                document.body.appendChild(Object.assign(
                    document.createElement('script'), {
                        type: 'text/javascript',
                        src: googleMapsUrl,
                        onload: () => doMapInitLogic()
                    }));
            } else {
                this.doMapInitLogic();
            }
        }*/

        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
            console.log("on arrie sur la requete !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            //lancemen tscip
            /*this.http.get(`${this.resourceUrl}/${this.place.locationId}`).map((value: Response, index: number) => {
                console.log("valeur de value :" + value + " valeur de l'index : " + index + "\n") ;
            });*/

                const point = {lat: -25.363, lng: 131.044};
                var test = document.getElementById( 'map');
                console.log(test) ;

                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 4,
                    center: point
                });
                var marker = new google.maps.Marker({
                    position: point,
                    map: map
                });
         });
        this.registerChangeInPlaces();
    }

    load(id) {
        this.placeService.find(id).subscribe((place) => {
            this.place = place;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPlaces() {
        this.eventSubscriber = this.eventManager.subscribe(
            'placeListModification',
            (response) => this.load(this.place.id)
        );
    }
}
