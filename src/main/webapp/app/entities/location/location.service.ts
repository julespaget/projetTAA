import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Location } from './location.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

// test de maps avec angular google maps
import { AgmCoreModule } from '@agm/core';

@Component({
    selector: 'app-root',
    styles: [`
    agm-map {
      height: 300px;
    }
  `],
    template: `
  <agm-map [latitude]="lat" [longitude]="lng"></agm-map>
  `
})
export class AppComponent {
    lat: number = 51.678418;
    lng: number = 7.809007;
}

@NgModule({
    imports: [
        BrowserModule,
        AgmCoreModule.forRoot({
            apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
        })
    ],
    declarations: [ AppComponent ],
    bootstrap: [ AppComponent ]
})
export class AppModule {}
//______________________________________fin de l'import de l'api google maps



@Injectable()
export class LocationService {

    private resourceUrl = SERVER_API_URL + 'api/locations';

    constructor(private http: Http) { }

    create(location: Location): Observable<Location> {
        const copy = this.convert(location);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(location: Location): Observable<Location> {
        const copy = this.convert(location);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Location> {
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
     * Convert a returned JSON object to Location.
     */
    private convertItemFromServer(json: any): Location {
        const entity: Location = Object.assign(new Location(), json);
        return entity;
    }

    /**
     * Convert a Location to a JSON which can be sent to the server.
     */
    private convert(location: Location): Location {
        const copy: Location = Object.assign({}, location);
        return copy;
    }
}
