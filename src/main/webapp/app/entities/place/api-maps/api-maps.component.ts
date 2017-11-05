import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Person} from "../../person/person.model";
import {Principal} from "../../../shared/auth/principal.service";
import {Router} from "@angular/router";
import {Place} from "../place.model";
import {LocationService} from "../../location/location.service";
import {PlaceService} from "../place.service";

@Component({
  selector: 'jhi-api-maps',
  templateUrl: './api-maps.component.html',
  styles: []
})
export class ApiMapsComponent implements OnInit, OnChanges {
    place: any;

    @Input() person: Person;
    @Input() locationIn: Location;
    @Input() placeIn: Place ;
    lat: number;
    lng: number;
    zoom: number;
    radius: number;
    locationid: number;
    modalRef: NgbModalRef;


  constructor(
      /*private editLocationService: EditLocationService,
      private editLocationModalService: EditLocationModalService,*/
      private MylocationService: LocationService,
      private MyPlaceService: PlaceService,
      private principal: Principal,
      private router: Router
  ) { }

  ngOnInit() {
      console.log("on est dans le ngoninit")
      this.lat  = 48.1119800;
      this.lng  = -1.6742900;
      this.zoom = 7;
      this.radius = 10000;
      this.place ;
  }
    ngOnChanges(changes: SimpleChanges) {
        if ( changes['person'] && (this.person != null) ) {
            // Update des informations de localisation
            this.MyPlaceService.find(this.person.currentPlaceId).subscribe(data => {
                // Read the result field from the JSON response.
                console.log(data);
                this.place = data['results'];
            });
            if (this.person.currentPlaceId) {
                this.lat = this.place.latitude;
                this.lng = this.place.longitude;
            } else {
                //TODO aucune idée de comment faire pour creer une nouvelle place et mettre la curendplaceid dans la variable...s
                this.person.currentPlaceId = new Place().id;
            }
        }
    }

    /*editLocation() {
        this.modalRef = this.editLocationModalService.open();
    }*/

}
