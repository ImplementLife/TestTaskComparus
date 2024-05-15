
CREATE TABLE IF NOT EXISTS public.p_users (
    user_id bigint NOT NULL,
    login VARCHAR(45),
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    PRIMARY KEY (user_id)
);

INSERT INTO public.p_users (user_id, login, first_name, last_name)
VALUES
(1, 'postgres_login_1', 'first_name_1', 'last_name_1'),
(2, 'postgres_login_2', 'first_name_2', 'last_name_2'),
(3, 'postgres_login_3', 'first_name_3', 'last_name_3');
