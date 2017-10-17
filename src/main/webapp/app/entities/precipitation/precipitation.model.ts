import { BaseEntity } from './../../shared';

export const enum PrecipitationType {
    'RAIN',
    'SNOW'
}

export class Precipitation implements BaseEntity {
    constructor(
        public id?: number,
        public type?: PrecipitationType,
        public value?: number,
    ) {
    }
}
