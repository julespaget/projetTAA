import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WeekandgoSharedModule } from '../../shared';
import {
    PrecipitationService,
    PrecipitationPopupService,
    PrecipitationComponent,
    PrecipitationDetailComponent,
    PrecipitationDialogComponent,
    PrecipitationPopupComponent,
    PrecipitationDeletePopupComponent,
    PrecipitationDeleteDialogComponent,
    precipitationRoute,
    precipitationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...precipitationRoute,
    ...precipitationPopupRoute,
];

@NgModule({
    imports: [
        WeekandgoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PrecipitationComponent,
        PrecipitationDetailComponent,
        PrecipitationDialogComponent,
        PrecipitationDeleteDialogComponent,
        PrecipitationPopupComponent,
        PrecipitationDeletePopupComponent,
    ],
    entryComponents: [
        PrecipitationComponent,
        PrecipitationDialogComponent,
        PrecipitationPopupComponent,
        PrecipitationDeleteDialogComponent,
        PrecipitationDeletePopupComponent,
    ],
    providers: [
        PrecipitationService,
        PrecipitationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoPrecipitationModule {}
