import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AgmCoreModule} from '@agm/core';

import { WeekandgoSharedModule } from '../../../shared';
import {
    PlaceComponent,
    PlaceDetailComponent,
    PlaceDialogComponent,
    PlacePopupComponent,
    PlaceDeletePopupComponent,
    PlaceDeleteDialogComponent,
    placeRoute,
    placePopupRoute,
} from '../';
import { ApiMapsComponent } from './api-maps.component';
import {BrowserModule} from "@angular/platform-browser";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {LocationService} from "../../location/location.service";
import {PlaceService} from "../place.service";

const ENTITY_STATES = [
    ...placeRoute,
    ...placePopupRoute,
];

@NgModule({
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyANdkV0x6IJoenkkdr7yx6kyj0Flahh2O4'
        }),
        WeekandgoSharedModule,
        LocationService,
        PlaceService,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PlaceComponent,
        PlaceDetailComponent,
        PlaceDialogComponent,
        PlaceDeleteDialogComponent,
        PlacePopupComponent,
        PlaceDeletePopupComponent,
        ApiMapsComponent,
    ],
    entryComponents: [
        ApiMapsComponent,
    ],
    providers: [LocationService, PlaceService],
    exports: [
        ApiMapsComponent,
    ],


    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoApiMapsModule{}
