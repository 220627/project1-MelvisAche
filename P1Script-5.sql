--Reimbursement Table
create table ers_reimbursement(
reimb_id serial primary key,
reimb_amount_$  numeric,
reimb_description varchar(250),
reimb_author int references ers_users(ers_users_id),--review this
reimb_resolver int references ers_users(ers_users_id),--revew this
reimb_status_id_fk  int references ers_reimbursement_status(reim_status_id),
reimb_type_id_fk int references ers_reimbursement_type(reimb_type_id),
reimb_receipt bytea,--blob
reimb_submitted timestamp,
reimb_resolved timestamp
);
insert into ers_reimbursement(reimb_amount_$, reimb_description, reimb_author, reimb_resolver, reimb_status_id_fk, reimb_type_id_fk, reimb_receipt, reimb_submitted, reimb_resolved)
values(2000, 'Obeyed rules', 02, 02, 1, 1, '\x123456', current_timestamp, current_timestamp);

select * from ers_reimbursement;
--drop table ers_reimbursement cascade;

--Reimbursement Status Approved, Pending or Denied
create table ers_reimbursement_status(
reim_status_id serial primary key,
reimb_status varchar(10)
);
insert into ers_reimbursement_status(reimb_status)
values('Approved');
select * from ers_reimbursement_status;
--drop table ers_reimbursement_status cascade;

--Reimbursement Type
create table ers_reimbursement_type(
reimb_type_id serial primary key,
reimb_type varchar(20)
);
insert into ers_reimbursement_type(reimb_type)
values('Direct Deposit');
select * from ers_reimbursement_type;
--drop table ers_reimbursement_type cascade;

--User roles table
create table ers_user_roles(
ers_user_role_id serial primary key,
user_role varchar(50)
);
insert into ers_user_roles(user_role) 
values('System Analyst');
select * from ers_user_roles;
--drop  table ers_user_roles cascade;

--Users Credentials/Table
create table ers_users(
ers_users_id serial primary key,
ers_username varchar(50), unique(ers_username),
"ers_password" varchar(50),
user_first_name varchar(100),
user_last_name varchar(100),
user_email varchar(150), unique(user_email),
user_role_id_fk int references ers_user_roles(ers_user_role_id)
);
insert into ers_users(ers_username, "ers_password", user_first_name, user_last_name, user_email, user_role_id_fk)
values ('MelvisAche', 'Explosive01', 'Melvis', 'Ache', 'achemelvis@gmail.com', 1), 
('Khalifa', 'khalifa02', 'Denning', 'Billah', 'khalifa02@gmail.com', 2);
select * from ers_users;
--drop  table ers_users cascade;