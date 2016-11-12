CREATE TABLE configuracionvertical (
    id character varying(255) NOT NULL,
    css text,
    habilitado boolean,
    nombre character varying(255),
    porcentajeretencion real,
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
    proveedor_id character varying(255),
    servicioactivo_id character varying(255)
);


ALTER TABLE public.jornadalaboral OWNER TO yuberadmin;

CREATE TABLE jornadalaboral_servicio (
    jornadalaboral_id character varying(255) NOT NULL,
    servicios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.jornadalaboral_servicio OWNER TO yuberadmin;

CREATE TABLE pagosproveedor (
    id character varying(255) NOT NULL,
    fechapago timestamp without time zone,
    pago boolean,
    porcentageretencion real,
    proveedor_id character varying(255),
    servicio_id character varying(255)
);


ALTER TABLE public.pagosproveedor OWNER TO yuberadmin;

CREATE TABLE persona (
    dtype character varying(31) NOT NULL,
    id character varying(255) NOT NULL,
    apellido character varying(255),
    clave character varying(255),
    eliminado boolean,
    descripcionmail character varying(255),
    email character varying(255),
    fechanacimiento date,
    nombre character varying(255),
    descripciontel character varying(255),
    telefono character varying(255),
    cantidadservicios integer,
    idredsocial character varying(255),
    rating real,
    redsocialusada character varying(255),
    tokentarjeta character varying(255),
    ultimosnumerostarjeta integer,
    activo boolean,
    proveedor_id character varying(255),
    servicioactivo_id character varying(255)
);


ALTER TABLE public.persona OWNER TO yuberadmin;

CREATE TABLE persona_servicio (
    usuario_id character varying(255) NOT NULL,
    servicios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.persona_servicio OWNER TO yuberadmin;

CREATE TABLE proveedor (
    id character varying(255) NOT NULL,
    activo boolean,
    cantidadservicios integer,
    descripcion character varying(255),
    nombre character varying(255),
    rating real,
    stripeaccid character varying(255),
    descripciontel character varying(255),
    telefono character varying(255),
    tokentarjeta character varying(255),
    ultimosnumerostarjeta integer,
    jornadaactual_id character varying(255),
    usuario_id character varying(255)
);


ALTER TABLE public.proveedor OWNER TO yuberadmin;

CREATE TABLE proveedor_jornadalaboral (
    proveedor_id character varying(255) NOT NULL,
    jornadas_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.proveedor_jornadalaboral OWNER TO yuberadmin;

CREATE TABLE puntosrecorrido (
    servicio_id character varying(255) NOT NULL,
    punto character varying(255)
);


ALTER TABLE public.puntosrecorrido OWNER TO yuberadmin;

CREATE TABLE servicio (
    id character varying(255) NOT NULL,
    comentario character varying(255),
    coordenadasdestino character varying(255),
    coordenadasorigen character varying(255),
    descripcion character varying(255),
    estado character varying(255),
    fecha timestamp without time zone,
    fin timestamp without time zone,
    inicio timestamp without time zone,
    precio real,
    rating real,
    proveedor_id character varying(255),
    usuario_id character varying(255)
);


ALTER TABLE public.servicio OWNER TO yuberadmin;

ALTER TABLE ONLY configuracionvertical
    ADD CONSTRAINT configuracionvertical_pkey PRIMARY KEY (id);


ALTER TABLE ONLY jornadalaboral
    ADD CONSTRAINT jornadalaboral_pkey PRIMARY KEY (id);


ALTER TABLE ONLY jornadalaboral_servicio
    ADD CONSTRAINT jornadalaboral_servicio_pkey PRIMARY KEY (jornadalaboral_id, list_index);


ALTER TABLE ONLY pagosproveedor
    ADD CONSTRAINT pagosproveedor_pkey PRIMARY KEY (id);


ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);


ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT persona_servicio_pkey PRIMARY KEY (usuario_id, list_index);


ALTER TABLE ONLY proveedor_jornadalaboral
    ADD CONSTRAINT proveedor_jornadalaboral_pkey PRIMARY KEY (proveedor_id, list_index);


ALTER TABLE ONLY proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (id);


ALTER TABLE ONLY servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (id);


ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT uk_13rsnmbq1idiq67tusa4rr9mm UNIQUE (servicios_id);


ALTER TABLE ONLY proveedor_jornadalaboral
    ADD CONSTRAINT uk_2so8r69fjva0f9urajv6fkjoe UNIQUE (jornadas_id);


ALTER TABLE ONLY jornadalaboral_servicio
    ADD CONSTRAINT uk_jyfwon15pn82dbvpxxfjuns0o UNIQUE (servicios_id);


ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT fk1c882b2ooyb580118504v77u8 FOREIGN KEY (usuario_id) REFERENCES persona(id);


ALTER TABLE ONLY jornadalaboral
    ADD CONSTRAINT fk4275ekoyrtlpr1mqhsfs512lu FOREIGN KEY (servicioactivo_id) REFERENCES servicio(id);


ALTER TABLE ONLY puntosrecorrido
    ADD CONSTRAINT fk86q236oruw6bb8s0iu679trqy FOREIGN KEY (servicio_id) REFERENCES servicio(id);


ALTER TABLE ONLY pagosproveedor
    ADD CONSTRAINT fka2heugae2xpdlqh22mgvci7oo FOREIGN KEY (proveedor_id) REFERENCES proveedor(id);


ALTER TABLE ONLY proveedor_jornadalaboral
    ADD CONSTRAINT fkbjuq16rk9eexkrluob5xy8tqa FOREIGN KEY (proveedor_id) REFERENCES proveedor(id);


ALTER TABLE ONLY persona_servicio
    ADD CONSTRAINT fkdly2cd6721sdp8w5itui4r1y3 FOREIGN KEY (servicios_id) REFERENCES servicio(id);


ALTER TABLE ONLY persona
    ADD CONSTRAINT fke2pq3sptb1bywssbu99jk8gen FOREIGN KEY (servicioactivo_id) REFERENCES servicio(id);


ALTER TABLE ONLY jornadalaboral_servicio
    ADD CONSTRAINT fkfefxg8wolsdt6cgvaoskbjfq2 FOREIGN KEY (jornadalaboral_id) REFERENCES jornadalaboral(id);


ALTER TABLE ONLY jornadalaboral_servicio
    ADD CONSTRAINT fkm93dduldkqfme7819i594igtj FOREIGN KEY (servicios_id) REFERENCES servicio(id);


ALTER TABLE ONLY jornadalaboral
    ADD CONSTRAINT fkmxv54wjad5nu7itf6ksxyr6b6 FOREIGN KEY (proveedor_id) REFERENCES proveedor(id);


ALTER TABLE ONLY pagosproveedor
    ADD CONSTRAINT fkn7vlpanelvuxqs0wsff19s956 FOREIGN KEY (servicio_id) REFERENCES servicio(id);


ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fkoby0y865irqomom3k97h3ylb0 FOREIGN KEY (usuario_id) REFERENCES persona(id);


ALTER TABLE ONLY proveedor
    ADD CONSTRAINT fkood4v7flm7y5vlvlisufyjp7s FOREIGN KEY (jornadaactual_id) REFERENCES jornadalaboral(id);


ALTER TABLE ONLY persona
    ADD CONSTRAINT fkoxspmdh62c59oy57tduwhf5s7 FOREIGN KEY (proveedor_id) REFERENCES proveedor(id);


ALTER TABLE ONLY proveedor_jornadalaboral
    ADD CONSTRAINT fkr8d50cl57qcfl88gl3jr6uskq FOREIGN KEY (jornadas_id) REFERENCES jornadalaboral(id);


ALTER TABLE ONLY servicio
    ADD CONSTRAINT fkre9dqoc58875cj1dbus356o2y FOREIGN KEY (usuario_id) REFERENCES persona(id);


ALTER TABLE ONLY servicio
    ADD CONSTRAINT fksj4nwfvostymmgiisdishllsy FOREIGN KEY (proveedor_id) REFERENCES proveedor(id);

