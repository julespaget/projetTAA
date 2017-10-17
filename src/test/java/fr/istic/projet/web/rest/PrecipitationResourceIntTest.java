package fr.istic.projet.web.rest;

import fr.istic.projet.WeekandgoApp;

import fr.istic.projet.domain.Precipitation;
import fr.istic.projet.repository.PrecipitationRepository;
import fr.istic.projet.service.PrecipitationService;
import fr.istic.projet.service.dto.PrecipitationDTO;
import fr.istic.projet.service.mapper.PrecipitationMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.istic.projet.domain.enumeration.PrecipitationType;
/**
 * Test class for the PrecipitationResource REST controller.
 *
 * @see PrecipitationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeekandgoApp.class)
public class PrecipitationResourceIntTest {

    private static final PrecipitationType DEFAULT_TYPE = PrecipitationType.RAIN;
    private static final PrecipitationType UPDATED_TYPE = PrecipitationType.SNOW;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;

    @Autowired
    private PrecipitationRepository precipitationRepository;

    @Autowired
    private PrecipitationMapper precipitationMapper;

    @Autowired
    private PrecipitationService precipitationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPrecipitationMockMvc;

    private Precipitation precipitation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrecipitationResource precipitationResource = new PrecipitationResource(precipitationService);
        this.restPrecipitationMockMvc = MockMvcBuilders.standaloneSetup(precipitationResource)
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
    public static Precipitation createEntity(EntityManager em) {
        Precipitation precipitation = new Precipitation()
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE);
        return precipitation;
    }

    @Before
    public void initTest() {
        precipitation = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrecipitation() throws Exception {
        int databaseSizeBeforeCreate = precipitationRepository.findAll().size();

        // Create the Precipitation
        PrecipitationDTO precipitationDTO = precipitationMapper.toDto(precipitation);
        restPrecipitationMockMvc.perform(post("/api/precipitations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(precipitationDTO)))
            .andExpect(status().isCreated());

        // Validate the Precipitation in the database
        List<Precipitation> precipitationList = precipitationRepository.findAll();
        assertThat(precipitationList).hasSize(databaseSizeBeforeCreate + 1);
        Precipitation testPrecipitation = precipitationList.get(precipitationList.size() - 1);
        assertThat(testPrecipitation.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPrecipitation.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createPrecipitationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = precipitationRepository.findAll().size();

        // Create the Precipitation with an existing ID
        precipitation.setId(1L);
        PrecipitationDTO precipitationDTO = precipitationMapper.toDto(precipitation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrecipitationMockMvc.perform(post("/api/precipitations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(precipitationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Precipitation in the database
        List<Precipitation> precipitationList = precipitationRepository.findAll();
        assertThat(precipitationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrecipitations() throws Exception {
        // Initialize the database
        precipitationRepository.saveAndFlush(precipitation);

        // Get all the precipitationList
        restPrecipitationMockMvc.perform(get("/api/precipitations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(precipitation.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPrecipitation() throws Exception {
        // Initialize the database
        precipitationRepository.saveAndFlush(precipitation);

        // Get the precipitation
        restPrecipitationMockMvc.perform(get("/api/precipitations/{id}", precipitation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(precipitation.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPrecipitation() throws Exception {
        // Get the precipitation
        restPrecipitationMockMvc.perform(get("/api/precipitations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrecipitation() throws Exception {
        // Initialize the database
        precipitationRepository.saveAndFlush(precipitation);
        int databaseSizeBeforeUpdate = precipitationRepository.findAll().size();

        // Update the precipitation
        Precipitation updatedPrecipitation = precipitationRepository.findOne(precipitation.getId());
        updatedPrecipitation
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE);
        PrecipitationDTO precipitationDTO = precipitationMapper.toDto(updatedPrecipitation);

        restPrecipitationMockMvc.perform(put("/api/precipitations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(precipitationDTO)))
            .andExpect(status().isOk());

        // Validate the Precipitation in the database
        List<Precipitation> precipitationList = precipitationRepository.findAll();
        assertThat(precipitationList).hasSize(databaseSizeBeforeUpdate);
        Precipitation testPrecipitation = precipitationList.get(precipitationList.size() - 1);
        assertThat(testPrecipitation.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPrecipitation.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrecipitation() throws Exception {
        int databaseSizeBeforeUpdate = precipitationRepository.findAll().size();

        // Create the Precipitation
        PrecipitationDTO precipitationDTO = precipitationMapper.toDto(precipitation);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPrecipitationMockMvc.perform(put("/api/precipitations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(precipitationDTO)))
            .andExpect(status().isCreated());

        // Validate the Precipitation in the database
        List<Precipitation> precipitationList = precipitationRepository.findAll();
        assertThat(precipitationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePrecipitation() throws Exception {
        // Initialize the database
        precipitationRepository.saveAndFlush(precipitation);
        int databaseSizeBeforeDelete = precipitationRepository.findAll().size();

        // Get the precipitation
        restPrecipitationMockMvc.perform(delete("/api/precipitations/{id}", precipitation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Precipitation> precipitationList = precipitationRepository.findAll();
        assertThat(precipitationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Precipitation.class);
        Precipitation precipitation1 = new Precipitation();
        precipitation1.setId(1L);
        Precipitation precipitation2 = new Precipitation();
        precipitation2.setId(precipitation1.getId());
        assertThat(precipitation1).isEqualTo(precipitation2);
        precipitation2.setId(2L);
        assertThat(precipitation1).isNotEqualTo(precipitation2);
        precipitation1.setId(null);
        assertThat(precipitation1).isNotEqualTo(precipitation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrecipitationDTO.class);
        PrecipitationDTO precipitationDTO1 = new PrecipitationDTO();
        precipitationDTO1.setId(1L);
        PrecipitationDTO precipitationDTO2 = new PrecipitationDTO();
        assertThat(precipitationDTO1).isNotEqualTo(precipitationDTO2);
        precipitationDTO2.setId(precipitationDTO1.getId());
        assertThat(precipitationDTO1).isEqualTo(precipitationDTO2);
        precipitationDTO2.setId(2L);
        assertThat(precipitationDTO1).isNotEqualTo(precipitationDTO2);
        precipitationDTO1.setId(null);
        assertThat(precipitationDTO1).isNotEqualTo(precipitationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(precipitationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(precipitationMapper.fromId(null)).isNull();
    }
}
