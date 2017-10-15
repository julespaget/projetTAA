import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Sport e2e test', () => {

    let navBarPage: NavBarPage;
    let sportDialogPage: SportDialogPage;
    let sportComponentsPage: SportComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Sports', () => {
        navBarPage.goToEntity('sport');
        sportComponentsPage = new SportComponentsPage();
        expect(sportComponentsPage.getTitle()).toMatch(/Sports/);

    });

    it('should load create Sport dialog', () => {
        sportComponentsPage.clickOnCreateButton();
        sportDialogPage = new SportDialogPage();
        expect(sportDialogPage.getModalTitle()).toMatch(/Create or edit a Sport/);
        sportDialogPage.close();
    });

    it('should create and save Sports', () => {
        sportComponentsPage.clickOnCreateButton();
        sportDialogPage.setTitleInput('title');
        expect(sportDialogPage.getTitleInput()).toMatch('title');
        sportDialogPage.weatherRequiredSelectLastOption();
        // sportDialogPage.placeListSelectLastOption();
        sportDialogPage.save();
        expect(sportDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SportComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-sport div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class SportDialogPage {
    modalTitle = element(by.css('h4#mySportLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    titleInput = element(by.css('input#field_title'));
    weatherRequiredSelect = element(by.css('select#field_weatherRequired'));
    placeListSelect = element(by.css('select#field_placeList'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setTitleInput = function (title) {
        this.titleInput.sendKeys(title);
    }

    getTitleInput = function () {
        return this.titleInput.getAttribute('value');
    }

    weatherRequiredSelectLastOption = function () {
        this.weatherRequiredSelect.all(by.tagName('option')).last().click();
    }

    weatherRequiredSelectOption = function (option) {
        this.weatherRequiredSelect.sendKeys(option);
    }

    getWeatherRequiredSelect = function () {
        return this.weatherRequiredSelect;
    }

    getWeatherRequiredSelectedOption = function () {
        return this.weatherRequiredSelect.element(by.css('option:checked')).getText();
    }

    placeListSelectLastOption = function () {
        this.placeListSelect.all(by.tagName('option')).last().click();
    }

    placeListSelectOption = function (option) {
        this.placeListSelect.sendKeys(option);
    }

    getPlaceListSelect = function () {
        return this.placeListSelect;
    }

    getPlaceListSelectedOption = function () {
        return this.placeListSelect.element(by.css('option:checked')).getText();
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
