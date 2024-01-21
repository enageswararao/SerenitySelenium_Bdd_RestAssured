package example.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

;
@DefaultUrl("https://www.gov.uk/check-uk-visa")
public class StartPage extends PageObject {

        //@FindBy(css = "#get-started > a")
@FindBy(css=".gem-c-button.govuk-button.govuk-button--start")
        private WebElementFacade startNowButton;

        public void clickStartNow(){
            startNowButton.waitUntilClickable().click();
            //clickOn(startNowButton);
        }

}
