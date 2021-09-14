# universities-list
Get a list of universities in a specified country.

## Sample API URL:
<http://universities.hipolabs.com/search?country=United+States>


# Explanation of Approach:

Used Rest Assured framework for API validation with Java programming language. Test cases are written in User Friendly BDD Gherkin Language.

Used Maven as Build tool and API tests can be triggered either from IDE by running TestRunner as Junit test or from CMD prompt using Maven syntax - mvn test.
 

# Pros and Cons:

Pros
 - Feasible to Automate API tests
 - Tests can be written in User Friendly Given/When/Then notation
 - Code Reusability using Java programming language
 - Easy to Integrate with Junit/testNG framework
 - Easy to Integrate with CI/CD Jenkins pipeline
 - Customize Reports based on user needs
 
Cons
 - Need Programming Experience to write/maintain test cases using Rest Assured
 - When we prepare Feature file, we should also write Step Definitions which is actually doubling the work

