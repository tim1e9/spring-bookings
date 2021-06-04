CREATE TABLE charters (
    id serial primary key,
    name varchar(64),
    descr varchar(255)
);

CREATE TABLE bookings (
    id serial primary key,
    charter_id int NOT NULL,
    customer_name varchar(128),
    appt_datetime timestamp,
    comments varchar(255)
);

INSERT INTO charters(name, descr) values ('Inshore 1', 'Half day inshore fishing');
INSERT INTO charters(name, descr) values ('Inshore 2', 'Full day inshore fishing');
INSERT INTO bookings(charter_id, customer_name, appt_datetime, comments) values (1, 'John Smith', now(), 'Interested in Snapper and Trout');
