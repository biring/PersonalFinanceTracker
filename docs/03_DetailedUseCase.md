### UC-01: Create Account

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.

**Post conditions:**
- A new account is created and stored in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Account Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Create Account" option from the submenu.
5. The application prompts the user to enter the Account Name (e.g., "Bank Account", "Credit Card").
6. The application validates the Account Name:
   1. Ensures that the account name is not empty.
   2. Ensures that the account name is not already in use.
7. The application prompts the user to select the account type: Credit or Debit.
8. The application displays a success message confirming that the account type has been created.
9. The application displays all the account names and types.

**Alternative Flow(s):**

1. **Validation Error:**
   1. If account validation fails, the application displays a message indicating the specific issue (e.g., "Account name cannot be empty" or "Account name already in use").
   2. The user is prompted to correct the input and resubmit.

### UC-02: Modify Account

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one account type exists.

**Post conditions:**
- An account is modified and stored in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Account Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Modify Account" option from the submenu.
5. The application prompts the user to select the account to modify.
6. The user selects the account to modify.
7. The application prompts the user to enter the new Account Name (e.g., "Bank Account", "Credit Card").
8. The application validates the Account Name:
   1. Ensures that the account name is not empty.
   2. Ensures that the account name is not already in use.
9. The application prompts the user to select the account type: Credit or Debit.
10. The application displays a success message confirming that the account type has been modified.
11. The application displays all the account names and types.

**Alternative Flow(s):**

1. **No Account Found:**
   1. If no account exists, the application displays a message indicating that no accounts were found (e.g., "No accounts found.").
   2. The application returns to the "Account Management" submenu.

2. **Validation Error:**
   1. If account validation fails, the application displays an error message.
   2. The user is prompted to correct the input and resubmit.

### UC-03: View Accounts

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one account type exists.

**Post conditions:**
- The user can view a list of all existing accounts and their type in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Account Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "View Accounts" option from the submenu.
5. The application displays all the account names and types.

**Alternative Flow(s):**

1. **No Account Found:**
   1. If no account exists, the application displays a message indicating that no accounts were found (e.g., "No accounts found.").
   2. The application returns to the "Account Management" submenu.

### UC-04: Delete Account

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one account type exists.

**Post conditions:**
- The account is deleted from the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Account Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Delete" option from the submenu.
5. The application prompts the user to select the account to delete.
6. The user selects the account to delete.
7. The application displays a success message confirming that the account type has been deleted.
8. The application displays all the account names and types.
9. The application returns to the "Account Management" submenu.

**Alternative Flow(s):**

1. **No Account Found:**
   1. If no account exists, the application displays a message indicating that no accounts were found (e.g., "No accounts found.").
   2. The application returns to the "Account Management" submenu.

### UC-05: Create Category

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.

**Post conditions:**
- A new category is created and stored in the system.
- The user can view the new category in their list of categories.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Category Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Create Category" option from the submenu.
5. The application prompts the user to enter the Category Name (e.g., "Groceries").
6. The application validates the Category Name:
   1. Ensures that the category name is not empty.
   2. Ensures that the category name is not already in use.
7. The application displays a success message confirming that the category has been created. 
8. The application displays all the categories in the updated category list.

**Alternative Flow(s):**

1. **Validation Error:**
   1. If category validation fails, the application displays a message indicating the specific issue (e.g., "Category name cannot be empty" or "Category name already in use").
   2. The user is prompted to correct the input and resubmit.

### UC-06: Modify Category

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category exists in the system.

**Post conditions:**
- An existing category is modified and stored in the system.
- The user can view the updated category in their list of categories.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Category Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Modify Category" option from the submenu.
5. The application prompts the user to select the category to modify.
6. The user selects the category they wish to modify.
7. The application prompts the user to enter the new Category Name (e.g., "Dining Out").
8. The application validates the new Category Name:
   1. Ensures that the new category name is not empty.
   2. Ensures that the new category name is not already in use.
9. The application displays a success message confirming that the category has been modified.
10. The application displays all the categories in the updated category list.

**Alternative Flow(s):**

1. **No Category Found:**
   1. If no categories exist, the application displays a message indicating that no categories were found (e.g., "No categories found.").
   2. The application returns to the "Category Management" section.

2. **Validation Error:**
   1. If validation fails (e.g., the new category name is empty or already in use), the application displays a message indicating the specific issue (e.g., "Category name cannot be empty" or "Category name already in use").
   2. The user is prompted to correct the input and resubmit.

### UC-07: View Categories

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category exists in the system.

**Post conditions:**
- The user can view a list of all existing categories and their details.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Category Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "View Categories" option from the submenu.
5. The application displays all the categories and their details.
6. The application returns to the "Category Management" section.

**Alternative Flow(s):**

1. **No Category Found:**
   1. If no categories exist, the application displays a message indicating that no categories were found (e.g., "No categories found.").
   2. The application returns to the "Category Management" section.

### UC-08: Delete Category

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category exists in the system.

**Post conditions:**
- The selected category is deleted from the system.
- The user can view the updated list of categories.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Category Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Delete Category" option from the submenu.
5. The application prompts the user to select the category to delete.
6. The user selects the category to delete.
7. The application displays a success message confirming that the category has been deleted.
8. The application displays all the categories in the updated category list.

**Alternative Flow(s):**

1. **No Category Found:**
   1. If no categories exist, the application displays a message indicating that no categories were found (e.g., "No categories found.").
   2. The application returns to the "Category Management" section.

### UC-09: Create Budget

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category exists in the system.

**Post conditions:**
- A new budget is created and stored in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Budget Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Create Budget" option.
5. The application prompts the user to select a category for the budget.
6. The user selects the desired category (e.g., "Groceries").
7. The application prompts the user to enter the budget amount (e.g., "$300").
8. The application validates that the budget amount is a positive number.
9. The user submits the new budget.
10. The application displays a success message confirming that the budget has been created.
11. The application displays all the budgets in the updated budget list.

**Alternative Flow(s):**

1. **No Categories:**
   1. If no categories exist, the application displays a message indicating the specific issue (e.g., "Create a category before creating a budget").
   2. The application returns to the main menu.

2. **Validation Error:**
   1. If budget validation fails, the application displays a message indicating the specific issue (e.g., "Budget amount must be a positive number").
   2. The user is prompted to correct the input and resubmit.

### UC-10: Modify Budget

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one budget exists in the system.

**Post conditions:**
- An existing budget is modified and stored in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Budget Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Modify Budget" option.
5. The application prompts the user to enter the new budget amount (e.g., "$400").
6. The application validates that the budget amount is a positive number.
7. The application displays a success message confirming that the budget has been modified.
8. The application displays all the budgets in the updated budget list.

**Alternative Flow(s):**

1. **No Budget Found:**
   1. If no budgets exist, the application displays a message indicating that no budgets were found (e.g., "No budgets found.").
   2. The application returns to the "Budget Management" section.

2. **Validation Error:**
   1. If validation fails (e.g., the new budget amount is not a positive number), the application displays a message indicating the specific issue (e.g., "Budget amount must be a positive number").
   2. The user is prompted to correct the input and resubmit.

### UC-11: View Budgets

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one budget exists in the system.

**Post conditions:**
- The user can view a list of all existing budgets and their details.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Budget Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "View Budgets" option.
5. The application displays all the budgets and their details.

**Alternative Flow(s):**

1. **No Budget Found:**
   1. If no budgets exist, the application displays a message indicating that no budgets were found (e.g., "No budgets found.").
   2. The application returns to the "Budget Management" section.

### UC-12: Delete Budget

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one budget exists in the system.

**Post conditions:**
- The selected budget is deleted from the system.
- The user can view the updated list of budgets.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Budget Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Delete Budget" option.
5. The application prompts the user to select the budget to delete.
6. The user selects the budget to delete.
7. The application displays a success message confirming that the budget has been deleted.
8. The application displays all the budgets in the updated budget list.

**Alternative Flow(s):**

1. **No Budget Found:**
   1. If no budgets exist, the application displays a message indicating that no budgets were found (e.g., "No budgets found.").
   2. The application returns to the "Budget Management" section.

### UC-13: Import Transactions

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- The user has a valid CSV file containing account transactions.
- At least one account exists in the system.
- The CSV file contains the following columns: Date, Description, Amount.

**Post conditions:**
- The transactions from the CSV file are imported and stored in the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Data Import" option from the main menu.
3. The application prompts the user to select the account type to associate with the data import.
4. The user selects the desired account type to associate with the imported data.
5. The application prompts the user to select the CSV file.
6. The user selects the CSV file to import.
7. The application imports the transactions into the system and displays a success message indicating the number of transactions imported. 
8. The application updates the transaction list to include the newly imported transactions.

**Alternative Flow(s):**

1. **No Account Error:**
   1. If no accounts exist, the application displays a message indicating the specific issue (e.g., "Create an account before importing data").
   2. The application returns to the main menu.

### UC-14: Create Link

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category and one transaction exist.

**Post conditions:**
- The selected category is linked to all transaction descriptions that match the partial input.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Link Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Create Link" option from the submenu.
5. The application prompts the user to select a category from the list.
6. The user selects the category they want to link to a description.
7. The application prompts the user to enter a string for description matching (e.g., "Walmart").
8. The application displays all transactions that match the entered partial description.
9. The application displays a success message confirming that the category has been linked.
10. The application returns to the "Link Management" submenu.

**Alternative Flow(s):**

1. **No Category Found:**
   1. If no categories exist, the application displays a message indicating that no categories were found (e.g., "No categories found.").
   2. The application returns to the "Link Management" submenu.

2. **No Transactions Found:**
   1. If no transactions exist, the application displays a message indicating that no transactions were found (e.g., "No transactions found.").
   2. The application returns to the "Link Management" submenu.

3. **No Matching Transactions Found:**
   1. If no transactions match the entered partial description, the application displays a message indicating that no matches were found (e.g., "No transactions found for the provided description.").
   2. The application prompts the user to enter a new string for description matching.

### UC-15: Modify Link

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category-to-description link exists.

**Post conditions:**
- The user successfully modifies the description match string for linking a category to the description of a transaction.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Link Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Modify Link" option from the submenu.
5. The application prompts the user to select a category from the list.
6. The user selects the category to modify.
7. The application prompts the user to select a string from the list.
8. The user selects the string to modify.
9. The application prompts the user to enter a string for description matching (e.g., "Walmart").
10. The application displays all transactions that match the entered partial description.
11. The application displays a success message confirming that the category has been linked.
12. The application returns to the "Link Management" submenu.

**Alternative Flow(s):**

1. **No Category Found:**
   1. If no categories exist, the application displays a message indicating that no categories were found (e.g., "No categories found.").
   2. The application returns to the "Link Management" submenu.

2. **No Transactions Found:**
   1. If no transactions exist, the application displays a message indicating that no transactions were found (e.g., "No transactions found.").
   2. The application returns to the "Link Management" submenu.

3. **No Matching Transactions Found:**
   1. If no transactions match the entered partial description, the application displays a message indicating that no matches were found (e.g., "No transactions found for the provided description.").
   2. The application prompts the user to enter a new string for description matching.

### UC-16: View Link

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category-to-description link exists.

**Post conditions:**
- The user can view the details of all existing category-to-description links.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Link Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "View Link" option from the submenu.
5. The application retrieves and displays a list of all category-to-description links, showing the linked categories and their corresponding description match strings.
6. The application returns to the "Link Management" submenu.

**Alternative Flow(s):**

1. **No Links Found:**
   1. If no category-to-description links exist, the application displays a message indicating that no links were found (e.g., "No category-to-description links found.").
   2. The application returns to the "Link Management" submenu.

### UC-17: Delete Link

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- At least one category-to-description link exists.

**Post conditions:**
- The selected category-to-description link is deleted from the system.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Link Management" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Delete Link" option from the submenu.
5. The application prompts the user to select a category from the list of existing links.
6. The user selects the link they wish to delete.
7. The application deletes the selected link and displays a success message confirming that the link has been deleted.
8. The application returns to the "Link Management" submenu.

**Alternative Flow(s):**

1. **No Links Found:**
   1. If no category-to-description links exist, the application displays a message indicating that no links were found (e.g., "No links found.").
   2. The application returns to the "Link Management" submenu.

### UC-18: Account Report

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- There are existing transactions in the system.

**Post conditions:**
- The user can view the account report.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Reports" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Account Report" option from the submenu.
5. The application generates the account report and displays the following information in a tabular format: Account Names, Total Credits, and Total Debits.
6. The application displays a success message confirming the report generation.

**Alternative Flow(s):**

1. **No Transactions Found:**
   1. If there are no transactions in the selected period, the application displays a message indicating that no data is available (e.g., "No transactions found for the selected period.").
   2. The application returns to the "Reports" submenu.

### UC-19: Category Report

**Actor:** User

**Pre conditions:**
- The user has launched the Personal Finance Tracker application.
- There are existing transactions in the system.
- At least one category exists in the system.
- At least one budget exists in the system.
- At least one link exists.

**Post conditions:**
- The user can view the category report.

**Main Flow:**
1. The application prompts the user to make a selection from the main menu.
2. The user selects the "Reports" option from the main menu.
3. The application prompts the user to make a selection from the submenu.
4. The user selects the "Category Report" option from the submenu.
5. The application generates the category report and displays the following information in a tabular format: Category Names, Budget Amounts, Total Spent, and Budget Status (e.g., Over Budget, Under Budget).
6. The application displays a success message confirming the report generation.

**Alternative Flow(s):**
1. **No Transactions Found:**
   1. If there are no transactions in the selected period, the application displays a message indicating that no data is available (e.g., "No transactions found for the selected period.").
   2. The application returns to the "Reports" submenu.


