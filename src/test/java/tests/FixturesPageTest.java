package tests;

import Utils.AppBaseSetup;
import Utils.Assertions;
import Utils.PropertiesFile;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.FixturesPage;
import page.HomePage;
import page.LoginPage;
import page.SignUpPage;

public class FixturesPageTest extends AppBaseSetup {
    HomePage homePage;

    LoginPage loginPage;
    SignUpPage signUpPage;

    FixturesPage fixturesPage;
    PropertiesFile propertiesFile;
    Assertions assertions;

    String team1 = "";
    String team2 = "";
    String score = "";
    String venue = "";
    String result = "";



    @BeforeMethod
    public void HomePageObject() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        fixturesPage = new FixturesPage(driver);
        propertiesFile = new PropertiesFile("data.properties");
        assertions = new Assertions();
    }


    @Test(description = "1 | verify fixture card", priority = 0, enabled = true)
    public void verifyFixtureCard() throws InterruptedException {
//        signUpPage.clickOnContinueButton();
//        signUpPage.enterEmailForSignup("harsh.sn727254@gmail.com");
//        signUpPage.clickContinueSignUpProcess();
////        signUpPage.enterOTP();
//        signUpPage.hideKeyboard();
//        signUpPage.clickContinueSignUpProcess();
        fixturesPage.clickFixtures();
//        assertions.assertTrue(fixturesPage.checkFixtureCard());
//        assertions.assertAll();
    }

    @Test(description = "2 | verify fixture card details recent", priority = 1, enabled = true)
    public void verifyTeamScoreVenueInRecentFixtureCard() throws InterruptedException {
        fixturesPage.clickRecentTab();
        Thread.sleep(3000);
        team1 = fixturesPage.getTeam1FromFixtureCard();
        team2 = fixturesPage.getTeam2FromFixtureCard();
        score = fixturesPage.getScoreFromFixtureCard();
        venue = fixturesPage.getVenueFromFixtureCard();
        result = fixturesPage.getResultFromFixtureCard();
        fixturesPage.clickFixtureCard();
        assertions.assertTrue(fixturesPage.checkMatchStatsTab());
        assertions.assertEquals(team1, fixturesPage.getTeam1FromDetails());
        assertions.assertEquals(team2, fixturesPage.getTeam2FromDetails());
        assertions.assertEquals(score, fixturesPage.getScoreFromDetails());
        assertions.assertTrue(venue.contains(fixturesPage.getVenueFromDetails()));
        assertions.assertEquals(result, fixturesPage.getResultFromDetails());
        assertions.assertAll();
    }

    @Test(description = "3 | verify fixture card details recent", priority = 2, enabled = true)
    public void verifyTeamInRecentFixtureDetailsDiscussion() throws InterruptedException {
        fixturesPage.clickMatchDiscussionTab();
        assertions.assertEquals(team1, fixturesPage.getDiscussionTeam1());
        assertions.assertEquals(team2, fixturesPage.getDiscussionTeam2());
        assertions.assertAll();
    }

    @Test(description = "4 | verify prediction card score details recent", priority = 3, enabled = true)
    public void verifyScorePredictionInRecentFixtureCardDiscussionTeam1Favour() throws InterruptedException {
        fixturesPage.clickIncreaseTeam1PredictScore();
        fixturesPage.clickIncreaseTeam1PredictScore();
        fixturesPage.clickIncreaseTeam2PredictScore();
        fixturesPage.submitPredictions();
        Thread.sleep(2000);
        assertions.assertEquals(fixturesPage.getPredictionScore(), "2 - 1");
        assertions.assertAll();
    }

    @Test(description = "5 | verify prediction card score details recent", priority = 4, enabled = true)
    public void verifyScorePredictionInRecentFixtureCardDiscussionTeam2Favour() throws InterruptedException {
        fixturesPage.clickDecreaseTeam1PredictScore();
        fixturesPage.clickDecreaseTeam1PredictScore();
        fixturesPage.clickDecreaseTeam2PredictScore();
        fixturesPage.clickIncreaseTeam2PredictScore();
        fixturesPage.clickIncreaseTeam2PredictScore();
        fixturesPage.clickIncreaseTeam2PredictScore();
        fixturesPage.submitPredictions();
        Thread.sleep(2000);
        assertions.assertEquals(fixturesPage.getPredictionScore(), "0 - 3");
        assertions.assertAll();
    }

    @Test(description = "6 | verify fixture card details today", priority = 5, enabled = true)
    public void verifyTeamScoreVenueInTodayFixtureCard() throws InterruptedException {
        fixturesPage.navigateBack();
        fixturesPage.clickTodayTab();
        if(fixturesPage.checkNoMatches())
        {
            throw new SkipException("No matches today");
        }
        else {
            Thread.sleep(3000);
            team1 = fixturesPage.getTeam1FromFixtureCard();
            team2 = fixturesPage.getTeam2FromFixtureCard();
            score = fixturesPage.getScoreFromFixtureCard();
            venue = fixturesPage.getVenueFromFixtureCard();
            fixturesPage.clickFixtureCard();
            assertions.assertTrue(fixturesPage.checkMatchStatsTab());
            assertions.assertEquals(team1, fixturesPage.getTeam1FromDetails());
            assertions.assertEquals(team2, fixturesPage.getTeam2FromDetails());
            assertions.assertTrue(venue.contains(fixturesPage.getVenueFromDetails()));
            assertions.assertAll();
        }
    }

    @Test(description = "7 | verify fixture card details today", priority = 6, enabled = true)
    public void verifyTeamInTodayFixtureDetailsDiscussion() throws InterruptedException {
        if(fixturesPage.checkNoMatches())
        {
            throw new SkipException("No matches today");
        }
        else {
            fixturesPage.clickMatchDiscussionTab();
            assertions.assertEquals(team1, fixturesPage.getDiscussionTeam1());
            assertions.assertEquals(team2, fixturesPage.getDiscussionTeam2());
            assertions.assertAll();
        }
    }

    @Test(description = "8 | verify prediction card score details today", priority = 7, enabled = true)
    public void verifyScorePredictionInTodayFixtureCardDiscussionTeam1Favour() throws InterruptedException {
        if(fixturesPage.checkNoMatches())
        {
            throw new SkipException("No matches today");
        }
        else {
            fixturesPage.clickIncreaseTeam1PredictScore();
            fixturesPage.clickIncreaseTeam1PredictScore();
            fixturesPage.clickIncreaseTeam2PredictScore();
            fixturesPage.submitPredictions();
            Thread.sleep(2000);
            assertions.assertEquals(fixturesPage.getPredictionScore(), "2 - 1");
            assertions.assertAll();
        }
    }

    @Test(description = "9 | verify prediction card score details today", priority = 8, enabled = true)
    public void verifyScorePredictionInTodayFixtureCardDiscussionTeam2Favour() throws InterruptedException {
        if(fixturesPage.checkNoMatches())
        {
            throw new SkipException("No matches today");
        }
        else {
            fixturesPage.clickDecreaseTeam1PredictScore();
            fixturesPage.clickDecreaseTeam1PredictScore();
            fixturesPage.clickDecreaseTeam2PredictScore();
            fixturesPage.clickIncreaseTeam2PredictScore();
            fixturesPage.clickIncreaseTeam2PredictScore();
            fixturesPage.clickIncreaseTeam2PredictScore();
            fixturesPage.submitPredictions();
            Thread.sleep(2000);
            assertions.assertEquals(fixturesPage.getPredictionScore(), "0 - 3");
            assertions.assertAll();
        }
    }

    @Test(description = "10 | verify fixture card details upcoming", priority = 9, enabled = true)
    public void verifyTeamScoreVenueInUpcomingFixtureCard() throws InterruptedException {
        fixturesPage.navigateBack();
        fixturesPage.clickUpcomingTab();
        int i = 0;
        while(fixturesPage.checkNoMatches())
        {
            fixturesPage.clickNextUpcomingDate(i);
            i++;
        }
        team1 = fixturesPage.getTeam1FromFixtureCard();
        team2 = fixturesPage.getTeam2FromFixtureCard();
        score = fixturesPage.getScoreFromFixtureCard();
        venue = fixturesPage.getVenueFromFixtureCard();
        fixturesPage.clickFixtureCard();
        assertions.assertEquals(team1, fixturesPage.getTeam1FromDetails());
        assertions.assertEquals(team2, fixturesPage.getTeam2FromDetails());
        assertions.assertTrue(venue.contains(fixturesPage.getVenueFromDetails()));
        assertions.assertAll();
    }

    @Test(description = "11 | verify fixture card details upcoming", priority = 10, enabled = true)
    public void verifyUpcomingFixtureDetailsDiscussion() throws InterruptedException {
        assertions.assertTrue(fixturesPage.checkMatchDiscussionButton());
        assertions.assertTrue(fixturesPage.checkMatchSummaryField());
        assertions.assertTrue(fixturesPage.checkCommunityOpenField());
        assertions.assertAll();
    }

    @Test(description = "12 | verify prediction card score details upcoming", priority = 11, enabled = true)
    public void verifyUpcomingFixtureMatchSummary() throws InterruptedException {
        assertions.assertTrue(fixturesPage.checkKickOffField());
        assertions.assertTrue(fixturesPage.checkCommunityCloseField());
        assertions.assertAll();
    }

    @Test(description = "13 | verify prediction card score details upcoming", priority = 12, enabled = true)
    public void verifyUpcomingFixtureHeadToHead() throws InterruptedException {
        assertions.assertTrue(fixturesPage.checkHeadToHeadField());
        assertions.assertTrue(fixturesPage.checkLastMeetings());
        assertions.assertTrue(fixturesPage.checkCurrentForm());
        assertions.assertTrue(fixturesPage.checkLeaguePosition());
        assertions.assertAll();
    }




}
