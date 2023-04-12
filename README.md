# Accesa-gamification-app

Firstly, I would like to mention that the application is not in it's final state. Due to some pretty serious medical issues I encountered, I could only work roughly around 3 days on the development of the app.

***I will briefly present the whole project in case the .jar or something doesn't work as it should.***

## Table of contents:
1. [ Unfinished features ](#unfinished)
2. [ Introductory notions ](#intro)
3. [ Database description ](#db)
4. [ Project presentations ](present)
5. [ Thanks ](thx)

<a name="unfinished"></a>
## ***Unfinished features:***
  - Tests!!
  - Commented code
  - Efficient code practice (there are some duplicate functions somewhere)
  - Bugs testing
  - Proper error handling (it's actually horrible in its current state :')
  - Tokens mechanism {<br>
<p>
      &emsp; &emsp; &emsp; By default at the beginning everyone (except the Admin) has the "Junior" rank, which can only solve tasks.<br>
      &emsp; &emsp; &emsp; For every task, depending on the difficulty, the user was supposed to get a specified amount of points.<br>
      &emsp; &emsp; &emsp; After achieving a certain total of points, the user will get the rank of "Senior",<br>
      &emsp; &emsp; &emsp; which can give at most a third of their assigned tasks to lower ranks.<br>

  &emsp; } &nbsp; I was only able to implement the Admin-Junior relation due to reduced time...
</p>

<a name="intro"></a>
## ***Introductory notions:***
  - The local database is populated everytime (ONLY IF IT'S EMPTY!!!) the program is run (to avoid possible errors..)
  - Accounts: 
<p>
      &emsp; &emsp; (rank = Admin) userName = Admin; pass = admin <br>
      &emsp; &emsp; (rank = Senior) userName = Elena; pass = elena (this account doesn't do anything =)) <br>
      &emsp; &emsp; (rank = Junior) userName = Alex; pass = alex <br>
</p>

  - The **demo.jar** for running the app can be found in **'Accesa-gamification-app/demo/out/artifacts/demo_jar/'**

<a name="db"></a>
## ***Database:***
  - Each rank has special permissions (see ***Unfinished featutres***).
  - Each user has an encrypted password and an assigned rank.
  - Each task has a difficulty which should show the number of tokens it values (it currently doesn't), a 'UserName' indicating who it has been assigned to, and a state of being 'Done'(1) or 'Not Done'(0).

![image](https://user-images.githubusercontent.com/35970991/231394015-f3896f55-0969-4db5-a7a0-bb6238af29d9.png)

<a name="present"></a>
## ***Project presentation:***

#### Login page
![image](https://user-images.githubusercontent.com/35970991/231394692-7b99f021-4a14-4a46-9734-e940560a6ed3.png)

#### Admin landing page
![image](https://user-images.githubusercontent.com/35970991/231394909-6c99c25b-b896-4c5c-9f65-2ed2fe99d8fc.png)

#### Assign tasks admin page

Select tasks, press 'Select', select users, press 'Assign tasks', press 'Confirm' to confirm the assignments.<br>
The task entries will be removed from the **Admin tasks list** and will be assigned to the coresponding user.<br>
The assigned tasks will then appear in the **User's tasks list**.<br>

![image](https://user-images.githubusercontent.com/35970991/231398399-59e051e4-2bcc-4055-af60-cfc2d3af9010.png)

#### Verify tasks admin page

Select tasks, if the tasks is well done, press 'Approve task', this will delete the task entry from the **Admin verify tasks list** and will keep the state of the task to 'Done' (1).<br>
If the task is not well done, press 'Deny task', this will delete the task from the **Admin verify tasks list** and will change the state of the task to 'Not Done' (0), which will make it to reappear in **User's tasks list**.<br>

![image](https://user-images.githubusercontent.com/35970991/231398258-daf5fba2-994b-48a3-93c9-9bfc450f1c20.png)


#### Junior landing page

Select task, press 'Mark as done', this will change the state of the task to 'Done' (1) and it will appear in the **Admin verify tasks list**.<br>
The selected task's description will appear in the text area so it can be seen, considering that the description migh be long.<br>

![image](https://user-images.githubusercontent.com/35970991/231398779-958a9341-5bea-4278-b16e-ff93980fdbce.png)

<a name="thx"></a>
## ***Thank you for your time!***
