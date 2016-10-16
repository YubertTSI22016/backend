CREATE TABLE administrador (
    id character varying(255) NOT NULL,
    activo boolean,
    apellido character varying(255),
    clave character varying(255),
    eliminado boolean,
    descripcion character varying(255),
    email character varying(255),
    nombre character varying(255)
);

ALTER TABLE public.administrador OWNER TO yuberadmin;

CREATE TABLE configuracionvertical (
    id character varying(255) NOT NULL,
    habilitado boolean,
    nombre character varying(255),
    precioporhora real,
    precioporkm real,
    tarifabase real,
    transporte boolean
);


ALTER TABLE public.configuracionvertical OWNER TO yuberadmin;

CREATE TABLE jornadalaboral (
    id character varying(255) NOT NULL,
    fin timestamp without time zone,
    inicio timestamp without time zone,
    proveedor_id character varying(255)
);


ALTER TABLE public.jornadalaboral OWNER TO yuberadmin;

CREATE TABLE persona (
    dtype character varying(31) NOT NULL,
    id character varying(255) NOT NULL,
    apellido character varying(255),
    clave character varying(255),
    eliminado boolean,
    descripcion character varying(255),
    email character varying(255),
    fechanacimiento date,
    nombre character varying(255),
    idredsocial character varying(255),
    redsocialusada character varying(255),
    activo boolean
);


ALTER TABLE public.persona OWNER TO yuberadmin;

CREATE TABLE persona_jornadalaboral (
    proveedor_id character varying(255) NOT NULL,
    jornadas_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.persona_jornadalaboral OWNER TO yuberadmin;

CREATE TABLE persona_servicio (
    persona_id character varying(255) NOT NULL,
    servicios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.persona_servicio OWNER TO yuberadmin;

CREATE TABLE persona_telefonoscontacto (
    persona_id character varying(255) NOT NULL,
    descripcion character varying(255),
    telefono character varying(255),
    list_index integer NOT NULL
);


ALTER TABLE public.persona_telefonoscontacto OWNER TO yuberadmin;

CREATE TABLE servicio (
    id character varying(255) NOT NULL,
    comentario character varying(255),
    coordenadasdestino character varying(255),
    coordenadasorigen character varying(255),
    descripcion character varying(255),
    estado character varying(255),
    fecha timestamp without time zone,
    precio real,
    rating integer,
    proveedor_id character varying(255),
    usuario_id character varying(255)
);


ALTER TABLE public.servicio OWNER TO yuberadmin;


ALTER TABLE ONLY administrador
    ADD CONSTRAINT administrador_pkey PRIMARY KEY (id);


ALTER TABLE ONLY configuracionvertical
    ADD CONSTRAINT configuracionvertical_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jornadalaboral
    ADD CONSTRAINT jornadalaboral_pkey PRIMARY KEY (id);

ALTER TABLE ONLY persona_jornadalaboral
    ADD CONSTRAINT persona_jornadalaboral_pkey PRIMARY KEY (proveedor_id, list_index);

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);

ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT persona_servicio_pkey PRIMARY KEY (persona_id, list_index);

ALTER TABLE ONLY persona_telefonoscontacto
    ADD CONSTRAINT persona_telefonoscontacto_pkey PRIMARY KEY (persona_id, list_index);

ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tenant
    ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);

ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT uk_13rsnmbq1idiq67tusa4rr9mm UNIQUE (servicios_id);

ALTER TABLE ONLY persona_jornadalaboral
    ADD CONSTRAINT uk_knm5apgqkven4lc6h9tqqh0yk UNIQUE (jornadas_id);

ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT fk2mvn52td44hsicpnapimo4by FOREIGN KEY (persona_id) REFERENCES persona(id);

ALTER TABLE ONLY persona_jornadalaboral
    ADD CONSTRAINT fk38nwtpoqq9tnlou0gt9vq33nh FOREIGN KEY (jornadas_id) REFERENCES jornadalaboral(id);

ALTER TABLE ONLY servicio
    ADD CONSTRAINT fk9uw258lr8pcl7r2px6bxm0694 FOREIGN KEY (proveedor_id) REFERENCES persona(id);

ALTER TABLE ONLY jornadalaboral
    ADD CONSTRAINT fkc8ksl28r3jlwt77k50mng1bnn FOREIGN KEY (proveedor_id) REFERENCES persona(id);

ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT fkdly2cd6721sdp8w5itui4r1y3 FOREIGN KEY (servicios_id) REFERENCES servicio(id);

ALTER TABLE ONLY persona_telefonoscontacto
    ADD CONSTRAINT fkk40w7lbc0hpiolhkh845krknh FOREIGN KEY (persona_id) REFERENCES persona(id);

ALTER TABLE ONLY persona_jornadalaboral
    ADD CONSTRAINT fkqn4qlfdyd7xgajw6waaeafnwi FOREIGN KEY (proveedor_id) REFERENCES persona(id);

ALTER TABLE ONLY servicio
    ADD CONSTRAINT fkre9dqoc58875cj1dbus356o2y FOREIGN KEY (usuario_id) REFERENCES persona(id);