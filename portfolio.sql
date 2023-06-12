-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2023 a las 00:39:12
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `portfolio`
--
CREATE DATABASE IF NOT EXISTS `portfolio` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `portfolio`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `educacion`
--

DROP TABLE IF EXISTS `educacion`;
CREATE TABLE `educacion` (
  `id` int(10) UNSIGNED NOT NULL,
  `institucion` varchar(50) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `locacion` varchar(50) NOT NULL,
  `habilidades` varchar(500) NOT NULL,
  `logo` varchar(50) NOT NULL,
  `logo_url` varchar(100) DEFAULT NULL,
  `logo_public_id` varchar(50) DEFAULT NULL,
  `desde` date NOT NULL,
  `hasta` date DEFAULT NULL,
  `usuarios_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `educacion`
--

INSERT INTO `educacion` (`id`, `institucion`, `titulo`, `locacion`, `habilidades`, `logo`, `logo_url`, `logo_public_id`, `desde`, `hasta`, `usuarios_id`) VALUES
(1, 'manuel ortiz guerrero', 'primaria', 'san lorenzo - paraguay', 'las mamadas de la primaria, participar en clase', 'perfil_educacion_1', 'http://res.cloudinary.com/dkxzdyjpv/image/upload/v1667858494/x7vmffkyhcwgvq04avwr.png', 'x7vmffkyhcwgvq04avwr', '1996-03-01', '1998-12-31', 1),
(3, 'ipne', 'secundaria', 'san lorenzo - paraguay', 'la secundaria apesta, comer caramelos halls', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2002-02-01', '2006-11-30', 1),
(5, 'Escuela Normal Superior n10', 'Analista programador', 'Posadas - Misiones - Argentina', 'programar, mucho, y muy fuerte', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2014-03-03', '2021-03-16', 1),
(6, 'Escuela Normal Superior n10', 'Analista de Sistemas de Computacion', 'Posadas - Misiones - Argentina', 'analizar, mucho, y muy fuerte, hasta el limite de la desesperacion', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2014-03-03', '2022-11-10', 1),
(7, 'INTI', 'Desarrollador FullStack', 'TuVieja - Buenos Aires - Argentina', 'aprendi a googlear copado, aprender viendo videos', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2020-02-02', '2021-02-20', 4),
(8, 'normal 10', 'aviador', 'pito-pal-culiao', 'coser, cantar', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2020-01-01', NULL, 4),
(10, 'INTI', 'Desarrollador FullStack', 'TuVieja - Buenos Aires - Argentina', 'aprendi a googlear copado, aprender viendo videos', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2020-02-02', '2022-11-24', 1),
(11, 'Ministerio de Desarrollo Productivo y CESSI', 'YoProgramo Argentina Programa', 'Posadas - Misiones - Argentina', 'Desarrollo FullStack, manquear un poco', 'educacion_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965751/educacion_foto_default.png', 'educacion_foto_default', '2022-06-01', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `experiencias`
--

DROP TABLE IF EXISTS `experiencias`;
CREATE TABLE `experiencias` (
  `id` int(10) UNSIGNED NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `tareas` varchar(500) NOT NULL,
  `desde` date NOT NULL,
  `hasta` date DEFAULT NULL,
  `logo` varchar(50) NOT NULL DEFAULT 'maletin-web.png',
  `logo_url` varchar(100) DEFAULT NULL,
  `logo_public_id` varchar(50) DEFAULT NULL,
  `empresa` varchar(50) NOT NULL,
  `referenciatel` varchar(50) NOT NULL,
  `referencianombre` varchar(50) NOT NULL,
  `usuarios_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `experiencias`
--

INSERT INTO `experiencias` (`id`, `cargo`, `tareas`, `desde`, `hasta`, `logo`, `logo_url`, `logo_public_id`, `empresa`, `referenciatel`, `referencianombre`, `usuarios_id`) VALUES
(4, 'Tecnico PC', 'limpiar, engrazar, desarmar, aceitar', '2020-08-20', '2022-08-20', 'perfil_experiencia_4', 'http://res.cloudinary.com/dkxzdyjpv/image/upload/v1668032883/aodnmufjjoztgy5cvfvh.png', 'aodnmufjjoztgy5cvfvh', 'Autonomo', '0123456789', 'elfa lopa', 1),
(5, 'programador', 'depurar, mejorar funcionalidades, scrum diario', '2022-08-14', '2022-08-21', 'perfil_experiencia_5', 'http://res.cloudinary.com/dkxzdyjpv/image/upload/v1668032893/g2muqvoeviia5rn2hlrz.png', 'g2muqvoeviia5rn2hlrz', 'devsu', '54123456789', 'elfa lopa', 1),
(7, 'ayudante de cocina', 'lavar verduras, picar verduras, saltear en aceite', '2016-09-15', '2022-08-21', 'experiencia_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/experiencia_foto_default.png', 'experiencia_foto_default', 'la cocineta sa', '3764396896', 'mi vieja', 1),
(9, 'vendedor', 'vender cosas, cobrar, no dar fiado, recibir pedidos', '2020-09-15', '2021-08-21', 'experiencia_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/experiencia_foto_default.png', 'experiencia_foto_default', 'kiosquito', '37644587984', 'elfa lopa', 1),
(10, 'el pepe', 'ufff, muchas, cosas', '2020-02-06', '2022-09-04', 'experiencia_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/experiencia_foto_default.png', 'experiencia_foto_default', 'elpepe s.a.', '123654987', 'carapapa', 5),
(11, '123', 'muchas cosas asd asd asdasdddasdasdas asd asd asd asdasd', '2024-11-01', '2023-06-07', 'experiencia_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/experiencia_foto_default.png', 'experiencia_foto_default', 'argprog', '01326549887', 'fulanito', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habilidades`
--

DROP TABLE IF EXISTS `habilidades`;
CREATE TABLE `habilidades` (
  `id` int(10) UNSIGNED NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `nivel` int(10) UNSIGNED NOT NULL,
  `usuarios_id` int(10) UNSIGNED NOT NULL,
  `tipo_habilidad_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `habilidades`
--

INSERT INTO `habilidades` (`id`, `descripcion`, `nivel`, `usuarios_id`, `tipo_habilidad_id`) VALUES
(1, 'PHP', 7, 1, 1),
(5, 'COBOL', 10, 1, 1),
(6, 'Team Work', 10, 4, 2),
(7, 'manquear', 10, 9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
CREATE TABLE `perfiles` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `titulo` varchar(50) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `linkedin` varchar(100) DEFAULT NULL,
  `github` varchar(100) DEFAULT NULL,
  `foto` varchar(50) NOT NULL DEFAULT 'perfil_foto_default',
  `foto_url` varchar(250) DEFAULT 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp',
  `foto_public_id` varchar(100) DEFAULT 'perfil_foto_default',
  `acercade` varchar(500) NOT NULL,
  `usuarios_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `perfiles`
--

INSERT INTO `perfiles` (`id`, `nombre`, `apellido`, `titulo`, `correo`, `linkedin`, `github`, `foto`, `foto_url`, `foto_public_id`, `acercade`, `usuarios_id`) VALUES
(1, 'Leandro', 'Boos', 'Analista Programador', 'leandroboos@gmail.com', 'https://www.linkedin.com/in/leandroboos91/', 'https://www.github.com/leombpunk', 'perfil_foto_1', 'http://res.cloudinary.com/dkxzdyjpv/image/upload/v1666370367/ywvjoxuwzuqwhmrych6m.jpg', 'ywvjoxuwzuqwhmrych6m', 'descripción hablando un poco de mi equisde, empezando el proyecto final de la tesis, ojala vaya todo bien. En busqueda de un puesto de programador trainee/junior. Creo que ya lo termine!', 1),
(3, 'Pancrasio', 'Elver', 'tecnico el algo', 'correo@mail.com', 'https://linkedin.com/usuFalso', 'https://github.com/usuFalso', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'escribe algo sobre tí', 4),
(4, 'pepito', 'master', 'El mas capito', 'pepito@pepe.pe', '', '', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'Que se yo!', 5),
(5, 'Inserte nombre', 'Inserte apellido', 'Inserte su titulo/puesto', 'Inserte su correo', 'Inserte su linkedin', 'Inserte su github, si lo tuviese', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'Inserte una descripcion acerca de usted', 6),
(6, 'Inserte nombre', 'Inserte apellido', 'Inserte su titulo/puesto', 'Inserte su correo', 'Inserte su linkedin', 'Inserte su github, si lo tuviese', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'Inserte una descripcion acerca de usted', 7),
(7, 'Malen', 'apellido', 'Profe', 'correo@correo.com', 'Inserte su linkedin', 'Inserte su github, si lo tuviese', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'Inserte una descripcion acerca de usted', 8),
(8, 'Tito', 'Puente', 'Asador', 'tito@puente.com', 'https://tito.com', 'https://tito.com', 'perfil_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1665965583/perfil_foto_default.webp', 'perfil_foto_default', 'Es un personaje de la vida real que apareció en los simpsons', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
CREATE TABLE `proyectos` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `desde` date NOT NULL,
  `hasta` date DEFAULT NULL,
  `descripcion` varchar(500) NOT NULL,
  `logo` varchar(50) NOT NULL,
  `logo_url` varchar(100) DEFAULT NULL,
  `logo_public_id` varchar(50) DEFAULT NULL,
  `enlace` varchar(100) DEFAULT NULL,
  `sitio` varchar(100) DEFAULT NULL,
  `usuarios_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`id`, `nombre`, `desde`, `hasta`, `descripcion`, `logo`, `logo_url`, `logo_public_id`, `enlace`, `sitio`, `usuarios_id`) VALUES
(1, 'Lista de Tareas', '2021-06-07', '2021-06-11', 'App desarrolla en AngularJS 13, junto con una libreria que no recuerdo el nombre que funciona como backend para consumir datos. La app sirve como recordatorio de tareas, quehaceres y otras actividades', 'perfil_proyecto_1.jpg', 'http://res.cloudinary.com/dkxzdyjpv/image/upload/v1666375538/crvkbixpldis4gll2hjt.jpg', 'crvkbixpldis4gll2hjt', 'https://www.github.com/usuario/lista-de-tareas', '', 1),
(3, 'Jueguito en Python', '2022-08-01', '2022-08-10', 'Un juego de navecitas bien pero bien choto', 'proyecto_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/proyecto_foto_default.png', 'proyecto_foto_default', 'github.com/usuario/naves-chotas', '', 1),
(4, 'Practicas POO', '2022-06-28', '2022-07-27', 'practicas de programacion orientada a objectos en vb.net', 'proyecto_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/proyecto_foto_default.png', 'proyecto_foto_default', 'github.com/usuario/practica-vb', '', 1),
(5, 'C++ repo', '2022-08-15', '2022-08-23', 'practicas en c++ hechas usando visual studio comunity', 'proyecto_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/proyecto_foto_default.png', 'proyecto_foto_default', 'github.com/usuario/c-pluplus', '', 1),
(7, 'proyecto prueba 1 ', '2022-10-11', NULL, 'soy un proyecto de prueba equisde', 'proyecto_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/proyecto_foto_default.png', 'proyecto_foto_default', '', '', 4),
(8, 'proyecto prueba 2', '2022-10-30', NULL, 'blablablablablablabla', 'proyecto_foto_default', 'https://res.cloudinary.com/dkxzdyjpv/image/upload/v1666366963/proyecto_foto_default.png', 'proyecto_foto_default', NULL, NULL, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `redes`
--

DROP TABLE IF EXISTS `redes`;
CREATE TABLE `redes` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `redes`
--

INSERT INTO `redes` (`id`, `nombre`) VALUES
(1, 'facebook'),
(3, 'instagram'),
(4, 'reddit'),
(2, 'twitter');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_habilidad`
--

DROP TABLE IF EXISTS `tipo_habilidad`;
CREATE TABLE `tipo_habilidad` (
  `id` int(10) UNSIGNED NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_habilidad`
--

INSERT INTO `tipo_habilidad` (`id`, `descripcion`) VALUES
(2, 'Habilidades Blandas'),
(1, 'Habilidades Duras'),
(3, 'Herramientas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(10) UNSIGNED NOT NULL,
  `usuario` varchar(16) NOT NULL,
  `contrasena` varchar(255) NOT NULL COMMENT 'caquita123'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `contrasena`) VALUES
(1, 'lean', '$2a$10$AW7LZHgsp0EyfZKfObDL2.RBPCrPi32pZR5nyUk4r1hQLdTRROmC6'),
(4, 'usuFalso', '$2a$10$AW7LZHgsp0EyfZKfObDL2.RBPCrPi32pZR5nyUk4r1hQLdTRROmC6'),
(5, 'pepito', '$2a$10$AW7LZHgsp0EyfZKfObDL2.RBPCrPi32pZR5nyUk4r1hQLdTRROmC6'),
(6, 'marcelo', '$2a$10$ukU/cfo0XU86pJIVt2/fUe7HJFEEZlTWIM0L9Yb1zwgGu1FpJF7AG'),
(7, 'kakaroto', '$2a$10$oRM2azeaL0/6k09mEtOoIODMhbS579D4FMLkvNrdAo2mY/taRPldS'),
(8, 'malen', '$2a$10$AW7LZHgsp0EyfZKfObDL2.RBPCrPi32pZR5nyUk4r1hQLdTRROmC6'),
(9, '123456=', '$2y$10$KkzMDO360tDtIL0upJH/neMTsaui2W/87eXrMZGAdEVIZqW3gN1J6');

--
-- Disparadores `usuarios`
--
DROP TRIGGER IF EXISTS `TrUsuariosInsertPerfil`;
DELIMITER $$
CREATE TRIGGER `TrUsuariosInsertPerfil` AFTER INSERT ON `usuarios` FOR EACH ROW BEGIN
/*INSERT INTO perfiles(nombre, apellido, titulo, correo, linkedin, github, acercade, usuarios_id) VALUES('Inserte nombre', 'Inserte apellido', 'Inserte su titulo/puesto', 'Inserte su correo', 'Inserte su linkedin', 'Inserte su github, si lo tuviese', 'Inserte una descripcion acerca de usted', NEW.id);*/
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE `usuarios_roles` (
  `usuarios_id` int(10) UNSIGNED NOT NULL,
  `roles_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT INTO `usuarios_roles` (`usuarios_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(4, 2),
(5, 1),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `educacion`
--
ALTER TABLE `educacion`
  ADD PRIMARY KEY (`id`,`usuarios_id`),
  ADD KEY `fk_educacion_usuarios1_idx` (`usuarios_id`);

--
-- Indices de la tabla `experiencias`
--
ALTER TABLE `experiencias`
  ADD PRIMARY KEY (`id`,`usuarios_id`),
  ADD KEY `fk_experiencias_usuarios1_idx` (`usuarios_id`);

--
-- Indices de la tabla `habilidades`
--
ALTER TABLE `habilidades`
  ADD PRIMARY KEY (`id`,`usuarios_id`,`tipo_habilidad_id`) USING BTREE,
  ADD KEY `fk_habilidades_usuarios1_idx` (`usuarios_id`),
  ADD KEY `fk_habilidades_tipo_habilidad1_idx` (`tipo_habilidad_id`);

--
-- Indices de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD PRIMARY KEY (`id`,`usuarios_id`),
  ADD KEY `fk_perfiles_usuarios_idx` (`usuarios_id`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`id`,`usuarios_id`),
  ADD KEY `fk_proyectos_usuarios1_idx` (`usuarios_id`);

--
-- Indices de la tabla `redes`
--
ALTER TABLE `redes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`) USING BTREE;

--
-- Indices de la tabla `tipo_habilidad`
--
ALTER TABLE `tipo_habilidad`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `descripcion_UNIQUE` (`descripcion`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  ADD KEY `usuario_INDEX` (`usuario`) USING BTREE;

--
-- Indices de la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD PRIMARY KEY (`usuarios_id`,`roles_id`) USING BTREE,
  ADD KEY `fk_usuarios_roles_2` (`roles_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `educacion`
--
ALTER TABLE `educacion`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `experiencias`
--
ALTER TABLE `experiencias`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `habilidades`
--
ALTER TABLE `habilidades`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `redes`
--
ALTER TABLE `redes`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_habilidad`
--
ALTER TABLE `tipo_habilidad`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `educacion`
--
ALTER TABLE `educacion`
  ADD CONSTRAINT `fk_educacion_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `experiencias`
--
ALTER TABLE `experiencias`
  ADD CONSTRAINT `fk_experiencias_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `habilidades`
--
ALTER TABLE `habilidades`
  ADD CONSTRAINT `fk_habilidades_tipo_habilidad1` FOREIGN KEY (`tipo_habilidad_id`) REFERENCES `tipo_habilidad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_habilidades_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD CONSTRAINT `fk_perfiles_usuarios` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD CONSTRAINT `fk_proyectos_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `fk_usuarios_roles_1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `fk_usuarios_roles_2` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
