INSERT INTO `yukawa_db`.`user_roles`
(`uuid`, `name`, `can_post`, `can_edit`, `can_delete`, `can_view`)
VALUES
    ('8eb00976-e878-11ef-9be3-2cf05d052b10', 'BLACKLIST', b'0', b'0', b'0', b'0'),
    ('d2fcb2c5-e877-11ef-9be3-2cf05d052b10', 'ADMIN', b'1', b'1', b'1', b'1'),
    ('d2fcc4fe-e877-11ef-9be3-2cf05d052b10', 'GUEST', b'0', b'0', b'0', b'1');