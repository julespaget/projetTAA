import { BaseEntity } from './../../shared';

export class Place implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public locationId?: number,
        public weatherId?: number,
        public sportLists?: BaseEntity[],
    ) {
    }
}
