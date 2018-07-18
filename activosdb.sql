-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-07-2018 a las 22:42:22
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

DROP DATABASE IF EXISTS activosdb;

CREATE DATABASE activosdb;

use activosdb;



SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `activosdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activo`
--

CREATE TABLE `activo` (
  `idactivo` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fk_tipo` int(10) NOT NULL,
  `serial` varchar(50) NOT NULL,
  `numerointernoinventario` int(10) NOT NULL,
  `peso` float NOT NULL,
  `alto` float NOT NULL,
  `ancho` float NOT NULL,
  `largo` float NOT NULL,
  `valor_compra` double NOT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `estado` enum('activo','dado de baja','en reparacion','disponible','asignado') NOT NULL,
  `color` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `activo`
--

INSERT INTO `activo` (`idactivo`, `nombre`, `descripcion`, `fk_tipo`, `serial`, `numerointernoinventario`, `peso`, `alto`, `ancho`, `largo`, `valor_compra`, `fecha_compra`, `fecha_baja`, `estado`, `color`) VALUES
(1, 'Edificio Atagrama', 'Edificio de Oficinas operativas', 1, 'INM-0001', 12, 2.1, 2.1, 3.1, 5.8, 500000, '2018-07-17 00:00:00', NULL, 'activo', 'Verde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `id` int(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `idciudad` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `fk_departamento` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `iddepartamento` int(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idpersona` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sexo` enum('Masculino','Femenino') NOT NULL,
  `fk_area` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rel_activo_area`
--

CREATE TABLE `rel_activo_area` (
  `idrelactivoarea` int(10) NOT NULL,
  `fk_activo` int(10) NOT NULL,
  `fk_area` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rel_activo_persona`
--

CREATE TABLE `rel_activo_persona` (
  `idrelactivopersona` int(11) NOT NULL,
  `fk_persona` int(11) NOT NULL,
  `fk_activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rel_area_ciudad`
--

CREATE TABLE `rel_area_ciudad` (
  `idrelareaciudad` int(10) NOT NULL,
  `fk_area` int(10) NOT NULL,
  `fk_ciudad` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_activo`
--

CREATE TABLE `tipo_activo` (
  `id` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_activo`
--

INSERT INTO `tipo_activo` (`id`, `nombre`) VALUES
(1, 'Terrenos y bienes naturales'),
(2, 'Construcciones'),
(3, 'Instalaciones técnicas'),
(4, 'Maquinaria'),
(5, 'Mobiliario'),
(6, 'Equipos para procesos informaticos'),
(7, 'Elementos de transporte'),
(8, 'Otros');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `activo`
--
ALTER TABLE `activo`
  ADD PRIMARY KEY (`idactivo`),
  ADD UNIQUE KEY `numerointernoinventario` (`numerointernoinventario`),
  ADD KEY `fk_tipo` (`fk_tipo`);

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`idciudad`),
  ADD KEY `fk_departamento` (`fk_departamento`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`iddepartamento`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idpersona`),
  ADD KEY `fk_area` (`fk_area`);

--
-- Indices de la tabla `rel_activo_area`
--
ALTER TABLE `rel_activo_area`
  ADD PRIMARY KEY (`idrelactivoarea`),
  ADD KEY `fk_activo` (`fk_activo`),
  ADD KEY `fk_area` (`fk_area`);

--
-- Indices de la tabla `rel_activo_persona`
--
ALTER TABLE `rel_activo_persona`
  ADD PRIMARY KEY (`idrelactivopersona`),
  ADD KEY `fk_persona` (`fk_persona`),
  ADD KEY `fk_activo` (`fk_activo`);

--
-- Indices de la tabla `rel_area_ciudad`
--
ALTER TABLE `rel_area_ciudad`
  ADD PRIMARY KEY (`idrelareaciudad`),
  ADD KEY `fk_area` (`fk_area`),
  ADD KEY `fk_ciudad` (`fk_ciudad`);

--
-- Indices de la tabla `tipo_activo`
--
ALTER TABLE `tipo_activo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `activo`
--
ALTER TABLE `activo`
  MODIFY `idactivo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `area`
--
ALTER TABLE `area`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `idciudad` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `iddepartamento` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idpersona` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `rel_activo_area`
--
ALTER TABLE `rel_activo_area`
  MODIFY `idrelactivoarea` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `rel_activo_persona`
--
ALTER TABLE `rel_activo_persona`
  MODIFY `idrelactivopersona` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `rel_area_ciudad`
--
ALTER TABLE `rel_area_ciudad`
  MODIFY `idrelareaciudad` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipo_activo`
--
ALTER TABLE `tipo_activo`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `activo`
--
ALTER TABLE `activo`
  ADD CONSTRAINT `activo_ibfk_1` FOREIGN KEY (`fk_tipo`) REFERENCES `tipo_activo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`fk_departamento`) REFERENCES `departamento` (`iddepartamento`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`fk_area`) REFERENCES `rel_area_ciudad` (`idrelareaciudad`);

--
-- Filtros para la tabla `rel_activo_area`
--
ALTER TABLE `rel_activo_area`
  ADD CONSTRAINT `rel_activo_area_ibfk_1` FOREIGN KEY (`fk_activo`) REFERENCES `activo` (`idactivo`),
  ADD CONSTRAINT `rel_activo_area_ibfk_2` FOREIGN KEY (`fk_area`) REFERENCES `rel_area_ciudad` (`idrelareaciudad`);

--
-- Filtros para la tabla `rel_activo_persona`
--
ALTER TABLE `rel_activo_persona`
  ADD CONSTRAINT `rel_activo_persona_ibfk_1` FOREIGN KEY (`fk_persona`) REFERENCES `persona` (`idpersona`),
  ADD CONSTRAINT `rel_activo_persona_ibfk_2` FOREIGN KEY (`fk_activo`) REFERENCES `activo` (`idactivo`);

--
-- Filtros para la tabla `rel_area_ciudad`
--
ALTER TABLE `rel_area_ciudad`
  ADD CONSTRAINT `rel_area_ciudad_ibfk_1` FOREIGN KEY (`fk_area`) REFERENCES `area` (`id`),
  ADD CONSTRAINT `rel_area_ciudad_ibfk_2` FOREIGN KEY (`fk_ciudad`) REFERENCES `ciudad` (`idciudad`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
