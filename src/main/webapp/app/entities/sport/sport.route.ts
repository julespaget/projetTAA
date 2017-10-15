import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SportComponent } from './sport.component';
import { SportDetailComponent } from './sport-detail.component';
import { SportPopupComponent } from './sport-dialog.component';
import { SportDeletePopupComponent } from './sport-delete-dialog.component';

export const sportRoute: Routes = [
    {
        path: 'sport',
        component: SportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sports'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sport/:id',
        component: SportDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sports'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sportPopupRoute: Routes = [
    {
        path: 'sport-new',
        component: SportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sports'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sport/:id/edit',
        component: SportPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sports'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sport/:id/delete',
        component: SportDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Sports'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
