insert into users (id, is_active, username, deleted, first_name, last_name, email, password, type_of_user, phone_number,
                   address,
                   city, country)
values ('623a2230-17fa-4fa6-b96f-291803e84f0b', true, 'marko123', false, 'Marko', 'Markovic', 'marko@gmail.com',
        '$2a$12$fB5RqVpYN/WUl3saxHn49Ouckh20MVcdy5Br50OchE6yr0ijgGWse', 0, '+381-64-123-11-11', 'Bulevar 3', 'Novi Sad',
        'Srbija'), -- Administrator password marko123
       ('6607774e-d6fb-4748-b31b-0b5c8ea69210', true, 'janko', false, 'Janko', 'Jankovic', 'janko@gmail.com',
        '$2y$12$3qfDyOG93aZexzYPzw.dRe2hvNIqFpXON/X84pO6zb83o5f7NJYLG', 1, '0000320302032', 'Bulevar 1', 'Bg',
        'Srbija'), -- Klijent password janko123
       ('8e4dec87-d2de-4544-abd3-b3814a5f95db', true, 'igor', false, 'Igor', 'Jankovic', 'igor@gmail.com',
        '$2y$12$W44QE.t/LwNtRElX32sGQeQXsKRZHayJWJESJN1sF7UPUjwWCAfBS', 2, '0000320302032', 'Bulevar 1', 'Bg',
        'Srbija'), -- Boatowner password igor123
       ('986c9a6f-be20-4101-8873-d943d3d2c76f', true, 'jasmina', false, 'Jasmina', 'Jankovic', 'jasmina@gmail.com',
        '$2y$12$UnlS97.hbBI5sfHcUI5PKOZ6a/kmYFceHT28Z3sdhyQFNWChG4Qhe', 3, '0000320302032', 'Bulevar 1', 'Bg',
        'Srbija'),-- HOUSEOWNER password jasmina123
       ('450ed6c3-fe1e-4840-b4dd-641b60247c2f', true, 'marija', false, 'Marija', 'Jankovic', 'marija@gmail.com',
        '$2y$10$eeoBM7y.H6X1qkGfhznVjegZFUhiYv1yrq5H1Vj5Se8Uwhe3R8sn6', 4, '0000320302032', 'Bulevar 1', 'Bg',
        'Srbija'); --instructor password marija123


insert into administrators(id, is_first_login)
values ('623a2230-17fa-4fa6-b96f-291803e84f0b', false);

insert into boat_owners(id, description)
values ('6607774e-d6fb-4748-b31b-0b5c8ea69210', 'Top sam vam!');

insert into house_owners(id, description)
values ('8e4dec87-d2de-4544-abd3-b3814a5f95db', 'Top sam vam!');

insert into instructors(id, description)
values ('986c9a6f-be20-4101-8873-d943d3d2c76f', 'Top sam vam!');

insert into clients(id)
values ('450ed6c3-fe1e-4840-b4dd-641b60247c2f');

insert into roles (id, deleted, name)
values ('380b2cf1-7f15-4694-8fb7-9a3246a10691', false, 'ROLE_ADMINISTRATOR'),
       ('cf782a9b-9d3b-47d2-8cad-0b5bf38de251', false, 'ROLE_CLIENT'),
       ('32c57dc2-6d99-11ec-90d6-0242ac120003', false, 'ROLE_HOUSE_OWNER'),
       ('37d9b6b6-6d99-11ec-90d6-0242ac120003', false, 'ROLE_BOAT_OWNER'),
       ('3e1f5ac6-6d99-11ec-90d6-0242ac120003', false, 'ROLE_INSTRUCTOR');


insert into users_roles(user_id, role_id)
values ('623a2230-17fa-4fa6-b96f-291803e84f0b', '380b2cf1-7f15-4694-8fb7-9a3246a10691'),
       ('6607774e-d6fb-4748-b31b-0b5c8ea69210', 'cf782a9b-9d3b-47d2-8cad-0b5bf38de251'),
       ('8e4dec87-d2de-4544-abd3-b3814a5f95db', '32c57dc2-6d99-11ec-90d6-0242ac120003'),
       ('986c9a6f-be20-4101-8873-d943d3d2c76f', '37d9b6b6-6d99-11ec-90d6-0242ac120003'),
       ('450ed6c3-fe1e-4840-b4dd-641b60247c2f', '3e1f5ac6-6d99-11ec-90d6-0242ac120003');

insert into countries (id, deleted, name)
values ('5dd5ee5f-15d7-4480-8291-dd5ced667a94', false, 'Serbia'),
       ('a570e1bc-768d-4cfd-889e-8c95527f43f7', false, 'Bosnia and Herzegovina');


insert into reservation_entity(id, deleted, title, description, average_mark, address, type)
values ('43eeb758-2031-41c2-a85b-e3268b47dd9f', false, 'Brod kod Palme', 'Dodji da se provedes!', 10.0,
        'Bogu iza nogu!', 0),
       ('6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', false, 'Brod1', 'Opis broj 1', 9, 'Neka adress 1', 0),
       ('e754d1ce-050f-4fab-b690-f12a0559786b', false, 'Brod2', 'Opis broj 2', 8, 'Neka adress 2', 0),
       ('73d71e24-4460-46cb-a2f0-a0e9d47b0145', false, 'Brod3', 'Opis broj 3', 7, 'Neka adress 3', 0),
       ('af93ba3b-b01a-47e5-8deb-494a533b58df', false, 'Brod4', 'Opis broj 4', 6, 'Neka adress 4', 0),
       ('e342a717-1954-4b50-adb2-716e93f6c18e', false, 'Vikendica1', 'Opis vikendice1', 6, 'Neka adress 1', 1),
       ('2c4bc7a2-8d4f-433f-84ed-a25d19d6b531', false, 'Vikendica2', 'Opis vikendice2', 6, 'Neka adress 2', 1),
       ('06198d85-ddf0-4545-a718-103e2eccf1a1', false, 'Vikendica3', 'Opis vikendice3', 6, 'Neka adress 3', 1),
       ('4bd54cb8-d582-4157-9baf-a562c378c59a', false, 'Vikendica4', 'Opis vikendice4', 6, 'Neka adress 4', 1),
       ('e7cc59d3-3c83-4d4f-afa0-1d04584a68af', false, 'Avantura1', 'Opis avanture1', 6, 'Neka adress 1', 2),
       ('14b42025-d3fc-475d-8957-ae74aff12e0d', false, 'Avantura2', 'Opis avanture2', 6, 'Neka adress 2', 2),
       ('5292c693-2404-466f-97d5-8d68f39f577d', false, 'Avantura3', 'Opis avanture3', 6, 'Neka adress 3', 2),
       ('8f5eb499-3759-42ca-b354-5b02ecad333d', false, 'Avantura4', 'Opis avanture4', 6, 'Neka adress 4', 2);

insert into adventures(id, max_persons)
values ('e7cc59d3-3c83-4d4f-afa0-1d04584a68af', 1),
       ('14b42025-d3fc-475d-8957-ae74aff12e0d', 2),
       ('5292c693-2404-466f-97d5-8d68f39f577d', 2),
       ('8f5eb499-3759-42ca-b354-5b02ecad333d', 2);


insert into cottages(id, number_of_bads, number_of_rooms)
values ('e342a717-1954-4b50-adb2-716e93f6c18e', 1, 1),
    ('2c4bc7a2-8d4f-433f-84ed-a25d19d6b531', 1, 1),
    ('06198d85-ddf0-4545-a718-103e2eccf1a1', 1, 1),
    ('4bd54cb8-d582-4157-9baf-a562c378c59a', 1, 1);


insert into ships (id, capacity, percentage_for_canceled)
values ('43eeb758-2031-41c2-a85b-e3268b47dd9f', 4, 5),
       ('6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', 4, 5),
       ('73d71e24-4460-46cb-a2f0-a0e9d47b0145', 4, 5),
       ('af93ba3b-b01a-47e5-8deb-494a533b58df', 4, 5),
       ('e754d1ce-050f-4fab-b690-f12a0559786b', 4, 5);

