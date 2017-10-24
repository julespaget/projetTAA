import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Person e2e test', () => {

    let navBarPage: NavBarPage;
    let personDialogPage: PersonDialogPage;
    let personComponentsPage: PersonComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load People', () => {
        navBarPage.goToEntity('person');
        personComponentsPage = new PersonComponentsPage();
        expect(personComponentsPage.getTitle()).toMatch(/People/);

    });

    it('should load create Person dialog', () => {
        personComponentsPage.clickOnCreateButton();
        personDialogPage = new PersonDialogPage();
        expect(personDialogPage.getModalTitle()).toMatch(/Create or edit a Person/);
        personDialogPage.close();
    });

    it('should create and save People', () => {
        personComponentsPage.clickOnCreateButton();
        personDialogPage.setFirstNameInput('firstName');
        expect(personDialogPage.getFirstNameInput()).toMatch('firstName');
        personDialogPage.setLastNameInput('lastName');
        expect(personDialogPage.getLastNameInput()).toMatch('lastName');
        personDialogPage.setEmailInput('email');
        expect(personDialogPage.getEmailInput()).toMatch('email');
        personDialogPage.setPhoneNumberInput('phoneNumber');
        expect(personDialogPage.getPhoneNumberInput()).toMatch('phoneNumber');
        personDialogPage.setBirthDateInput(12310020012301);
        expect(personDialogPage.getBirthDateInput()).toMatch('2001-12-31T02:30');
        personDialogPage.setDistanceMaxInput('5');
        expect(personDialogPage.getDistanceMaxInput()).toMatch('5');
        personDialogPage.currentPlaceSelectLastOption();
        // personDialogPage.sportListSelectLastOption();
        personDialogPage.save();
        expect(personDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class PersonComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-person div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class PersonDialogPage {
    modalTitle = element(by.css('h4#myPersonLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    firstNameInput = element(by.css('input#field_firstName'));
    lastNameInput = element(by.css('input#field_lastName'));
    emailInput = element(by.css('input#field_email'));
    phoneNumberInput = element(by.css('input#field_phoneNumber'));
    birthDateInput = element(by.css('input#field_birthDate'));
    distanceMaxInput = element(by.css('input#field_distanceMax'));
    currentPlaceSelect = element(by.css('select#field_currentPlace'));
    sportListSelect = element(by.css('select#field_sportList'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setFirstNameInput = function (firstName) {
        this.firstNameInput.sendKeys(firstName);
    }

    getFirstNameInput = function () {
        return this.firstNameInput.getAttribute('value');
    }

    setLastNameInput = function (lastName) {
        this.lastNameInput.sendKeys(lastName);
    }

    getLastNameInput = function () {
        return this.lastNameInput.getAttribute('value');
    }

    setEmailInput = function (email) {
        this.emailInput.sendKeys(email);
    }

    getEmailInput = function () {
        return this.emailInput.getAttribute('value');
    }

    setPhoneNumberInput = function (phoneNumber) {
        this.phoneNumberInput.sendKeys(phoneNumber);
    }

    getPhoneNumberInput = function () {
        return this.phoneNumberInput.getAttribute('value');
    }

    setBirthDateInput = function (birthDate) {
        this.birthDateInput.sendKeys(birthDate);
    }

    getBirthDateInput = function () {
        return this.birthDateInput.getAttribute('value');
    }

    setDistanceMaxInput = function (distanceMax) {
        this.distanceMaxInput.sendKeys(distanceMax);
    }

    getDistanceMaxInput = function () {
        return this.distanceMaxInput.getAttribute('value');
    }

    currentPlaceSelectLastOption = function () {
        this.currentPlaceSelect.all(by.tagName('option')).last().click();
    }

    currentPlaceSelectOption = function (option) {
        this.currentPlaceSelect.sendKeys(option);
    }

    getCurrentPlaceSelect = function () {
        return this.currentPlaceSelect;
    }

    getCurrentPlaceSelectedOption = function () {
        return this.currentPlaceSelect.element(by.css('option:checked')).getText();
    }

    sportListSelectLastOption = function () {
        this.sportListSelect.all(by.tagName('option')).last().click();
    }

    sportListSelectOption = function (option) {
        this.sportListSelect.sendKeys(option);
    }

    getSportListSelect = function () {
        return this.sportListSelect;
    }

    getSportListSelectedOption = function () {
        return this.sportListSelect.element(by.css('option:checked')).getText();
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
