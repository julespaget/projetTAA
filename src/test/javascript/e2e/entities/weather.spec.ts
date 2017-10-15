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
        weatherDialogPage.setTemperatureInput('5');
        expect(weatherDialogPage.getTemperatureInput()).toMatch('5');
        weatherDialogPage.setWindSpeedInput('5');
        expect(weatherDialogPage.getWindSpeedInput()).toMatch('5');
        weatherDialogPage.getRainInput().isSelected().then(function (selected) {
            if (selected) {
                weatherDialogPage.getRainInput().click();
                expect(weatherDialogPage.getRainInput().isSelected()).toBeFalsy();
            } else {
                weatherDialogPage.getRainInput().click();
                expect(weatherDialogPage.getRainInput().isSelected()).toBeTruthy();
            }
        });
        weatherDialogPage.setWaveHeightInput('5');
        expect(weatherDialogPage.getWaveHeightInput()).toMatch('5');
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
    temperatureInput = element(by.css('input#field_temperature'));
    windSpeedInput = element(by.css('input#field_windSpeed'));
    rainInput = element(by.css('input#field_rain'));
    waveHeightInput = element(by.css('input#field_waveHeight'));

    getModalTitle() {
        return this.modalTitle.getText();
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

    getRainInput = function () {
        return this.rainInput;
    }
    setWaveHeightInput = function (waveHeight) {
        this.waveHeightInput.sendKeys(waveHeight);
    }

    getWaveHeightInput = function () {
        return this.waveHeightInput.getAttribute('value');
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
