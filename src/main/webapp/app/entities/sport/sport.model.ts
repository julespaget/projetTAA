import { BaseEntity } from './../../shared';

export class Sport implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public weatherRequired?: BaseEntity,
        public placeLists?: BaseEntity[],
    ) {
    }
}
