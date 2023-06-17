CREATE TABLE cambio (
  id NUMERIC(18) PRIMARY KEY,
  from_currency CHAR(3) NOT NULL,
  to_currency CHAR(3) NOT NULL,
  conversion_factor decimal(65,2) NOT NULL
);


CREATE SEQUENCE SEQCAMBIO
    START WITH 1  
    INCREMENT BY 1 ;