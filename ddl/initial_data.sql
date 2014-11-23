DELETE FROM history;
DELETE FROM rel_product_product_tag;
DELETE FROM product_tag;
DELETE FROM product_type_a;
DELETE FROM product_type_b;
DELETE FROM product;
DELETE FROM product_category;

INSERT INTO product_category VALUES
(1, 'おもちゃ'),
(2, 'ゲーム'),
(3, 'パソコン')
;

INSERT INTO product VALUES
(1, 1, 'ミニ四駆', 1),
(2, 1, 'ベイブレード', 2),
(3, 2, '3DS', 1),
(4, 2, 'PS Vita', 1),
(5, 2, 'Wii U', 2),
(6, 3, 'Thinkpad', 2)
;

INSERT INTO product_type_a VALUES
(1, 'サンダードラゴン'),
(3, 'ホワイト'),
(4, 'ブラック')
;

INSERT INTO product_type_b VALUES
(2, 3),
(5, 100),
(6, 150)
;

INSERT INTO product_tag VALUES
(1, '屋外'),
(2, '屋内'),
(3, 'NEW'),
(4, '多用途')
;

INSERT INTO rel_product_product_tag VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 4),
(5, 2),
(6, 1),
(6, 2),
(6, 4)
;

INSERT INTO history (product_id, quantity, amount) VALUES
(3, 2, 34000),
(1, 1, 800),
(3, 1, 16000),
(5, 1, 24000),
(5, 2, 48000),
(6, 2, 224000),
(2, 2, 1780),
(4, 10, 380000)
;
