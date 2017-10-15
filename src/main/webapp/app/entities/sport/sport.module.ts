import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WeekandgoSharedModule } from '../../shared';
import {
    SportService,
    SportPopupService,
    SportComponent,
    SportDetailComponent,
    SportDialogComponent,
    SportPopupComponent,
    SportDeletePopupComponent,
    SportDeleteDialogComponent,
    sportRoute,
    sportPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sportRoute,
    ...sportPopupRoute,
];

@NgModule({
    imports: [
        WeekandgoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SportComponent,
        SportDetailComponent,
        SportDialogComponent,
        SportDeleteDialogComponent,
        SportPopupComponent,
        SportDeletePopupComponent,
    ],
    entryComponents: [
        SportComponent,
        SportDialogComponent,
        SportPopupComponent,
        SportDeleteDialogComponent,
        SportDeletePopupComponent,
    ],
    providers: [
        SportService,
        SportPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoSportModule {}
