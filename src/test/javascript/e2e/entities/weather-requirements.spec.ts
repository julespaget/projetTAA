import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('WeatherRequirements e2e test', () => {

    let navBarPage: NavBarPage;
    let weatherRequirementsDialogPage: WeatherRequirementsDialogPage;
    let weatherRequirementsComponentsPage: WeatherRequirementsComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load WeatherRequirements', () => {
        navBarPage.goToEntity('weather-requirements');
        weatherRequirementsComponentsPage = new WeatherRequirementsComponentsPage();
        expect(weatherRequirementsComponentsPage.getTitle()).toMatch(/Weather Requirements/);

    });

    it('should load create WeatherRequirements dialog', () => {
        weatherRequirementsComponentsPage.clickOnCreateButton();
        weatherRequirementsDialogPage = new WeatherRequirementsDialogPage();
        expect(weatherRequirementsDialogPage.getModalTitle()).toMatch(/Create or edit a Weather Requirements/);
        weatherRequirementsDialogPage.close();
    });

    it('should create and save WeatherRequirements', () => {
        weatherRequirementsComponentsPage.clickOnCreateButton();
        weatherRequirementsDialogPage.setTemperatureMinInput('5');
        expect(weatherRequirementsDialogPage.getTemperatureMinInput()).toMatch('5');
        weatherRequirementsDialogPage.setTemperatureMaxInput('5');
        expect(weatherRequirementsDialogPage.getTemperatureMaxInput()).toMatch('5');
        weatherRequirementsDialogPage.setWindSpeedMinInput('5');
        expect(weatherRequirementsDialogPage.getWindSpeedMinInput()).toMatch('5');
        weatherRequirementsDialogPage.setWindSpeedMaxInput('5');
        expect(weatherRequirementsDialogPage.getWindSpeedMaxInput()).toMatch('5');
        weatherRequirementsDialogPage.setWindAngleMinInput('5');
        expect(weatherRequirementsDialogPage.getWindAngleMinInput()).toMatch('5');
        weatherRequirementsDialogPage.setWindAngleMaxInput('5');
        expect(weatherRequirementsDialogPage.getWindAngleMaxInput()).toMatch('5');
        weatherRequirementsDialogPage.setWaveHeightMinInput('5');
        expect(weatherRequirementsDialogPage.getWaveHeightMinInput()).toMatch('5');
        weatherRequirementsDialogPage.setWaveHeightMaxInput('5');
        expect(weatherRequirementsDialogPage.getWaveHeightMaxInput()).toMatch('5');
        weatherRequirementsDialogPage.precipitationMinSelectLastOption();
        weatherRequirementsDialogPage.precipitationMaxSelectLastOption();
        weatherRequirementsDialogPage.save();
        expect(weatherRequirementsDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class WeatherRequirementsComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-weather-requirements div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class WeatherRequirementsDialogPage {
    modalTitle = element(by.css('h4#myWeatherRequirementsLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    temperatureMinInput = element(by.css('input#field_temperatureMin'));
    temperatureMaxInput = element(by.css('input#field_temperatureMax'));
    windSpeedMinInput = element(by.css('input#field_windSpeedMin'));
    windSpeedMaxInput = element(by.css('input#field_windSpeedMax'));
    windAngleMinInput = element(by.css('input#field_windAngleMin'));
    windAngleMaxInput = element(by.css('input#field_windAngleMax'));
    waveHeightMinInput = element(by.css('input#field_waveHeightMin'));
    waveHeightMaxInput = element(by.css('input#field_waveHeightMax'));
    precipitationMinSelect = element(by.css('select#field_precipitationMin'));
    precipitationMaxSelect = element(by.css('select#field_precipitationMax'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setTemperatureMinInput = function (temperatureMin) {
        this.temperatureMinInput.sendKeys(temperatureMin);
    }

    getTemperatureMinInput = function () {
        return this.temperatureMinInput.getAttribute('value');
    }

    setTemperatureMaxInput = function (temperatureMax) {
        this.temperatureMaxInput.sendKeys(temperatureMax);
    }

    getTemperatureMaxInput = function () {
        return this.temperatureMaxInput.getAttribute('value');
    }

    setWindSpeedMinInput = function (windSpeedMin) {
        this.windSpeedMinInput.sendKeys(windSpeedMin);
    }

    getWindSpeedMinInput = function () {
        return this.windSpeedMinInput.getAttribute('value');
    }

    setWindSpeedMaxInput = function (windSpeedMax) {
        this.windSpeedMaxInput.sendKeys(windSpeedMax);
    }

    getWindSpeedMaxInput = function () {
        return this.windSpeedMaxInput.getAttribute('value');
    }

    setWindAngleMinInput = function (windAngleMin) {
        this.windAngleMinInput.sendKeys(windAngleMin);
    }

    getWindAngleMinInput = function () {
        return this.windAngleMinInput.getAttribute('value');
    }

    setWindAngleMaxInput = function (windAngleMax) {
        this.windAngleMaxInput.sendKeys(windAngleMax);
    }

    getWindAngleMaxInput = function () {
        return this.windAngleMaxInput.getAttribute('value');
    }

    setWaveHeightMinInput = function (waveHeightMin) {
        this.waveHeightMinInput.sendKeys(waveHeightMin);
    }

    getWaveHeightMinInput = function () {
        return this.waveHeightMinInput.getAttribute('value');
    }

    setWaveHeightMaxInput = function (waveHeightMax) {
        this.waveHeightMaxInput.sendKeys(waveHeightMax);
    }

    getWaveHeightMaxInput = function () {
        return this.waveHeightMaxInput.getAttribute('value');
    }

    precipitationMinSelectLastOption = function () {
        this.precipitationMinSelect.all(by.tagName('option')).last().click();
    }

    precipitationMinSelectOption = function (option) {
        this.precipitationMinSelect.sendKeys(option);
    }

    getPrecipitationMinSelect = function () {
        return this.precipitationMinSelect;
    }

    getPrecipitationMinSelectedOption = function () {
        return this.precipitationMinSelect.element(by.css('option:checked')).getText();
    }

    precipitationMaxSelectLastOption = function () {
        this.precipitationMaxSelect.all(by.tagName('option')).last().click();
    }

    precipitationMaxSelectOption = function (option) {
        this.precipitationMaxSelect.sendKeys(option);
    }

    getPrecipitationMaxSelect = function () {
        return this.precipitationMaxSelect;
    }

    getPrecipitationMaxSelectedOption = function () {
        return this.precipitationMaxSelect.element(by.css('option:checked')).getText();
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
