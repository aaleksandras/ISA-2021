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
values ('8e4dec87-d2de-4544-abd3-b3814a5f95db', 'Top sam vam!');

insert into house_owners(id, description)
values ('986c9a6f-be20-4101-8873-d943d3d2c76f', 'Top sam vam!');

insert into instructors(id, description)
values ('450ed6c3-fe1e-4840-b4dd-641b60247c2f', 'Top sam vam!');

insert into clients(id, penalty, loyalty_category, points)
values ('6607774e-d6fb-4748-b31b-0b5c8ea69210', 0, 0 , 0);

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


insert into reservation_entity(id, deleted, name, description, average_mark, address, type, price)
values ('43eeb758-2031-41c2-a85b-e3268b47dd9f', false, 'Brod kod Palme', 'Dodji da se provedes!', 10.0,
        'Bogu iza nogu!', 0, 100),
       ('6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', false, 'Brod1', 'Opis broj 1', 9, 'Neka adress 1', 0, 100),
       ('e754d1ce-050f-4fab-b690-f12a0559786b', false, 'Brod2', 'Opis broj 2', 8, 'Neka adress 2', 0, 100),
       ('73d71e24-4460-46cb-a2f0-a0e9d47b0145', false, 'Brod3', 'Opis broj 3', 7, 'Neka adress 3', 0, 100),
       ('af93ba3b-b01a-47e5-8deb-494a533b58df', false, 'Brod4', 'Opis broj 4', 6, 'Neka adress 4', 0, 100),
       ('e342a717-1954-4b50-adb2-716e93f6c18e', false, 'Vikendica1', 'Opis vikendice1', 6, 'Neka adress 1', 1, 100),
       ('2c4bc7a2-8d4f-433f-84ed-a25d19d6b531', false, 'Vikendica2', 'Opis vikendice2', 6, 'Neka adress 2', 1, 100),
       ('06198d85-ddf0-4545-a718-103e2eccf1a1', false, 'Vikendica3', 'Opis vikendice3', 6, 'Neka adress 3', 1, 100),
       ('4bd54cb8-d582-4157-9baf-a562c378c59a', false, 'Vikendica4', 'Opis vikendice4', 6, 'Neka adress 4', 1, 100),
       ('e7cc59d3-3c83-4d4f-afa0-1d04584a68af', false, 'Avantura1', 'Opis avanture1', 6, 'Neka adress 1', 2, 100),
       ('14b42025-d3fc-475d-8957-ae74aff12e0d', false, 'Avantura2', 'Opis avanture2', 6, 'Neka adress 2', 2, 100),
       ('5292c693-2404-466f-97d5-8d68f39f577d', false, 'Avantura3', 'Opis avanture3', 6, 'Neka adress 3', 2, 100),
       ('8f5eb499-3759-42ca-b354-5b02ecad333d', false, 'Avantura4', 'Opis avanture4', 6, 'Neka adress 4', 2, 100);

insert into available_days(id, deleted, day, is_free, time, reservation_entyty_id)
values('f176ff01-f0a6-4cd1-827f-3dc598b1ee4f', false , '2022-06-22 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('d7d26378-8f5d-4c25-9375-9adef5202c78', false , '2022-06-23 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('1916cff8-7f93-466d-85d4-825c6a840ca6', false , '2022-06-24 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('551cdedc-ed6a-4643-8ea1-3fac14537f2f', false , '2022-06-25 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('bfb4501e-18fd-4dbd-8481-b468b5c43a6f', false , '2022-06-26 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('16281e9e-ac48-4417-9304-cdb3da4d9b9f', false , '2022-06-27 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('f20ef147-7b68-449c-8ca9-1491f239f0b5', false , '2022-06-28 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('0179f341-f7bf-4378-88da-af0a5bd92a84', false , '2022-06-29 00:00:00.000000', true, '12:58:06','43eeb758-2031-41c2-a85b-e3268b47dd9f' ),
      ('26985722-9dfb-442d-8a93-bd8ce66528bc', false , '2022-07-01 00:00:00.000000', true, '00:58:06','e7cc59d3-3c83-4d4f-afa0-1d04584a68af' ),
      ('54e9b330-5aa4-4769-9e12-cec719758d5e', false , '2022-07-02 00:00:00.000000', true, '00:58:06','e7cc59d3-3c83-4d4f-afa0-1d04584a68af' ),
      ('7483093c-e606-4d88-8d0f-befd441d4d4c', false , '2022-07-03 00:00:00.000000', true, '00:58:06','e7cc59d3-3c83-4d4f-afa0-1d04584a68af' ),
      ('cbae00d6-81ed-4e1a-bb7a-b82a3dc57f19', false , '2022-07-04 00:00:00.000000', true, '00:58:06','e7cc59d3-3c83-4d4f-afa0-1d04584a68af' ),
      ('27262220-eff7-454f-83a5-0d5c2d5cdad8', false , '2022-06-21 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('58b2f351-5ca5-4bf8-8a06-f7297d7a2b24', false , '2022-06-22 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('aea7b2e3-33c4-465d-a84d-0b60c28efd45', false , '2022-06-23 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('56c4f07d-eff5-490f-b5c1-6ac5b1bc812a', false , '2022-06-24 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('d99bf35c-c3b8-4731-bb96-5a5c9144113e', false , '2022-06-25 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('63a63df0-220e-435a-9f1b-8af2cc1feb9b', false , '2022-06-26 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('f77c92b0-56cb-4fd3-af29-ff2ea56f90ea', false , '2022-06-27 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('49e15773-f99e-4c1c-89b9-c62ae2359319', false , '2022-06-28 00:00:00.000000', true, '12:58:06','e754d1ce-050f-4fab-b690-f12a0559786b' ),
      ('74255594-4715-43cb-bffe-bc5583088484', false , '2022-07-04 00:00:00.000000', true, '12:58:06','2c4bc7a2-8d4f-433f-84ed-a25d19d6b531' ),
      ('ce656d9f-06f8-46dc-9b36-88f0bfdd0d6f', false , '2022-07-05 00:00:00.000000', true, '12:58:06','2c4bc7a2-8d4f-433f-84ed-a25d19d6b531' ),
      ('baf0f0c1-fbf0-4885-b5df-497079d4a4c2', false , '2022-07-06 00:00:00.000000', true, '12:58:06','2c4bc7a2-8d4f-433f-84ed-a25d19d6b531' ),
      ('8879e93f-c73e-4f59-99c8-3ee6d9b7069a', false , '2022-07-07 00:00:00.000000', true, '12:58:06','2c4bc7a2-8d4f-433f-84ed-a25d19d6b531' ),
      ('253d279b-d7b1-4338-89db-c227df9ee2ea', false , '2022-07-01 00:00:00.000000', true, '12:58:06','06198d85-ddf0-4545-a718-103e2eccf1a1' ),
      ('f34e1bd7-2d06-420e-90de-4bf3d84fe96f', false , '2022-07-02 00:00:00.000000', true, '12:58:06','06198d85-ddf0-4545-a718-103e2eccf1a1' ),
      ('9d14cd17-2857-4df4-b2a6-29faff45efb0', false , '2022-07-03 00:00:00.000000', true, '12:58:06','06198d85-ddf0-4545-a718-103e2eccf1a1' ),
      ('4669e86a-d964-4652-ba7c-3ebc763c66ea', false , '2022-07-04 00:00:00.000000', true, '12:58:06','06198d85-ddf0-4545-a718-103e2eccf1a1' ),
      ('4f724fac-c65c-48d0-85bf-b1d12a4b279f', false , '2022-06-22 00:00:00.000000', true, '12:58:06','5292c693-2404-466f-97d5-8d68f39f577d' ),
      ('e8127d6d-2ce0-4cc7-a083-03ad7c1bf26c', false , '2022-06-23 00:00:00.000000', true, '12:58:06','5292c693-2404-466f-97d5-8d68f39f577d' ),
      ('c361bd45-c64a-4a5c-86ca-aa85eb5bbf53', false , '2022-06-24 00:00:00.000000', true, '12:58:06','5292c693-2404-466f-97d5-8d68f39f577d' ),
      ('fb3c847d-7c68-4a0e-a643-1887d3cc81fb', false , '2022-06-25 00:00:00.000000', true, '12:58:06','5292c693-2404-466f-97d5-8d68f39f577d' ),
      ('524fed73-c747-4026-94b7-91e2c69692ee', false , '2022-07-01 00:00:00.000000', true, '12:58:06','8f5eb499-3759-42ca-b354-5b02ecad333d' ),
      ('39dcafd1-ee60-42d5-bb26-ac8d247dde5f', false , '2022-07-02 00:00:00.000000', true, '12:58:06','8f5eb499-3759-42ca-b354-5b02ecad333d' ),
      ('3fa92d6a-9421-4a62-ab9f-f44feed2823b', false , '2022-07-03 00:00:00.000000', true, '12:58:06','8f5eb499-3759-42ca-b354-5b02ecad333d' ),
      ('b77d6018-9193-4c6a-a5b4-49b77718ba16', false , '2022-07-04 00:00:00.000000', true, '12:58:06','8f5eb499-3759-42ca-b354-5b02ecad333d' );

insert into terms(id, deleted, end_date, end_time, start_date, start_time)
values('24492db3-5931-4a3a-bd5b-36ba3cd753c9', false , '2022-06-25 00:00:00.000000', '12:58:06', '2022-06-23 00:00:00.000000', '12:50:00'),
      ('5b24792e-d484-11ec-9d64-0242ac120002', false , '2022-05-17 00:00:00.000000', '12:58:06', '2022-05-15 00:00:00.000000', '12:50:00'),
      ('8f302ea6-e420-11ec-8fea-0242ac120002', false , '2022-05-26 00:00:00.000000', '12:58:06', '2022-05-23 00:00:00.000000', '12:50:00'),
      ('9815c76a-e420-11ec-8fea-0242ac120002', false , '2022-05-11 00:00:00.000000', '12:58:06', '2022-05-10 00:00:00.000000', '12:50:00'),
      ('77e5e9a4-e5d6-11ec-8fea-0242ac120002', false , '2022-07-04 00:00:00.000000', '12:58:06', '2022-07-03 00:00:00.000000', '12:50:00'),
      ('7d037154-e5d6-11ec-8fea-0242ac120002', false , '2022-07-10 00:00:00.000000', '12:58:06', '2022-07-08 00:00:00.000000', '12:50:00'),
      ('823d5e50-e5d6-11ec-8fea-0242ac120002', false , '2022-06-23 00:00:00.000000', '12:58:06', '2022-06-22 00:00:00.000000', '12:50:00'),
      ('882e44e6-e5d6-11ec-8fea-0242ac120002', false , '2022-07-17 00:00:00.000000', '12:58:06', '2022-07-15 00:00:00.000000', '12:50:00');

insert into reservations (id, deleted, discount, number_of_persons, price, status_of_reservation, reservation_entyty_id, term_id, user_id )
values('f45e93b1-e64c-4f32-bd88-216bc2898a74', false , 200, 4, 400, 2, '8f5eb499-3759-42ca-b354-5b02ecad333d', '24492db3-5931-4a3a-bd5b-36ba3cd753c9', null),
      ('5b247a0a-d484-11ec-9d64-0242ac120002', false , 200, 4, 400, 0, '8f5eb499-3759-42ca-b354-5b02ecad333d', '5b24792e-d484-11ec-9d64-0242ac120002', '6607774e-d6fb-4748-b31b-0b5c8ea69210'),
      ('fbc875dc-e420-11ec-8fea-0242ac120002', false , 300, 4, 400, 0, '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', '8f302ea6-e420-11ec-8fea-0242ac120002', '6607774e-d6fb-4748-b31b-0b5c8ea69210'),
      ('009d2436-e421-11ec-8fea-0242ac120002', false , 100, 4, 150, 2, 'e342a717-1954-4b50-adb2-716e93f6c18e', '9815c76a-e420-11ec-8fea-0242ac120002', '6607774e-d6fb-4748-b31b-0b5c8ea69210'),
      ('a77bccfc-e5d5-11ec-8fea-0242ac120002', false , 200, 4, 200, 2, '8f5eb499-3759-42ca-b354-5b02ecad333d', '77e5e9a4-e5d6-11ec-8fea-0242ac120002', null),
      ('b10a6a4e-e5d5-11ec-8fea-0242ac120002', false , 200, 4, 100, 2, 'e342a717-1954-4b50-adb2-716e93f6c18e', '7d037154-e5d6-11ec-8fea-0242ac120002', null),
      ('b60dced2-e5d5-11ec-8fea-0242ac120002', false , 200, 4, 300, 2, 'e342a717-1954-4b50-adb2-716e93f6c18e', '823d5e50-e5d6-11ec-8fea-0242ac120002', null),
      ('bec2f5fc-e5d5-11ec-8fea-0242ac120002', false , 200, 4, 100, 2, '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', '882e44e6-e5d6-11ec-8fea-0242ac120002', null);




insert into pictures (id, deleted, name, reservation_entity_id)
values ('fe623e8e-88ab-49cc-a8a1-da0e63cc5735', false, '1.jpg', '43eeb758-2031-41c2-a85b-e3268b47dd9f'),
       ('5eecf935-a2b8-42e6-aeb1-94a46f1f6504', false, '2.jpg', '43eeb758-2031-41c2-a85b-e3268b47dd9f'),
       ('ac2b24d1-e2ad-4008-9e29-14ae708a4f5b', false, '3.jpg', '43eeb758-2031-41c2-a85b-e3268b47dd9f'),
       ('028fda86-bd2c-407e-abf9-0f8d27af2269', false, '2.jpg', 'e754d1ce-050f-4fab-b690-f12a0559786b'),
       ('1db64cba-66a2-446b-837b-aaf1275b9e59', false, '3.jpg', 'e754d1ce-050f-4fab-b690-f12a0559786b'),
       ('103f72f2-131b-4f42-9b22-dd547d5b68ea', false, '1.jpg', '73d71e24-4460-46cb-a2f0-a0e9d47b0145'),
       ('b7ff5ceb-b9c0-48ae-90b4-65495cb9ff77', false, '3.jpg', '73d71e24-4460-46cb-a2f0-a0e9d47b0145'),
       ('e2bc3749-d8c3-4f4e-b42b-60050b3bd622', false, '3.jpg', 'af93ba3b-b01a-47e5-8deb-494a533b58df'),
       ('3cf556fc-4093-4a51-93e0-9e07b77f7ba2', false, '4.jpg', '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4'),
       ('e4d738f0-4531-467d-9799-69676bb7c252', false, '5.jpeg', '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4'),
       ('7e580eb9-8515-4cad-bafe-087b7617fa20', false, '6.jpg', '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4'),
       ('a8f9437d-f788-4a18-9548-92e708986bf5', false, '7.jpg', 'e342a717-1954-4b50-adb2-716e93f6c18e'),
       ('753545a4-c941-40a0-b797-f9e56aff51fa', false, '8.jpg', '2c4bc7a2-8d4f-433f-84ed-a25d19d6b531'),
       ('33fcb17d-2ae2-4a3f-b2f3-485867958704', false, '8a.jpg', '06198d85-ddf0-4545-a718-103e2eccf1a1'),
       ('e29f2ac8-324c-4d7b-8698-9e4e4b9b4be5', false, '6.jpg', '4bd54cb8-d582-4157-9baf-a562c378c59a'),
       ('09117cf6-6e5c-49d5-a7da-c9edf64317a5', false, '9.jpeg', 'e7cc59d3-3c83-4d4f-afa0-1d04584a68af'),
       ('5dead200-acf2-4382-a168-c63f3a3ff001', false, '9.jpeg', '5292c693-2404-466f-97d5-8d68f39f577d'),
       ('8c1d689e-0332-4356-a00c-8525b0286185', false, '9.jpeg', '8f5eb499-3759-42ca-b354-5b02ecad333d'),
       ('fdba5d13-c52f-4e65-8b02-6406b65fae58', false, '9a.jpg', '14b42025-d3fc-475d-8957-ae74aff12e0d');


insert into rules (id, deleted, name)
values ('742b40f1-7a9e-439a-bbc6-f841ca4d9076', false, 'Pusenje'),
       ('39fcdd8c-8361-4599-98d2-d71fe4f12bf3', false, 'Topla voda'),
       ('c4582904-1f6c-4706-a991-85f57c2f36fb', false, 'Pusenje'),
       ('9f0c0ee4-423d-4af4-9f7d-e13949652cbd', false, 'Klima'),
       ('d9cf26af-0b68-4ef1-b1e2-08b4bff061a4', false, 'Internet');


insert into reservation_entity_rule(id, deleted, allowed, reservation_entity_id, rule_id)
values
('3a09a08f-a889-4f6d-8996-6769ff8ff771', false, true, '43eeb758-2031-41c2-a85b-e3268b47dd9f', '742b40f1-7a9e-439a-bbc6-f841ca4d9076'),
('beb9711e-0008-460d-bd04-b0c4cb5ab23e', false, true, '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', '742b40f1-7a9e-439a-bbc6-f841ca4d9076'),
('0c788016-5d8e-4828-aa8c-7800beee2a4a', false, true, 'e342a717-1954-4b50-adb2-716e93f6c18e', '742b40f1-7a9e-439a-bbc6-f841ca4d9076'),
('34c9efdb-2e15-4e6f-99dc-8d7e15064dcd', false, true, '43eeb758-2031-41c2-a85b-e3268b47dd9f', '39fcdd8c-8361-4599-98d2-d71fe4f12bf3'),
('49868230-2672-49e1-bc08-0818d8e8ca82', false, true, '6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', '39fcdd8c-8361-4599-98d2-d71fe4f12bf3');


insert into adventures(id, max_persons)
values ('e7cc59d3-3c83-4d4f-afa0-1d04584a68af', 1),
       ('14b42025-d3fc-475d-8957-ae74aff12e0d', 2),
       ('5292c693-2404-466f-97d5-8d68f39f577d', 2),
       ('8f5eb499-3759-42ca-b354-5b02ecad333d', 2);

insert into adventure_additional_service(adventure_id, additional_service)
values ('e7cc59d3-3c83-4d4f-afa0-1d04584a68af', 'wifi'),
       ('14b42025-d3fc-475d-8957-ae74aff12e0d', 'hot-watter'),
       ('5292c693-2404-466f-97d5-8d68f39f577d', 'TV'),
       ('8f5eb499-3759-42ca-b354-5b02ecad333d', 'air-conditional');


insert into cottages(id, number_of_beds, number_of_rooms)
values ('e342a717-1954-4b50-adb2-716e93f6c18e', 1, 1),
       ('2c4bc7a2-8d4f-433f-84ed-a25d19d6b531', 1, 1),
       ('06198d85-ddf0-4545-a718-103e2eccf1a1', 1, 1),
       ('4bd54cb8-d582-4157-9baf-a562c378c59a', 1, 1);


insert into ships (id, capacity, percentage_for_canceled, type_of_boat, length, engine_number, engine_power, max_speed)
values ('43eeb758-2031-41c2-a85b-e3268b47dd9f', 4, 5, 'tip' ,10, 200, 300, 40),
       ('6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', 4, 5, 'tip' ,10, 200, 300, 40),
       ('73d71e24-4460-46cb-a2f0-a0e9d47b0145', 4, 5, 'tip' ,10, 200, 300, 40),
       ('af93ba3b-b01a-47e5-8deb-494a533b58df', 4, 5, 'tip' ,10, 200, 300, 40),
       ('e754d1ce-050f-4fab-b690-f12a0559786b', 4, 5, 'tip' ,10, 200, 300, 40);

insert into ship_additional_service(ship_id, additional_service)
values ('43eeb758-2031-41c2-a85b-e3268b47dd9f', 'wifi'),
       ('6a7431d6-64d2-4a09-a3be-ad1a962fbfe4', 'hot-watter'),
       ('73d71e24-4460-46cb-a2f0-a0e9d47b0145', 'TV'),
       ('af93ba3b-b01a-47e5-8deb-494a533b58df', 'air-conditional'),
       ('e754d1ce-050f-4fab-b690-f12a0559786b', 'TV');

insert into percentages_from_reservations (id, percentage) values ('24eeb758-2031-41c2-a85b-e3268b47dd9f', 10);

insert into revision(id, user_id, reservation_id,content, mark, status, deleted) values ('60699e7e-deab-11ec-9d64-0242ac120002', '6607774e-d6fb-4748-b31b-0b5c8ea69210', '5b247a0a-d484-11ec-9d64-0242ac120002', 'Pocepao je zavese', 1, 0, false);

insert into loyality_program (id_program, percent_gold, percent_silver, percent_regular, points_to_gold, points_to_silver, points_to_regular, client_points_for_reservation)
values ('b5a3aafc-e058-11ec-9d64-0242ac120002', 20, 15, 10 ,50, 30, 10, 5)