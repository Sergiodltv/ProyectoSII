CREATE TABLE ALUMNO (ID VARCHAR NOT NULL, apellido_1 VARCHAR NOT NULL, APELLIDO2 VARCHAR, contrasenya VARCHAR NOT NULL, dni VARCHAR NOT NULL UNIQUE, DIRECCION VARCHAR, email_institucional VARCHAR NOT NULL, EMAILPERSONAL VARCHAR, MOVIL INTEGER, nombre VARCHAR NOT NULL, TELEFONO INTEGER, PRIMARY KEY (ID))
CREATE TABLE ASIGNATURA (REFERENCIA INTEGER NOT NULL, CARACTER VARCHAR, codigo INTEGER NOT NULL, creditos INTEGER NOT NULL, CURSO VARCHAR, DURACION VARCHAR, ingles BOOLEAN NOT NULL, nombre VARCHAR NOT NULL, ofertada BOOLEAN NOT NULL, UNIDADTEMPORAL INTEGER, AS_TITUL_CODIGO INTEGER NOT NULL, PRIMARY KEY (REFERENCIA))
CREATE TABLE CENTRO (ID VARCHAR NOT NULL, direccion VARCHAR NOT NULL, nombre VARCHAR NOT NULL UNIQUE, TELEFONOCONSERJERIA INTEGER, PRIMARY KEY (ID))
CREATE TABLE CLASE (HORAFIN TIME, DIA DATE NOT NULL, HORAINICIO VARCHAR NOT NULL, GR_CL_ID INTEGER NOT NULL, ASIG_CL_REFERENCIA INTEGER NOT NULL, PRIMARY KEY (DIA, HORAINICIO, GR_CL_ID))
CREATE TABLE ENCUESTA (FECHAENVIO DATE NOT NULL, ENC_EXP_NUMEXPEDIENTE INTEGER NOT NULL, PRIMARY KEY (FECHAENVIO))
CREATE TABLE EXPEDIENTE (NUMEXPEDIENTE INTEGER NOT NULL, ACTIVO BOOLEAN, NOTAMEDIAPROVISIONAL DOUBLE, AL_EXP_ID VARCHAR NOT NULL, PRIMARY KEY (NUMEXPEDIENTE))
CREATE TABLE GRUPO (ID INTEGER NOT NULL, ASIGNAR VARCHAR, contrasenya VARCHAR NOT NULL, curso VARCHAR NOT NULL UNIQUE, ingles BOOLEAN NOT NULL, letra CHAR NOT NULL UNIQUE, PLAZAS INTEGER, turno_manyana_tarde VARCHAR NOT NULL UNIQUE, VISIBLE BOOLEAN, G_TITUL_CODIGO INTEGER NOT NULL, GRUPO_GR_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE GRUPOS_POR_ASIGNATURA (OFERTA BOOLEAN, CURSOACADEMICO VARCHAR NOT NULL, ASI_REFERENCIA INTEGER NOT NULL, GRUP_ID INTEGER NOT NULL, PRIMARY KEY (CURSOACADEMICO, ASI_REFERENCIA, GRUP_ID))
CREATE TABLE MATRICULA (estado BOOLEAN NOT NULL, fecha_matricula DATE NOT NULL, LISTADOASIGNATURAS VARCHAR, NUEVOINGRESO BOOLEAN, NUMARCHIVO INTEGER, TURNOPREFERENTE VARCHAR, CURSOACADEMICO VARCHAR NOT NULL, MT_EXP_NUMEXPEDIENTE INTEGER NOT NULL, PRIMARY KEY (CURSOACADEMICO, MT_EXP_NUMEXPEDIENTE))
CREATE TABLE OPTATIVA (MENCION VARCHAR, plazas INTEGER NOT NULL, OPT_ASIG_REFERENCIA INTEGER NOT NULL, PRIMARY KEY (OPT_ASIG_REFERENCIA))
CREATE TABLE PAS (ID VARCHAR NOT NULL, apellido_1 VARCHAR NOT NULL, APELLIDO2 VARCHAR, contrasena VARCHAR NOT NULL, dni VARCHAR NOT NULL UNIQUE, DIRECCION VARCHAR, email_institucional VARCHAR NOT NULL, nombre VARCHAR NOT NULL, telefono_contacto INTEGER NOT NULL, CR_PAS_ID VARCHAR NOT NULL, PRIMARY KEY (ID))
CREATE TABLE TITULACION (CODIGO INTEGER NOT NULL, CREDITOS INTEGER, NOMBRE VARCHAR, PRIMARY KEY (CODIGO))
CREATE TABLE ASIG_MATRICULA (ASIGMT_ASIG_REFERENCIA INTEGER NOT NULL, MT_EXP_NUMEXPEDIENTE INTEGER NOT NULL, CURSOACADEMICO VARCHAR NOT NULL, GR_ASIG_ID INTEGER, PRIMARY KEY (ASIGMT_ASIG_REFERENCIA, MT_EXP_NUMEXPEDIENTE, CURSOACADEMICO))
CREATE TABLE CENTRO_TITULACION (idCentros_ID VARCHAR NOT NULL, c_titul_CODIGO INTEGER NOT NULL, PRIMARY KEY (idCentros_ID, c_titul_CODIGO))
CREATE TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA (gr_enc_FECHAENVIO DATE NOT NULL, CURSOACADEMICO VARCHAR NOT NULL, GRUP_ID INTEGER NOT NULL, ASI_REFERENCIA INTEGER NOT NULL, PRIMARY KEY (gr_enc_FECHAENVIO, CURSOACADEMICO, GRUP_ID, ASI_REFERENCIA))
ALTER TABLE ASIGNATURA ADD CONSTRAINT FK_ASIGNATURA_AS_TITUL_CODIGO FOREIGN KEY (AS_TITUL_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_ASIG_CL_REFERENCIA FOREIGN KEY (ASIG_CL_REFERENCIA) REFERENCES ASIGNATURA (REFERENCIA)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_GR_CL_ID FOREIGN KEY (GR_CL_ID) REFERENCES GRUPO (ID)
ALTER TABLE ENCUESTA ADD CONSTRAINT FK_ENCUESTA_ENC_EXP_NUMEXPEDIENTE FOREIGN KEY (ENC_EXP_NUMEXPEDIENTE) REFERENCES EXPEDIENTE (NUMEXPEDIENTE)
ALTER TABLE EXPEDIENTE ADD CONSTRAINT FK_EXPEDIENTE_AL_EXP_ID FOREIGN KEY (AL_EXP_ID) REFERENCES ALUMNO (ID)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_G_TITUL_CODIGO FOREIGN KEY (G_TITUL_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_GRUPO_GR_ID FOREIGN KEY (GRUPO_GR_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPOS_POR_ASIGNATURA ADD CONSTRAINT FK_GRUPOS_POR_ASIGNATURA_GRUP_ID FOREIGN KEY (GRUP_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPOS_POR_ASIGNATURA ADD CONSTRAINT FK_GRUPOS_POR_ASIGNATURA_ASI_REFERENCIA FOREIGN KEY (ASI_REFERENCIA) REFERENCES ASIGNATURA (REFERENCIA)
ALTER TABLE MATRICULA ADD CONSTRAINT FK_MATRICULA_MT_EXP_NUMEXPEDIENTE FOREIGN KEY (MT_EXP_NUMEXPEDIENTE) REFERENCES EXPEDIENTE (NUMEXPEDIENTE)
ALTER TABLE OPTATIVA ADD CONSTRAINT FK_OPTATIVA_OPT_ASIG_REFERENCIA FOREIGN KEY (OPT_ASIG_REFERENCIA) REFERENCES ASIGNATURA (REFERENCIA)
ALTER TABLE PAS ADD CONSTRAINT FK_PAS_CR_PAS_ID FOREIGN KEY (CR_PAS_ID) REFERENCES CENTRO (ID)
ALTER TABLE ASIG_MATRICULA ADD CONSTRAINT FK_ASIG_MATRICULA_ASIGMT_ASIG_REFERENCIA FOREIGN KEY (ASIGMT_ASIG_REFERENCIA) REFERENCES ASIGNATURA (REFERENCIA)
ALTER TABLE ASIG_MATRICULA ADD CONSTRAINT FK_ASIG_MATRICULA_GR_ASIG_ID FOREIGN KEY (GR_ASIG_ID) REFERENCES GRUPO (ID)
ALTER TABLE ASIG_MATRICULA ADD CONSTRAINT FK_ASIG_MATRICULA_CURSOACADEMICO FOREIGN KEY (CURSOACADEMICO, MT_EXP_NUMEXPEDIENTE) REFERENCES MATRICULA (CURSOACADEMICO, MT_EXP_NUMEXPEDIENTE)
ALTER TABLE CENTRO_TITULACION ADD CONSTRAINT FK_CENTRO_TITULACION_idCentros_ID FOREIGN KEY (idCentros_ID) REFERENCES CENTRO (ID)
ALTER TABLE CENTRO_TITULACION ADD CONSTRAINT FK_CENTRO_TITULACION_c_titul_CODIGO FOREIGN KEY (c_titul_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA ADD CONSTRAINT ENCUESTA_GRUPOS_POR_ASIGNATURA_gr_enc_FECHAENVIO FOREIGN KEY (gr_enc_FECHAENVIO) REFERENCES ENCUESTA (FECHAENVIO)
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA ADD CONSTRAINT FK_ENCUESTA_GRUPOS_POR_ASIGNATURA_CURSOACADEMICO FOREIGN KEY (CURSOACADEMICO, ASI_REFERENCIA, GRUP_ID) REFERENCES GRUPOS_POR_ASIGNATURA (CURSOACADEMICO, ASI_REFERENCIA, GRUP_ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
