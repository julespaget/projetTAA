import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PrecipitationComponent } from './precipitation.component';
import { PrecipitationDetailComponent } from './precipitation-detail.component';
import { PrecipitationPopupComponent } from './precipitation-dialog.component';
import { PrecipitationDeletePopupComponent } from './precipitation-delete-dialog.component';

export const precipitationRoute: Routes = [
    {
        path: 'precipitation',
        component: PrecipitationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Precipitations'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'precipitation/:id',
        component: PrecipitationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Precipitations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const precipitationPopupRoute: Routes = [
    {
        path: 'precipitation-new',
        component: PrecipitationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Precipitations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'precipitation/:id/edit',
        component: PrecipitationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Precipitations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'precipitation/:id/delete',
        component: PrecipitationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Precipitations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
