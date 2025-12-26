CREATE TABLE user_mst
(
  user_id varchar(6) NOT NULL,
  user_name varchar(20) NOT NULL,
  user_pass varchar(4) NOT NULL,
  PRIMARY KEY (user_id)
) ;

INSERT INTO user_mst VALUES
('000002','中川','7410');

SELECT *from user_mst;

SELECT proceeds_mst1.proceeds_id, proceeds_mst1.person, proceeds_mst1.pcs, proceeds_mst1.sales_date, goods_mst.goods_name, goods_mst.goods_cost, goods_mst.goods_price, customer_mst.customer_name FROM proceeds_mst1 JOIN goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst1.customer_id = customer_mst.customer_id;

SELECT proceeds_mst1.person, proceeds_mst1.pcs, proceeds_mst1.profit, proceeds_mst1.sales_date, proceeds_mst1.sales, goods_mst.goods_name, goods_mst.goods_price, goods_mst.goods_cost, customer_mst.customer_name FROM proceeds_mst1 JOIN goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst1.customer_id = customer_mst.customer_id

DELETE FROM goods_mst where goods_id = 0001;

SELECT *from goods_mst;

UPDATE goods_mst SET goods_name = 'あああ', goods_price = 800, goods_cost = 50 where goods_id = '0001';
SELECT goods_id ,goods_name, goods_price, goods_cost FROM goods_mst where goods_id = '0001';

UPDATE proceeds_mst1 JOIN goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id  LEFT JOIN customer_mst ON proceeds_mst1.customer_id = customer_mst.customer_id SET proceeds_mst1.person = '川村', proceeds_mst1.sales_date = '2024-08-01', goods_mst.goods_id = 0020, customer_mst.customer_id = 0015, proceeds_mst1.pcs = 20 WHERE proceeds_id = 1;

UPDATE goods_mst SET goods_mst.goods_id = 0020 WHERE proceeds_mst1.proceeds_id = 1;
UPDATE proceeds_mst1 JOIN proceeds_mst1 AS goods_mst ON proceeds_mst1.goods_id = goods_mst.goods_id SET goods_mst.goods_id = 0020 WHERE proceeds_mst1.proceeds_id = 1;

SELECT customer_id ,customer_name FROM customer_mst WHERE customer_id LIKE '%000%' and delete_at = 0;