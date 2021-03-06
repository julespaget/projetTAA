import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WeekandgoSharedModule } from '../../shared';
import {
    PlaceService,
    PlacePopupService,
    PlaceComponent,
    PlaceDetailComponent,
    PlaceDialogComponent,
    PlacePopupComponent,
    PlaceDeletePopupComponent,
    PlaceDeleteDialogComponent,
    placeRoute,
    placePopupRoute,
} from './';
import { ApiMapsComponent } from './api-maps/api-maps.component';

const ENTITY_STATES = [
    ...placeRoute,
    ...placePopupRoute,
];

@NgModule({
    imports: [
        WeekandgoSharedModule,
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
        PlaceComponent,
        PlaceDialogComponent,
        PlacePopupComponent,
        PlaceDeleteDialogComponent,
        PlaceDeletePopupComponent,
    ],
    providers: [
        PlaceService,
        PlacePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoPlaceModule {}
