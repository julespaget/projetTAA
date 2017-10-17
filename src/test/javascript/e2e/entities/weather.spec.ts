import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Weather e2e test', () => {

    let navBarPage: NavBarPage;
    let weatherDialogPage: WeatherDialogPage;
    let weatherComponentsPage: WeatherComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Weathers', () => {
        navBarPage.goToEntity('weather');
        weatherComponentsPage = new WeatherComponentsPage();
        expect(weatherComponentsPage.getTitle()).toMatch(/Weathers/);

    });

    it('should load create Weather dialog', () => {
        weatherComponentsPage.clickOnCreateButton();
        weatherDialogPage = new WeatherDialogPage();
        expect(weatherDialogPage.getModalTitle()).toMatch(/Create or edit a Weather/);
        weatherDialogPage.close();
    });

    it('should create and save Weathers', () => {
        weatherComponentsPage.clickOnCreateButton();
        weatherDialogPage.setDateInput(12310020012301);
        expect(weatherDialogPage.getDateInput()).toMatch('2001-12-31T02:30');
        weatherDialogPage.setTemperatureInput('5');
        expect(weatherDialogPage.getTemperatureInput()).toMatch('5');
        weatherDialogPage.setWindSpeedInput('5');
        expect(weatherDialogPage.getWindSpeedInput()).toMatch('5');
        weatherDialogPage.setWindAngleInput('5');
        expect(weatherDialogPage.getWindAngleInput()).toMatch('5');
        weatherDialogPage.setWaveHeightInput('5');
        expect(weatherDialogPage.getWaveHeightInput()).toMatch('5');
        weatherDialogPage.setCloudsInput('5');
        expect(weatherDialogPage.getCloudsInput()).toMatch('5');
        weatherDialogPage.setPressureInput('5');
        expect(weatherDialogPage.getPressureInput()).toMatch('5');
        weatherDialogPage.setHumidityInput('5');
        expect(weatherDialogPage.getHumidityInput()).toMatch('5');
        weatherDialogPage.precipitationSelectLastOption();
        weatherDialogPage.save();
        expect(weatherDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class WeatherComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-weather div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class WeatherDialogPage {
    modalTitle = element(by.css('h4#myWeatherLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    dateInput = element(by.css('input#field_date'));
    temperatureInput = element(by.css('input#field_temperature'));
    windSpeedInput = element(by.css('input#field_windSpeed'));
    windAngleInput = element(by.css('input#field_windAngle'));
    waveHeightInput = element(by.css('input#field_waveHeight'));
    cloudsInput = element(by.css('input#field_clouds'));
    pressureInput = element(by.css('input#field_pressure'));
    humidityInput = element(by.css('input#field_humidity'));
    precipitationSelect = element(by.css('select#field_precipitation'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setDateInput = function (date) {
        this.dateInput.sendKeys(date);
    }

    getDateInput = function () {
        return this.dateInput.getAttribute('value');
    }

    setTemperatureInput = function (temperature) {
        this.temperatureInput.sendKeys(temperature);
    }

    getTemperatureInput = function () {
        return this.temperatureInput.getAttribute('value');
    }

    setWindSpeedInput = function (windSpeed) {
        this.windSpeedInput.sendKeys(windSpeed);
    }

    getWindSpeedInput = function () {
        return this.windSpeedInput.getAttribute('value');
    }

    setWindAngleInput = function (windAngle) {
        this.windAngleInput.sendKeys(windAngle);
    }

    getWindAngleInput = function () {
        return this.windAngleInput.getAttribute('value');
    }

    setWaveHeightInput = function (waveHeight) {
        this.waveHeightInput.sendKeys(waveHeight);
    }

    getWaveHeightInput = function () {
        return this.waveHeightInput.getAttribute('value');
    }

    setCloudsInput = function (clouds) {
        this.cloudsInput.sendKeys(clouds);
    }

    getCloudsInput = function () {
        return this.cloudsInput.getAttribute('value');
    }

    setPressureInput = function (pressure) {
        this.pressureInput.sendKeys(pressure);
    }

    getPressureInput = function () {
        return this.pressureInput.getAttribute('value');
    }

    setHumidityInput = function (humidity) {
        this.humidityInput.sendKeys(humidity);
    }

    getHumidityInput = function () {
        return this.humidityInput.getAttribute('value');
    }

    precipitationSelectLastOption = function () {
        this.precipitationSelect.all(by.tagName('option')).last().click();
    }

    precipitationSelectOption = function (option) {
        this.precipitationSelect.sendKeys(option);
    }

    getPrecipitationSelect = function () {
        return this.precipitationSelect;
    }

    getPrecipitationSelectedOption = function () {
        return this.precipitationSelect.element(by.css('option:checked')).getText();
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
