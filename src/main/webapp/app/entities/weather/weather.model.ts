import { BaseEntity } from './../../shared';

export class Weather implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public temperature?: number,
        public windSpeed?: number,
        public windAngle?: number,
        public waveHeight?: number,
        public clouds?: number,
        public pressure?: number,
        public humidity?: number,
        public precipitationId?: number,
    ) {
    }
}
