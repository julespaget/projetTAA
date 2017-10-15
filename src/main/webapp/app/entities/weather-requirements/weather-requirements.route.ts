import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { WeatherRequirementsComponent } from './weather-requirements.component';
import { WeatherRequirementsDetailComponent } from './weather-requirements-detail.component';
import { WeatherRequirementsPopupComponent } from './weather-requirements-dialog.component';
import { WeatherRequirementsDeletePopupComponent } from './weather-requirements-delete-dialog.component';

export const weatherRequirementsRoute: Routes = [
    {
        path: 'weather-requirements',
        component: WeatherRequirementsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WeatherRequirements'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'weather-requirements/:id',
        component: WeatherRequirementsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WeatherRequirements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const weatherRequirementsPopupRoute: Routes = [
    {
        path: 'weather-requirements-new',
        component: WeatherRequirementsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WeatherRequirements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'weather-requirements/:id/edit',
        component: WeatherRequirementsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WeatherRequirements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'weather-requirements/:id/delete',
        component: WeatherRequirementsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WeatherRequirements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
