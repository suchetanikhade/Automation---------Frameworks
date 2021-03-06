package com.uat.suite.triage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.uat.base.TestBase;
import com.uat.util.TestUtil;
public class TestSuiteBase extends TestBase 
{
	// check if Triage Suite has to be skipped
	@BeforeSuite
	public void checkSuiteSkip() throws Exception
	{
		initialize();
		APP_LOGS.debug("Checking Runmode of Traige Suite");
		if(!TestUtil.isSuiteRunnable(suiteXls, "Triage Suite"))
		{
			APP_LOGS.debug("Skipped Triage Suite  as the runmode was set to NO");
			throw new SkipException("Runmode of Triage Suite set to NO ... So Skipping all tests in Triage Suite");
		}
	}

	//common function for creating project
	public boolean createProject(String group, String portfolio, String project, String version, String endMonth, String endYear, String endDate, String versionLead ) throws IOException, InterruptedException
	{
		APP_LOGS.debug("Creating Projects.");
					
		//wait.until(ExpectedConditions.presenceOfElementLocated())

		getObject("Projects_createNewProjectLink").click();
		try 
		{
			dropDownSelectAdd(getElement("ProjectCreateNew_groupDropDown_Id"),getObject("ProjectCreateNew_groupAddButton"), group );
							
			dropDownSelectAdd(getElement("ProjectCreateNew_PortfolioDropDown_Id"),getObject("ProjectCreateNew_portfolioAddButton"), portfolio );
							
			dropDownSelectAdd(getElement("ProjectCreateNew_projectDropDown_Id"),getObject("ProjectCreateNew_projectAddButton"), project );
							
			getObject("ProjectCreateNew_versionTextField").sendKeys(version);
			//getElement("Version").sendKeys(version);
							
			selectStartOrEndDate(getObject("ProjectCreateNew_endDateImage"),endMonth,endYear, endDate);
							
			getObject("ProjectCreateNew_versionLeadPeoplePickerImg").click();
							   
			Thread.sleep(500);driver.switchTo().frame(1);
							
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_PlaceHolderDialogBodySection_ctl07_queryTextBox']")));
												
			getObject("ProjectCreateNew_versionLeadStakeholderTextField").sendKeys(versionLead); 
							   
			getObject("ProjectCreateNew_versionLeadStakeholderSearchBtn").click();
					   
			getObject("ProjectCreateNew_versionLeadStakeholderSelectSearchResult").click();
					   
			getObject("ProjectCreateNew_versionLeadStakeholderOkBtn").click();
					   
			driver.switchTo().defaultContent();
							
			getObject("ProjectCreateNew_projectSaveBtn").click();
							
			Thread.sleep(2000);
							
			if (getElement("ProjectCreateNew_projectSuccessMessageText_Id").getText().contains("successfully")) 
			{
				getObject("ProjectCreateNew_projectSuccessPopUpOkBtn").click();
				return true;
			}
			else 
			{
				return false;
			}
		} 
		catch (Throwable e) 
		{
			APP_LOGS.debug("EXception in createProject function.");
			e.printStackTrace();
			return false;
		}
	}
				
	// Create Test Pass for that project:
	public boolean createTestPass(String group, String portfolio, String project, String version, String testPassName, 
		String endMonth, String endYear, String endDate, String testManager ) throws IOException, InterruptedException
	{
		APP_LOGS.debug("Creating Test Pass");
		Thread.sleep(2000);
		getElement("TestPassNavigation_Id").click();
		Thread.sleep(2000);
		getObject("TestPasses_createNewProjectLink").click();
					
		try 
		{
			dropDownSelect(getElement("TestPassUpperRibbon_groupDropDown_Id"), getElement("TestPassUpperRibbon_GroupList_Id"), group );
							
			dropDownSelect(getElement("TestPassUpperRibbon_PortfolioDropDown_Id"), getElement("TestPassUpperRibbon_PortfolioList_Id"), portfolio );
							
			dropDownSelect(getElement("TestPassUpperRibbon_projectDropDown_Id"), getElement("TestPassUpperRibbon_ProjectList_Id"), project );
							
			dropDownSelect(getElement("TestPassUpperRibbon_versionDropDown_Id"), getElement("TestPassUpperRibbon_VersionList_Id"), version );
			//getElement("Version").sendKeys(version);
			getElement("TestPassCreateNew_testPassNameTextField_Id").sendKeys(testPassName);
							
			getObject("TestPassCreateNew_testManagerPeoplePickerImg").click();
							   
			Thread.sleep(500);driver.switchTo().frame(1);
							
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_PlaceHolderDialogBodySection_ctl07_queryTextBox']")));
							
			getObject("TestPassCreateNew_testManagerPeoplePickerTextField").sendKeys(testManager); 
							   
			getObject("TestPassCreateNew_testManagerPeoplePickerSearchBtn").click();
					   
			getObject("TestPassCreateNew_testManagerPeoplePickerSelectSearchResult").click();
					   
			getObject("TestPassCreateNew_testManagerPeoplePickerOkBtn").click();
					   
			driver.switchTo().defaultContent();
							
			selectStartOrEndDate(getObject("TestPassCreateNew__endDateImage"),endMonth,endYear, endDate);
							
			getObject("TestPassCreateNew_testPassSaveBtn").click();
							
			Thread.sleep(2000);							
							
			if (getElement("TestPassCreateNew_testPassSuccessMessageText_Id").getText().contains("successfully")) 
			{
				getObject("TestPassCreateNew_testPassaddedsuccessfullyOkButton").click();
				return true;
			}
			else 
			{
				return false;
			}
		} 
		catch (Throwable e) 
		{
			APP_LOGS.debug("Exception in createProject function.");
			e.printStackTrace();
			return false;
		}
	}
				
	public boolean createTester(String group, String portfolio, String project, String version, String testPassName, 
		String tester, String testerRoleCreation, String testerRoleSelection) throws IOException, InterruptedException
	{
		APP_LOGS.debug("Creating Tester");
		Thread.sleep(2000);
		getElement("TesterNavigation_Id").click();
					
		if(getElement("TesterCreateNew_TesterActiveX_Id").isDisplayed())
		{
			getObject("TesterCreateNew_TesterActiveX_Close").click();
		}
		Thread.sleep(2000);
					
		try 
		{
			dropDownSelect(getElement("TesterUpperRibbon_groupDropDown_Id"), getElement("TesterUpperRibbon_GroupList_Id"), group );
							
			dropDownSelect(getElement("TesterUpperRibbon_PortfolioDropDown_Id"), getElement("TesterUpperRibbon_PortfolioList_Id"), portfolio );
							
			dropDownSelect(getElement("TesterUpperRibbon_projectDropDown_Id"), getElement("TesterUpperRibbon_ProjectList_Id"), project );
							
			dropDownSelect(getElement("TesterUpperRibbon_versionDropDown_Id"), getElement("TesterUpperRibbon_VersionList_Id"), version );
							
			dropDownSelect(getElement("TesterUpperRibbon_testPassDropDown_Id"), getElement("TesterUpperRibbon_TestPassList_Id"), testPassName );
			
			getElement("Tester_createNewProjectLink_Id").click();
			Thread.sleep(1000);
			
			getObject("TesterCreateNew_PeoplePickerImg").click();
							   
			Thread.sleep(500);driver.switchTo().frame(1);
							
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_PlaceHolderDialogBodySection_ctl07_queryTextBox']")));
							
			getObject("TesterCreateNew_PeoplePickerTextField").sendKeys(tester); 
							   
			getObject("TesterCreateNew_PeoplePickerSearchBtn").click();
					   
			getObject("TesterCreateNew_PeoplePickerSelectSearchResult").click();
					   
			getObject("TesterCreateNew_PeoplePickerOkBtn").click();
					   
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			String[] testerRoleArray = testerRoleCreation.split(",");
							
			for(int i=0;i<testerRoleArray.length;i++)
			{
				if((!testerRoleArray[i].equals("Standard"))&&(!testerRoleArray[i].equals("All")))
				{
					getElement("TesterCreateNew_addTesterRoleLink_Id").click();
					getElement("TesterCreateNew_roleName_Id").sendKeys(testerRoleArray[i]);
					getElement("TesterCreateNew_addTesterRole_Id").click();
				}
			}
			String[] testerRoleSelectionArray = testerRoleSelection.split(",");

			List<WebElement> roleSelectionNames = getObject("TesterCreateNew_SelectionRoleList").findElements(By.tagName("li"));
			int numOfRoles = roleSelectionNames.size();
			for(int i = 0; i<numOfRoles;i++)
			{
				for(int j = 0; j < testerRoleSelectionArray.length;j++)
				{
					if((roleSelectionNames.get(i)).getAttribute("title").equals(testerRoleSelectionArray[j]))
					{
						getObject("TesterCreateNew_SelectionRoleList_Xpath1", "TesterCreateNew_SelectionRoleList_Xpath2", (i+1)).click();
					}
				}
			}
			getElement("TesterCreateNew_testerSaveBtn_Id").click();
			Thread.sleep(2000);
							
			if (getElement("TesterCreateNew_testerSuccessMessageText_Id").getText().contains("successfully")) 
			{
				getObject("Tester_testeraddedsuccessfullyOkButton").click();
				return true;
			}
			else 
			{
				return false;
			}
		} 
		catch (Throwable e) 
		{
			APP_LOGS.debug("Exception in createTester function.");
			e.printStackTrace();
			return false;
		}
	}

	public boolean createTestCase(String group, String portfolio, String project, String version, String testPassName, 
			String testCaseName) throws IOException, InterruptedException
	{
		APP_LOGS.debug("Creating Test Case");
		Thread.sleep(2000);
		getElement("TestCaseNavigation_Id").click();
		if(getElement("TestCaseCreateNew_TestCaseActiveX_Id").isDisplayed())
		{
			getObject("TestCaseCreateNew_TestCaseActiveX_Close").click();
		}
		Thread.sleep(2000);

		try {
			
				dropDownSelect(getElement("TestCaseUpperRibbon_groupDropDown_Id"), getElement("TestCaseUpperRibbon_GroupList_Id"), group );
				
				dropDownSelect(getElement("TestCaseUpperRibbon_PortfolioDropDown_Id"), getElement("TestCaseUpperRibbon_PortfolioList_Id"), portfolio );
				
				dropDownSelect(getElement("TestCaseUpperRibbon_projectDropDown_Id"), getElement("TestCaseUpperRibbon_ProjectList_Id"), project );
				
				dropDownSelect(getElement("TestCaseUpperRibbon_versionDropDown_Id"), getElement("TestCaseUpperRibbon_VersionList_Id"), version );
				
				dropDownSelect(getElement("TestCaseUpperRibbon_testPassDropDown_Id"), getElement("TestCaseUpperRibbon_TestPassList_Id"), testPassName );
				
				getObject("TestCase_createNewProjectLink").click();
				
				Thread.sleep(1000);
				
				getElement("TestCaseCreateNew_TestCaseNameTextField_Id").sendKeys(testCaseName); 
				
				getElement("TestCaseCreateNew_testCaseSaveBtn_Id").click();
				
				Thread.sleep(2000);
				
				if (getElement("TestCaseCreateNew_testCaseSuccessMessageText_Id").getText().contains("successfully")) 
				{
					getObject("TestCase_testCaseaddedsuccessfullyOkButton").click();
					
					return true;
				}
				else 
				{
					return false;
				}
				
				
			
		} 
		catch (Throwable e) 
		{
			APP_LOGS.debug("Exception in createTestCase function.");
			e.printStackTrace();
			return false;
		}
		
	}
				
	public boolean createTestStep(String group, String portfolio, String project, String version, String testPassName, 
			String testStepName, String testStepExpectedResults, String testCasesToBeSelected, String rolesToBeSelected) throws IOException, InterruptedException
	{
		APP_LOGS.debug("Creating Test Step");
		Thread.sleep(2000);
		getElement("TestStepNavigation_Id").click();
		if(getElement("TestStepCreateNew_TestStepActiveX_Id").isDisplayed())
		{
			getObject("TestStepCreateNew_TestStepActiveX_Close").click();
		}
		Thread.sleep(2000);
		
		try {
			
				dropDownSelect(getElement("TestStepUpperRibbon_groupDropDown_Id"), getElement("TestStepUpperRibbon_GroupList_Id"), group );
				
				dropDownSelect(getElement("TestStepUpperRibbon_PortfolioDropDown_Id"), getElement("TestStepUpperRibbon_PortfolioList_Id"), portfolio );
				
				dropDownSelect(getElement("TestStepUpperRibbon_projectDropDown_Id"), getElement("TestStepUpperRibbon_ProjectList_Id"), project );
				
				dropDownSelect(getElement("TestStepUpperRibbon_versionDropDown_Id"), getElement("TestStepUpperRibbon_VersionList_Id"), version );
				
				dropDownSelect(getElement("TestStepUpperRibbon_testPassDropDown_Id"), getElement("TestStepUpperRibbon_TestPassList_Id"), testPassName );
				
				getObject("TestStep_createNewProjectLink").click();
				Thread.sleep(1000);
				
				String testStepDetails = "$(document).contents().find('#rte1').contents().find('body').text('"+testStepName+"')";
			    eventfiringdriver.executeScript(testStepDetails);
			    
			    getElement("TestStepCreateNew_testStepExpectedResults_ID").sendKeys(testStepExpectedResults); 
				
				List<WebElement> TestCaseSelectionNames = getObject("TestStepCreateNew_SelectionTestCaseList").findElements(By.tagName("li"));
				int numOfTestCases = TestCaseSelectionNames.size();
				for(int i = 0; i<numOfTestCases;i++)
				{
						if((TestCaseSelectionNames.get(i)).getAttribute("title").equals(testCasesToBeSelected))
						{
							getObject("TestStepCreateNew_SelectionTestCaseList_Xpath1", "TestStepCreateNew_SelectionTestCaseList_Xpath2", (i+1)).click();
							break;
						}
				}
				
				String[] testerRoleSelectionArray = rolesToBeSelected.split(",");
				List<WebElement> roleSelectionNames = getObject("TestStepCreateNew_SelectionRoleList").findElements(By.tagName("li"));
				int numOfRoles = roleSelectionNames.size();
				for(int i = 0; i<numOfRoles;i++)
				{
					for(int j = 0; j < testerRoleSelectionArray.length;j++)
					{
						if((roleSelectionNames.get(i)).getAttribute("title").equals(testerRoleSelectionArray[j]))
						{
							getObject("TestStepCreateNew_SelectionRoleList_Xpath1", "TestStepCreateNew_SelectionRoleList_Xpath2", (i+1)).click();
						}
					}
				}
				
				getElement("TestStepCreateNew_testStepSaveBtn_Id").click();
				
				Thread.sleep(2000);
				
				if (getElement("TestStepCreateNew_testStepSuccessMessageText_Id").getText().contains("successfully")) 
				{
					getObject("TestStep_testStepaddedsuccessfullyOkButton").click();
					return true;
				}
				else 
				{
					return false;
				}
		} 
		catch (Throwable e) 
		{
			APP_LOGS.debug("Exception in createTestStep function.");
			e.printStackTrace();
			return false;
		}
		
	}
			
	public void dropDownSelect(WebElement dropDownList,WebElement SelectionList, String selectionText) throws IOException 
	{
		dropDownList.click();
		List<WebElement> elements = SelectionList.findElements(By.tagName("li"));

		for(int i =0 ;i<elements.size();i++)
		{
			if(elements.get(i).getText().equals(selectionText))
			{
				elements.get(i).click();
				APP_LOGS.debug( selectionText + " : is selected...");
				break;
			}
		}
	}

	public void dropDownSelectAdd(WebElement dropDownList, WebElement addButton, String text) throws IOException
	{
		int flag = 0;
		try
		{
			List<WebElement> elements = dropDownList.findElements(By.tagName("option"));
					  
			for(int i =0 ;i<elements.size();i++)
			{
				if(elements.get(i).getText().equals(text))
				{
					flag++;
					elements.get(i).click();
					APP_LOGS.debug( text + " : is selected...");
					break;
				}
			}
			if(flag==0)
			{
				//Click on Plus icon to add Group or Portfolio
				APP_LOGS.debug("Clicking on Add icon ");
						
				addButton.click();
				APP_LOGS.debug("Inputting Text :" +text +" in Text Field ");
				getElement("ProjectCreateNew_groupPortfolioProjectTextField_Id").sendKeys(text);
	
				//Save the entered group or portfolio 
				if (getElement("ProjectCreateNew_projectAddPopUpLabel_Id").getText().contains("Project")) 
				{
					getObject("ProjectCreateNew_projectAddBtn").click();
				}
				else 
				{
					getObject("ProjectCreateNew_groupPortfolioSaveBtn").click(); 
				}
			}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}			

	public void selectStartOrEndDate(WebElement calendarImg, String StartEndMonth, String StartEndYear,String StartEndDate) throws IOException
	{
		try
		{
			//Select start date   
			WebElement startDateImage = calendarImg;  
	   
			APP_LOGS.debug("Clicking on Date Calendar icon...");
	   
			startDateImage.click();  
	   
			Select year = new Select(getElementByClassAttr("ProjectCreateNew_yearDropDown_Class"));
	   
			year.selectByValue(StartEndYear);
	   
			APP_LOGS.debug(StartEndYear +" : Year is selected...");
	   
			Select month = new Select(getElementByClassAttr("ProjectCreateNew_monthDropDown_Class"));
	   
			month.selectByVisibleText(StartEndMonth);
	   
			APP_LOGS.debug(StartEndMonth +" : Month is selected...");
					
			WebElement datepicker= getObject("ProjectCreateNew_dateTable");
	   
			//List<WebElement> rows = datepicker.findElements(By.tagName("tr"));
	   
			List<WebElement> cols = datepicker.findElements(By.tagName("td"));
					
			for(WebElement cell :cols)
			{
				if(cell.getText().equals(StartEndDate))
				{
					cell.findElement(By.linkText(""+StartEndDate+"")).click();
					APP_LOGS.debug(StartEndDate +" : Date is selected...");
					break;
				}
			}
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	 }			
		
	public String searchProjectAndExtractID(String project, String version) throws IOException, InterruptedException
	{
		int totalPages;
		int projectsOnPage;
		String gridProject;
		String gridVersion;
		APP_LOGS.debug("Searching Project to Extract its ID");
	  
		//click on testManagement tab
		APP_LOGS.debug(" Clicking on Test Management Tab ");
		getElement("UAT_testManagement_Id").click();
		Thread.sleep(1000);

		try
		{
			if (getElement("ProjectViewAll_Pagination_Id").findElements(By.xpath("div/span")).size()==3) 
			{
				APP_LOGS.debug("Only 1 page available on View All page.");
				totalPages=1;
			}
			else 
			{
				APP_LOGS.debug("More than 1 page avaialble on View All page. Calculating total pages.");
				totalPages = getElement("ProjectViewAll_Pagination_Id").findElements(By.xpath("div/a")).size();
			}  
	   
			for (int i = 0; i < totalPages; i++) 
			{
				projectsOnPage = getElement("ProjectViewAll_Table_Id").findElements(By.xpath("tr")).size();
	    
				for (int j = 1; j <= projectsOnPage; j++) 
				{
					gridProject=getObject("ProjectViewAll_projectNameColumn1", "ProjectViewAll_projectNameColumn2", j).getAttribute("title");
					gridVersion= getObject("ProjectViewAll_projectVersionColumn1", "ProjectViewAll_projectVersionColumn2", j).getText();
	     
					if (gridProject.equals(project) && gridVersion.equals(version)) 
					{
						String ProjectID=getObject("ProjectViewAll_projectNameColumn1", "ProjectViewAll_IDColXpath2", j).getText();
						return ProjectID;
					}
				}   
	    
				if (totalPages>1 && totalPages!=(totalPages-1)) 
				{
					getObject("ProjectViewAll_NextLink").click();
				}
			}
			APP_LOGS.debug("Project Not found in View All page");
			return " Project ID not found" ;
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			APP_LOGS.debug("Exception in searchProjectAndExtractID(). ");
			return "Exception in extracting project ID";
		}
	 }
				
	 public String getMonthNumber(String month)
	 {
		 switch (month) 
		 {
		 	case "Jan":
		     return "01";
		    case "Feb":
		     return "02";
		    case "Mar":
		     return "03";
		    case "Apr":
		     return "04";
		    case "May":
		     return "05";
		    case "Jun":
		     return "06";
		    case "Jul":
		     return "07";
		    case "Aug":
		     return "08";
		    case "Sep":
		     return "09";
		    case "Oct":
		     return "10";
		    case "Nov":
		     return "11";
		    case "Dec":
		     return "12";

		    default:
		     return null;
		 }
	 }			 
				 
	 public String searchTPAndExtractID(String group,String portfolio,String project,String version,String testPassName) throws IOException, InterruptedException
	 {
		 int totalPages;
		 int testPassesOnPage;
		 String gridTestPass;
		 // getElement("UAT_testManagement_Id").click();
		 APP_LOGS.debug("Searching Test Pass to Edit");
		 getElement("TestPassNavigation_Id").click();
	 
		 dropDownSelect(getElement("TestPassUpperRibbon_groupDropDown_Id"), getElement("TestPassUpperRibbon_GroupList_Id"), group );
		
		 dropDownSelect(getElement("TestPassUpperRibbon_PortfolioDropDown_Id"), getElement("TestPassUpperRibbon_PortfolioList_Id"), portfolio );
		
		 dropDownSelect(getElement("TestPassUpperRibbon_projectDropDown_Id"), getElement("TestPassUpperRibbon_ProjectList_Id"), project );
		
		 dropDownSelect(getElement("TestPassUpperRibbon_versionDropDown_Id"), getElement("TestPassUpperRibbon_VersionList_Id"), version );
	
		 try
		 {
	  
			 if (getElement("TestPassViewAll_Pagination_Id").findElements(By.xpath("div/span")).size()==3) 
			 {
				 APP_LOGS.debug("Only 1 page available on View All page.");
				 totalPages=1;
	    
			 }
			 else 
			 {
				 APP_LOGS.debug("More than 1 page avaialble on View All page. Calculating total pages.");
				 totalPages = getElement("testPassesOnPageViewAll_Pagination_Id").findElements(By.xpath("div/a")).size();
			 }  
	   
			 for (int i = 0; i < totalPages; i++) 
			 {
				 testPassesOnPage = getElement("TestPassViewAll_Table_Id").findElements(By.xpath("tr")).size();
	    
				 for (int j = 1; j <= testPassesOnPage; j++) 
				 {
					 gridTestPass=getObject("TestPassViewAll_testPassNameXpath1", "TestPassViewAll_testPassNameXpath2", j).getAttribute("title");
	     
					 if (gridTestPass.equals(testPassName)) 
					 {
						 String testPassID=getObject("TestPassViewAll_editProjectIcon1", "ProjectViewAll_IDColXpath2", j).getText();
						 return testPassID;
					 }
				 }   
	    
				 if (totalPages>1 && i!=(totalPages-1)) 
				 {
					 getObject("TestPassViewAll_NextLink").click();
				 }
			 }
			 APP_LOGS.debug("Test Pass Not found in View All page");
	   
			 return "Test Pass not found";
		 }
		 catch(Throwable t)
		 {
			 t.printStackTrace();
			 APP_LOGS.debug("Exception in searchTPAndExtractID(). ");
			 return "Exception in searchTPAndExtractID()";
		 }
	 }			 
		
	//Function
	 public void clickOnPASSRadioButtonAndSave() throws IOException, InterruptedException
	{
		getElement("TestingPage_passRadioButton_Id").click();
		Thread.sleep(500);
							
		getElement("TestingPage_saveButton_Id").click();
		Thread.sleep(500);
	}		
				
	public void clickOnFAILRadioButtonAndSave() throws IOException, InterruptedException
	{
		getElement("TestingPage_failRadioButton_Id").click();
		Thread.sleep(500);
							
		getElement("TestingPage_saveButton_Id").click();
	}	
	
	public boolean isElementExistsByXpath(String key)
	{
		try
		{
			eventfiringdriver.findElement(By.xpath(OR.getProperty(key))).isDisplayed();
			return true;
		}
		catch(Throwable t)
		{
			return false;
		}
	}
		
	public boolean isElementExistsById(String key)
	{
		try
		{
			eventfiringdriver.findElement(By.id(OR.getProperty(key))).isDisplayed();
			return true;
		}
		catch(Throwable t)
		{
			return false;
		}
	}
		
	public boolean searchTestPassAndClickOnBeginOrContinueTesting(String project, String version,String testPass) throws IOException, InterruptedException
	{
		int totalPages;
		int projectsInTable;
		String gridProject;
		String gridVersion;
		String gridTestPass;
		boolean nextLinkEnabled;
		int paginationCount=0;
		boolean projectFound = false;
		
		APP_LOGS.debug("Searching Test pass for testing");
			
		try
		{
			if (getElement("DashboardMyActivities_pagination_Id").findElements(By.xpath("div/span")).size()==3) 
			{
				APP_LOGS.debug("Only 1 page available on My Activity page.");
				totalPages=1;
				paginationCount=1;
			}
			else 
			{
				APP_LOGS.debug("More than 1 page avaialble on View All page. Calculating total pages.");
				totalPages = getElement("DashboardMyActivities_pagination_Id").findElements(By.xpath("div/a")).size();
			}		
				
			nextLinkEnabled = isElementExistsByXpath("DashboardProjectTestPassSummary_nextLink");
			while(nextLinkEnabled==true || paginationCount==1)
			{
				projectsInTable = getElement("DashboardMyActivities_table_Id").findElements(By.xpath("tr")).size();
					
				for (int j = 1; j <= projectsInTable; j++) 
				{
					gridProject =getObject("DashboardMyActivities_projectNameColumn1", "DashboardMyActivities_projectNameColumn2", j).getAttribute("title");
					gridVersion =getObject("DashboardMyActivities_versionColumn1", "DashboardMyActivities_versionColumn2", j).getAttribute("title");
					gridTestPass=getObject("DashboardMyActivities_testPassNameColumn1", "DashboardMyActivities_testPassNameColumn2", j).getAttribute("title");
						
					if (gridProject.equals(project) && gridVersion.equals(version) && gridTestPass.equals(testPass)) 
					{
						APP_LOGS.debug("Clicking on Begin Testing or Testing Complete in My Activity");
						getObject("DashboardMyActivities_actionColumnLinksXpath1", "DashboardMyActivities_actionColumnLinksXpath2", j).click();
						Thread.sleep(1000);
						APP_LOGS.debug("Project Found in My Activity Block.");
						projectFound=true;
						return true;
					}
				}			
					
				if (totalPages>1 && projectFound==false)
				{
						if(isElementExistsByXpath("DashboardProjectTestPassSummary_nextLink"))
						{
							getObject("DashboardProjectTestPassSummary_nextLink").click();
							Thread.sleep(500);
						}
						else
						{
							APP_LOGS.debug("Next link is disabled");
						}
				}
				else
				{
					break;
				}
				nextLinkEnabled = isElementExistsByXpath("DashboardProjectTestPassSummary_nextLink");
			}
			APP_LOGS.debug("Project, version and test pass Not found in View All page. Test case has been failed.");
			return false;
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			APP_LOGS.debug("Exception in searchProjectAndEdit(). ");
			return false;
		}
	}	
				 
}
