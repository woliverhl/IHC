drop table if exists postulante;

/*==============================================================*/
/* Table: postulante                                            */
/*==============================================================*/
create table postulante
(
   rut                  varchar(50) not null,
   id_proceso_de_seleccion int,
   curriculum           text,
   preferencia_de_posicion varchar(50),
   primary key (rut)
);

drop table if exists persona;

/*==============================================================*/
/* Table: persona                                               */
/*==============================================================*/
create table persona
(
   rut                  varchar(50) not null,
   id_interaccion       int,
   id_proceso_de_seleccion int,
   id_prupuesta_de_valor int,
   cargo                varchar(50),
   nombre_persona       varchar(100),
   genero               varchar(2),
   fecha_nacimiento     date,
   direccion            varchar(100),
   telefono             int,
   correo               varchar(100),
   es_postulante        bool,
   es_experto           bool,
   primary key (rut)
);