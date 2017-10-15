import { BaseEntity } from './../../shared';

export class Weather implements BaseEntity {
    constructor(
        public id?: number,
        public temperature?: number,
        public windSpeed?: number,
        public rain?: boolean,
        public waveHeight?: number,
    ) {
        this.rain = false;
    }
}
