import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Precipitation } from './precipitation.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PrecipitationService {

    private resourceUrl = SERVER_API_URL + 'api/precipitations';

    constructor(private http: Http) { }

    create(precipitation: Precipitation): Observable<Precipitation> {
        const copy = this.convert(precipitation);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(precipitation: Precipitation): Observable<Precipitation> {
        const copy = this.convert(precipitation);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Precipitation> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Precipitation.
     */
    private convertItemFromServer(json: any): Precipitation {
        const entity: Precipitation = Object.assign(new Precipitation(), json);
        return entity;
    }

    /**
     * Convert a Precipitation to a JSON which can be sent to the server.
     */
    private convert(precipitation: Precipitation): Precipitation {
        const copy: Precipitation = Object.assign({}, precipitation);
        return copy;
    }
}
