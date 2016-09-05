/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.reference.page;

import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PatientVisitsDashboardPage extends Page {
	private static final By CAPTURE_VITALS = By.id("referenceapplication.realTime.vitals");
	private static final By VISIT_LIST = By.cssSelector("#visits-list li.menu-item.viewVisitDetails span.menu-date");
	private static final By END_VISIT = By.cssSelector("#visit-details div.visit-actions.active-visit a:nth-child(1)");
	private static final By END_VISIT_DIALOG = By.id("end-visit-dialog");
	private static final By END_VISIT_CONFIRM = By.cssSelector("#end-visit-dialog button[class='confirm right']");
	private static final By ADMIT_TO_INPATIENT = By.id("referenceapplication.realTime.simpleAdmission");
	private static final By EXIT_FROM_INPATIENT = By.id("referenceapplication.realTime.simpleDischarge");

	public PatientVisitsDashboardPage(Page parent) {
		super(parent);
	}

	@Override
	public String getPageUrl() {
		return "coreapps/patientdashboard/patientDashboard.page";
	}

	public void goToCaptureVitals() {
	    findElement(CAPTURE_VITALS).click();
    }

    public WebElement getActiveVisit(){
    	for(WebElement webElement: findElements(VISIT_LIST)){
    		if(webElement.getText().contains("active")){
    			return webElement;
			}
		}
		return null;
	}

	public List<WebElement> getVisitList(){
		return findElements(VISIT_LIST);
	}

	public void endVisit(){
		clickOn(END_VISIT);
		waitForElement(END_VISIT_DIALOG);
		clickOn(END_VISIT_CONFIRM);
	}

	public AdmitToInpatientPage goToAdmitToInpatient(){
		clickOn(ADMIT_TO_INPATIENT);
		return new AdmitToInpatientPage(this);
	}

	public ExitFromInpatientPage goToExitFromInpatient(){
		clickOn(EXIT_FROM_INPATIENT);
		return new ExitFromInpatientPage(this);
	}

	/**
	 * Before using it, note that it will take full timeout time if encountersList is empty
     */
	public int getEncountersCount(){
		try {
			return findElements(By.xpath("//*[@id='encountersList']/li")).size();
		} catch(TimeoutException e){
			return 0;
		}
	}
}
