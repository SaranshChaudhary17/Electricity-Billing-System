CREATE DATABASE IF NOT EXISTS ebs;
USE ebs;

CREATE TABLE IF NOT EXISTS login (
    meter_no varchar(50),
    username varchar(50),
    name varchar(50),
    password varchar(50),
    user varchar(50)
);

CREATE TABLE IF NOT EXISTS customer (
    name varchar(100),
    meter_no varchar(50),
    address varchar(200),
    city varchar(50),
    state varchar(50),
    email varchar(100),
    phone varchar(20)
);

CREATE TABLE IF NOT EXISTS meter_info (
    meter_no varchar(50),
    meter_location varchar(50),
    meter_type varchar(50),
    phase_code varchar(50),
    bill_type varchar(50),
    days varchar(20)
);

CREATE TABLE IF NOT EXISTS tax (
    cost_per_unit varchar(20),
    meter_rent varchar(20),
    service_charge varchar(20),
    service_tax varchar(20),
    swacch_bharat_cess varchar(20),
    fixed_tax varchar(20)
);

CREATE TABLE IF NOT EXISTS bill (
    meter_no varchar(50),
    month varchar(20),
    units varchar(20),
    total_bill varchar(20),
    status varchar(20)
);

-- Seed default admin account
INSERT INTO login (meter_no, username, name, password, user) 
VALUES ('', 'admin', 'Admin User', 'admin', 'Admin');

-- Seed default tax values
INSERT INTO tax (cost_per_unit, meter_rent, service_charge, service_tax, swacch_bharat_cess, fixed_tax) 
VALUES ('9', '47', '22', '57', '6', '18');

-- Seed Indian Mock Customer Data
INSERT INTO customer (name, meter_no, address, city, state, email, phone) VALUES
('Saransh Chaudhary', '100001', '123, Raj Nagar', 'Ghaziabad', 'Uttar Pradesh', 'saranshchaudhary17@gmail.com', '9876543210'),
('Aarav Sharma', '100002', '45, Saket', 'New Delhi', 'Delhi', 'aarav.sharma@gmail.com', '9988776655'),
('Priya Patel', '100003', '12, Satellite Road', 'Ahmedabad', 'Gujarat', 'priya.patel@yahoo.com', '9898989898'),
('Rohan Das', '100004', 'A-22, Salt Lake', 'Kolkata', 'West Bengal', 'rohan.das@hotmail.com', '9123456789');

-- Seed Meter Info
INSERT INTO meter_info (meter_no, meter_location, meter_type, phase_code, bill_type, days) VALUES
('100001', 'Outside', 'Electronic', '011', 'Domestic', '30'),
('100002', 'Inside', 'Solar', '022', 'Domestic', '30'),
('100003', 'Outside', 'Electronic', '011', 'Commercial', '30'),
('100004', 'Inside', 'Electronic', '011', 'Domestic', '30');

-- Seed Logins for Customers
INSERT INTO login (meter_no, username, name, password, user) VALUES
('100001', 'saransh', 'Saransh Chaudhary', 'password', 'Customer'),
('100002', 'aarav', 'Aarav Sharma', 'password', 'Customer'),
('100003', 'priya', 'Priya Patel', 'password', 'Customer'),
('100004', 'rohan', 'Rohan Das', 'password', 'Customer');

-- Seed Bills
INSERT INTO bill (meter_no, month, units, total_bill, status) VALUES
('100001', 'January', '150', '1550', 'Not Paid'),
('100001', 'February', '120', '1260', 'Paid'),
('100001', 'March', '180', '1820', 'Not Paid'),
('100002', 'January', '90', '990', 'Paid'),
('100003', 'January', '340', '3260', 'Not Paid'),
('100004', 'January', '210', '2090', 'Paid');

