package page;

import Listeners.TestListener;
import Utils.AppActionDriver;
import Utils.PropertiesFile;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FixturesPage extends AppActionDriver {

    AndroidDriver driver;
    Logger logger= LogManager.getLogger(HomePage.class);
    PropertiesFile propertiesFile;
    public FixturesPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        propertiesFile= new PropertiesFile("data.properties");
        this.driver = driver;
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Fixtures']/android.widget.ImageView")
    private WebElement fixtureButton;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS'])[1]//parent::android.view.ViewGroup")
    private WebElement fixtureCard;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'RECENT')]")
    private WebElement recentTab;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='TODAY']")
    private WebElement todayTab;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='UPCOMING']")
    private WebElement upcomingTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All']")
    private WebElement allFilter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='International']")
    private WebElement internationalFilter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Domestic']")
    private WebElement domesticFilter;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='League'])[1]")
    private WebElement leagueFilter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cup']")
    private WebElement cupFilter;

    @AndroidFindBy(xpath = "((//android.widget.TextView[@text='VS'])[1]//following-sibling::android.view.ViewGroup/following-sibling::android.widget.TextView)[1]")
    private WebElement team1FixtureCard;

    @AndroidFindBy(xpath = "((//android.widget.TextView[@text='VS'])[1]//following-sibling::android.view.ViewGroup/following-sibling::android.widget.TextView)[3]")
    private WebElement team2FixtureCard;


    @AndroidFindBy(xpath = "((//android.widget.TextView[@text='VS'])[1]//following-sibling::android.view.ViewGroup/following-sibling::android.widget.TextView)[2]")
    private WebElement scoreFixtureCard;

    @AndroidFindBy(xpath = "((//android.widget.TextView[@text='VS'])[1]//following-sibling::android.view.ViewGroup/following-sibling::android.widget.TextView)[4]")
    private WebElement venueFixtureCard;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS'])[1]//preceding-sibling::android.view.ViewGroup[2]/following-sibling::android.widget.TextView[1]")
    private WebElement resultFixtureCard;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Details']/following-sibling::android.widget.ImageView[2]/following-sibling::android.widget.TextView[1]")
    private WebElement team1FixtureDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Details']/following-sibling::android.widget.ImageView[2]/following-sibling::android.widget.TextView[4]")
    private WebElement team2FixtureDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Details']/following-sibling::android.widget.ImageView[2]/following-sibling::android.widget.TextView[2]")
    private WebElement scoreFixtureDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Details']/following-sibling::android.widget.ImageView[2]/following-sibling::android.widget.TextView[3]")
    private WebElement venueFixtureDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Details']/following-sibling::android.view.ViewGroup[2]/following-sibling::android.widget.TextView[1]")
    private WebElement resultFixtureDetails;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Stats']")
    private WebElement matchStatsTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Discussion']")
    private WebElement matchDiscussionTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Predict Final Score']/following-sibling::android.widget.TextView[1]")
    private WebElement discussionTeam1;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Predict Final Score']/following-sibling::android.widget.TextView[2]")
    private WebElement discussionTeam2;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Discussions']")
    private WebElement discussionsTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Predictions']/following-sibling::android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[3]")
    private WebElement predictionsCardScore;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS']/preceding-sibling::android.view.ViewGroup//android.widget.ImageView)[2]")
    private WebElement decreasePredictScoreTeam1;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS']/preceding-sibling::android.view.ViewGroup//android.widget.ImageView)[3]")
    private WebElement increasePredictScoreTeam1;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS']/following-sibling::android.view.ViewGroup//android.widget.ImageView)[1]")
    private WebElement decreasePredictScoreTeam2;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='VS']/following-sibling::android.view.ViewGroup//android.widget.ImageView)[2]")
    private WebElement increasePredictScoreTeam2;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit Prediction']")
    private WebElement submitPredictionButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'No Matches found')]")
    private WebElement noMatchesText;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'No matches scheduled')]")
    private WebElement noTodayMatchesText;

    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<WebElement> upcomingDates;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Match Summary']")
    private WebElement matchSummaryField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Community Opens']")
    private WebElement communityOpensField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Kickoff']")
    private WebElement kickoffField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Community Closes']")
    private WebElement communityClosesField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Head to Head']")
    private WebElement headToHeadField;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Meetings')]")
    private WebElement lastMeetingsField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Current Form']")
    private WebElement currentFormField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='League Position']")
    private WebElement leaguePositionField;

    public FixturesPage clickFixtures()
    {
        TestListener.test.log(Status.INFO, "Clicking Fixtures button");
        click(fixtureButton);
        return this;
    }

    public boolean checkFixtureCard()
    {
        TestListener.test.log(Status.INFO, "check fixture card is present");
        return isDisplayed(fixtureCard);
    }

    public FixturesPage clickFixtureCard()
    {
        TestListener.test.log(Status.INFO, "click fixture card");
        click(fixtureCard);
        return this;
    }

    public FixturesPage clickRecentTab()
    {
        TestListener.test.log(Status.INFO, "Clicking Recent tab");
        click(recentTab);
        return this;
    }

    public FixturesPage clickTodayTab()
    {
        TestListener.test.log(Status.INFO, "Clicking Today tab");
        click(todayTab);
        return this;
    }

    public FixturesPage clickUpcomingTab()
    {
        TestListener.test.log(Status.INFO, "Clicking Upcoming tab");
        click(upcomingTab);
        return this;
    }

    public FixturesPage clickAllFilter()
    {
        TestListener.test.log(Status.INFO, "Clicking All filter");
        click(allFilter);
        return this;
    }

    public FixturesPage clickInternationalFilter()
    {
        TestListener.test.log(Status.INFO, "Clicking International filter");
        click(internationalFilter);
        return this;
    }

    public FixturesPage clickDomesticFilter()
    {
        TestListener.test.log(Status.INFO, "Clicking Domestic filter");
        click(domesticFilter);
        return this;
    }

    public FixturesPage clickLeagueFilter()
    {
        TestListener.test.log(Status.INFO, "Clicking League filter");
        click(leagueFilter);
        return this;
    }

    public FixturesPage clickCupFilter()
    {
        TestListener.test.log(Status.INFO, "Clicking Cup filter");
        click(cupFilter);
        return this;
    }

    public String getTeam1FromFixtureCard()
    {
        TestListener.test.log(Status.INFO,"get team1 from fixture card");
        return getText(team1FixtureCard);
    }

    public String getTeam2FromFixtureCard()
    {
        TestListener.test.log(Status.INFO,"get team2 from fixture card");
        return getText(team2FixtureCard);
    }

    public String getScoreFromFixtureCard()
    {
        TestListener.test.log(Status.INFO,"get score from fixture card");
        return getText(scoreFixtureCard);
    }

    public String getVenueFromFixtureCard()
    {
        TestListener.test.log(Status.INFO,"get venue from fixture card");
        return getText(venueFixtureCard);
    }

    public String getResultFromFixtureCard()
    {
        TestListener.test.log(Status.INFO,"get result from fixture card");
        return getText(resultFixtureCard);
    }

    public String getTeam1FromDetails()
    {
        TestListener.test.log(Status.INFO,"get team1 from details");
        return getText(team1FixtureDetails);
    }

    public String getTeam2FromDetails()
    {
        TestListener.test.log(Status.INFO,"get team2 from details");
        return getText(team2FixtureDetails);
    }

    public String getScoreFromDetails()
    {
        TestListener.test.log(Status.INFO,"get score from details");
        return getText(scoreFixtureDetails);
    }

    public String getVenueFromDetails()
    {
        TestListener.test.log(Status.INFO,"get venue from details");
        return getText(venueFixtureDetails);
    }

    public String getResultFromDetails()
    {
        TestListener.test.log(Status.INFO,"get result from details");
        return getText(resultFixtureDetails);
    }

    public boolean checkMatchStatsTab()
    {
        TestListener.test.log(Status.INFO, "Checking Match Stats tab");
        return isDisplayed(matchStatsTab);
    }

    public FixturesPage clickMatchDiscussionTab()
    {
        TestListener.test.log(Status.INFO, "Clicking Match Discussion tab");
        click(matchDiscussionTab);
        return this;
    }

    public boolean clickDiscussionsTab()
    {
        TestListener.test.log(Status.INFO, "Check Discussions tab");
        return isDisplayed(discussionsTab);
    }

    public String getDiscussionTeam1()
    {
        TestListener.test.log(Status.INFO,"get discussion team1");
        return getText(discussionTeam1);
    }

    public String getDiscussionTeam2()
    {
        TestListener.test.log(Status.INFO,"get discussion team2");
        return getText(discussionTeam2);
    }

    public boolean checkNoMatches()
    {
        TestListener.test.log(Status.INFO,"no matches displayed");
        return isDisplayed(noMatchesText);
    }

    public boolean checkNoTodayMatches()
    {
        TestListener.test.log(Status.INFO,"no matches displayed");
        return isDisplayed(noTodayMatchesText);
    }

    public String getNoMatchesText()
    {
        TestListener.test.log(Status.INFO,"get no matches text");
        return getText(noMatchesText);
    }

    public String getNoTodayMatchesText()
    {
        TestListener.test.log(Status.INFO,"get no matches text");
        return getText(noTodayMatchesText);
    }

    public FixturesPage clickIncreaseTeam1PredictScore()
    {
        TestListener.test.log(Status.INFO,"increase team 1 score prediction");
        click(increasePredictScoreTeam1);
        return this;
    }

    public FixturesPage clickIncreaseTeam2PredictScore()
    {
        TestListener.test.log(Status.INFO,"increase team 2 score prediction");
        click(increasePredictScoreTeam2);
        return this;
    }

    public FixturesPage clickDecreaseTeam1PredictScore()
    {
        TestListener.test.log(Status.INFO,"decrease team 1 score prediction");
        click(decreasePredictScoreTeam1);
        return this;
    }

    public FixturesPage clickDecreaseTeam2PredictScore()
    {
        TestListener.test.log(Status.INFO,"decrease team 2 score prediction");
        click(decreasePredictScoreTeam2);
        return this;
    }

    public FixturesPage submitPredictions()
    {
        TestListener.test.log(Status.INFO, "submit predictions");
        click(submitPredictionButton);
        return this;
    }

    public String getPredictionScore()
    {
        TestListener.test.log(Status.INFO,"getting predicted score");
        return getText(predictionsCardScore);
    }

    public FixturesPage clickNextUpcomingDate(int index) throws InterruptedException {
        TestListener.test.log(Status.INFO,"Clicking next upcoming date");
        Thread.sleep(3000);
        click(upcomingDates.get(index));
        return this;
    }

    public boolean checkMatchDiscussionButton()
    {
        TestListener.test.log(Status.INFO, "check presence of match discussion tab");
        return isDisplayed(matchDiscussionTab);
    }

    public boolean checkMatchSummaryField()
    {
        TestListener.test.log(Status.INFO,"check presence of match summary field");
        scrollToElement(matchSummaryField);
        return isDisplayed(matchSummaryField);
    }

    public boolean checkCommunityOpenField()
    {
        TestListener.test.log(Status.INFO, "check presence of community open field");
        scrollToElement(communityOpensField);
        return isDisplayed(communityOpensField);
    }

    public boolean checkKickOffField()
    {
        TestListener.test.log(Status.INFO, "check presence of kick off field");
        scrollToElement(kickoffField);
        return isDisplayed(kickoffField);
    }


    public boolean checkCommunityCloseField()
    {
        TestListener.test.log(Status.INFO, "check presence of community closes field");
        scrollToElement(communityClosesField);
        return isDisplayed(communityClosesField);
    }


    public boolean checkHeadToHeadField()
    {
        TestListener.test.log(Status.INFO, "check presence of head to head field");
        scrollToElement(headToHeadField);
        return isDisplayed(headToHeadField);
    }

    public boolean checkLastMeetings()
    {
        TestListener.test.log(Status.INFO, "check presence of last meetings field");
        scrollToElement(lastMeetingsField);
        return isDisplayed(lastMeetingsField);
    }

    public boolean checkCurrentForm()
    {
        TestListener.test.log(Status.INFO, "check current form field");
        scrollToElement(currentFormField);
        return isDisplayed(currentFormField);
    }

    public boolean checkLeaguePosition()
    {
        TestListener.test.log(Status.INFO,"check league position field");
        scrollToElement(leaguePositionField);
        return isDisplayed(leaguePositionField);
    }


}
