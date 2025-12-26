CREATE TABLE goods_mst
(
  goods_id varchar(4) NOT NULL,
  goods_name varchar(20) NOT NULL,
  goods_price int(9) NOT NULL,
  goods_cost int(9) NOT NULL,
  PRIMARY KEY (goods_id)
) ;

INSERT INTO goods_mst VALUES
('0003','おもちゃ3','650','170');



ALTER TABLE goods_mst ADD COLUMN delete_at varchar(4);

UPDATE goods_mst SET delete_at = 1 where goods_id = 0003;

SELECT *from goods_mst WHERE delete_at = 0;

UPDATE goods_mst SET delete_at = 0 where goods_id = 0001;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0002;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0003;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0004;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0005;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0006;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0007;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0008;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0009;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0010;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0011;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0012;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0013;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0014;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0015;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0016;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0017;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0018;
UPDATE goods_mst SET delete_at = 0 where goods_id = 0019;