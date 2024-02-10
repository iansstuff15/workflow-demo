
create table
    public.department (
                          id uuid not null,
                          created_at timestamp without time zone null default current_timestamp,
                          name character varying(255) not null,
                          updated_at timestamp without time zone null default current_timestamp,
                          constraint department_pkey primary key (id)
) tablespace pg_default;

create table
    public.supplier (
                        id uuid not null,
                        address character varying(255) not null,
                        contact_number character varying(255) not null,
                        created_at timestamp without time zone null default current_timestamp,
                        email character varying(255) not null,
                        name character varying(255) not null,
                        updated_at timestamp without time zone null default current_timestamp,
                        constraint supplier_pkey primary key (id)
) tablespace pg_default;

create table
    public.employee (
                        id uuid not null default uuid_generate_v4 (),
                        created_at timestamp without time zone null default current_timestamp,
                        daily_rate double precision null default 0.0,
                        email character varying(255) not null,
                        emergency_leave_credits double precision null default 15.0,
                        first_name character varying(255) not null,
                        last_name character varying(255) not null,
                        maternity_leave_credits double precision null default 0.0,
                        paternity_leave_credits double precision null default 0.0,
                        pay_schedule character varying(255) not null,
                        phone character varying(255) not null,
                        position character varying(255) not null,
                        sick_leave_credits double precision null default 15.0,
                        updated_at timestamp without time zone null default current_timestamp,
                        vacation_leave_credits double precision null default 15.0,
                        department_id uuid not null,
                        supervisor_id uuid null default uuid_generate_v4 (),
                        constraint employee_pkey primary key (id),
                        constraint uk_fopic1oh5oln2khj8eat6ino0 unique (email),
                        constraint fkbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department (id),
                        constraint fkc30m2tyq9as0ht5xctaypfdqp foreign key (supervisor_id) references employee (id)
) tablespace pg_default;


create table
    public.certificate_of_attendance (
                                         id uuid not null,
                                         attachment character varying(255) null,
                                         created_at timestamp without time zone null default current_timestamp,
                                         end_date timestamp without time zone not null,
                                         end_time time without time zone not null,
                                         reason character varying(255) not null,
                                         start_date timestamp without time zone not null,
                                         start_time time without time zone not null,
                                         status character varying(255) null default 'PENDING'::character varying,
                                         updated_at timestamp without time zone null default current_timestamp,
                                         applied_by_id uuid not null default uuid_generate_v4 (),
                                         reviwed_by_id uuid not null default uuid_generate_v4 (),
                                         adjusted_end_time time without time zone not null,
                                         adjusted_start_time time without time zone not null,
                                         current_end_time time without time zone not null,
                                         current_start_time time without time zone not null,
                                         constraint certificate_of_attendance_pkey primary key (id),
                                         constraint fk5fq0a33b1g01nekhlrfpm97jx foreign key (reviwed_by_id) references employee (id),
                                         constraint fkk7sk8pnioj39nq9o5riim4j8p foreign key (applied_by_id) references employee (id)
) tablespace pg_default;

create table
    public.incident_report (
                               id uuid not null,
                               created_at timestamp without time zone null default current_timestamp,
                               updated_at timestamp without time zone null default current_timestamp,
                               reported_by_id uuid not null default uuid_generate_v4 (),
                               reported_individual_id uuid not null default uuid_generate_v4 (),
                               reviewed_by_id uuid not null default uuid_generate_v4 (),
                               constraint incident_report_pkey primary key (id),
                               constraint fk64b3xm65emvpveegpng2jlnaw foreign key (reported_individual_id) references employee (id),
                               constraint fkfinmx2g6jr3jim8nicpko0fpd foreign key (reviewed_by_id) references employee (id),
                               constraint fkitwmvm1moff1mv78u51x41kva foreign key (reported_by_id) references employee (id)
) tablespace pg_default;

create table
    public.inventory (
                         id uuid not null,
                         brand character varying(255) not null,
                         created_at timestamp without time zone null default current_timestamp,
                         model character varying(255) not null,
                         serial_number character varying(255) not null,
                         status character varying(255) not null,
                         type character varying(255) not null,
                         updated_at timestamp without time zone null default current_timestamp,
                         assigned_by_id uuid not null default uuid_generate_v4 (),
                         assigned_to_id uuid not null default uuid_generate_v4 (),
                         department_id uuid not null,
                         supplier_id uuid not null,
                         acquired_date timestamp without time zone not null,
                         constraint inventory_pkey primary key (id),
                         constraint uk_dufvfeivhwvunykayl0fmxfb unique (department_id),
                         constraint fk61cqv9di3qmxr8jpum4l79vqe foreign key (assigned_by_id) references employee (id),
                         constraint fke0810rp6mmsbj1f46yhc4h7vb foreign key (supplier_id) references supplier (id),
                         constraint fktdklagb2wd13di930v1qyg4f4 foreign key (department_id) references department (id),
                         constraint fkuj7eg4l0ju3jk4x8tprlia3g foreign key (assigned_to_id) references employee (id)
) tablespace pg_default;

create table
    public.leave_request (
                             id uuid not null,
                             attachment character varying(255) null,
                             created_at timestamp without time zone null default current_timestamp,
                             end_date timestamp without time zone not null,
                             is_half_day boolean null default false,
                             reason character varying(255) not null,
                             start_date timestamp without time zone not null,
                             status character varying(255) null default 'PENDING'::character varying,
                             updated_at timestamp without time zone null default current_timestamp,
                             applied_by_id uuid not null default uuid_generate_v4 (),
                             reviwed_by_id uuid not null default uuid_generate_v4 (),
                             constraint leave_request_pkey primary key (id),
                             constraint fk2k7u863qc8xda0pd5axmjsf0v foreign key (reviwed_by_id) references employee (id),
                             constraint fk9s9ctuwv20d4ollbvmfscngo6 foreign key (applied_by_id) references employee (id)
) tablespace pg_default;

create table
    public.official_business (
                                 id uuid not null,
                                 attachment character varying(255) null,
                                 created_at timestamp without time zone null default current_timestamp,
                                 end_date timestamp without time zone not null,
                                 end_time time without time zone not null,
                                 reason character varying(255) not null,
                                 start_date timestamp without time zone not null,
                                 start_time time without time zone not null,
                                 status character varying(255) null default 'PENDING'::character varying,
                                 updated_at timestamp without time zone null default current_timestamp,
                                 applied_by_id uuid not null default uuid_generate_v4 (),
                                 reviwed_by_id uuid not null default uuid_generate_v4 (),
                                 constraint official_business_pkey primary key (id),
                                 constraint fk9kwsmijmcoh6ouc98lkn0frry foreign key (reviwed_by_id) references employee (id),
                                 constraint fke631klw99e587jjsfik1clgm0 foreign key (applied_by_id) references employee (id)
) tablespace pg_default;

create table
    public.payroll (
                       id uuid not null,
                       created_at timestamp without time zone null default current_timestamp,
                       deductions real not null,
                       gross_pay real not null,
                       net_pay real not null,
                       updated_at timestamp without time zone null default current_timestamp,
                       employee_id uuid not null default uuid_generate_v4 (),
                       constraint payroll_pkey primary key (id),
                       constraint fk5o7fr6cbvrkgud2unv0p5rqlm foreign key (employee_id) references employee (id)
) tablespace pg_default;

create table
    public.schedule_adjustment (
                                   id uuid not null,
                                   adjusted_end_time time without time zone not null,
                                   adjusted_start_time time without time zone not null,
                                   attachment character varying(255) null,
                                   created_at timestamp without time zone null default current_timestamp,
                                   current_end_time time without time zone not null,
                                   current_start_time time without time zone not null,
                                   reason character varying(255) not null,
                                   status character varying(255) null default 'PENDING'::character varying,
                                   updated_at timestamp without time zone null default current_timestamp,
                                   applied_by_id uuid not null default uuid_generate_v4 (),
                                   reviwed_by_id uuid not null default uuid_generate_v4 (),
                                   constraint schedule_adjustment_pkey primary key (id),
                                   constraint fkevev90kwbpveo1g6nkiwqu3qp foreign key (reviwed_by_id) references employee (id),
                                   constraint fklce8nv9rds1lft5sbd8r47ssq foreign key (applied_by_id) references employee (id)
) tablespace pg_default;



create table
    public.time_logs (
                         id uuid not null default uuid_generate_v4 (),
                         created_at timestamp without time zone null default current_timestamp,
                         location character varying(255) not null,
                         log_type character varying(255) not null,
                         employee_id uuid not null default uuid_generate_v4 (),
                         constraint time_logs_pkey primary key (id),
                         constraint fkate41bopyxa8x51u39nce21cc foreign key (employee_id) references employee (id)
) tablespace pg_default;

create table
    public.undertime (
                         id uuid not null,
                         attachment character varying(255) null,
                         created_at timestamp without time zone null default current_timestamp,
                         end_date timestamp without time zone not null,
                         end_time time without time zone not null,
                         reason character varying(255) not null,
                         start_date timestamp without time zone not null,
                         start_time time without time zone not null,
                         status character varying(255) null default 'PENDING'::character varying,
                         updated_at timestamp without time zone null default current_timestamp,
                         applied_by_id uuid not null default uuid_generate_v4 (),
                         reviwed_by_id uuid not null default uuid_generate_v4 (),
                         constraint undertime_pkey primary key (id),
                         constraint fk7lu19iwgo212ymjcscl3qlbo0 foreign key (reviwed_by_id) references employee (id),
                         constraint fkrmrtgsy4vh0ylvclrs1x346sc foreign key (applied_by_id) references employee (id)
) tablespace pg_default;