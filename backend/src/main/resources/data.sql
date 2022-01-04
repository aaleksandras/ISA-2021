insert into users (id, deleted, first_name, last_name, email, password, type_of_user, phone_number) values
    ('623a2230-17fa-4fa6-b96f-291803e84f0b', false ,'Marko', 'Markovic', 'marko@gmail.com', 'marko123', 0, '+381-64-123-11-11');

insert into roles (id, deleted, name) values ('380b2cf1-7f15-4694-8fb7-9a3246a10691', false, 'ROLE_ADMINISTRATOR'),
                                             ('cf782a9b-9d3b-47d2-8cad-0b5bf38de251', false, 'ROLE_CUSTOMER');


insert into users_roles(user_id, role_id) values ('623a2230-17fa-4fa6-b96f-291803e84f0b', '380b2cf1-7f15-4694-8fb7-9a3246a10691');

insert into countries (id, deleted, name) values ('5dd5ee5f-15d7-4480-8291-dd5ced667a94', false, 'Serbia'),
                                                 ('a570e1bc-768d-4cfd-889e-8c95527f43f7', false, 'Bosnia and Herzegovina');