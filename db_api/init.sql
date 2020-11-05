CREATE TABLE optionvotes
(
    option VARCHAR(20) primary key NOT NULL,
    votes  INTEGER                 NOT NULL
);

INSERT INTO optionvotes
VALUES ('cats', 25);
INSERT INTO optionvotes
VALUES ('dogs', 8);
