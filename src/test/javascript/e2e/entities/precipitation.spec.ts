import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Precipitation e2e test', () => {

    let navBarPage: NavBarPage;
    let precipitationDialogPage: PrecipitationDialogPage;
    let precipitationComponentsPage: PrecipitationComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Precipitations', () => {
        navBarPage.goToEntity('precipitation');
        precipitationComponentsPage = new PrecipitationComponentsPage();
        expect(precipitationComponentsPage.getTitle()).toMatch(/Precipitations/);

    });

    it('should load create Precipitation dialog', () => {
        precipitationComponentsPage.clickOnCreateButton();
        precipitationDialogPage = new PrecipitationDialogPage();
        expect(precipitationDialogPage.getModalTitle()).toMatch(/Create or edit a Precipitation/);
        precipitationDialogPage.close();
    });

    it('should create and save Precipitations', () => {
        precipitationComponentsPage.clickOnCreateButton();
        precipitationDialogPage.typeSelectLastOption();
        precipitationDialogPage.setValueInput('5');
        expect(precipitationDialogPage.getValueInput()).toMatch('5');
        precipitationDialogPage.save();
        expect(precipitationDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class PrecipitationComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-precipitation div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class PrecipitationDialogPage {
    modalTitle = element(by.css('h4#myPrecipitationLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    typeSelect = element(by.css('select#field_type'));
    valueInput = element(by.css('input#field_value'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setTypeSelect = function (type) {
        this.typeSelect.sendKeys(type);
    }

    getTypeSelect = function () {
        return this.typeSelect.element(by.css('option:checked')).getText();
    }

    typeSelectLastOption = function () {
        this.typeSelect.all(by.tagName('option')).last().click();
    }
    setValueInput = function (value) {
        this.valueInput.sendKeys(value);
    }

    getValueInput = function () {
        return this.valueInput.getAttribute('value');
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
