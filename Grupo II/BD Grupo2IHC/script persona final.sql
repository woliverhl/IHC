CREATE TABLE `persona` (
  `rut` varchar(50) NOT NULL,
  `nombre_persona` varchar(100) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `id_interaccion` int(11) DEFAULT NULL,
  `id_proceso_de_seleccion` int(11) DEFAULT NULL,
  `id_prupuesta_de_valor` int(11) DEFAULT NULL,
  `genero` varchar(2) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `tipo_persona` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `postulante` (
  `rut` varchar(50) NOT NULL,
  `id_proceso_de_seleccion` int(11) DEFAULT NULL,
  `curriculum` text,
  `preferencia_de_posicion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



