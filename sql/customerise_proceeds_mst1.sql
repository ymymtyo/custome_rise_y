CREATE TABLE proceeds_mst1
(
  person varchar(20) NOT NULL,
  proceeds_id int(4) NOT NULL AUTO_INCREMENT,
  pcs varchar(2) NOT NULL,
  goods_id varchar(4) NOT NULL,
  sales_date date NOT NULL,
  customer_id varchar(4) NOT NULL,
  PRIMARY KEY (proceeds_id),
  FOREIGN KEY (goods_id) REFERENCES goods_mst (goods_id),
  FOREIGN KEY (customer_id) REFERENCES customer_mst (customer_id)
) ;

ALTER TABLE proceeds_mst1 ADD COLUMN delete_at varchar(4);



ALTER TABLE proceeds_mst1 ADD CONSTRAINT FK 
ALTER TABLE proceeds_mst1 ADD CONSTRAINT FK_C 
ALTER TABLE proceeds_mst1 ADD COLUMN delete_at varchar(4) FIRST;

UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0001;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0002;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0003;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0004;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0005;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0006;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0007;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0008;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0009;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0010;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0011;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0012;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0013;
UPDATE proceeds_mst1 SET delete_at = 0 where proceeds_id = 0014;

SELECT proceeds_mst1.proceeds_id, proceeds_mst1.person, proceeds_mst1.pcs, proceeds_mst1.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst1 JOIN goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst1.customer_id = customer_mst.customer_id WHERE proceeds_mst1.delete_at = 0 ORDER BY proceeds_mst1.sales_date ASC;



UPDATE proceeds_mst1 JOIN proceeds_mst1 AS goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id SET goods_mst.goods_id = 0006 WHERE proceeds_mst1.proceeds_id = 34;

ALTER TABLE proceeds_mst1 RENAME TO proceeds_mst;