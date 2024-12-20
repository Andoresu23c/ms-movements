PGDMP                      |            ms-movements    17.2 (Debian 17.2-1.pgdg120+1)    17.0     E           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            F           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            G           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            H           1262    16384    ms-movements    DATABASE     y   CREATE DATABASE "ms-movements" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE "ms-movements";
                     admin    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            I           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16385    cliente    TABLE     �   CREATE TABLE public.cliente (
    contrasenia character varying(255) NOT NULL,
    id_cliente character varying(255) NOT NULL,
    id_persona bigint NOT NULL
);
    DROP TABLE public.cliente;
       public         heap r       admin    false    4            �            1259    16393    cuenta    TABLE       CREATE TABLE public.cuenta (
    id_cuenta bigint NOT NULL,
    estado boolean NOT NULL,
    numero_cuenta character varying(255) NOT NULL,
    saldo_inicial double precision NOT NULL,
    tipo_cuenta character varying(255),
    fk_cliente bigint NOT NULL
);
    DROP TABLE public.cuenta;
       public         heap r       admin    false    4            �            1259    16392    cuenta_id_cuenta_seq    SEQUENCE     �   ALTER TABLE public.cuenta ALTER COLUMN id_cuenta ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.cuenta_id_cuenta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               admin    false    219    4            �            1259    16401 
   movimiento    TABLE       CREATE TABLE public.movimiento (
    id_movimiento bigint NOT NULL,
    estado boolean NOT NULL,
    fecha date,
    saldo double precision NOT NULL,
    tipo_movimiento character varying(255),
    valor double precision NOT NULL,
    fk_cuenta bigint NOT NULL
);
    DROP TABLE public.movimiento;
       public         heap r       admin    false    4            �            1259    16400    movimiento_id_movimiento_seq    SEQUENCE     �   ALTER TABLE public.movimiento ALTER COLUMN id_movimiento ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.movimiento_id_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               admin    false    4    221            �            1259    16407    persona    TABLE     =  CREATE TABLE public.persona (
    id_persona bigint NOT NULL,
    direccion character varying(255),
    edad integer NOT NULL,
    estado boolean NOT NULL,
    genero character varying(255),
    identificacion character varying(255) NOT NULL,
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.persona;
       public         heap r       admin    false    4            �            1259    16406    persona_id_persona_seq    SEQUENCE     �   ALTER TABLE public.persona ALTER COLUMN id_persona ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.persona_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               admin    false    4    223            <          0    16385    cliente 
   TABLE DATA           F   COPY public.cliente (contrasenia, id_cliente, id_persona) FROM stdin;
    public               admin    false    217   �%       >          0    16393    cuenta 
   TABLE DATA           j   COPY public.cuenta (id_cuenta, estado, numero_cuenta, saldo_inicial, tipo_cuenta, fk_cliente) FROM stdin;
    public               admin    false    219   �%       @          0    16401 
   movimiento 
   TABLE DATA           l   COPY public.movimiento (id_movimiento, estado, fecha, saldo, tipo_movimiento, valor, fk_cuenta) FROM stdin;
    public               admin    false    221   &       B          0    16407    persona 
   TABLE DATA           p   COPY public.persona (id_persona, direccion, edad, estado, genero, identificacion, nombre, telefono) FROM stdin;
    public               admin    false    223   |&       J           0    0    cuenta_id_cuenta_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cuenta_id_cuenta_seq', 4, true);
          public               admin    false    218            K           0    0    movimiento_id_movimiento_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.movimiento_id_movimiento_seq', 5, true);
          public               admin    false    220            L           0    0    persona_id_persona_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.persona_id_persona_seq', 2, true);
          public               admin    false    222            �           2606    16391    cliente cliente_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_persona);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public                 admin    false    217            �           2606    16399    cuenta cuenta_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id_cuenta);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public                 admin    false    219            �           2606    16405    movimiento movimiento_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id_movimiento);
 D   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT movimiento_pkey;
       public                 admin    false    221            �           2606    16413    persona persona_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public                 admin    false    223            �           2606    16421 #   persona uk19624pour0ky6k8m7oc9vnsrm 
   CONSTRAINT     b   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT uk19624pour0ky6k8m7oc9vnsrm UNIQUE (telefono);
 M   ALTER TABLE ONLY public.persona DROP CONSTRAINT uk19624pour0ky6k8m7oc9vnsrm;
       public                 admin    false    223            �           2606    16417 "   cuenta ukpj7ncg765kt4klndu25bwbwe4 
   CONSTRAINT     f   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT ukpj7ncg765kt4klndu25bwbwe4 UNIQUE (numero_cuenta);
 L   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT ukpj7ncg765kt4klndu25bwbwe4;
       public                 admin    false    219            �           2606    16415 #   cliente ukqfu9lhga79cj19jnxshe1uhdj 
   CONSTRAINT     d   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT ukqfu9lhga79cj19jnxshe1uhdj UNIQUE (id_cliente);
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT ukqfu9lhga79cj19jnxshe1uhdj;
       public                 admin    false    217            �           2606    16419 #   persona ukr5vsms84ih2viwd6tatk9o5pq 
   CONSTRAINT     h   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT ukr5vsms84ih2viwd6tatk9o5pq UNIQUE (identificacion);
 M   ALTER TABLE ONLY public.persona DROP CONSTRAINT ukr5vsms84ih2viwd6tatk9o5pq;
       public                 admin    false    223            �           2606    16427 "   cuenta fkagy9d8kqq9pwdmdda52ewjmbs    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fkagy9d8kqq9pwdmdda52ewjmbs FOREIGN KEY (fk_cliente) REFERENCES public.cliente(id_persona);
 L   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fkagy9d8kqq9pwdmdda52ewjmbs;
       public               admin    false    219    3225    217            �           2606    16422 #   cliente fklbs69o9qkvv7lgn06idak3crb    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fklbs69o9qkvv7lgn06idak3crb FOREIGN KEY (id_persona) REFERENCES public.persona(id_persona);
 M   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fklbs69o9qkvv7lgn06idak3crb;
       public               admin    false    217    3235    223            �           2606    16432 &   movimiento fkpf59wx90s05rwweqx1utrbyg5    FK CONSTRAINT     �   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fkpf59wx90s05rwweqx1utrbyg5 FOREIGN KEY (fk_cuenta) REFERENCES public.cuenta(id_cuenta);
 P   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT fkpf59wx90s05rwweqx1utrbyg5;
       public               admin    false    221    3229    219            <   2   x��+M-Kt��+)J,N��L442�t���\@���400�4����� ]"�      >   .   x�3�,�4�4�06�440�t�/*�L�+I�4�2�IbH��qqq ���      @   V   x�3�,�4202�54�5428]Ro.�,��L�9Ӑ�p��d�s����QM 	"+0�2EU`�a�!�	~3�b���� ��%�      B   �   x�U�A�0@���sm�K����@M�`K(��<��[�?�����c�/�?-90��}1A�M��t�D�}?�㐦��L	���Ƒ5Zi�nrȜ0G|ᲆ8�Ef0�?ʕ+1t){��S
Úlä�'��,g     