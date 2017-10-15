import { BaseEntity } from './../../shared';

export class Place implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public emplacement?: BaseEntity,
        public location?: BaseEntity,
        public weather?: BaseEntity,
        public sportLists?: BaseEntity[],
    ) {
    }
}
