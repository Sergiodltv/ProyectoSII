ALTER TABLE ASIGNATURA DROP FOREIGN KEY FK_ASIGNATURA_AS_TITUL_CODIGO
ALTER TABLE CLASE DROP FOREIGN KEY FK_CLASE_ASIG_CL_REFERENCIA
ALTER TABLE CLASE DROP FOREIGN KEY FK_CLASE_GR_CL_ID
ALTER TABLE ENCUESTA DROP FOREIGN KEY FK_ENCUESTA_ENC_EXP_NUMEXPEDIENTE
ALTER TABLE EXPEDIENTE DROP FOREIGN KEY FK_EXPEDIENTE_AL_EXP_ID
ALTER TABLE GRUPO DROP FOREIGN KEY FK_GRUPO_G_TITUL_CODIGO
ALTER TABLE GRUPO DROP FOREIGN KEY FK_GRUPO_GRUPO_GR_ID
ALTER TABLE GRUPOS_POR_ASIGNATURA DROP FOREIGN KEY FK_GRUPOS_POR_ASIGNATURA_GRUP_ID
ALTER TABLE GRUPOS_POR_ASIGNATURA DROP FOREIGN KEY FK_GRUPOS_POR_ASIGNATURA_ASI_REFERENCIA
ALTER TABLE MATRICULA DROP FOREIGN KEY FK_MATRICULA_MT_EXP_NUMEXPEDIENTE
ALTER TABLE OPTATIVA DROP FOREIGN KEY FK_OPTATIVA_OPT_ASIG_REFERENCIA
ALTER TABLE PAS DROP FOREIGN KEY FK_PAS_CR_PAS_ID
ALTER TABLE ASIG_MATRICULA DROP FOREIGN KEY FK_ASIG_MATRICULA_ASIGMT_ASIG_REFERENCIA
ALTER TABLE ASIG_MATRICULA DROP FOREIGN KEY FK_ASIG_MATRICULA_GR_ASIG_ID
ALTER TABLE ASIG_MATRICULA DROP FOREIGN KEY FK_ASIG_MATRICULA_CURSOACADEMICO
ALTER TABLE CENTRO_TITULACION DROP FOREIGN KEY FK_CENTRO_TITULACION_idCentros_ID
ALTER TABLE CENTRO_TITULACION DROP FOREIGN KEY FK_CENTRO_TITULACION_c_titul_CODIGO
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA DROP FOREIGN KEY ENCUESTA_GRUPOS_POR_ASIGNATURA_gr_enc_FECHAENVIO
ALTER TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA DROP FOREIGN KEY FK_ENCUESTA_GRUPOS_POR_ASIGNATURA_CURSOACADEMICO
DROP TABLE ALUMNO
DROP TABLE ASIGNATURA
DROP TABLE CENTRO
DROP TABLE CLASE
DROP TABLE ENCUESTA
DROP TABLE EXPEDIENTE
DROP TABLE GRUPO
DROP TABLE GRUPOS_POR_ASIGNATURA
DROP TABLE MATRICULA
DROP TABLE OPTATIVA
DROP TABLE PAS
DROP TABLE TITULACION
DROP TABLE ASIG_MATRICULA
DROP TABLE CENTRO_TITULACION
DROP TABLE ENCUESTA_GRUPOS_POR_ASIGNATURA
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
