/*Empresas Prueba*/
INSERT INTO `enterprise`(`id_enterprise`, `business_role`, `max_branch`, `name`, `rfc`, `status`) VALUES (1,'Business Role Test',3,'Takis', 'TOPJ840101KR1', 1 )
INSERT INTO `enterprise`(`id_enterprise`, `business_role`, `max_branch`, `name`, `rfc`, `status`) VALUES (2,'Gestion',4,'Bimbo', 'CCCJ847777KA4', 1 )
INSERT INTO `enterprise`(`id_enterprise`, `business_role`, `max_branch`, `name`, `rfc`, `status`) VALUES (3,'Review',3,'Nike', 'NIKE887744KR1', 1 )
INSERT INTO job (jobname, status) VALUES ('Primero', '1'), ('Segundo', '1'),('Encargado', '1'), ('Gerente', '1'),('Director', '1'), ('Subdirector', '1');
INSERT INTO area ( areaname, status) VALUES ('Contabilidad', b'1'), ('Auditoria', b'1'), ('Consultoria', b'1'), ('TI', b'1'), ('Calidad', b'1');
INSERT INTO workday ( workdayname) VALUES ('Nocturno'), ('Diurno'), ('Mixto');
INSERT INTO branch (id_branch, address, branch_type, employees, min_respondents, name, status, validator, enterprise_id) VALUES ('1', 'Guillermo prieto numero 76', 'Contabilidad', '180', '123', 'Lofton Contabilidad', '1', '1a2b3', '1');
INSERT INTO branch (id_branch, address, branch_type, employees, min_respondents, name, status, validator, enterprise_id) VALUES ('2', 'Guillermo prieto numero 76', 'Tecnologias de la informacion', '16', '16', 'Lofton TI', '1', 'a1b2c', '1');
INSERT INTO area_has_branch(branch_id, area_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
INSERT INTO job_has_branch(job_id, branch_id) VALUES (1,1),(2,1),(3,1),(4,1),(4,1),(6,1);
INSERT INTO `employee`(`id_employee`, `age`, `education`, `employee_name`, `marital_status`, `sex`, `status`, `work_years`, `area_id`, `branch_id`, `job_id`, `workday_id`, `user_id`) VALUES (1,25,'Licenciatura','julia garcia torres','soltero','M',1,1,4,1,2,2,NULL);
INSERT INTO `employee`(`id_employee`, `age`, `education`, `employee_name`, `marital_status`, `sex`, `status`, `work_years`, `area_id`, `branch_id`, `job_id`, `workday_id`, `user_id`) VALUES (2,20,'Licenciatura','juan lopez perez','Casado','H',1,2,3,1,4,3,NULL);

/*////////////////////////////////////////////////////////////////////////////*/

/*Inserts tabla category*/

INSERT INTO `category` (`id`, `name`, `rangos`) VALUES (1, 'Ambiente de trabajo', '3@5@7@9;5@9@11@14'),(2, 'Factores Propios de la actividad', '10@20@30@40;15@30@45@60'),(3, 'Organizaci&oacute;n del tiempo de trabajo', '4@6@9@12;5@7@10@13'),(4, 'Liderazgo y relaciones en el trabajo', '10@18@28@38;14@29@42@58'),(5, 'Entorno organizacional', '0@0@0@0;10@14@18@23');
insert into `category` (`id`, `name`, `rangos`) values (6,'N/A', '0@0@0@0;0@0@0@0');


/*Inserts tabla domain*/

INSERT INTO `domain` (`id`, `name`, `category_id`, `rangos`) VALUES (1, 'Condiciones en el ambiente de trabajo', 1, '3@5@7@9;5@9@11@14'),(2, 'Carga de Trabajo', 2, '12@16@20@24;15@21@27@37'),(3, 'Falta de control sobre el trabajo', 2, '5@8@11@14;11@16@21@25'),(4, 'Jornada de trabajo', 3, '1@2@4@6;1@2@4@6'),(5, 'Interferencia en la relaci&oacute;n trabajo familia', 3, '1@2@4@6;4@6@8@10'),(6, 'Liderazgo', 4, '3@5@8@11;9@12@16@20'),(7, 'Relaciones en el trabajo', 4, '5@8@11@14;10@13@17@21'),(8, 'Violencia', 4, '7@10@13@16;7@10@13@16'),(9, 'Reconocimiento del desempe&ntilde;o', 5, '0@0@0@0;6@10@14@18'),(10, 'Insuficiente sentido de pertenencia e inestabilidad', 5, '0@0@0@0;4@6@8@10');
insert into `domain` (`id`, `name`, `rangos`, `category_id`) values (11, 'N/A', '0@0@0@0;0@0@0@0', 6);
/*Inserts tabla dimension*/

insert into dimension (id, name, domain_id) values (1,'Condiciones peligrosas e inseguras', 1);
insert into dimension (id, name, domain_id) values (2,'Condiciones deficientes e insalubres', 1);
insert into dimension (id, name, domain_id) values (3,'Trabajos peligrosos', 1);
insert into dimension (id, name, domain_id) values (4,'Cargas cuantitativas', 2);
insert into dimension (id, name, domain_id) values (5,'Ritmos de trabajo acelerado', 2);
insert into dimension (id, name, domain_id) values (6,'Carga mental', 2);
insert into dimension (id, name, domain_id) values (7,'Cargas psicol&oacute;gicas emocionales', 2);
insert into dimension (id, name, domain_id) values (8,'Cargas de alta responsabilidad', 2);
insert into dimension (id, name, domain_id) values (9,'Cargas contradictorias o inconsistentes', 2);
insert into dimension (id, name, domain_id) values (10,'Falta de control y autonom&iacute;a sobre el trabajo', 3);
insert into dimension (id, name, domain_id) values (11,'Limitada o nula posibilidad de desarrollo', 3);
insert into dimension (id, name, domain_id) values (12,'Limitada o inexistente capacitaci&oacute;n', 3);
insert into dimension (id, name, domain_id) values (13,'Jornadas de trabajo extensas', 4);
insert into dimension (id, name, domain_id) values (14,'Influencia del trabajo fuera del centro laboral', 5);
insert into dimension (id, name, domain_id) values (15,'Influencia de las responsabilidades familiares', 5);
insert into dimension (id, name, domain_id) values (16,'Escasa claridad de funciones', 6);
insert into dimension (id, name, domain_id) values (17,'Caracter&iacute;sticas del liderazgo', 6);
insert into dimension (id, name, domain_id) values (18,'Relaciones sociales en el trabajo', 7);
insert into dimension (id, name, domain_id) values (19,'Deficiente relaci&oacute;n con los colaboradores que supervisa', 7);
insert into dimension (id, name, domain_id) values (20,'Violencia laboral', 8);
insert into dimension (id, name, domain_id) values (21,'Escasa o nula retroalimentaci&oacute;n del desempe&ntilde;o', 9);
insert into dimension (id, name, domain_id) values (22,'Escaso o nulo reconocimiento y compensaci&oacute;n', 9);
insert into dimension (id, name, domain_id) values (23,'Limitado sentido de pertenencia', 10);
insert into dimension (id, name, domain_id) values (24,'Inestabilidad laboral', 10);
insert into dimension (id, name, domain_id) values (25,'Insuficiente participaci&oacute;n y manejo del cambio', 3);
insert into dimension (id, name, domain_id) values (26, 'N/A', 11);

/*Inserts tabla response_type*/

insert into response_type (id, name, response) values (1,'Multiple', 'Siempre@Casi siempre@Algunas veces@Casi nunca@Nunca');
insert into response_type (id, name, response) values (2,'Si/No', 'Si@No');

/*Inserts tabla response_type*/

insert into survey (id, name, status,rangos) values (1,'IDENTIFICAR A LOS TRABAJADORES QUE FUERON SUJETOS A ACONTECIMIENTOS TRAUMÁTICOS SEVEROS', TRUE,'10@30@80@120');
insert into survey (id, name, status,rangos) values (2,'IDENTIFICACIÓN Y ANÁLISIS DE LOS FACTORES DE RIESGO PSICOSOCIAL', TRUE,'20@45@70@90');
insert into survey (id, name, status,rangos) values (3,'IDENTIFICACIÓN Y ANÁLISIS DE LOS FACTORES DE RIESGO PSICOSOCIAL Y EVALUACIÓN DEL ENTORNO ORGANIZACIONAL EN LOS CENTROS DE TRABAJO', TRUE,'50@75@99@140');
insert into branch_has_survey(survey_id, branch_id) VALUES (1,1),(2,1),(3,1);

insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (18,'Mi trabajo permite que desarrolle nuevas habilidades', '0@1@2@3@4', 2 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (19,'En mi trabajo puedo aspirar a un mejor puesto', '0@1@2@3@4', 2 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (20,'Durante mi jornada de trabajo puedo tomar pausas cuando las necesito', '0@1@2@3@4', 2 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (21,'Puedo decidir la velocidad a la que realizo mis actividades en mi trabajo', '0@1@2@3@4', 2 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (22,'Puedo cambiar el orden de las actividades que realizo en mi trabajo', '0@1@2@3@4', 2 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (23,'Me informan con claridad cu&aacute;les son mis funciones', '0@1@2@3@4', 2 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (24,'Me explican claramente los resultados que debo obtener en mi trabajo', '0@1@2@3@4', 2 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (25,'Me informan con qui&eacute;n puedo resolver problemas o asuntos de trabajo', '0@1@2@3@4', 2 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (26,'Me permiten asistir a capacitaciones relacionadas con mi trabajo', '0@1@2@3@4', 2 , 1, 12);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (27,'Recibo capacitaci&oacute;n &uacute;til para hacer mi trabajo', '0@1@2@3@4', 2 , 1, 12);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (28,'Mi jefe tiene en cuenta mis puntos de vista y opiniones', '0@1@2@3@4', 2 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (29,'Mi jefe ayuda a solucionar los problemas que se presentan en el trabajo', '0@1@2@3@4', 2 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (30,'Puedo confiar en mis compa&ntilde;eros de trabajo', '0@1@2@3@4', 2 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (31,'Cuando tenemos que realizar trabajo de equipo los compa&ntilde;eros colaboran', '0@1@2@3@4', 2 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (32,'Mis compa&ntilde;eros de trabajo me ayudan cuando tengo dificultades', '0@1@2@3@4', 2 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (33,'En mi trabajo puedo expresarme libremente sin interrupciones', '0@1@2@3@4', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (1,'Mi trabajo me exige hacer mucho esfuerzo f&iacute;sico', '4@3@2@1@0', 2 , 1, 2);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (2,'Me preocupa sufrir un accidente en mi trabajo', '4@3@2@1@0', 2 , 1, 1);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (3,'Considero que las actividades que realizo son peligrosas', '4@3@2@1@0', 2 , 1, 3);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (4,'Por la cantidad de trabajo que tengo debo quedarme tiempo adicional a mi turno', '4@3@2@1@0', 2 , 1, 4);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (5,'Por la cantidad de trabajo que tengo debo trabajar sin parar', '4@3@2@1@0', 2 , 1, 5);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (6,'Considero que es necesario mantener un ritmo de trabajo acelerado', '4@3@2@1@0', 2 , 1, 5);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (7,'Mi trabajo exige que est&eacute; muy concentrado', '4@3@2@1@0', 2 , 1, 6);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (8,'Mi trabajo requiere que memorice mucha informaci&oacute;n', '4@3@2@1@0', 2 , 1, 6);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (9,'Mi trabajo exige que atienda varios asuntos al mismo tiempo', '4@3@2@1@0', 2 , 1, 4);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (10,'En mi trabajo soy responsable de cosas de mucho valor', '4@3@2@1@0', 2 , 1, 8);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (11,'Respondo ante mi jefe por los resultados de toda mi &aacute;rea de trabajo', '4@3@2@1@0', 2 , 1, 8);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (12,'En mi trabajo me dan &oacute;rdenes contradictorias', '4@3@2@1@0', 2 , 1, 9);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (13,'Considero que en mi trabajo me piden hacer cosas innecesarias', '4@3@2@1@0', 2 , 1, 9);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (14,'Trabajo horas extras m&aacute;s de tres veces a la semana', '4@3@2@1@0', 2 , 1, 13);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (15,'Mi trabajo me exige laborar en d&iacute;as de descanso, festivos o fines de semana', '4@3@2@1@0', 2 , 1, 13);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (16,'Considero que el tiempo en el trabajo es mucho y perjudica mis actividades familiares o personales', '4@3@2@1@0', 2 , 1, 14);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (17,'Pienso en las actividades familiares o personales cuando estoy en mi trabajo', '4@3@2@1@0', 2 , 1, 15);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (34,'Recibo cr&iacute;ticas constantes a mi persona y/o trabajo', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (35,'Recibo burlas, calumnias, difamaciones, humillaciones o ridiculizaciones', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (36,'Se ignora mi presencia o se me excluye de las reuniones de trabajo y en la toma de decisiones', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (37,'Se manipulan las situaciones de trabajo para hacerme parecer un mal trabajador', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (38,'Se ignoran mis &eacute;xitos laborales y se atribuyen a otros trabajadores', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (39,'Me bloquean o impiden las oportunidades que tengo para obtener ascenso o mejora en mi trabajo', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (40,'He presenciado actos de violencia en mi centro de trabajo', '4@3@2@1@0', 2 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (41,'Atiendo clientes o usuarios muy enojados', '4@3@2@1@0', 2 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (42,'Mi trabajo me exige atender personas muy necesitadas de ayuda o enfermas', '4@3@2@1@0', 2 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (43,'Para hacer mi trabajo debo demostrar sentimientos distintos a los m&iacute;os', '4@3@2@1@0', 2 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (44,'Comunican tarde los asuntos de trabajo', '4@3@2@1@0', 2 , 1, 19);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (45,'Dificultan el logro de los resultados del trabajo', '4@3@2@1@0', 2 , 1, 19);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (46,'Ignoran las sugerencias para mejorar su trabajo', '4@3@2@1@0', 2 , 1, 19);


	/*Cuestionario tabla 3*/

insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (1,'El espacio donde trabajo me permite realizar mis actividades de manera segura e higi&eacute;nica', '0@1@2@3@4', 3 , 1, 1);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (2,'Mi trabajo me exige hacer mucho esfuerzo f&iacute;sico', '4@3@2@1@0', 3 , 1, 2);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (3,'Me preocupa sufrir un accidente en mi trabajo', '4@3@2@1@0', 3 , 1, 1);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (4,'Considero que en mi trabajo se aplican las normas de seguridad y salud en el trabajo', '0@1@2@3@4', 3 , 1, 2);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (5,'Considero que las actividades que realizo son peligrosas', '4@3@2@1@0', 3 , 1, 3);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (6,'Por la cantidad de trabajo que tengo debo quedarme tiempo adicional a mi turno', '4@3@2@1@0', 3 , 1, 4);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (7,'Por la cantidad de trabajo que tengo debo trabajar sin parar', '4@3@2@1@0', 3 , 1, 5);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (8,'Considero que es necesario mantener un ritmo de trabajo acelerado', '4@3@2@1@0', 3 , 1, 5);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (9,'Mi trabajo exige que est&eacute; muy concentrado', '4@3@2@1@0', 3 , 1, 6);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (10,'Mi trabajo requiere que memorice mucha informaci&oacute;n', '4@3@2@1@0', 3 , 1, 6);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (11,'En mi trabajo tengo que tomar decisiones dif&iacute;ciles muy r&aacute;pido', '4@3@2@1@0', 3 , 1, 6);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (12,'Mi trabajo exige que atienda varios asuntos al mismo tiempo', '4@3@2@1@0', 3 , 1, 4);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (13,'En mi trabajo soy responsable de cosas de mucho valor', '4@3@2@1@0', 3 , 1, 8);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (14,'Respondo ante mi jefe por los resultados de toda mi &aacute;rea de trabajo', '4@3@2@1@0', 3 , 1, 8);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (15,'En el trabajo me dan &oacute;rdenes contradictorias', '4@3@2@1@0', 3 , 1, 9);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (16,'Considero que en mi trabajo me piden hacer cosas innecesarias', '4@3@2@1@0', 3 , 1, 9);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (17,'Trabajo horas extras m&aacute;s de tres veces a la semana', '4@3@2@1@0', 3 , 1, 13);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (18,'Mi trabajo me exige laborar en d&iacute;as de descanso, festivos o fines de semana', '4@3@2@1@0', 3 , 1, 13);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (19,'Considero que el tiempo en el trabajo es mucho y perjudica mis actividades familiares o personales', '4@3@2@1@0', 3 , 1, 4);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (20,'Debo atender asuntos de trabajo cuando estoy en casa', '4@3@2@1@0', 3 , 1, 14);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (21,'Pienso en las actividades familiares o personales cuando estoy en mi trabajo', '4@3@2@1@0', 3 , 1, 15);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (22,'Pienso que mis responsabilidades familiares afectan mi trabajo', '4@3@2@1@0', 3 , 1, 15);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (23,'Mi trabajo permite que desarrolle nuevas habilidades', '0@1@2@3@4', 3 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (24,'En mi trabajo puedo aspirar a un mejor puesto', '0@1@2@3@4', 3 , 1, 11);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (25,'Durante mi jornada de trabajo puedo tomar pausas cuando las necesito', '0@1@2@3@4', 3 , 1, 10);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (26,'Puedo decidir cu&aacute;nto trabajo realizo durante la jornada laboral', '0@1@2@3@4', 3 , 1, 10);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (27,'Puedo decidir la velocidad a la que realizo mis actividades en mi trabajo', '0@1@2@3@4', 3 , 1, 10);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (28,'Puedo cambiar el orden de las actividades que realizo en mi trabajo', '0@1@2@3@4', 3 , 1, 10);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (29,'Los cambios que se presentan en mi trabajo dificultan mi labor', '4@3@2@1@0', 3 , 1, 25);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (30,'Cuando se presentan cambios en mi trabajo se tienen en cuenta mis ideas o aportaciones', '0@1@2@3@4', 3 , 1, 25);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (31,'Me informan con claridad cu&aacute;les son mis funciones', '0@1@2@3@4', 3 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (32,'Me explican claramente los resultados que debo obtener en mi trabajo', '0@1@2@3@4', 3 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (33,'Me explican claramente los objetivos de mi trabajo', '0@1@2@3@4', 3 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (34,'Me informan con qui&eacute;n puedo resolver problemas o asuntos de trabajo', '0@1@2@3@4', 3 , 1, 16);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (35,'Me permiten asistir a capacitaciones relacionadas con mi trabajo', '0@1@2@3@4', 3 , 1, 12);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (36,'Recibo capacitaci&oacute;n &uacute;til para hacer mi trabajo', '0@1@2@3@4', 3 , 1, 12);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (37,'Mi jefe ayuda a organizar mejor el trabajo', '0@1@2@3@4', 3 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (38,'Mi jefe tiene en cuenta mis puntos de vista y opiniones', '0@1@2@3@4', 3 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (39,'Mi jefe me comunica a tiempo la informaci&oacute;n relacionada con el trabajo', '0@1@2@3@4', 3 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (40,'La orientaci&oacute;n que me da mi jefe me ayuda a realizar mejor mi trabajo', '0@1@2@3@4', 3 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (41,'Mi jefe ayuda a solucionar los problemas que se presentan en el trabajo', '0@1@2@3@4', 3 , 1, 17);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (42,'Puedo confiar en mis compa&ntilde;eros de trabajo', '0@1@2@3@4', 3 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (43,'Entre compa&ntilde;eros solucionamos los problemas de trabajo de forma respetuosa', '0@1@2@3@4', 3 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (44,'En mi trabajo me hacen sentir parte del grupo', '0@1@2@3@4', 3 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (45,'Cuando tenemos que realizar trabajo de equipo los compa&ntilde;eros colaboran', '0@1@2@3@4', 3 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (46,'Mis compa&ntilde;eros de trabajo me ayudan cuando tengo dificultades', '0@1@2@3@4', 3 , 1, 18);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (47,'Me informan sobre lo que hago bien en mi trabajo', '0@1@2@3@4', 3 , 1, 21);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (48,'La forma como eval&uacute;an mi trabajo en mi centro de trabajo me ayuda a mejorar mi desempe&ntilde;o', '0@1@2@3@4', 3 , 1, 21);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (49,'En mi centro de trabajo me pagan a tiempo mi salario', '0@1@2@3@4', 3 , 1, 22);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (50,'El pago que recibo es el que merezco por el trabajo que realizo', '0@1@2@3@4', 3 , 1, 22);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (51,'Si obtengo los resultados esperados en mi trabajo me recompensan o reconocen', '0@1@2@3@4', 3 , 1, 22);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (52,'Las personas que hacen bien el trabajo pueden crecer laboralmente', '0@1@2@3@4', 3 , 1, 22);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (53,'Considero que mi trabajo es estable', '0@1@2@3@4', 3 , 1, 24);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (54,'En mi trabajo existe continua rotaci&oacute;n de personal', '4@3@2@1@0', 3 , 1, 24);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (55,'Siento orgullo de laborar en este centro de trabajo', '0@1@2@3@4', 3 , 1, 23);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (56,'Me siento comprometido con mi trabajo', '0@1@2@3@4', 3 , 1, 23);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (57,'En mi trabajo puedo expresarme libremente sin interrupciones', '0@1@2@3@4', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (58,'Recibo cr&iacute;ticas constantes a mi persona y/o trabajo', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (59,'Recibo burlas, calumnias, difamaciones, humillaciones o ridiculizaciones', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (60,'Se ignora mi presencia o se me excluye de las reuniones de trabajo y en la toma de decisiones', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (61,'Se manipulan las situaciones de trabajo para hacerme parecer un mal trabajador', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (62,'Se ignoran mis &eacute;xitos laborales y se atribuyen a otros trabajadores', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (63,'Me bloquean o impiden las oportunidades que tengo para obtener ascenso o mejora en mi trabajo', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (64,'He presenciado actos de violencia en mi centro de trabajo', '4@3@2@1@0', 3 , 1, 20);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (65,'Atiendo clientes o usuarios muy enojados', '4@3@2@1@0', 3 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (66,'Mi trabajo me exige atender personas muy necesitadas de ayuda o enfermas', '4@3@2@1@0', 3 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (67,'Para hacer mi trabajo debo demostrar sentimientos distintos a los m&iacute;os', '4@3@2@1@0', 3 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (68,'Mi trabajo me exige atender situaciones de violencia', '4@3@2@1@0', 3 , 1, 7);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (69,'Comunican tarde los asuntos de trabajo', '4@3@2@1@0', 3 , 1, 19);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (70,'Dificultan el logro de los resultados del trabajo', '4@3@2@1@0', 3 , 1, 19);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (71,'Cooperan poco cuando se necesita', '4@3@2@1@0', 3 , 1, 19);
insert into question (position_question, text, values_question, survey_id, response_type_id, dimension_id) values (72,'Ignoran las sugerencias para mejorar su trabajo', '4@3@2@1@0', 3 , 1, 19);

insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (1,'&iquest;Ha presenciado o sufrido alguna vez, durante o con motivo del trabajo un acontecimiento como los siguientes:<br>Accidente que tenga como consecuencia la muerte, la p&eacute;rdida de un miembro o una lesi&oacute;n grave?<br>Asaltos?<br>Actos violentos que derivaron en lesiones graves?<br>Secuestro?<br>Amenazas?, o<br>Cualquier otro que ponga en riesgo su vida o salud, y/o la de otras personas?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (2,'&iquest;Ha tenido recuerdos recurrentes sobre el acontecimiento que le provocan malestares?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (3,'&iquest;Se ha esforzado por evitar todo tipo de sentimientos, conversaciones o situaciones que le puedan recordar el acontecimiento?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (4,'&iquest;Se ha esforzado por evitar todo tipo de actividades, lugares o personas que motivan recuerdos del acontecimiento?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (5,'&iquest;Ha tenido dificultad para recordar alguna parte importante del evento?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (6,'&iquest;Ha disminuido su inter&eacute;s en sus actividades cotidianas?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (7,'&iquest;Se ha sentido usted alejado o distante de los dem&aacute;s?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (8,'&iquest;Ha notado que tiene dificultad para expresar sus sentimientos?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (9,'&iquest;Ha tenido la impresi&oacute;n de que su vida se va a acortar, que va a morir antes que otras personas o que tiene un futuro limitado?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (10,'&iquest;Ha tenido usted dificultades para dormir?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (11,'&iquest;Ha estado particularmente irritable o le han dado arranques de coraje?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (12,'&iquest;Ha tenido dificultad para concentrarse?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (13,'&iquest;Ha estado nervioso o constantemente en alerta?','10@0', 26, 2, 1);
insert into question (position_question, text, values_question, dimension_id, response_type_id, survey_id) values (14,'&iquest;Se ha sobresaltado f&aacute;cilmente por cualquier cosa?','10@0', 26, 2, 1);


INSERT INTO `response` (`id`, `value`, `question_id`, `employee_id`) VALUES (0, Floor(Rand() * 5), 1,2),(0, Floor(Rand() * 5), 2,2),(0, Floor(Rand() * 5), 3,2),(0, Floor(Rand() * 5), 4,2),(0, Floor(Rand() * 5), 5,2),(0, Floor(Rand() * 5), 6,2),(0, Floor(Rand() * 5), 7,2),(0, Floor(Rand() * 5), 8,2),(0, Floor(Rand() * 5), 9,2),(0, Floor(Rand() * 5), 10,2),(0, Floor(Rand() * 5), 11,2),(0, Floor(Rand() * 5), 12,2),(0, Floor(Rand() * 5), 13,2),(0, Floor(Rand() * 5), 14,2),(0, Floor(Rand() * 5), 15,2),(0, Floor(Rand() * 5), 16,2),(0, Floor(Rand() * 5), 17,2),(0, Floor(Rand() * 5), 18,2),(0, Floor(Rand() * 5), 19,2),(0, Floor(Rand() * 5), 20,2),(0, Floor(Rand() * 5), 21,2),(0, Floor(Rand() * 5), 22,2),(0, Floor(Rand() * 5), 23,2),(0, Floor(Rand() * 5), 24,2),(0, Floor(Rand() * 5), 25,2),(0, Floor(Rand() * 5), 26,2),(0, Floor(Rand() * 5), 27,2),(0, Floor(Rand() * 5), 28,2),(0, Floor(Rand() * 5), 29,2),(0, Floor(Rand() * 5), 30,2),(0, Floor(Rand() * 5), 31,2),(0, Floor(Rand() * 5), 32,2),(0, Floor(Rand() * 5), 33,2),(0, Floor(Rand() * 5), 34,2),(0, Floor(Rand() * 5), 35,2),(0, Floor(Rand() * 5), 36,2),(0, Floor(Rand() * 5), 37,2),(0, Floor(Rand() * 5), 38,2),(0, Floor(Rand() * 5), 39,2),(0, Floor(Rand() * 5), 40,2),(0, Floor(Rand() * 5), 41,2),(0, Floor(Rand() * 5), 42,2),(0, Floor(Rand() * 5), 43,2),(0, Floor(Rand() * 5), 44,2),(0, Floor(Rand() * 5), 45,2),(0, Floor(Rand() * 5), 46,2),(0, Floor(Rand() * 5), 1,1),(0, Floor(Rand() * 5), 2,1),(0, Floor(Rand() * 5), 3,1),(0, Floor(Rand() * 5), 4,1),(0, Floor(Rand() * 5), 5,1),(0, Floor(Rand() * 5), 6,1),(0, Floor(Rand() * 5), 7,1),(0, Floor(Rand() * 5), 8,1),(0, Floor(Rand() * 5), 9,1),(0, Floor(Rand() * 5), 10,1),(0, Floor(Rand() * 5), 11,1),(0, Floor(Rand() * 5), 12,1),(0, Floor(Rand() * 5), 13,1),(0, Floor(Rand() * 5), 14,1),(0, Floor(Rand() * 5), 15,1),(0, Floor(Rand() * 5), 16,1),(0, Floor(Rand() * 5), 17,1),(0, Floor(Rand() * 5), 18,1),(0, Floor(Rand() * 5), 19,1),(0, Floor(Rand() * 5), 20,1),(0, Floor(Rand() * 5), 21,1),(0, Floor(Rand() * 5), 22,1),(0, Floor(Rand() * 5), 23,1),(0, Floor(Rand() * 5), 24,1),(0, Floor(Rand() * 5), 25,1),(0, Floor(Rand() * 5), 26,1),(0, Floor(Rand() * 5), 27,1),(0, Floor(Rand() * 5), 28,1),(0, Floor(Rand() * 5), 29,1),(0, Floor(Rand() * 5), 30,1),(0, Floor(Rand() * 5), 31,1),(0, Floor(Rand() * 5), 32,1),(0, Floor(Rand() * 5), 33,1),(0, Floor(Rand() * 5), 34,1),(0, Floor(Rand() * 5), 35,1),(0, Floor(Rand() * 5), 36,1),(0, Floor(Rand() * 5), 37,1),(0, Floor(Rand() * 5), 38,1),(0, Floor(Rand() * 5), 39,1),(0, Floor(Rand() * 5), 40,1),(0, Floor(Rand() * 5), 41,1),(0, Floor(Rand() * 5), 42,1),(0, Floor(Rand() * 5), 43,1),(0, Floor(Rand() * 5), 44,1),(0, Floor(Rand() * 5), 45,1),(0, Floor(Rand() * 5), 46,1),(0, floor(rand()*5), 47, 1),(0, floor(rand()*5), 48, 1),(0, floor(rand()*5), 49, 1),(0, floor(rand()*5), 50, 1),(0, floor(rand()*5), 51, 1),(0, floor(rand()*5), 52, 1),(0, floor(rand()*5), 53, 1),(0, floor(rand()*5), 54, 1),(0, floor(rand()*5), 55, 1),(0, floor(rand()*5), 56, 1),(0, floor(rand()*5), 57, 1),(0, floor(rand()*5), 58, 1),(0, floor(rand()*5), 59, 1),(0, floor(rand()*5), 60, 1),(0, floor(rand()*5), 61, 1),(0, floor(rand()*5), 62, 1),(0, floor(rand()*5), 63, 1),(0, floor(rand()*5), 64, 1),(0, floor(rand()*5), 65, 1),(0, floor(rand()*5), 66, 1),(0, floor(rand()*5), 67, 1),(0, floor(rand()*5), 68, 1),(0, floor(rand()*5), 69, 1),(0, floor(rand()*5), 70, 1),(0, floor(rand()*5), 71, 1),(0, floor(rand()*5), 72, 1),(0, floor(rand()*5), 73, 1),(0, floor(rand()*5), 74, 1),(0, floor(rand()*5), 75, 1),(0, floor(rand()*5), 76, 1),(0, floor(rand()*5), 77, 1),(0, floor(rand()*5), 78, 1),(0, floor(rand()*5), 79, 1),(0, floor(rand()*5), 80, 1),(0, floor(rand()*5), 81, 1),(0, floor(rand()*5), 82, 1),(0, floor(rand()*5), 83, 1),(0, floor(rand()*5), 84, 1),(0, floor(rand()*5), 85, 1),(0, floor(rand()*5), 86, 1),(0, floor(rand()*5), 87, 1),(0, floor(rand()*5), 88, 1),(0, floor(rand()*5), 89, 1),(0, floor(rand()*5), 90, 1),(0, floor(rand()*5), 91, 1),(0, floor(rand()*5), 92, 1),(0, floor(rand()*5), 93, 1),(0, floor(rand()*5), 94, 1),(0, floor(rand()*5), 95, 1),(0, floor(rand()*5), 96, 1),(0, floor(rand()*5), 97, 1),(0, floor(rand()*5), 98, 1),(0, floor(rand()*5), 99, 1),(0, floor(rand()*5), 100, 1),(0, floor(rand()*5), 101, 1),(0, floor(rand()*5), 102, 1),(0, floor(rand()*5), 103, 1),(0, floor(rand()*5), 104, 1),(0, floor(rand()*5), 105, 1),(0, floor(rand()*5), 106, 1),(0, floor(rand()*5), 107, 1),(0, floor(rand()*5), 108, 1),(0, floor(rand()*5), 109, 1),(0, floor(rand()*5), 110, 1),(0, floor(rand()*5), 111, 1),(0, floor(rand()*5), 112, 1),(0, floor(rand()*5), 113, 1),(0, floor(rand()*5), 114, 1),(0, floor(rand()*5), 115, 1),(0, floor(rand()*5), 116, 1),(0, floor(rand()*5), 117, 1),(0, floor(rand()*5), 118, 1);

INSERT INTO `role`(`id_role`, `role`, `status`) VALUES (1,'ROOT',1);
INSERT INTO `role`(`id_role`, `role`, `status`) VALUES (2,'ADMIN',1);
INSERT INTO `role`(`id_role`, `role`, `status`) VALUES (3,'EMPLOYEE',1);
INSERT INTO `role`(`id_role`, `role`, `status`) VALUES (4,'CLIENT',1);

INSERT INTO `module`(`id_module`, `name`, `status`) VALUES (1,'USUARIOS',1);
INSERT INTO `module`(`id_module`, `name`, `status`) VALUES (2,'EMPRESAS',1);
INSERT INTO `module`(`id_module`, `name`, `status`) VALUES (3,'REPORTES',1);
INSERT INTO `module`(`id_module`, `name`, `status`) VALUES (4,'ENCUESTAS',1);

INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (1,'1111',1);
INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (2,'1111',2);
INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (3,'1111',3);
INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (4,'1110',4);
INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (5,'0110',1);
INSERT INTO `module_permiso`(`id`, `permisos`, `module_id`) VALUES (6,'0100',3);


INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (1,1);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (1,2);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (1,3);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (2,5);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (2,6);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (3,4);
INSERT INTO `role_has_module_permiso`(`role_id`, `module_permiso_id`) VALUES (4,6);

/* calidad nE.rN8*@iBMx**/
INSERT INTO `user`(`id_user`, `email`, `name`, `password`, `status`, `username`, `role_id`) VALUES (1,'rootcalidad@loftonsc.com','calidad','$2a$10$Ge.CqpfhWw75E3/rvG4kCeF.T2t3ivM/ESY0jayCf6ee9hz1OItgi',1,'calidad',1);
INSERT INTO `user`(`id_user`, `email`, `name`, `password`, `status`, `username`, `role_id`) VALUES (2,'root@lofton.com','Root','$2a$10$rXBAL33WlMwXKTk4RXeqXO7CL6F89qirsh1Lhwhkb.9cWcbIwFaYS',1,'root',1);

/* Datos Secciones */

INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('1', '0', '9', '1', '1', 'N/A', 'Para responder las preguntas siguientes considere las condiciones de su centro de trabajo, as&iacute; como la cantidad y ritmo de trabajo.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('2', '0', '13', '10', '2', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con las actividades que realiza en su trabajo y las responsabilidades que tiene.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('3', '0', '17', '14', '3', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con el tiempo destinado a su trabajo y sus responsabilidades familiares.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('4', '0', '22', '18', '4', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con las decisiones que puede tomar en su trabajo.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('5', '0', '27', '23', '5', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con la capacitaci&oacute;n e informaci&oacute;n que recibe sobre su trabajo.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('6', '0', '40', '28', '6', 'N/A', 'Las preguntas siguientes se refieren a las relaciones con sus compañeros de trabajo y su jefe.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('7', '1', '43', '41', '7', 'En mi trabajo debo brindar servicio a clientes o usuarios:', 'Las preguntas siguientes est&aacute;n relacionadas con la atenci&oacute;n a clientes y usuarios.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('8', '1', '46', '44', '8', 'Soy jefe de otros trabajadores:', 'Las siguientes preguntas est&aacute;n relacionadas con las actitudes de los trabajadores que supervisa.', '2');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('9', '0', '5', '1', '1', 'N/A', 'Para responder las preguntas siguientes considere las condiciones ambientales de su centro de trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('10', '0', '8', '6', '2', 'N/A', 'Para responder a las preguntas siguientes piense en la cantidad y ritmo de trabajo que tiene.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('11', '0', '12', '9', '3', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con el esfuerzo mental que le exige su trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('12', '0', '16', '13', '4', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con las actividades que realiza en su trabajo y las responsabilidades que tiene.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('13', '0', '22', '17', '5', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con su jornada de trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('14', '0', '28', '23', '6', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con las decisiones que puede tomar en su trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('15', '0', '30', '29', '7', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con cualquier tipo de cambio que ocurra en su trabajo (considere los &uacute;ltimos cambios realizados).', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('16', '0', '36', '31', '8', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con la capacitaci&oacute;n e informaci&oacute;n que se le proporciona sobre su trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('17', '0', '41', '37', '9', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con el o los jefes con quien tiene contacto.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('18', '0', '46', '42', '10', 'N/A', 'Las preguntas siguientes se refieren a las relaciones con sus compañeros.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('19', '0', '56', '47', '11', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con la informaci&oacute;n que recibe sobre su rendimiento en el trabajo, el reconocimiento, el sentido de pertenencia y la estabilidad que le ofrece su trabajo.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('20', '0', '64', '57', '12', 'N/A', 'Las preguntas siguientes est&aacute;n relacionadas con actos de violencia laboral (malos tratos, acoso, hostigamiento, acoso psicol&oacute;gico).', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('21', '1', '68', '65', '13', 'En mi trabajo debo brindar servicio a clientes o usuarios:', 'Las preguntas siguientes est&aacute;n relacionadas con la atenci&oacute;n a clientes y usuarios.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('22', '1', '72', '69', '14', 'Soy jefe de otros trabajadores:', 'Las preguntas siguientes est&aacute;n relacionadas con las actitudes de las personas que supervisa.', '3');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('23', '0', '1', '1', '1', 'N/A', 'I.- Acontecimiento traum&aacute;tico severo', '1');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('24', '0', '3', '2', '2', 'N/A', 'II.- Recuerdos persistentes sobre el acontecimiento (durante el &uacute;ltimo mes):', '1');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('25', '0', '10', '4', '3', 'N/A', 'III.- Esfuerzo por evitar circunstancias parecidas o asociadas al acontecimiento (durante el &uacute;ltimo mes):', '1');
INSERT INTO `section` (`id`, `dependiente`, `fin`, `inicio`, `position`, `question`, `texto`, `survey_id`) VALUES ('26', '0', '15', '11', '4', 'N/A', 'IV Afectaci&oacute;n (durante el &uacute;ltimo mes):', '1');


CREATE OR REPLACE VIEW `Tipo_1` AS Select  employee.id_employee , employee.employee_name as nombre ,enterprise_id,enterprise.name as empresa, branch.name as centro,branch.validator, survey_id,survey.name as Encuesta ,(select sum(value) from response where employee_id = employee.id_employee and question_id in (119)) as sectionI,(select sum(value) from response where employee_id = employee.id_employee and question_id in (120,121)) as sectionII,(select sum(value) from response where employee_id = employee.id_employee and question_id in (122,123,124,125,126,127,128))as sectionIII,(select sum(value) from response where employee_id = employee.id_employee and question_id in (129,130,131, 132))as sectionIV from response,question,employee,survey,branch,enterprise where employee_id = employee.id_employee and response.question_id=question.id and question.survey_id = survey.id and survey.id = 1 and employee.branch_id = branch.id_branch and branch.enterprise_id = enterprise.id_enterprise GROUP by survey_id, employee.id_employee;
