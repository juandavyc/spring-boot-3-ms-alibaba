-- Orders
INSERT INTO orders (id, account_id, total, status, deleted, created_at, created_by)
VALUES ('11111111-1111-1111-1111-111111111111', '1e8f1c88-0d4b-4f2a-a7f8-1234567890ab', 68.60, 'CREATED', FALSE, NOW(),
        'system'),
       ('22222222-2222-2222-2222-222222222222', '2a9b2d99-1c5c-4f3b-b8f9-2345678901bc', 120.00, 'PAID', FALSE, NOW(),
        'system');

-- Order Items
INSERT INTO order_items (id, order_id, product_id, quantity, price)
VALUES ('10101010-1010-1010-1010-101010101010', '11111111-1111-1111-1111-111111111111',
        '1a1f1c88-0d4b-4f2a-a7f8-1234567890ab', 2, 34.30),
       ('10101010-1010-1010-1010-101010101011', '11111111-1111-1111-1111-111111111111',
        '3c3d3eaa-2d6d-4f4c-c9fa-3456789012cd', 4, 27.30),
       ('20202020-2020-2020-2020-202020202020', '22222222-2222-2222-2222-222222222222',
        '2b2f2d99-1c5c-4f3b-b8f9-2345678901bc', 1, 120.00);
