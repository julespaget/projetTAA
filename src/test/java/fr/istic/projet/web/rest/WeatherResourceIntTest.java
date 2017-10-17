package fr.istic.projet.web.rest;

import fr.istic.projet.WeekandgoApp;

import fr.istic.projet.domain.Weather;
import fr.istic.projet.repository.WeatherRepository;
import fr.istic.projet.service.WeatherService;
import fr.istic.projet.service.dto.WeatherDTO;
import fr.istic.projet.service.mapper.WeatherMapper;
import fr.istic.projet.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static fr.istic.projet.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WeatherResource REST controller.
 *
 * @see WeatherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeekandgoApp.class)
public class WeatherResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_TEMPERATURE = 1D;
    private static final Double UPDATED_TEMPERATURE = 2D;

    private static final Double DEFAULT_WIND_SPEED = 1D;
    private static final Double UPDATED_WIND_SPEED = 2D;

    private static final Double DEFAULT_WIND_ANGLE = 1D;
    private static final Double UPDATED_WIND_ANGLE = 2D;

    private static final Double DEFAULT_WAVE_HEIGHT = 1D;
    private static final Double UPDATED_WAVE_HEIGHT = 2D;

    private static final Double DEFAULT_CLOUDS = 1D;
    private static final Double UPDATED_CLOUDS = 2D;

    private static final Double DEFAULT_PRESSURE = 1D;
    private static final Double UPDATED_PRESSURE = 2D;

    private static final Double DEFAULT_HUMIDITY = 1D;
    private static final Double UPDATED_HUMIDITY = 2D;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherMapper weatherMapper;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWeatherMockMvc;

    private Weather weather;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WeatherResource weatherResource = new WeatherResource(weatherService);
        this.restWeatherMockMvc = MockMvcBuilders.standaloneSetup(weatherResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Weather createEntity(EntityManager em) {
        Weather weather = new Weather()
            .date(DEFAULT_DATE)
            .temperature(DEFAULT_TEMPERATURE)
            .windSpeed(DEFAULT_WIND_SPEED)
            .windAngle(DEFAULT_WIND_ANGLE)
            .waveHeight(DEFAULT_WAVE_HEIGHT)
            .clouds(DEFAULT_CLOUDS)
            .pressure(DEFAULT_PRESSURE)
            .humidity(DEFAULT_HUMIDITY);
        return weather;
    }

    @Before
    public void initTest() {
        weather = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeather() throws Exception {
        int databaseSizeBeforeCreate = weatherRepository.findAll().size();

        // Create the Weather
        WeatherDTO weatherDTO = weatherMapper.toDto(weather);
        restWeatherMockMvc.perform(post("/api/weathers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(weatherDTO)))
            .andExpect(status().isCreated());

        // Validate the Weather in the database
        List<Weather> weatherList = weatherRepository.findAll();
        assertThat(weatherList).hasSize(databaseSizeBeforeCreate + 1);
        Weather testWeather = weatherList.get(weatherList.size() - 1);
        assertThat(testWeather.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testWeather.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testWeather.getWindSpeed()).isEqualTo(DEFAULT_WIND_SPEED);
        assertThat(testWeather.getWindAngle()).isEqualTo(DEFAULT_WIND_ANGLE);
        assertThat(testWeather.getWaveHeight()).isEqualTo(DEFAULT_WAVE_HEIGHT);
        assertThat(testWeather.getClouds()).isEqualTo(DEFAULT_CLOUDS);
        assertThat(testWeather.getPressure()).isEqualTo(DEFAULT_PRESSURE);
        assertThat(testWeather.getHumidity()).isEqualTo(DEFAULT_HUMIDITY);
    }

    @Test
    @Transactional
    public void createWeatherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weatherRepository.findAll().size();

        // Create the Weather with an existing ID
        weather.setId(1L);
        WeatherDTO weatherDTO = weatherMapper.toDto(weather);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeatherMockMvc.perform(post("/api/weathers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(weatherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Weather in the database
        List<Weather> weatherList = weatherRepository.findAll();
        assertThat(weatherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWeathers() throws Exception {
        // Initialize the database
        weatherRepository.saveAndFlush(weather);

        // Get all the weatherList
        restWeatherMockMvc.perform(get("/api/weathers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weather.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].windSpeed").value(hasItem(DEFAULT_WIND_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].windAngle").value(hasItem(DEFAULT_WIND_ANGLE.doubleValue())))
            .andExpect(jsonPath("$.[*].waveHeight").value(hasItem(DEFAULT_WAVE_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].clouds").value(hasItem(DEFAULT_CLOUDS.doubleValue())))
            .andExpect(jsonPath("$.[*].pressure").value(hasItem(DEFAULT_PRESSURE.doubleValue())))
            .andExpect(jsonPath("$.[*].humidity").value(hasItem(DEFAULT_HUMIDITY.doubleValue())));
    }

    @Test
    @Transactional
    public void getWeather() throws Exception {
        // Initialize the database
        weatherRepository.saveAndFlush(weather);

        // Get the weather
        restWeatherMockMvc.perform(get("/api/weathers/{id}", weather.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(weather.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.windSpeed").value(DEFAULT_WIND_SPEED.doubleValue()))
            .andExpect(jsonPath("$.windAngle").value(DEFAULT_WIND_ANGLE.doubleValue()))
            .andExpect(jsonPath("$.waveHeight").value(DEFAULT_WAVE_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.clouds").value(DEFAULT_CLOUDS.doubleValue()))
            .andExpect(jsonPath("$.pressure").value(DEFAULT_PRESSURE.doubleValue()))
            .andExpect(jsonPath("$.humidity").value(DEFAULT_HUMIDITY.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWeather() throws Exception {
        // Get the weather
        restWeatherMockMvc.perform(get("/api/weathers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeather() throws Exception {
        // Initialize the database
        weatherRepository.saveAndFlush(weather);
        int databaseSizeBeforeUpdate = weatherRepository.findAll().size();

        // Update the weather
        Weather updatedWeather = weatherRepository.findOne(weather.getId());
        updatedWeather
            .date(UPDATED_DATE)
            .temperature(UPDATED_TEMPERATURE)
            .windSpeed(UPDATED_WIND_SPEED)
            .windAngle(UPDATED_WIND_ANGLE)
            .waveHeight(UPDATED_WAVE_HEIGHT)
            .clouds(UPDATED_CLOUDS)
            .pressure(UPDATED_PRESSURE)
            .humidity(UPDATED_HUMIDITY);
        WeatherDTO weatherDTO = weatherMapper.toDto(updatedWeather);

        restWeatherMockMvc.perform(put("/api/weathers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(weatherDTO)))
            .andExpect(status().isOk());

        // Validate the Weather in the database
        List<Weather> weatherList = weatherRepository.findAll();
        assertThat(weatherList).hasSize(databaseSizeBeforeUpdate);
        Weather testWeather = weatherList.get(weatherList.size() - 1);
        assertThat(testWeather.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testWeather.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testWeather.getWindSpeed()).isEqualTo(UPDATED_WIND_SPEED);
        assertThat(testWeather.getWindAngle()).isEqualTo(UPDATED_WIND_ANGLE);
        assertThat(testWeather.getWaveHeight()).isEqualTo(UPDATED_WAVE_HEIGHT);
        assertThat(testWeather.getClouds()).isEqualTo(UPDATED_CLOUDS);
        assertThat(testWeather.getPressure()).isEqualTo(UPDATED_PRESSURE);
        assertThat(testWeather.getHumidity()).isEqualTo(UPDATED_HUMIDITY);
    }

    @Test
    @Transactional
    public void updateNonExistingWeather() throws Exception {
        int databaseSizeBeforeUpdate = weatherRepository.findAll().size();

        // Create the Weather
        WeatherDTO weatherDTO = weatherMapper.toDto(weather);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWeatherMockMvc.perform(put("/api/weathers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(weatherDTO)))
            .andExpect(status().isCreated());

        // Validate the Weather in the database
        List<Weather> weatherList = weatherRepository.findAll();
        assertThat(weatherList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWeather() throws Exception {
        // Initialize the database
        weatherRepository.saveAndFlush(weather);
        int databaseSizeBeforeDelete = weatherRepository.findAll().size();

        // Get the weather
        restWeatherMockMvc.perform(delete("/api/weathers/{id}", weather.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Weather> weatherList = weatherRepository.findAll();
        assertThat(weatherList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Weather.class);
        Weather weather1 = new Weather();
        weather1.setId(1L);
        Weather weather2 = new Weather();
        weather2.setId(weather1.getId());
        assertThat(weather1).isEqualTo(weather2);
        weather2.setId(2L);
        assertThat(weather1).isNotEqualTo(weather2);
        weather1.setId(null);
        assertThat(weather1).isNotEqualTo(weather2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeatherDTO.class);
        WeatherDTO weatherDTO1 = new WeatherDTO();
        weatherDTO1.setId(1L);
        WeatherDTO weatherDTO2 = new WeatherDTO();
        assertThat(weatherDTO1).isNotEqualTo(weatherDTO2);
        weatherDTO2.setId(weatherDTO1.getId());
        assertThat(weatherDTO1).isEqualTo(weatherDTO2);
        weatherDTO2.setId(2L);
        assertThat(weatherDTO1).isNotEqualTo(weatherDTO2);
        weatherDTO1.setId(null);
        assertThat(weatherDTO1).isNotEqualTo(weatherDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(weatherMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(weatherMapper.fromId(null)).isNull();
    }
}
