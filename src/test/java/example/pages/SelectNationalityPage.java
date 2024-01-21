package example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class SelectNationalityPage extends PageObject {

    @FindBy(css = "#response")
    private WebElementFacade nationalityDropDownList;

    @FindBy(css = "#current-question > button")
    private WebElementFacade nextStepButton;
    
    public void selectNationality(String nationality){
        selectFromDropdown(nationalityDropDownList, nationality);
    }

    public void clickNextStepButton(){
        clickOn(nextStepButton);
    }
}
