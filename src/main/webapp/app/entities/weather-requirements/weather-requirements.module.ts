import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WeekandgoSharedModule } from '../../shared';
import {
    WeatherRequirementsService,
    WeatherRequirementsPopupService,
    WeatherRequirementsComponent,
    WeatherRequirementsDetailComponent,
    WeatherRequirementsDialogComponent,
    WeatherRequirementsPopupComponent,
    WeatherRequirementsDeletePopupComponent,
    WeatherRequirementsDeleteDialogComponent,
    weatherRequirementsRoute,
    weatherRequirementsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...weatherRequirementsRoute,
    ...weatherRequirementsPopupRoute,
];

@NgModule({
    imports: [
        WeekandgoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        WeatherRequirementsComponent,
        WeatherRequirementsDetailComponent,
        WeatherRequirementsDialogComponent,
        WeatherRequirementsDeleteDialogComponent,
        WeatherRequirementsPopupComponent,
        WeatherRequirementsDeletePopupComponent,
    ],
    entryComponents: [
        WeatherRequirementsComponent,
        WeatherRequirementsDialogComponent,
        WeatherRequirementsPopupComponent,
        WeatherRequirementsDeleteDialogComponent,
        WeatherRequirementsDeletePopupComponent,
    ],
    providers: [
        WeatherRequirementsService,
        WeatherRequirementsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoWeatherRequirementsModule {}
