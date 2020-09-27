Feature: Login

	Scenario: username and password not match
		Given user open the web page
		When user input invalid 'admin' and 'password'
		Then user see error message