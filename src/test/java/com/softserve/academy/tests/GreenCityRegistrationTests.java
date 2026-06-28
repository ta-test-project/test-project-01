package com.softserve.academy.tests;

import com.softserve.academy.core.BaseTest;
import com.softserve.academy.data.User;
import com.softserve.academy.data.UserRepository;
import com.softserve.academy.pages.RegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GreenCityValidRegistrationTest extends BaseTest {

    @ParameterizedTest(name = "Data from file - Email: {0}")
    @DisplayName("Check registration via CsvFileSource (external file)")
    @CsvFileSource(resources = "/registration_data.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String email, String name, String password) {

        User user = UserRepository.createWithUniqueEmail(email, name, password, password);
        RegistrationService registrationService = new RegistrationService(driver, wait);


        registrationService.openRegistrationModal()
                .fillRegistrationForm(user)
                .submitRegistration();


        String msg = registrationService.getSuccessMessageText();
        String expectedSnippet = "successfully registered";

        assertTrue(msg.contains(expectedSnippet), "Success message should contain: '" + expectedSnippet + "'");
    }
}