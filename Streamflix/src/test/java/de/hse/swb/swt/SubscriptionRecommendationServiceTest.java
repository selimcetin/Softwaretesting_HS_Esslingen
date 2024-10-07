package Streamflix.src.test.java.de.hse.swb.swt;

import Streamflix.src.main.java.de.hse.swb.swt.streamflix.SubscriptionRecommendationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionRecommendationServiceTest {
    String DEFAULT_MODEL_IDENTIFIER = "W";
    String BASIC_MODEL_IDENTIFIER = "B";
    String STANDARD_MODEL_IDENTIFIER = "S";
    String PREMIUM_MODEL_IDENTIFIER = "P";

    boolean requiresNoAds;
    boolean requiresUltraHD;
    int devicesToWatch;
    int devicesToDownload;


    @BeforeEach
    void Setup() {
        setAllParamsToFalseAndZero();
    }

    @AfterEach
    void TearDown() { }

    @Test
    void recommendModel_ParamsForPremium_ShouldReturnPremium(){
        setupParamsForPremiumModel();

        String actualResult = SubscriptionRecommendationService.recommendModel(requiresNoAds, devicesToWatch, requiresUltraHD, devicesToDownload);

        assertEquals(PREMIUM_MODEL_IDENTIFIER, actualResult);
    }

    @Test
    void recommendModel_ParamsForStandard_ShouldReturnStandard() {
        setupParamsForStandardModel();

        String actualResult = SubscriptionRecommendationService.recommendModel(requiresNoAds, devicesToWatch, requiresUltraHD, devicesToDownload);

        assertEquals(STANDARD_MODEL_IDENTIFIER, actualResult);
    }

    @Test
    void recommendModel_ParamsForBasic_ShouldReturnBasic() {
        setupParamsForBasicModel();

        String actualResult = SubscriptionRecommendationService.recommendModel(requiresNoAds, devicesToWatch, requiresUltraHD, devicesToDownload);

        assertEquals(BASIC_MODEL_IDENTIFIER, actualResult);
    }

    @Test
    void recommendModel_NoParams_ShouldReturnStandardWithAds() {
        setupParamsForDefaultModel();

        String actualResult = SubscriptionRecommendationService.recommendModel(requiresNoAds, devicesToWatch, requiresUltraHD, devicesToDownload);

        assertEquals(DEFAULT_MODEL_IDENTIFIER, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "false, false, 0, 0, W",
            "false, false, 0, 10, P",
            "false, false, 2, 10, P",
            "false, false, 5, 10, P",
            "false, false, 10, 0, P",
            "false, false, 10, 2, P",
            "false, false, 10, 5, P",
            "false, false, 10, 10, P",
            "true, false, 0, 0, B",
            "true, false, 0, 10, P",
            "true, false, 2, 10, P",
            "true, false, 5, 10, P",
            "true, false, 10, 0, P",
            "true, false, 10, 2, P",
            "true, false, 10, 5, P",
            "true, false, 10, 10, P",
            "false, true, 0, 0, P",
            "false, true, 0, 10, P",
            "false, true, 2, 10, P",
            "false, true, 5, 10, P",
            "false, true, 10, 0, P",
            "false, true, 10, 2, P",
            "false, true, 10, 5, P",
            "false, true, 10, 10, P",
            "true, true, 0, 0, P",
            "true, true, 0, 10, P",
            "true, true, 2, 10, P",
            "true, true, 5, 10, P",
            "true, true, 10, 0, P",
            "true, true, 10, 2, P",
            "true, true, 10, 5, P",
            "true, true, 10, 10, P"
    })
    void recommendModel_DifferentParamCombination_ShouldReturnExpectedModel(boolean requiresNoAds, boolean requiresUltraHD, int devicesToWatch, int devicesToDownload, String expectedResult){
        String actualResult = SubscriptionRecommendationService.recommendModel(requiresNoAds, devicesToWatch, requiresUltraHD, devicesToDownload);

        assertEquals(expectedResult, actualResult);
    }

    private void setupModelParams(boolean requiresNoAds, boolean requiresUltraHD, int devicesToWatch, int devicesToDownload){
        this.requiresNoAds = requiresNoAds;
        this.requiresUltraHD = requiresUltraHD;
        this.devicesToWatch = devicesToWatch;
        this.devicesToDownload = devicesToDownload;
    }

    private void setAllParamsToFalseAndZero() {
        setupModelParams(false, false, 0, 0);
    }

    private void setupParamsForPremiumModel() {
        setupModelParams(true, true, 4, 6);
    }

    private void setupParamsForStandardModel() {
        setupModelParams(true, false, 2, 2);
    }

    private void setupParamsForBasicModel() {
        setupModelParams(true, false, 2, 1);
    }

    private void setupParamsForDefaultModel() {
        setupModelParams(false, false, 2, 0);
    }
}
