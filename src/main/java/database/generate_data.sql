USE book_store;


-- Insert data into author table
INSERT INTO `author` (`author_id`, `full_name`, `date_of_birth`, `bio`) VALUES
('AUTH001', 'J.K. Rowling', '1965-07-31', 'British author, best known for the Harry Potter series.'),
('AUTH002', 'George R.R. Martin', '1948-09-20', 'American novelist and short story writer.'),
('AUTH003', 'J.R.R. Tolkien', '1892-01-03', 'English writer, poet, philologist, and academic.'),
('AUTH004', 'Stephen King', '1947-09-21', 'American author of horror, supernatural fiction, suspense, and fantasy novels.'),
('AUTH005', 'Agatha Christie', '1890-09-15', 'English writer known for her sixty-six detective novels.'),
('AUTH006', 'Arthur Conan Doyle', '1859-05-22', 'British writer and physician.'),
('AUTH007', 'Isaac Asimov', '1920-01-02', 'American writer and professor of biochemistry.'),
('AUTH008', 'Ernest Hemingway', '1899-07-21', 'American novelist, short-story writer, and journalist.'),
('AUTH009', 'Jane Austen', '1775-12-16', 'English novelist known for her six major novels.'),
('AUTH010', 'Mark Twain', '1835-11-30', 'American writer, humorist, entrepreneur, publisher, and lecturer.');

-- Insert data into category table
INSERT INTO `category` (`category_id`, `category_name`) VALUES
('CAT001', 'Fantasy'),
('CAT002', 'Science Fiction'),
('CAT003', 'Mystery'),
('CAT004', 'Horror'),
('CAT005', 'Romance'),
('CAT006', 'Adventure'),
('CAT007', 'Historical'),
('CAT008', 'Thriller'),
('CAT009', 'Biography'),
('CAT010', 'Non-Fiction');

-- Insert data into book table
INSERT INTO `book` (`book_id`, `book_title`, `author_id`, `publication_year`, `import_price`, `list_price`, `selling_price`, `quantity`, `category_id`, `description`) VALUES
('BOOK001', 'Harry Potter and the Sorcerer\'s Stone', 'AUTH001', 1997, 10.00, 20.00, 18.00, 50, 'CAT001', 'A young boy discovers he is a wizard and attends a magical school.'),
('BOOK002', 'A Game of Thrones', 'AUTH002', 1996, 12.00, 25.00, 22.00, 40, 'CAT001', 'Noble families vie for control of the Iron Throne in the fantasy land of Westeros.'),
('BOOK003', 'The Hobbit', 'AUTH003', 1937, 8.00, 15.00, 13.00, 30, 'CAT006', 'Bilbo Baggins embarks on a journey with dwarves to reclaim their mountain home from a dragon.'),
('BOOK004', 'The Shining', 'AUTH004', 1977, 9.00, 18.00, 16.00, 20, 'CAT004', 'A family stays in an isolated hotel over the winter where supernatural forces influence the father.'),
('BOOK005', 'Murder on the Orient Express', 'AUTH005', 1934, 7.00, 14.00, 12.00, 25, 'CAT003', 'Detective Hercule Poirot investigates a murder on the famous train.'),
('BOOK006', 'Sherlock Holmes: The Complete Novels and Stories', 'AUTH006', 1887, 10.00, 20.00, 18.00, 35, 'CAT003', 'A collection of Sherlock Holmes stories featuring the iconic detective and his friend Dr. Watson.'),
('BOOK007', 'Foundation', 'AUTH007', 1951, 11.00, 22.00, 19.00, 40, 'CAT002', 'A mathematician develops a theory to save civilization from impending dark ages.'),
('BOOK008', 'The Old Man and the Sea', 'AUTH008', 1952, 5.00, 10.00, 9.00, 45, 'CAT007', 'An old fisherman battles a giant marlin in the Gulf Stream.'),
('BOOK009', 'Pride and Prejudice', 'AUTH009', 1813, 6.00, 12.00, 10.00, 50, 'CAT005', 'Elizabeth Bennet navigates the issues of manners, upbringing, and marriage in British society.'),
('BOOK010', 'The Adventures of Tom Sawyer', 'AUTH010', 1876, 4.00, 8.00, 7.00, 60, 'CAT006', 'Tom Sawyer and his friends go on various adventures along the Mississippi River.');

-- Insert data into customer table
INSERT INTO `customer` (`customer_id`, `username`, `password`, `full_name`, `gender`, `billing_address`, `shipping_address`, `invoice_address`, `date_of_birth`, `phone_number`, `email`, `sub_to_newsletter`) VALUES
('CUST001', 'jdoe', 'password123', 'John Doe', 'Male', '123 Main St', '123 Main St', '123 Main St', '1990-01-01', '1234567890', 'jdoe@example.com', 1),
('CUST002', 'asmith', 'password123', 'Alice Smith', 'Female', '456 Oak St', '456 Oak St', '456 Oak St', '1985-05-15', '0987654321', 'asmith@example.com', 0),
('CUST003', 'bwhite', 'password123', 'Bob White', 'Male', '789 Pine St', '789 Pine St', '789 Pine St', '1978-12-10', '5555555555', 'bwhite@example.com', 1),
('CUST004', 'cjones', 'password123', 'Carol Jones', 'Female', '321 Maple St', '321 Maple St', '321 Maple St', '1992-03-08', '4444444444', 'cjones@example.com', 0),
('CUST005', 'djohnson', 'password123', 'David Johnson', 'Male', '654 Birch St', '654 Birch St', '654 Birch St', '1980-07-22', '3333333333', 'djohnson@example.com', 1),
('CUST006', 'eclark', 'password123', 'Eve Clark', 'Female', '987 Cedar St', '987 Cedar St', '987 Cedar St', '1988-11-30', '2222222222', 'eclark@example.com', 0),
('CUST007', 'ffisher', 'password123', 'Frank Fisher', 'Male', '135 Walnut St', '135 Walnut St', '135 Walnut St', '1995-06-18', '1111111111', 'ffisher@example.com', 1),
('CUST008', 'gmartin', 'password123', 'Grace Martin', 'Female', '246 Ash St', '246 Ash St', '246 Ash St', '1983-09-25', '6666666666', 'gmartin@example.com', 0),
('CUST009', 'hlee', 'password123', 'Henry Lee', 'Male', '369 Elm St', '369 Elm St', '369 Elm St', '1991-04-14', '7777777777', 'hlee@example.com', 1),
('CUST010', 'ikim', 'password123', 'Ivy Kim', 'Female', '159 Cherry St', '159 Cherry St', '159 Cherry St', '1987-02-03', '8888888888', 'ikim@example.com', 0);

-- Insert data into order table
-- Insert data into order table
INSERT INTO `order` (`order_id`, `customer_id`, `billing_address`, `shipping_address`, `payment_method`, `order_status`, `payment_status`, `paid_amount`, `remaining_amount`, `order_date`, `delivery_date`) VALUES
('ORD001', 'CUST001', '123 Main St', '123 Main St', 'Credit Card', 'Shipped', 'Paid', 36.00, 0.00, '2024-01-15', '2024-01-20'),
('ORD002', 'CUST002', '456 Oak St', '456 Oak St', 'PayPal', 'Processing', 'Pending', 22.00, 0.00, '2024-02-10', '2024-02-15'),
('ORD003', 'CUST003', '789 Pine St', '789 Pine St', 'Credit Card', 'Delivered', 'Paid', 28.00, 0.00, '2024-03-05', '2024-03-10'),
('ORD004', 'CUST004', '321 Maple St', '321 Maple St', 'Credit Card', 'Cancelled', 'Refunded', 0.00, 0.00, '2024-04-01', '2024-04-06'),
('ORD005', 'CUST005', '654 Birch St', '654 Birch St', 'Bank Transfer', 'Shipped', 'Paid', 16.00, 0.00, '2024-05-20', '2024-05-25'),
('ORD006', 'CUST006', '987 Cedar St', '987 Cedar St', 'PayPal', 'Processing', 'Pending', 18.00, 0.00, '2024-06-15', '2024-06-20'),
('ORD007', 'CUST007', '135 Walnut St', '135 Walnut St', 'Credit Card', 'Delivered', 'Paid', 10.00, 0.00, '2024-07-10', '2024-07-15'),
('ORD008', 'CUST008', '246 Ash St', '246 Ash St', 'Credit Card', 'Shipped', 'Paid', 25.00, 0.00, '2024-08-05', '2024-08-10'),
('ORD009', 'CUST009', '369 Elm St', '369 Elm St', 'PayPal', 'Processing', 'Pending', 20.00, 0.00, '2024-09-01', '2024-09-06'),
('ORD010', 'CUST010', '159 Cherry St', '159 Cherry St', 'Bank Transfer', 'Delivered', 'Paid', 17.00, 0.00, '2024-10-15', '2024-10-20');

-- Insert data into order_detail table
INSERT INTO `order_detail` (`order_detail_id`, `order_id`, `book_id`, `quantity`, `list_price`, `discount`, `selling_price`, `vat`) VALUES
('ORDDET001', 'ORD001', 'BOOK001', 1, 20.00, 2.00, 18.00, 1.80),
('ORDDET002', 'ORD001', 'BOOK009', 1, 12.00, 2.00, 10.00, 1.00),
('ORDDET003', 'ORD002', 'BOOK004', 1, 18.00, 2.00, 16.00, 1.60),
('ORDDET004', 'ORD002', 'BOOK010', 1, 8.00, 2.00, 7.00, 0.70),
('ORDDET005', 'ORD003', 'BOOK005', 2, 14.00, 2.00, 12.00, 1.20),
('ORDDET006', 'ORD003', 'BOOK006', 1, 20.00, 2.00, 18.00, 1.80),
('ORDDET007', 'ORD004', 'BOOK007', 1, 22.00, 2.00, 19.00, 1.90),
('ORDDET008', 'ORD005', 'BOOK002', 1, 25.00, 2.00, 22.00, 2.20),
('ORDDET009', 'ORD005', 'BOOK008', 1, 10.00, 2.00, 9.00, 0.90),
('ORDDET010', 'ORD006', 'BOOK003', 1, 15.00, 2.00, 13.00, 1.30);