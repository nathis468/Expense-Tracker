# Personal Finance Tracker

## Overview

This Application helps an Individual user to manage daily based Expense, Income and Account Balance. After records your Financial status you can view an Report / Transaction statement for Monthly basis and as well as the Yearly basis. It will helps to let you know your daily Financial status. Here 3 different roles of person can access the Application such as `Individual user, Admin and Financial Advisor`

<br>
<br>

## Role based Functionalities :

### Admin 
  - Can read, write and delete user account and account balance
  - Can view statement for expense and income

### User
  - Can read, write and delete account for individual person
  - Can view statement for expense and income

### Financial Advisor
  - Can monitor user's total income and total expense
  - Can monitor and advice user's financial status from yearly and monthly statement basis
<br>
<br>

## API calls and Functionalities based on Roles

### Account Module
  
> `endpoint` - "/account/listaccount"   <br>
`function` - list all the user accounts  <br>
`access`  - ADMIN  <br>

<br>

> `endpoint` - "/account/listaccount/{accountNumber}/{userName}"  <br>
`function` - list the account details of individual person  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/account/addaccount"  <br>
`function` - to add a new account  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/account/insertincome/{accountNumber}"  <br>
`function` - add a record for income from any source and update record in seperate "income" collection  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/account/insertexpense/{accountNumber}"  <br>
`function` - add a record for expense from any source and update record in seperate "expense" collection  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/account/deleteaccount/{accountNumber}"  <br>
`function` - delete an individual person's account  <br>
`access` - ADMIN, USER  <br>


<br>

### Income Module

> `endpoint` - "/income/listall"  <br>
`function` - to list all the income  <br>
`access` - ADMIN  <br>

<br>

> `endpoint` - "/income/listall/{userName}"  <br>
`function` - to list all income of an individual person  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/insertincome"  <br>
`function` - create a new record for income  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listtotalincome/{userName}"  <br>
`function` - to view a total income for indivial user  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listannualincome/{userName}/{year}" <br>
`function` - to view yearly statement from every source  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listannualincometotal/{userName}/{year}"  <br>
`function` - to view total annual income from every source  <br>
`access` - ADMIN, USER, FINANCIAL ADVISOR  <br>

<br>

> `endpoint` - "/income/listuserssourceincome/{userName}/{source}"  <br>
`function` - to view total statement about particular source income  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listannualstatement/{userName}/{source}/{year}"  <br>
`function` - to view annual statement from particular source  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listannualstatementtotal/{userName}/{source}/{year}"  <br>
`function` - to list total annual income from particular source  <br>
`access` -   ADMIN, USER, FINANCIAL ADVISOR  <br>

<br>

> `endpoint` - "/income/listmonthlystatement/{userName}/{source}/{year}/{month}"  <br>
`function` - to view monthly statement from particular source  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/income/listmonthlystatementtotal/{userName}/{source}/{year}/{month}"  <br>
`function` - to list total montly income from particular source  <br>
`access` - ADMIN, USER, FINANCIAL ADVISOR  <br>


<br>

### Expense Module

> `endpoint` - "/expense/listall"  <br>
`function` - to list all the expense  <br>
`access` - ADMIN  <br>

<br>

> `endpoint` - "/expense/listall/{userName}"  <br>
`function` - to list all expense of an individual person  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/insertexpense"  <br>
`function` - create a new record for expense  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listtotalexpense/{userName}"  <br>
`function` - to view a total expense for indivial user  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listannualexpense/{userName}/{year}"  <br>
`function` - to view yearly statement from every category  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listannualexpensetotal/{userName}/{year}"  <br>
`function` - to view total annual expense from every category  <br>
`access` - ADMIN, USER, FINANCIAL ADVISOR  <br>

<br>

> `endpoint` - "/expense/listuserssourceexpense/{userName}/{category}"  <br>
`function` - to view total statement about particular category expense  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listannualstatement/{userName}/{category}/{year}"  <br>
`function` - to view annual statement from particular category  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listannualstatementtotal/{userName}/{category}/{year}"  <br>
`function` - to list total annual expense from particular category  <br>
`access` -   ADMIN, USER, FINANCIAL ADVISOR  <br>

<br>

> `endpoint` - "/expense/listmonthlystatement/{userName}/{category}/{year}/{month}"  <br>
`function` - to view monthly statement from particular catey  <br>
`access` - ADMIN, USER  <br>

<br>

> `endpoint` - "/expense/listmonthlystatementtotal/{userName}/{category}/{year}/{month}"  <br>
`function` - to list total montly expense from particular category  <br>
`access` - ADMIN, USER, FINANCIAL ADVISOR  <br>
