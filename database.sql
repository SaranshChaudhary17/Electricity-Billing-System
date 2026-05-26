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
