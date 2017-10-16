import { BaseEntity } from './../../shared';

export class Sport implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public weatherRequiredId?: number,
        public placeLists?: BaseEntity[],
        public personLists?: BaseEntity[],
    ) {
    }
}
