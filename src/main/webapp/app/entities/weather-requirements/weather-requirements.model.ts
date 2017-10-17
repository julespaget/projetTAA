import { BaseEntity } from './../../shared';

export class WeatherRequirements implements BaseEntity {
    constructor(
        public id?: number,
        public temperatureMin?: number,
        public temperatureMax?: number,
        public windSpeedMin?: number,
        public windSpeedMax?: number,
        public windAngleMin?: number,
        public windAngleMax?: number,
        public waveHeightMin?: number,
        public waveHeightMax?: number,
        public precipitationMinId?: number,
        public precipitationMaxId?: number,
    ) {
    }
}
