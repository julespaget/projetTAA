import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { WeekandgoLocationModule } from './location/location.module';
import { WeekandgoSportModule } from './sport/sport.module';
import { WeekandgoPlaceModule } from './place/place.module';
import { WeekandgoPersonModule } from './person/person.module';
import { WeekandgoWeatherModule } from './weather/weather.module';
import { WeekandgoWeatherRequirementsModule } from './weather-requirements/weather-requirements.module';
import { WeekandgoPrecipitationModule } from './precipitation/precipitation.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        WeekandgoLocationModule,
        WeekandgoSportModule,
        WeekandgoPlaceModule,
        WeekandgoPersonModule,
        WeekandgoWeatherModule,
        WeekandgoWeatherRequirementsModule,
        WeekandgoPrecipitationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WeekandgoEntityModule {}
