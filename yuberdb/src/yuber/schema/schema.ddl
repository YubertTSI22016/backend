
    create table ConfiguracionEmpresa (
        id varchar(255) not null,
        aceptaCuponera boolean,
        activo boolean,
        claveLdap varchar(255),
        colorBoton varchar(255),
        colorFondoLista varchar(255),
        colorFondosDePantalla varchar(255),
        colorLetras varchar(255),
        colorTextoLista varchar(255),
        colorTitulo varchar(255),
        css Text,
        diasCreacionViaje int4,
        iconoEmpresa varchar(255),
        nombre varchar(255),
        pagoOnlineCoche boolean,
        reservaPasajes boolean,
        trasferirPasajes boolean,
        ultimaCreacionDeViajes timestamp,
        urlAcceso varchar(255),
        urlLdap varchar(255),
        usuarioLdap varchar(255),
        validesReservasHoras int4,
        primary key (id)
    );

    create table ConfiguracionEmpresa_emails (
        ConfiguracionEmpresa_id varchar(255) not null,
        descripcion varchar(255),
        email varchar(255),
        LIST_INDEX int4 not null,
        primary key (ConfiguracionEmpresa_id, LIST_INDEX)
    );

    create table ConfiguracionEmpresa_telefonos (
        ConfiguracionEmpresa_id varchar(255) not null,
        descripcion varchar(255),
        telefono varchar(255),
        LIST_INDEX int4 not null,
        primary key (ConfiguracionEmpresa_id, LIST_INDEX)
    );

    create table Cuponera (
        id varchar(255) not null,
        saldo float4,
        primary key (id)
    );

    create table DiasGruposHorarios (
        GrupoHorarioId varchar(255) not null,
        DiasEspecificos timestamp,
        LIST_INDEX int4 not null,
        primary key (GrupoHorarioId, LIST_INDEX)
    );

    create table Encomienda (
        id varchar(255) not null,
        codigoEncomienda int4 not null,
        ciEmisor varchar(255),
        ciReceptor varchar(255),
        direccionReceptor varchar(255),
        eliminada boolean,
        fechaEntrega timestamp,
        fechaIngreso timestamp,
        monto float4,
        pagaReceptor boolean,
        precio float4,
        retiraEnSucursal boolean,
        descripcion varchar(255),
        telefono varchar(255),
        cocheAsignado_id varchar(255),
        destino_id varchar(255),
        emisor_id varchar(255),
        estadoActual_id varchar(255),
        origen_id varchar(255),
        receptor_id varchar(255),
        reglaCobro_id varchar(255),
        viajeAsignado_id varchar(255),
        primary key (id, codigoEncomienda)
    );

    create table Encomienda_HistorialEstadosEncomienda (
        Encomienda_id varchar(255) not null,
        Encomienda_codigoEncomienda int4 not null,
        estados_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Encomienda_id, Encomienda_codigoEncomienda, LIST_INDEX)
    );

    create table EstadosEncomienda (
        id varchar(255) not null,
        nombre varchar(255),
        primary key (id)
    );

    create table GrupoHorario (
        id varchar(255) not null,
        nombre varchar(255),
        tipo varchar(255),
        primary key (id)
    );

    create table GrupoHorario_Horario (
        GrupoHorario_id varchar(255) not null,
        horarios_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (GrupoHorario_id, LIST_INDEX)
    );

    create table HistorialEstadosEncomienda (
        id varchar(255) not null,
        fecha timestamp,
        estado_id varchar(255),
        primary key (id)
    );

    create table Horario (
        id varchar(255) not null,
        nombre varchar(255),
        primary key (id)
    );

    create table MantenimientoVehiculo (
        id varchar(255) not null,
        costo float4,
        descripcionCompleta varchar(255),
        descripcionReducida varchar(255),
        fechaCompleado timestamp,
        fechaIngreso timestamp,
        primary key (id)
    );

    create table MedioDePago (
        id varchar(255) not null,
        activo boolean,
        clave varchar(255),
        cuenta varchar(255),
        nombre varchar(255),
        usuario varchar(255),
        primary key (id)
    );

    create table Pasaje (
        id varchar(255) not null,
        codigoPasaje int4 not null,
        ciPersona varchar(255),
        eliminado boolean,
        fechaCompra timestamp,
        pago boolean,
        usado boolean,
        comprador_id varchar(255),
        destino_id varchar(255),
        origen_id varchar(255),
        precio_id varchar(255),
        vendedor_id varchar(255),
        viaje_id varchar(255),
        primary key (id, codigoPasaje)
    );

    create table Perfil (
        id varchar(255) not null,
        modulo1 boolean,
        modulo2 boolean,
        modulo3 boolean,
        modulo4 boolean,
        modulo5 boolean,
        modulo6 boolean,
        modulo7 boolean,
        modulo8 boolean,
        nombrePerfil varchar(255),
        primary key (id)
    );

    create table Perfil_Persona (
        Perfil_id varchar(255) not null,
        empleados_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Perfil_id, LIST_INDEX)
    );

    create table Persona (
        DTYPE varchar(31) not null,
        id varchar(255) not null,
        apellido varchar(255),
        clave varchar(255),
        eliminado boolean,
        descripcion varchar(255),
        email varchar(255),
        fechaNacimiento date,
        nombrePila varchar(255),
        idRedSocial varchar(255),
        redSocialUsada varchar(255),
        idEmpleadoLdap varchar(255),
        cuponera_id varchar(255),
        perfil_id varchar(255),
        primary key (id)
    );

    create table Persona_Encomienda (
        Persona_id varchar(255) not null,
        encomiendas_id varchar(255) not null,
        encomiendas_codigoEncomienda int4 not null,
        LIST_INDEX int4 not null,
        primary key (Persona_id, LIST_INDEX)
    );

    create table Persona_telefonosContacto (
        Persona_id varchar(255) not null,
        descripcion varchar(255),
        telefono varchar(255),
        LIST_INDEX int4 not null,
        primary key (Persona_id, LIST_INDEX)
    );

    create table Precio (
        id varchar(255) not null,
        monto float4,
        destino_id varchar(255),
        origen_id varchar(255),
        primary key (id)
    );

    create table PuntoRecorrido (
        DTYPE varchar(31) not null,
        id varchar(255) not null,
        eliminado boolean,
        nombre varchar(255),
        ubicacionMapa varchar(255),
        aceptaEncomiendas boolean,
        primary key (id)
    );

    create table Recorrido (
        id varchar(255) not null,
        eliminado boolean,
        nombre varchar(255),
        primary key (id)
    );

    create table Recorrido_GrupoHorario (
        Recorrido_id varchar(255) not null,
        horarios_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Recorrido_id, LIST_INDEX)
    );

    create table Recorrido_Precio (
        Recorrido_id varchar(255) not null,
        precios_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Recorrido_id, LIST_INDEX)
    );

    create table Recorrido_PuntoRecorrido (
        Recorrido_id varchar(255) not null,
        puntosDeRecorrido_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Recorrido_id, LIST_INDEX)
    );

    create table ReglaCobroEncomienda (
        id varchar(255) not null,
        nombre varchar(255),
        precioExactoOCalculo boolean,
        primary key (id)
    );

    create table ReglaCobroEncomiendaCriteria (
        id varchar(255) not null,
        operador varchar(255),
        precio float4,
        valor int4,
        primary key (id)
    );

    create table ReglaCobroEncomienda_ReglaCobroEncomiendaCriteria (
        ReglaCobroEncomienda_id varchar(255) not null,
        criterias_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (ReglaCobroEncomienda_id, LIST_INDEX)
    );

    create table Reserva (
        id varchar(255) not null,
        ciPersona varchar(255),
        eliminada boolean,
        fechaReserva timestamp,
        utilizada boolean,
        destino_id varchar(255),
        empleado_id varchar(255),
        origen_id varchar(255),
        precio_id varchar(255),
        usuarioReserva_id varchar(255),
        viaje_id varchar(255),
        primary key (id)
    );

    create table Tenant (
        id varchar(255) not null,
        domain varchar(255),
        isActive boolean,
        isDelete boolean,
        name varchar(255),
        primary key (id)
    );

    create table Terminal_mailsDeContacto (
        Terminal_id varchar(255) not null,
        descripcion varchar(255),
        email varchar(255),
        LIST_INDEX int4 not null,
        primary key (Terminal_id, LIST_INDEX)
    );

    create table Terminal_telefonosContacto (
        Terminal_id varchar(255) not null,
        descripcion varchar(255),
        telefono varchar(255),
        LIST_INDEX int4 not null,
        primary key (Terminal_id, LIST_INDEX)
    );

    create table Usuario_notificaciones (
        Usuario_id varchar(255) not null,
        fecha timestamp,
        mensaje varchar(255),
        LIST_INDEX int4 not null,
        primary key (Usuario_id, LIST_INDEX)
    );

    create table Vehiculo (
        id varchar(255) not null,
        anioFabricacion int4,
        cantidadAsientos int4,
        conGuarda boolean,
        eliminado boolean,
        fechaAlta date,
        marca varchar(255),
        matricula varchar(255),
        modelo varchar(255),
        numeroVehiculo varchar(255),
        primary key (id)
    );

    create table Vehiculo_Encomienda (
        Vehiculo_id varchar(255) not null,
        encomiendas_id varchar(255) not null,
        encomiendas_codigoEncomienda int4 not null,
        LIST_INDEX int4 not null,
        primary key (Vehiculo_id, LIST_INDEX)
    );

    create table Vehiculo_MantenimientoVehiculo (
        Vehiculo_id varchar(255) not null,
        mantenimientos_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Vehiculo_id, LIST_INDEX)
    );

    create table Viaje (
        id varchar(255) not null,
        fechaSalida timestamp,
        horario_id varchar(255),
        recorrido_id varchar(255),
        primary key (id)
    );

    create table Viaje_Persona (
        Viaje_id varchar(255) not null,
        empleados_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Viaje_id, LIST_INDEX)
    );

    create table Viaje_Reserva (
        Viaje_id varchar(255) not null,
        reservas_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Viaje_id, LIST_INDEX)
    );

    create table Viaje_Vehiculo (
        Viaje_id varchar(255) not null,
        coches_id varchar(255) not null,
        LIST_INDEX int4 not null,
        primary key (Viaje_id, LIST_INDEX)
    );

    create table dias (
        GrupoHorarioId varchar(255) not null,
        diasSemana varchar(255),
        LIST_INDEX int4 not null,
        primary key (GrupoHorarioId, LIST_INDEX)
    );

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint UK_o4ifqp9wtuyh6yyrj2y7dtunw  unique (estados_id);

    alter table GrupoHorario_Horario 
        add constraint UK_i8vwfxybh0quda0tavoaodq1q  unique (horarios_id);

    alter table Perfil_Persona 
        add constraint UK_72x6tlk8weghwf7fg7kwv6ggo  unique (empleados_id);

    alter table Persona_Encomienda 
        add constraint UK_ob0glgv6x5261k1a82wbu9r4a  unique (encomiendas_id, encomiendas_codigoEncomienda);

    alter table Recorrido_GrupoHorario 
        add constraint UK_3irwqpu998d89noj694x1aswh  unique (horarios_id);

    alter table Recorrido_Precio 
        add constraint UK_b0188ldsu72xkho27163gi1vr  unique (precios_id);

    alter table ReglaCobroEncomienda_ReglaCobroEncomiendaCriteria 
        add constraint UK_arpe4vnc33o327gr1s5t2ttmw  unique (criterias_id);

    alter table Vehiculo_Encomienda 
        add constraint UK_p1qpltmjq3qchbxp10kgg4x0q  unique (encomiendas_id, encomiendas_codigoEncomienda);

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint UK_myj2cqpd205do48yrvsg77nnn  unique (mantenimientos_id);

    alter table Viaje_Persona 
        add constraint UK_bc581nw2fuo8wooi6xklfjrg8  unique (empleados_id);

    alter table Viaje_Reserva 
        add constraint UK_nhxrsaq5q7iaebnhe0csko754  unique (reservas_id);

    alter table ConfiguracionEmpresa_emails 
        add constraint FK_1gy1wh0twl8d5pdpmkbc35rwc 
        foreign key (ConfiguracionEmpresa_id) 
        references ConfiguracionEmpresa;

    alter table ConfiguracionEmpresa_telefonos 
        add constraint FK_eq1skmvw0ahcnj2jjcg6jtqsv 
        foreign key (ConfiguracionEmpresa_id) 
        references ConfiguracionEmpresa;

    alter table DiasGruposHorarios 
        add constraint FK_j0fo4hou9o2cjpvscwql9otx8 
        foreign key (GrupoHorarioId) 
        references GrupoHorario;

    alter table Encomienda 
        add constraint FK_ndhmq6wiggedgn0716fq6hrxq 
        foreign key (cocheAsignado_id) 
        references Vehiculo;

    alter table Encomienda 
        add constraint FK_lu2t6lvkhos8kqv2mvkqqk5cl 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Encomienda 
        add constraint FK_sw3mg6bcaqxiehk012t7ljbkd 
        foreign key (emisor_id) 
        references Persona;

    alter table Encomienda 
        add constraint FK_m385yqv0o0i48aa1eghl2jqx6 
        foreign key (estadoActual_id) 
        references EstadosEncomienda;

    alter table Encomienda 
        add constraint FK_5x2u7bttkbkm5f80dm56pf59s 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Encomienda 
        add constraint FK_70r54agnflhmfrifumhap86xd 
        foreign key (receptor_id) 
        references Persona;

    alter table Encomienda 
        add constraint FK_5tvkuor274t22xm4ph1dp1aqb 
        foreign key (reglaCobro_id) 
        references ReglaCobroEncomienda;

    alter table Encomienda 
        add constraint FK_k9y4bsstq94qvbkstxqpm4itd 
        foreign key (viajeAsignado_id) 
        references Viaje;

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint FK_o4ifqp9wtuyh6yyrj2y7dtunw 
        foreign key (estados_id) 
        references HistorialEstadosEncomienda;

    alter table Encomienda_HistorialEstadosEncomienda 
        add constraint FK_qvyk31c31mj1toumyi9ivga4f 
        foreign key (Encomienda_id, Encomienda_codigoEncomienda) 
        references Encomienda;

    alter table GrupoHorario_Horario 
        add constraint FK_i8vwfxybh0quda0tavoaodq1q 
        foreign key (horarios_id) 
        references Horario;

    alter table GrupoHorario_Horario 
        add constraint FK_hdk3ibt2x2e3u7s8kr5tgxcwn 
        foreign key (GrupoHorario_id) 
        references GrupoHorario;

    alter table HistorialEstadosEncomienda 
        add constraint FK_iv2bin3afihjpwyullr559i59 
        foreign key (estado_id) 
        references EstadosEncomienda;

    alter table Pasaje 
        add constraint FK_qypdlnb5jm65d9i5d7724ic2y 
        foreign key (comprador_id) 
        references Persona;

    alter table Pasaje 
        add constraint FK_osxop9oh3av3oyg6l2phjie6e 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Pasaje 
        add constraint FK_tgrk1toib3l43ymmcp3rllen9 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Pasaje 
        add constraint FK_lrv16svbya50dn4j8fyjg4rxp 
        foreign key (precio_id) 
        references Precio;

    alter table Pasaje 
        add constraint FK_8eddxbxbrcyl1sjjs5b9bi8s9 
        foreign key (vendedor_id) 
        references Persona;

    alter table Pasaje 
        add constraint FK_45tgxw544atj8lq6oyo9ly3kg 
        foreign key (viaje_id) 
        references Viaje;

    alter table Perfil_Persona 
        add constraint FK_72x6tlk8weghwf7fg7kwv6ggo 
        foreign key (empleados_id) 
        references Persona;

    alter table Perfil_Persona 
        add constraint FK_3uqsd0g7qbuuatp8fyuomq0vj 
        foreign key (Perfil_id) 
        references Perfil;

    alter table Persona 
        add constraint FK_3mmokvoe2m3hovhc6bb603if8 
        foreign key (cuponera_id) 
        references Cuponera;

    alter table Persona 
        add constraint FK_nsjd0asvtjy77jmuqukhdqa8a 
        foreign key (perfil_id) 
        references Perfil;

    alter table Persona_Encomienda 
        add constraint FK_ob0glgv6x5261k1a82wbu9r4a 
        foreign key (encomiendas_id, encomiendas_codigoEncomienda) 
        references Encomienda;

    alter table Persona_Encomienda 
        add constraint FK_brofvaqxcvhk5v4beg5rsl4bm 
        foreign key (Persona_id) 
        references Persona;

    alter table Persona_telefonosContacto 
        add constraint FK_64rj9xfvsrdxq0ohep3u85q54 
        foreign key (Persona_id) 
        references Persona;

    alter table Precio 
        add constraint FK_syilby562ubf2nf5b3f25f4bw 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Precio 
        add constraint FK_rmk9yovcmawvj5nd0mloeipoj 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Recorrido_GrupoHorario 
        add constraint FK_3irwqpu998d89noj694x1aswh 
        foreign key (horarios_id) 
        references GrupoHorario;

    alter table Recorrido_GrupoHorario 
        add constraint FK_gjqvoynoi8mg61x1wf56vckhw 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table Recorrido_Precio 
        add constraint FK_b0188ldsu72xkho27163gi1vr 
        foreign key (precios_id) 
        references Precio;

    alter table Recorrido_Precio 
        add constraint FK_qq2b4r9uw7ovs7acdhnbh74po 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table Recorrido_PuntoRecorrido 
        add constraint FK_ee5p44tsn9dco5l0dxf6u2p0o 
        foreign key (puntosDeRecorrido_id) 
        references PuntoRecorrido;

    alter table Recorrido_PuntoRecorrido 
        add constraint FK_5fo8vfw5v601xd5ffw17rkfqs 
        foreign key (Recorrido_id) 
        references Recorrido;

    alter table ReglaCobroEncomienda_ReglaCobroEncomiendaCriteria 
        add constraint FK_arpe4vnc33o327gr1s5t2ttmw 
        foreign key (criterias_id) 
        references ReglaCobroEncomiendaCriteria;

    alter table ReglaCobroEncomienda_ReglaCobroEncomiendaCriteria 
        add constraint FK_f7phejn833mjnj8hpsekkovc3 
        foreign key (ReglaCobroEncomienda_id) 
        references ReglaCobroEncomienda;

    alter table Reserva 
        add constraint FK_p6k2oyyml1fny5w4on9compj3 
        foreign key (destino_id) 
        references PuntoRecorrido;

    alter table Reserva 
        add constraint FK_7cv1rsnw8180t5ixp1nk0gbsn 
        foreign key (empleado_id) 
        references Persona;

    alter table Reserva 
        add constraint FK_dhrmid2rwsgmg6wfqy8oekua3 
        foreign key (origen_id) 
        references PuntoRecorrido;

    alter table Reserva 
        add constraint FK_a585lpkv2x3oq2xo16iijouu7 
        foreign key (precio_id) 
        references Precio;

    alter table Reserva 
        add constraint FK_gj6mwu7px9cdeq4spnm660su2 
        foreign key (usuarioReserva_id) 
        references Persona;

    alter table Reserva 
        add constraint FK_km77kynwhjjp437juk8ln5n6n 
        foreign key (viaje_id) 
        references Viaje;

    alter table Terminal_mailsDeContacto 
        add constraint FK_jrq99b4vla9j195p014p9r1bk 
        foreign key (Terminal_id) 
        references PuntoRecorrido;

    alter table Terminal_telefonosContacto 
        add constraint FK_pcss06xxddygc7c2vek340hkc 
        foreign key (Terminal_id) 
        references PuntoRecorrido;

    alter table Usuario_notificaciones 
        add constraint FK_ta692fdsg4mr0iaige255tfff 
        foreign key (Usuario_id) 
        references Persona;

    alter table Vehiculo_Encomienda 
        add constraint FK_p1qpltmjq3qchbxp10kgg4x0q 
        foreign key (encomiendas_id, encomiendas_codigoEncomienda) 
        references Encomienda;

    alter table Vehiculo_Encomienda 
        add constraint FK_c1gkg84183qbjqdw819p0sre4 
        foreign key (Vehiculo_id) 
        references Vehiculo;

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint FK_myj2cqpd205do48yrvsg77nnn 
        foreign key (mantenimientos_id) 
        references MantenimientoVehiculo;

    alter table Vehiculo_MantenimientoVehiculo 
        add constraint FK_skcvsf2gy026guw5nom5jcuwf 
        foreign key (Vehiculo_id) 
        references Vehiculo;

    alter table Viaje 
        add constraint FK_l6exsxsgca9wo889gpl7os1ff 
        foreign key (horario_id) 
        references Horario;

    alter table Viaje 
        add constraint FK_s7a40spa1k4vgmf1i42boyb46 
        foreign key (recorrido_id) 
        references Recorrido;

    alter table Viaje_Persona 
        add constraint FK_bc581nw2fuo8wooi6xklfjrg8 
        foreign key (empleados_id) 
        references Persona;

    alter table Viaje_Persona 
        add constraint FK_ot5d3wyxfudo29ub3a8y100eu 
        foreign key (Viaje_id) 
        references Viaje;

    alter table Viaje_Reserva 
        add constraint FK_nhxrsaq5q7iaebnhe0csko754 
        foreign key (reservas_id) 
        references Reserva;

    alter table Viaje_Reserva 
        add constraint FK_hfinyo80vii4dt8h6nvdmp80l 
        foreign key (Viaje_id) 
        references Viaje;

    alter table Viaje_Vehiculo 
        add constraint FK_gfjj2ndw3v3b1aimy7bomb2bh 
        foreign key (coches_id) 
        references Vehiculo;

    alter table Viaje_Vehiculo 
        add constraint FK_kygwggiv599v0v7h8ljainla3 
        foreign key (Viaje_id) 
        references Viaje;

    alter table dias 
        add constraint FK_hbh26sy6gwvgixbk380fa5im2 
        foreign key (GrupoHorarioId) 
        references GrupoHorario;
        
    INSERT INTO estadosencomienda (id, nombre) VALUES ('9a265943-ab81-4a19-a752-03b2db475fed', 'Recibida');
INSERT INTO estadosencomienda (id, nombre) VALUES ('c1423557-e9fb-472a-92f6-023328107117', 'Enviada');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f8be0436-76f0-44b0-88d8-4051bb844b41', 'Perdida');
INSERT INTO estadosencomienda (id, nombre) VALUES ('cc9bf2f4-e09a-4067-82ab-4bd4e800c7a3', 'En viaje');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f75ebf4c-43a5-4331-91e6-bb16e94a42eb', 'Regresada');
INSERT INTO estadosencomienda (id, nombre) VALUES ('f7036bc6-a43e-4d14-ac9c-cb35f182235b', 'Entregada');




INSERT INTO perfil (id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, modulo8, nombreperfil) VALUES ('ecefa62b-185a-4e48-a019-f67521e2b9cd', true, true, true, true, true, true, true, true, 'Admin');
INSERT INTO perfil (id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, modulo8, nombreperfil) VALUES ('40b64aed-ae63-4908-914d-b011188c171a', true, true, true, true, false, false, true, true, 'Vendedor');
INSERT INTO perfil (id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, modulo8, nombreperfil) VALUES ('49b26bf8-1be4-4405-a0d7-0356f644440d', false, false, true, false, true, true, false, false, 'Encargado Deposito');
INSERT INTO perfil (id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, modulo8, nombreperfil) VALUES ('fb192132-7a5c-427a-9c55-bc4b339e1ffd', false, false, false, false, true, true, false, true, 'Guarda');
INSERT INTO perfil (id, modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, modulo8, nombreperfil) VALUES ('d4d86bd5-60e5-4b45-abd4-58ea63af3b84', false, false, false, true, true, true, false, false, 'Encargado Taller');

INSERT INTO persona (dtype, id, apellido, eliminado, descripcion, email, fechanacimiento, nombrepila, clave, idredsocial, redsocialusada, idempleadoldap, cuponera_id, perfil_id) VALUES ('Empleado', 'd1826b85-4527-4bd9-98d1-9dbad268ce5e', 'Perez', false, NULL, 'juan.perez@lacbus.tenant.com', NULL, 'Juan', NULL, NULL, NULL, '', NULL, NULL);
INSERT INTO persona (dtype, id, apellido, eliminado, descripcion, email, fechanacimiento, nombrepila, clave, idredsocial, redsocialusada, idempleadoldap, cuponera_id, perfil_id) VALUES ('Empleado', '52897d2a-49af-40cc-939c-e6a300f720e7', 'Sosa', false, NULL, 'julio.sosa@lacbus.tenant.com', NULL, 'Julio', NULL, NULL, NULL, '', NULL, NULL);
INSERT INTO persona (dtype, id, apellido, eliminado, descripcion, email, fechanacimiento, nombrepila, clave, idredsocial, redsocialusada, idempleadoldap, cuponera_id, perfil_id) VALUES ('Empleado', '2f769c0c-6ea4-4d67-a50b-b3167344bfce', 'Pacheco', false, NULL, 'antonio.pacheco@lacbus.tenant.com', NULL, 'Antonio', NULL, NULL, NULL, '', NULL, NULL);
INSERT INTO persona (dtype, id, apellido, eliminado, descripcion, email, fechanacimiento, nombrepila, clave, idredsocial, redsocialusada, idempleadoldap, cuponera_id, perfil_id) VALUES ('Empleado', 'c726c21c-f2e4-4094-a9b6-bb5811cf2e0e', 'Paez', false, NULL, 'ruben.paez@lacbus.tenant.com', NULL, 'Ruben', NULL, NULL, NULL, '', NULL, NULL);



INSERT INTO vehiculo (id, aniofabricacion, cantidadasientos, conguarda, eliminado, fechaalta, marca, matricula, modelo, numerovehiculo) VALUES ('65378fa8-8931-44c9-8a89-895af6a37c33', 1990, 45, NULL, false, NULL, 'VW', 'SAS123', 'W', '1234');
INSERT INTO vehiculo (id, aniofabricacion, cantidadasientos, conguarda, eliminado, fechaalta, marca, matricula, modelo, numerovehiculo) VALUES ('352f3329-90d4-4cfa-b0bd-4202d152dcb0', 1890, 43, NULL, false, NULL, 'vw', 'SaS234', 'sa', '2345');
INSERT INTO vehiculo (id, aniofabricacion, cantidadasientos, conguarda, eliminado, fechaalta, marca, matricula, modelo, numerovehiculo) VALUES ('c20854d0-4229-4ee9-9101-4f95681eaa21', 1999, 34, NULL, false, NULL, 'vw', 'sas345', 'gol', '3456');
INSERT INTO vehiculo (id, aniofabricacion, cantidadasientos, conguarda, eliminado, fechaalta, marca, matricula, modelo, numerovehiculo) VALUES ('7e7024fb-4cef-4133-95be-22ec7eb4448c', 1989, 60, NULL, false, NULL, 'vw', 'sas4567', 'gol', '45678');

 
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', false, 'tres cruces', '-34.8940096615171,-56.16642236709595', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', false, 'estadio', '-34.89233766031895,-56.15704536437988', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', false, 'clinicas', '-34.890859230912774,-56.15189552307129', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '9efaad2f-8189-4a77-950d-9874a02f316f', false, 'Comercio', '-34.887092635773804,-56.12966537475586', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '73bc4092-999d-4cfd-a38f-b28e831bf819', false, 'H Yirigoyen', '-34.88762067353586,-56.11048221588135', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', false, 'Portones', '-34.883185050874395,-56.08245849609375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', false, 'Puente delas America', '-34.87653116798392,-56.045165061950684', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', false, 'Aeropuerto', '-34.839731459420655,-56.019287109375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '686a9123-08e1-4b34-9155-94e334720248', false, 'Lagomar', '-34.81817221720561,-55.97877502441406', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', false, 'Bolivia', '-34.793294577935896,-55.940022468566895', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', false, 'Peaje', '-34.786457197671105,-55.8900260925293', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', false, 'Neptunia', '-34.780817694621916,-55.872602462768555', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', false, 'Pinar', '-34.777715803604686,-55.85123062133789', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', false, 'Salinas', '-34.77560081104566,-55.839385986328125', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', false, 'Marindia', '-34.772357717160965,-55.82050323486328', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', false, 'Fortin Sta Rosa', '-34.76953753191822,-55.805912017822266', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4434c6c1-bc64-4793-846b-9b09f7134af1', false, 'Villa Argentina', '-34.76756334490676,-55.777587890625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', false, 'Atlantida', '-34.766717250303486,-55.76291084289551', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8c7eebec-04d3-41b2-99e0-6649177063be', false, 'Las Toscas', '-34.76079434523751,-55.73965072631836', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', false, 'Parque Del Plata', '-34.75374271370495,-55.71836471557617', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '68ef97da-d3fd-4665-83c3-01ddf0028472', false, 'Las Vegas', '-34.7506398050501,-55.701026916503906', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', false, 'La Floresta', '-34.749793537001814,-55.6842041015625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4198d04a-f91b-4e75-a950-26b9a5de5018', false, 'Costa Azul', '-34.76051229153917,-55.66326141357422', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '68e955bb-2a3c-43db-a66b-4307d352acaf', false, 'Bello horizonte', '-34.76629419974942,-55.6431770324707', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '6e84cc8a-351c-4888-bc26-d879d63d7f80', false, 'Bello Horizonte', '-34.75289647745205,-55.642662048339844', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', false, 'Guazuvira', '-34.75501205182485,-55.61159133911133', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', false, 'San Luis', '-34.770806627203704,-55.57605743408203', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', false, 'La tuna', '-34.777574805787424,-55.55957794189453', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', false, 'Araminda', '-34.78152265358811,-55.55065155029297', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8cfb908f-da6e-479d-9f1d-de81562d7173', false, 'Sta Lucia Del Este', '-34.78434242921046,-55.53606033325195', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '040865ec-b451-4881-9054-e54a0321602d', false, 'Biarritz', '-34.78800799341509,-55.5164909362793', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'ea1124d0-4d13-407e-b939-9f386c7c58da', false, 'Cuchilla Alta Ruta', '-34.7847653872402,-55.49812316894531', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', false, 'Cuchilla alta', '-34.79392894530395,-55.49692153930664', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', false, 'Santa Ana', '-34.79096852249939,-55.46602249145508', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', false, 'Santa Ana', '-34.77954875330198,-55.46773910522461', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '4759fb25-cfe3-4b8e-a502-824ee900b243', false, 'Balneareo Argentino', '-34.78025372311158,-55.44130325317383', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', false, 'Jaureguiberry Acapulco', '-34.780500461121754,-55.41804313659668', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3240261f-1532-4ab3-b8af-f5b3b582220d', false, 'Jaureguiberry "el grillito"', '-34.78100274693366,-55.41203498840332', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '9276a300-26ae-4f30-ae7d-7718b2324d1f', false, 'Jaureguiberry', '-34.784518661986425,-55.39988994598389', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '61221ecb-61a0-4dcb-bd17-cb5fcf17f601', false, 'Peaje Solis', '-34.77856178544819,-55.39375305175781', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'ba3e7320-6903-496d-870f-1449ff18006d', false, 'Solis', '-34.78370798809935,-55.37959098815918', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'e84e028a-5072-4243-8875-1a8ad9d594dc', false, 'Las Flores', '-34.797876010378914,-55.38259506225586', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '46abb6e3-febc-41fa-b16f-5ee2e7d0b6e5', false, 'Bella Vista', '-34.80552291123269,-55.3551721572876', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '8baf5978-cb45-4e46-9f00-1bfdc353c705', false, 'Playa Verde', '-34.8222590430347,-55.314273834228516', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '171daf80-6982-48e1-9413-776a450bed9a', false, 'Playa Hermosa', '-34.83000936266889,-55.30963897705078', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '7f55527a-8d81-4e5f-a72f-3187844d05be', false, 'Playa Grande', '-34.8455078137077,-55.3022575378418', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '59507666-c77e-4a7e-9571-33134836bdcc', false, 'Piriapolis', '-34.8580453339167,-55.28989791870117', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '150c2dfd-4245-4ae6-a251-b9cc746cbeae', false, 'Piriapolis', '-34.864947198082824,-55.27050018310547', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '44236b41-3964-46e3-8b90-8615c07bed66', false, 'Cerro San Antonio', '-34.879734956615735,-55.260887145996094', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'a9c40613-cd2f-4535-bc51-12a31ad2ac03', false, 'Pta Colorada', '-34.90423452835512,-55.260372161865234', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3ed90098-22c6-4d01-93f9-1f6481aff6a0', false, 'Pta Negra', '-34.89128164202929,-55.21162033081055', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'eca5e2e3-c540-415e-9911-620226f20875', false, 'Interbalnearea Americas Unidas', '-34.859735639915385,-55.16338348388672', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'db3a806d-e948-4629-82cd-b76be942244d', false, 'Pta Ballena', '-34.90564232769945,-55.037384033203125', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'ba72215b-54e1-41e1-b77b-061bb197730f', false, 'Pta del Este', '-34.939422270331754,-54.93610382080078', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '81fae9c4-da1e-4b8c-af65-1677896d98c9', false, 'Maldonado', '-34.904797650989074,-54.95532989501953', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '2c70adff-5705-4c1d-ae03-1eab75a12f00', false, 'Balneario Solis ruta 9', '-34.78293255344788,-55.345516204833984', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '3e0666c5-45f1-43d2-8a85-c2514a227448', false, 'Las Flores', '-34.7894177824241,-55.322513580322266', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', 'b5b88fca-be10-4155-8b1a-fa0de43043d4', false, 'Pta de ls Sierra', '-34.78688014485674,-55.27719497680664', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '016ec03e-d5a2-493e-ada1-06007aeb5b5e', false, 'Pan de Azucar', '-34.7801127296316,-55.2311897277832', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', false, 'La Paz', '-34.7568455057737,-56.25823974609375', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Parada', '14926e20-1da5-4bf2-9135-abf198535bc5', false, 'Santiago Vazquez', '-34.78673916270251,-56.355743408203125', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', '82bc4ed2-043b-44e5-833d-20f07b740392', false, 'Colonia del Sacramento', '-34.452218472826544,-57.82928466796875', true);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', false, 'Paysandu', '-32.32195460020057,-58.08746337890625', NULL);
INSERT INTO puntorecorrido (dtype, id, eliminado, nombre, ubicacionmapa, aceptaencomiendas) VALUES ('Terminal', 'c6253122-990a-4891-9456-6d5c68f0c2a9', false, 'Salto', '-31.475524020001792,-57.90069580078125', true);


 

INSERT INTO recorrido (id, eliminado, nombre) VALUES ('b5acbe97-d2a3-4742-8e62-f421d3cba89b', true, 'sasa');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('de88535d-102a-4c6a-a7bf-72ffa408fbd1', true, 'sa');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', false, 'Montevideo-Jaure-Directo');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', false, 'Montevideo-Jaure');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', false, 'Montevideo-Pta del este');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('4d0609ce-eb2e-437c-be86-fb6ab22e5570', false, 'Montevideo-Colonia');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('96e77f63-25bf-4b8d-b116-fdbd1983f1e2', false, 'Paysandu-Salto');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('a0b6eda1-867e-4bf6-9b4d-711820bfdcc0', false, 'Salto-Paysandu');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('a483a3ee-ba85-4e6e-aa8c-d4ba987b5f65', false, 'Paysandy-Colonia');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('11f80976-5b98-4672-84e9-7a249c23b7ad', false, 'Colonia-Paysandu');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('cf552147-c495-4572-ae1c-22796aeec1e2', false, 'Colonia-Salto');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('5f30627f-8f24-476b-8b48-f9ede4ea91dd', false, 'Salto-Colonia');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('8fa8bab2-6e31-4459-a08e-5347de6eae06', false, 'Montevideo-La Paz');
INSERT INTO recorrido (id, eliminado, nombre) VALUES ('f991dd8a-7bb1-47e7-8aef-d85248c2a664', false, 'La Paz - Montevideo');


 INSERT INTO terminal_mailsdecontacto (terminal_id, descripcion, email, list_index) VALUES ('f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 'Tenant Tres Cruces', 'trescruces@tenant.lacbus.com', 0);
INSERT INTO terminal_mailsdecontacto (terminal_id, descripcion, email, list_index) VALUES ('6168805a-dfd0-4c74-8b7c-38b86cf21be6', 'Pinar', 'pinar@lacbus.tenant.com', 0);
INSERT INTO terminal_telefonoscontacto (terminal_id, descripcion, telefono, list_index) VALUES ('f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 'Telefono', '29004324', 0);



INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('de88535d-102a-4c6a-a7bf-72ffa408fbd1', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('de88535d-102a-4c6a-a7bf-72ffa408fbd1', '14926e20-1da5-4bf2-9135-abf198535bc5', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('b5acbe97-d2a3-4742-8e62-f421d3cba89b', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('b5acbe97-d2a3-4742-8e62-f421d3cba89b', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '9efaad2f-8189-4a77-950d-9874a02f316f', 3);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '73bc4092-999d-4cfd-a38f-b28e831bf819', 4);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', 5);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', 6);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', 7);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', 8);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', 9);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', 10);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', 11);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '6e84cc8a-351c-4888-bc26-d879d63d7f80', 12);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', 13);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', 14);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', 15);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '8cfb908f-da6e-479d-9f1d-de81562d7173', 16);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '040865ec-b451-4881-9054-e54a0321602d', 17);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', 'ea1124d0-4d13-407e-b939-9f386c7c58da', 18);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', 19);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '4759fb25-cfe3-4b8e-a502-824ee900b243', 20);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', 21);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '3240261f-1532-4ab3-b8af-f5b3b582220d', 22);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '9276a300-26ae-4f30-ae7d-7718b2324d1f', 23);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9efaad2f-8189-4a77-950d-9874a02f316f', 3);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '73bc4092-999d-4cfd-a38f-b28e831bf819', 4);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', 5);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', 6);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', 7);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '686a9123-08e1-4b34-9155-94e334720248', 8);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', 9);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', 10);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', 11);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', 12);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', 13);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', 14);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', 15);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4434c6c1-bc64-4793-846b-9b09f7134af1', 16);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', 17);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8c7eebec-04d3-41b2-99e0-6649177063be', 18);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', 19);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68ef97da-d3fd-4665-83c3-01ddf0028472', 20);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', 21);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4198d04a-f91b-4e75-a950-26b9a5de5018', 22);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '68e955bb-2a3c-43db-a66b-4307d352acaf', 23);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '6e84cc8a-351c-4888-bc26-d879d63d7f80', 24);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', 25);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', 26);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', 27);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', 28);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '8cfb908f-da6e-479d-9f1d-de81562d7173', 29);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '040865ec-b451-4881-9054-e54a0321602d', 30);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', 31);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'ea1124d0-4d13-407e-b939-9f386c7c58da', 32);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', 33);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', 34);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '4759fb25-cfe3-4b8e-a502-824ee900b243', 35);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', 36);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '3240261f-1532-4ab3-b8af-f5b3b582220d', 37);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('ff0034e6-dc22-4233-b429-a168f0301ce5', '9276a300-26ae-4f30-ae7d-7718b2324d1f', 38);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '9efaad2f-8189-4a77-950d-9874a02f316f', 3);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '73bc4092-999d-4cfd-a38f-b28e831bf819', 4);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '0cc593b3-76b5-4161-89aa-0f7df9adcd77', 5);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'd4883ebe-c2a5-487a-8f77-39bee0e6d7dd', 6);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '735ebdd4-7be6-4f8e-a6b2-ede7ee02e018', 7);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '686a9123-08e1-4b34-9155-94e334720248', 8);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'c41069bd-8679-4d46-9fab-819ebd7e99ee', 9);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'fa27fd3e-9086-45dc-87cf-ceb97e6bf5e7', 10);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '8f62914f-06ab-4cef-bfc8-5be22950b4dc', 11);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'd48ca6a1-338f-4fa0-b843-1e79b4a05ca5', 12);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '6168805a-dfd0-4c74-8b7c-38b86cf21be6', 13);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'e4bc0f09-0630-442d-9ee0-5c694af567f7', 14);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '60ccf27c-bc3e-492e-89d0-ed4d041040bc', 15);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '4434c6c1-bc64-4793-846b-9b09f7134af1', 16);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '348ba11b-7f7c-4620-80a8-92fd4d3a4249', 17);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '8c7eebec-04d3-41b2-99e0-6649177063be', 18);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'a077ee38-9417-42c0-a0af-4625fb3edeeb', 19);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '68ef97da-d3fd-4665-83c3-01ddf0028472', 20);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '050be1ad-d311-4f0e-9e56-9bfdeab728ed', 21);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '4198d04a-f91b-4e75-a950-26b9a5de5018', 22);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '68e955bb-2a3c-43db-a66b-4307d352acaf', 23);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '6e84cc8a-351c-4888-bc26-d879d63d7f80', 24);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '94abf431-af99-4b54-9c94-f1dba1d8a9ee', 25);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '05d4fda1-9328-4e10-bd5c-bed5a82fc0cc', 26);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'dac04f49-438f-4f1c-aa9a-6c2d1d0e8917', 27);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '7426ea21-12ed-4f7f-88b3-d6bc84f2bad0', 28);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '8cfb908f-da6e-479d-9f1d-de81562d7173', 29);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '040865ec-b451-4881-9054-e54a0321602d', 30);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'ea1124d0-4d13-407e-b939-9f386c7c58da', 31);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '09efbfbd-cbb1-41d0-ab04-d45b8ca01583', 32);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'd94274b3-c0b4-4368-a82f-bb2f96482a3f', 33);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '3d26cfb4-e22a-42d8-8462-a6addf0f8ccc', 34);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '4759fb25-cfe3-4b8e-a502-824ee900b243', 35);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '9e1d598f-7555-4eec-bb85-8a3e6ef0a6a7', 36);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '3240261f-1532-4ab3-b8af-f5b3b582220d', 37);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '61221ecb-61a0-4dcb-bd17-cb5fcf17f601', 38);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'ba3e7320-6903-496d-870f-1449ff18006d', 39);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'e84e028a-5072-4243-8875-1a8ad9d594dc', 40);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '46abb6e3-febc-41fa-b16f-5ee2e7d0b6e5', 41);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '8baf5978-cb45-4e46-9f00-1bfdc353c705', 42);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '171daf80-6982-48e1-9413-776a450bed9a', 43);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '7f55527a-8d81-4e5f-a72f-3187844d05be', 44);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '59507666-c77e-4a7e-9571-33134836bdcc', 45);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '150c2dfd-4245-4ae6-a251-b9cc746cbeae', 46);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '3ed90098-22c6-4d01-93f9-1f6481aff6a0', 47);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'eca5e2e3-c540-415e-9911-620226f20875', 48);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'db3a806d-e948-4629-82cd-b76be942244d', 49);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', 'ba72215b-54e1-41e1-b77b-061bb197730f', 50);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('4d0609ce-eb2e-437c-be86-fb6ab22e5570', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('4d0609ce-eb2e-437c-be86-fb6ab22e5570', '14926e20-1da5-4bf2-9135-abf198535bc5', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('4d0609ce-eb2e-437c-be86-fb6ab22e5570', '82bc4ed2-043b-44e5-833d-20f07b740392', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('96e77f63-25bf-4b8d-b116-fdbd1983f1e2', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('96e77f63-25bf-4b8d-b116-fdbd1983f1e2', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('a0b6eda1-867e-4bf6-9b4d-711820bfdcc0', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('a0b6eda1-867e-4bf6-9b4d-711820bfdcc0', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('a483a3ee-ba85-4e6e-aa8c-d4ba987b5f65', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('a483a3ee-ba85-4e6e-aa8c-d4ba987b5f65', '82bc4ed2-043b-44e5-833d-20f07b740392', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('11f80976-5b98-4672-84e9-7a249c23b7ad', '82bc4ed2-043b-44e5-833d-20f07b740392', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('11f80976-5b98-4672-84e9-7a249c23b7ad', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('cf552147-c495-4572-ae1c-22796aeec1e2', '82bc4ed2-043b-44e5-833d-20f07b740392', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('cf552147-c495-4572-ae1c-22796aeec1e2', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('cf552147-c495-4572-ae1c-22796aeec1e2', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('5f30627f-8f24-476b-8b48-f9ede4ea91dd', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('5f30627f-8f24-476b-8b48-f9ede4ea91dd', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('5f30627f-8f24-476b-8b48-f9ede4ea91dd', '82bc4ed2-043b-44e5-833d-20f07b740392', 2);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('8fa8bab2-6e31-4459-a08e-5347de6eae06', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('8fa8bab2-6e31-4459-a08e-5347de6eae06', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', 1);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('f991dd8a-7bb1-47e7-8aef-d85248c2a664', '63b84f4b-9b50-40d3-8eed-4e8c0203244d', 0);
INSERT INTO recorrido_puntorecorrido (recorrido_id, puntosderecorrido_id, list_index) VALUES ('f991dd8a-7bb1-47e7-8aef-d85248c2a664', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', 1);




INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('8d957d43-1997-4617-9375-381e73b04024', '2016-10-09 21:00:00', '96e77f63-25bf-4b8d-b116-fdbd1983f1e2');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('d0260ba1-51ea-4cd2-97a1-665110581835', '2016-10-09 21:00:00', 'a0b6eda1-867e-4bf6-9b4d-711820bfdcc0');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('526feac2-20d5-4c2d-8d6c-93df920461fb', '2016-10-09 21:00:00', 'a483a3ee-ba85-4e6e-aa8c-d4ba987b5f65');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('b1a84a3d-b280-42c6-b288-4bbd6bbadb62', '2016-10-09 21:00:00', '11f80976-5b98-4672-84e9-7a249c23b7ad');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('627e9822-1b5b-4736-9846-2fff81adb052', '2016-10-09 21:00:00', 'cf552147-c495-4572-ae1c-22796aeec1e2');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('83cac1e7-4e7f-40fc-b29f-a2f08574480e', '2016-10-09 21:00:00', '5f30627f-8f24-476b-8b48-f9ede4ea91dd');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('1a51ea2d-dd01-4648-b50d-4de9f2804244', '2016-10-09 21:00:00', 'f991dd8a-7bb1-47e7-8aef-d85248c2a664');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('25804c86-48e2-4a20-b9ea-ef743542146f', '2016-10-09 21:00:00', '8fa8bab2-6e31-4459-a08e-5347de6eae06');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('cc9b5ea9-fb86-4f6a-a949-302ab8fdc024', '2016-07-07 21:00:00', 'bb8186db-2af4-4081-8c21-302ab8fdc024');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('2eceb553-26f3-4e3d-a2e8-3681aff96f5b', '2016-10-09 21:00:00', 'c2b01749-0cba-44e1-9165-bf6bd629528e');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('106e6105-0248-478e-a5fb-674dd056d08d', '2016-10-09 21:00:00', 'ff0034e6-dc22-4233-b429-a168f0301ce5');
INSERT INTO viaje (id, fechasalida, recorrido_id) VALUES ('c090a88c-8fa6-4004-adc2-3b72c7c7e848', '2016-10-09 21:00:00', '4d0609ce-eb2e-437c-be86-fb6ab22e5570');


INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04045', '500.00', '82bc4ed2-043b-44e5-833d-20f07b740392', 'b61d2464-77e3-47f7-bc18-aa75871de5c6');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04065', '600.00', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '14926e20-1da5-4bf2-9135-abf198535bc5');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04125', '450.50', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 'b61d2464-77e3-47f7-bc18-aa75871de5c6');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04001', '366.50', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '63b84f4b-9b50-40d3-8eed-4e8c0203244d');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b07849', '280.50', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', 'c6253122-990a-4891-9456-6d5c68f0c2a9');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04122', '950.50', 'c6253122-990a-4891-9456-6d5c68f0c2a9', 'b61d2464-77e3-47f7-bc18-aa75871de5c6');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b01111', '520.50', 'b61d2464-77e3-47f7-bc18-aa75871de5c6', '82bc4ed2-043b-44e5-833d-20f07b740392');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04222', '460.50', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '63b84f4b-9b50-40d3-8eed-4e8c0203244d');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b04231', '1050.50', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '4ea4ba73-a2c1-4821-9c9e-09369aacbabf');
INSERT INTO precio (id, monto, destino_id, origen_id) VALUES ('8d957d43-1997-4617-9375-381e73b03321', '1450.50', 'f4f165d3-0881-4e8b-8208-ab8adc29a6c7', '2db79cf9-3811-4f3e-b1b4-23b86b3a8283'	);

INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('11f80976-5b98-4672-84e9-7a249c23b7ad', '8d957d43-1997-4617-9375-381e73b04045', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('4d0609ce-eb2e-437c-be86-fb6ab22e5570', '8d957d43-1997-4617-9375-381e73b04065', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('5f30627f-8f24-476b-8b48-f9ede4ea91dd', '8d957d43-1997-4617-9375-381e73b04125', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('8fa8bab2-6e31-4459-a08e-5347de6eae06', '8d957d43-1997-4617-9375-381e73b04001', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('96e77f63-25bf-4b8d-b116-fdbd1983f1e2', '8d957d43-1997-4617-9375-381e73b07849', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('a0b6eda1-867e-4bf6-9b4d-711820bfdcc0', '8d957d43-1997-4617-9375-381e73b04122', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('a483a3ee-ba85-4e6e-aa8c-d4ba987b5f65', '8d957d43-1997-4617-9375-381e73b01111', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('b5acbe97-d2a3-4742-8e62-f421d3cba89b', '8d957d43-1997-4617-9375-381e73b04222', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('bb8186db-2af4-4081-8c21-302ab8fdc024', '8d957d43-1997-4617-9375-381e73b04231', 0);
INSERT INTO recorrido_precio (recorrido_id, precios_id, list_index) VALUES ('c2b01749-0cba-44e1-9165-bf6bd629528e', '8d957d43-1997-4617-9375-381e73b03321', 0);

INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('d0260ba1-51ea-4cd2-97a1-665110581835', '352f3329-90d4-4cfa-b0bd-4202d152dcb0',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('526feac2-20d5-4c2d-8d6c-93df920461fb', 'c20854d0-4229-4ee9-9101-4f95681eaa21',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('b1a84a3d-b280-42c6-b288-4bbd6bbadb62', '7e7024fb-4cef-4133-95be-22ec7eb4448c',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('627e9822-1b5b-4736-9846-2fff81adb052', '7e7024fb-4cef-4133-95be-22ec7eb4448c',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('83cac1e7-4e7f-40fc-b29f-a2f08574480e', '7e7024fb-4cef-4133-95be-22ec7eb4448c',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('cc9b5ea9-fb86-4f6a-a949-302ab8fdc024', '7e7024fb-4cef-4133-95be-22ec7eb4448c',  0);
INSERT INTO viaje_vehiculo (viaje_id, coches_id, list_index) VALUES ('8d957d43-1997-4617-9375-381e73b04024', '7e7024fb-4cef-4133-95be-22ec7eb4448c',  0);

INSERT INTO encomienda (id, codigoencomienda, eliminada, destino_id, origen_id) VALUES ('f7036bc6-a43e-4d14-ac9c-cb35f182235b',1,false, 'c6253122-990a-4891-9456-6d5c68f0c2a9', 'b61d2464-77e3-47f7-bc18-aa75871de5c6');

INSERT INTO grupohorario (id, nombre, tipo) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Diario', 'diasSemana');
INSERT INTO grupohorario (id, nombre, tipo) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Diario', 'diasSemana');
INSERT INTO recorrido_grupohorario (recorrido_id, horarios_id, list_index) VALUES ('11f80976-5b98-4672-84e9-7a249c23b7ad', '3f205ba5-126c-4715-b264-4ac838641149', 0);
INSERT INTO recorrido_grupohorario (recorrido_id, horarios_id, list_index) VALUES ('cf552147-c495-4572-ae1c-22796aeec1e2', '28393733-6222-433d-850a-92dd7cdd8703', 0);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Lunes', 0);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Domingo', 1);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Martes', 2);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Miercoles', 3);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Jueves', 4);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Viernes', 5);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'Sabado', 6);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Lunes', 0);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Domingo', 1);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Martes', 2);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Miercoles', 3);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Jueves', 4);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Viernes', 5);
INSERT INTO dias (grupohorarioid, diassemana, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'Sabado', 6);
INSERT INTO horario (id, nombre) VALUES ('03a54d05-c336-4ecf-9ac7-4fd3332f1bb1', '08:00');
INSERT INTO horario (id, nombre) VALUES ('b95f3290-7040-4461-88c1-464c0a916622', '10:00');
INSERT INTO horario (id, nombre) VALUES ('feeca242-91c7-4c72-bb75-0f94bfb767ef', '12:00');
INSERT INTO horario (id, nombre) VALUES ('95279f7e-be46-47f7-9a2a-25935ce098d3', '14:00');
INSERT INTO horario (id, nombre) VALUES ('7ae662ba-7f35-4078-b030-b49e4b97df3f', '16:00');
INSERT INTO horario (id, nombre) VALUES ('e3a60fad-f8db-4260-ad69-c17c2b4a6913', '06:00');
INSERT INTO horario (id, nombre) VALUES ('fbaceae8-412c-48c0-ac2f-e99a4505632d', '09:00');
INSERT INTO horario (id, nombre) VALUES ('b3413122-baed-48b5-b577-647d0d3aba8f', '12:00');
INSERT INTO horario (id, nombre) VALUES ('b7b5467b-470d-43fc-98f2-45b64385f569', '15:00');
INSERT INTO horario (id, nombre) VALUES ('f0f4e1a8-0ba0-4376-8f87-ebe8ae1d3bb9', '18:00');
INSERT INTO horario (id, nombre) VALUES ('2642a5ab-23fb-4a97-88b8-d12e00a7f932', '21:00');
INSERT INTO horario (id, nombre) VALUES ('7c110f0d-b980-4811-912c-79bcb40a0936', '00:00');
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', '03a54d05-c336-4ecf-9ac7-4fd3332f1bb1', 0);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'b95f3290-7040-4461-88c1-464c0a916622', 1);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', 'feeca242-91c7-4c72-bb75-0f94bfb767ef', 2);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', '95279f7e-be46-47f7-9a2a-25935ce098d3', 3);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('3f205ba5-126c-4715-b264-4ac838641149', '7ae662ba-7f35-4078-b030-b49e4b97df3f', 4);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'e3a60fad-f8db-4260-ad69-c17c2b4a6913', 0);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'fbaceae8-412c-48c0-ac2f-e99a4505632d', 1);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'b3413122-baed-48b5-b577-647d0d3aba8f', 2);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'b7b5467b-470d-43fc-98f2-45b64385f569', 3);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', 'f0f4e1a8-0ba0-4376-8f87-ebe8ae1d3bb9', 4);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', '2642a5ab-23fb-4a97-88b8-d12e00a7f932', 5);
INSERT INTO grupohorario_horario (grupohorario_id, horarios_id, list_index) VALUES ('28393733-6222-433d-850a-92dd7cdd8703', '7c110f0d-b980-4811-912c-79bcb40a0936', 6);



    create sequence hibernate_sequence;