import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Place e2e test', () => {

    let navBarPage: NavBarPage;
    let placeDialogPage: PlaceDialogPage;
    let placeComponentsPage: PlaceComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Places', () => {
        navBarPage.goToEntity('place');
        placeComponentsPage = new PlaceComponentsPage();
        expect(placeComponentsPage.getTitle()).toMatch(/Places/);

    });

    it('should load create Place dialog', () => {
        placeComponentsPage.clickOnCreateButton();
        placeDialogPage = new PlaceDialogPage();
        expect(placeDialogPage.getModalTitle()).toMatch(/Create or edit a Place/);
        placeDialogPage.close();
    });

    it('should create and save Places', () => {
        placeComponentsPage.clickOnCreateButton();
        placeDialogPage.setNomInput('nom');
        expect(placeDialogPage.getNomInput()).toMatch('nom');
        placeDialogPage.emplacementSelectLastOption();
        placeDialogPage.locationSelectLastOption();
        placeDialogPage.weatherSelectLastOption();
        placeDialogPage.save();
        expect(placeDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class PlaceComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-place div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class PlaceDialogPage {
    modalTitle = element(by.css('h4#myPlaceLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nomInput = element(by.css('input#field_nom'));
    emplacementSelect = element(by.css('select#field_emplacement'));
    locationSelect = element(by.css('select#field_location'));
    weatherSelect = element(by.css('select#field_weather'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setNomInput = function (nom) {
        this.nomInput.sendKeys(nom);
    }

    getNomInput = function () {
        return this.nomInput.getAttribute('value');
    }

    emplacementSelectLastOption = function () {
        this.emplacementSelect.all(by.tagName('option')).last().click();
    }

    emplacementSelectOption = function (option) {
        this.emplacementSelect.sendKeys(option);
    }

    getEmplacementSelect = function () {
        return this.emplacementSelect;
    }

    getEmplacementSelectedOption = function () {
        return this.emplacementSelect.element(by.css('option:checked')).getText();
    }

    locationSelectLastOption = function () {
        this.locationSelect.all(by.tagName('option')).last().click();
    }

    locationSelectOption = function (option) {
        this.locationSelect.sendKeys(option);
    }

    getLocationSelect = function () {
        return this.locationSelect;
    }

    getLocationSelectedOption = function () {
        return this.locationSelect.element(by.css('option:checked')).getText();
    }

    weatherSelectLastOption = function () {
        this.weatherSelect.all(by.tagName('option')).last().click();
    }

    weatherSelectOption = function (option) {
        this.weatherSelect.sendKeys(option);
    }

    getWeatherSelect = function () {
        return this.weatherSelect;
    }

    getWeatherSelectedOption = function () {
        return this.weatherSelect.element(by.css('option:checked')).getText();
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
