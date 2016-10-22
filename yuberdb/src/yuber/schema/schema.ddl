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

CREATE TABLE jornadalaboral_servicio (
    jornadalaboral_id character varying(255) NOT NULL,
    servicios_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.jornadalaboral_servicio OWNER TO yuberadmin;

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
    activo boolean,
    proveedor_id character varying(255)
);


ALTER TABLE public.persona OWNER TO yuberadmin;

CREATE TABLE persona_servicio (
    usuario_id character varying(255) NOT NULL,
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

CREATE TABLE proveedor (
    id character varying(255) NOT NULL,
    activo boolean,
    rating real,
    usuario_id character varying(255)
);


ALTER TABLE public.proveedor OWNER TO yuberadmin;

CREATE TABLE proveedor_jornadalaboral (
    proveedor_id character varying(255) NOT NULL,
    jornadas_id character varying(255) NOT NULL,
    list_index integer NOT NULL
);


ALTER TABLE public.proveedor_jornadalaboral OWNER TO yuberadmin;

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
