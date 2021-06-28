# PetStoreAPITestFramework

-----------********GetPetStatus************--------

-	This class basically calls pet/findByStatus, then validate the response code, response time and iterate all the pets of specific category ‘Lions’.
-	pet/findByStatus is a Get method which takes query param as status.
-	In the test file ‘category’ and ‘status’ are not hardcoded for maintainability and reusability purpose.
-	User can pass the values via properties file PetStoreAPITestFramework/src/main/resources/val.properties or via system or environment variables.
Sample: 
-CATEGORY= Lions
-STATUS= available
-	Also I have validated with multiple query params in this test file.

-----------********AddPetAndFindStatus************--------

-	This class takes care of adding new pet, finding pet by status, deleting the pet and verifying whether the pet is deleted by searching for pet with the ID. 
-	To cover test cases respective to pet/findByStatus, used other APIs in the test.
-	Adding new pet in status.available, iterating through available list
-	Adding new pet in status.pending, iterating through pending list
-	Adding new pet in status.sold, iterating through sold list
-	Deleting specific category of pet in the status.sold, find the same pet via ID for presence of element and then iterate through the list which should validate that no pet of that category in the list.

//////////////How to run the test////////////////////

-	Clone the repository using command git clone https://github.com/bthenu9193/PetStoreAPITestFramework.git
-	Once repository is cloned, run maven commands
		mvn clean package [This will build project, then run both GetPetStatus.java and AddPetAndFindStatus.java]
		mvn clean test -Dtest="GetPetStatus.java" [To run only GetPetStatus.java]
		mvn clean test -Dtest=" AddPetAndFindStatus.java " [To run only AddPetAndFindStatus.java]
-	User can pass the values via system or environment variables or it will take default value in properties file PetStoreAPITestFramework/src/main/resources/val.properties

-Default Value in val.properties-
-CATEGORY= Lions
-STATUS= available

