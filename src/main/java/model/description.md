# Model Classes

1. **Book**

    - Book code (ID)
    - Book title
    - Author (should be an object containing more details)
    - Publication year
    - Price
    - Quantity
    - Category (should be a separate class for management)
    - Language
    - Description
2. **Author**

    - Author code (ID)
    - Full name
    - Date of birth
    - Biography (bio)
3. **Category**

    - Category code (ID)
    - Category name
4. **Order**

    - Order code (ID)
    - Customer
    - Billing address
    - Shipping address
    - Order status (new, processing, delivering, delivered, return)
    - Payment method
    - Payment status
    - Total amount paid
    - Remaining amount due
    - Order date
    - Delivery date
5. **Order Detail**

    - Order detail code (ID)
    - Order
    - Book code (ID)
    - Quantity
    - List price
    - Discount
    - Selling price
    - VAT
6. **Customer**

    - Customer code (ID)
    - Username
    - Password
    - Gender
    - Full name
    - Billing address
    - Shipping address
    - Invoice address
    - Date of birth
    - Phone number
    - Email address
    - Subscribe to newsletter