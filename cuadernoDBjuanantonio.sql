-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 23-02-2017 a las 21:51:03
-- Versión del servidor: 5.7.17-0ubuntu0.16.04.1
-- Versión de PHP: 7.0.13-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuadernoDBjuanantonio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control`
--

CREATE TABLE `control` (
  `idEstudiante` int(11) NOT NULL,
  `falta` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `actitud` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `trabajo` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `observacion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `control`
--

INSERT INTO `control` (`idEstudiante`, `falta`, `actitud`, `trabajo`, `observacion`, `fecha`) VALUES
(1, 'justificada', 'positiva', 'bueno', 'bueno le perdonamos', '2017-02-17'),
(2, 'retraso', 'positiva', 'bueno', 'no ha sido un mal dia', '2017-02-17'),
(3, 'injustificada', 'positiva', 'malo', 'a la proxima un negativo rojo', '2017-02-17'),
(1, 'injustificada', 'negativa', 'regular', 'Ha estado muy capullo hoy el alumno', '2017-02-18'),
(2, 'justificada', 'negativa', 'regular', 'me debe un kitkat', '2017-02-22'),
(3, 'justificada', 'negativa', 'regular', 'zorras todas', '2017-02-22'),
(1, 'injustificada', 'positiva', 'regular', 'No ha hecho los tururus necesarios hoy', '2017-02-23'),
(2, 'justificada', 'positiva', 'bueno', 'Hoy ha sido eslndido', '2017-02-23'),
(3, 'justificada', 'negativa', 'malo', 'osu con el niño', '2017-02-23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ciudad` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `codigoPos` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`id`, `apellidos`, `nombre`, `direccion`, `ciudad`, `codigoPos`, `telefono`, `email`) VALUES
(1, 'Suarez Rosa', 'Juan Antonio', 'C/TuruTuru nº OK', 'KitkatLand', '33333', '757575757', 'juany.sr@gmail.com'),
(2, 'Luengo', 'Carlos ', 'Calle la guarra n123', 'Badamoza', '06870', '54643792', 'cluengo29@gmail.com'),
(3, 'Almeriense', 'Juan', 'C/Verdeaprieto nº2', 'Saragosa', '69696', '952175382', 'ejemplodevoz@enespaniol.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `control`
--
ALTER TABLE `control`
  ADD PRIMARY KEY (`fecha`,`idEstudiante`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
