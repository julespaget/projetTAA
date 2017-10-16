import {NgModule, CUSTOM_ELEMENTS_SCHEMA, Component} from '@angular/core';
import { BrowserModule} from "@angular/platform-browser";
import { RouterModule } from '@angular/router';
import {AgmCoreModule} from '@agm/core';


import { WeekandgoSharedModule } from '../../shared';
import {
    LocationService,
    LocationPopupService,
    LocationComponent,
    LocationDetailComponent,
    LocationDialogComponent,
    LocationPopupComponent,
    LocationDeletePopupComponent,
    LocationDeleteDialogComponent,
    locationRoute,
    locationPopupRoute,
} from './';


const ENTITY_STATES = [
    ...locationRoute,
    ...locationPopupRoute,
];

@NgModule({
    imports: [
        WeekandgoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true }),
        BrowserModule,
        this.AgmCoreModule.forRoot({
            apiKey: 'AIzaSyCMDOyjSw6bSpW3VHj0IJghXZEnUQy1Y40'
        })

],
    declarations: [
        LocationComponent,
        LocationDetailComponent,
        LocationDialogComponent,
        LocationDeleteDialogComponent,
        LocationPopupComponent,
        LocationDeletePopupComponent,
    ],
    entryComponents: [
        LocationComponent,
        LocationDialogComponent,
        LocationPopupComponent,
        LocationDeleteDialogComponent,
        LocationDeletePopupComponent,
    ],
    providers: [
        LocationService,
        LocationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoLocationModule {}

