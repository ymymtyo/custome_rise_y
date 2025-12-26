CREATE TABLE customer_mst
(
  customer_id varchar(4) NOT NULL,
  customer_name varchar(20) NOT NULL,
  PRIMARY KEY (customer_id)
) ;

INSERT INTO customer_mst VALUES
('0001','あいうえお商事');

SELECT *from customer_mst;

ALTER TABLE customer_mst ADD COLUMN delete_at varchar(4);

UPDATE customer_mst SET delete_at = 0 where customer_id = 0001;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0002;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0003;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0004;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0005;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0006;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0007;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0008;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0009;
UPDATE customer_mst SET delete_at = 0 where customer_id = 0010;
