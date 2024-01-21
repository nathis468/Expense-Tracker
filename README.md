# Personal Finance Tracker

## Overview

This Application helps an Individual user to manage daily based Expense, Income and Account Balance. After records your Financial you can view an Report / Transaction statement for Monthly basis and as well as the Yearly basis. It will helps to let you know your daily Financial status. It allow 3 different role of users such as INDIVIDUAL USER, ADMIN and FINANCIAL ADVISOR

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

`endpoint` - "/account/deleteaccount/{accountNumber}"  <br>
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

endpoint - "/expense/listall"
function - to list all the expense
access - ADMIN


endpoint - "/expense/listall/{userName}"
function - to list all expense of an individual person
access - ADMIN, USER


endpoint - "/expense/insertexpense"
function - create a new record for expense
access - ADMIN, USER


endpoint - "/expense/listtotalexpense/{userName}"
function - to view a total expense for indivial user
access - ADMIN, USER


endpoint - "/expense/listannualexpense/{userName}/{year}"
function - to view yearly statement from every category
access - ADMIN, USER


endpoint - "/expense/listannualexpensetotal/{userName}/{year}"
function - to view total annual expense from every category
access - ADMIN, USER, FINANCIAL ADVISOR


endpoint - "/expense/listuserssourceexpense/{userName}/{category}"
function - to view total statement about particular category expense
access - ADMIN, USER


endpoint - "/expense/listannualstatement/{userName}/{category}/{year}"
function - to view annual statement from particular category
access - ADMIN, USER


endpoint - "/expense/listannualstatementtotal/{userName}/{category}/{year}"
function - to list total annual expense from particular category
access -   ADMIN, USER, FINANCIAL ADVISOR


endpoint - "/expense/listmonthlystatement/{userName}/{category}/{year}/{month}"
function - to view monthly statement from particular category
access - ADMIN, USER


endpoint - "/expense/listmonthlystatementtotal/{userName}/{category}/{year}/{month}"
function - to list total montly expense from particular category
access - ADMIN, USER, FINANCIAL ADVISOR
