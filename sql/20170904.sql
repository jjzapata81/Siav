CREATE TABLE ta_desactivacion
(
  codigo numeric(10,0) NOT NULL,
  nminstalacion numeric(10,0) NOT NULL,
  observacion character varying(500) NOT NULL,
  usuario character varying(10) NOT NULL,
  fecha date NOT NULL,
  CONSTRAINT pk_ta_desactivacion PRIMARY KEY (codigo),
  CONSTRAINT uk_ta_desactivacion_nminstalacion UNIQUE (nminstalacion)
);

CREATE SEQUENCE sq_ta_desactivacion
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_desactivacion
  OWNER TO postgres;

ALTER TABLE ta_salida_maestro DROP COLUMN nmordensalida;