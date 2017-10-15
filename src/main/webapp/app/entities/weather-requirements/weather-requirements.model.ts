import { BaseEntity } from './../../shared';

export const enum Ternary {
    'YES',
    'NO',
    'IRRELEVANT'
}

export class WeatherRequirements implements BaseEntity {
    constructor(
        public id?: number,
        public temperatureMin?: number,
        public temperatureMax?: number,
        public windSpeedMin?: number,
        public windSpeedMax?: number,
        public rain?: Ternary,
    ) {
    }
}
