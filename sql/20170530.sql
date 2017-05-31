ALTER TABLE ta_sistema ADD COLUMN idmatricula character varying(10);
UPDATE ta_sistema SET idmatricula = '222222' WHERE id = 1;
INSERT INTO ta_tarifas(cdconcepto, tipo, descripcion, estrato0, estrato1, estrato2,estrato3, estrato4, estrato5, estrato6)
    VALUES ('222222', 2, 'INICIAL MATRÍCULA', 0, 0, 0, 0, 0, 0, 0);
ALTER TABLE ta_causas_no_lectura ADD COLUMN snactiva character varying(1);
UPDATE ta_causas_no_lectura SET snactiva = 'S';

CREATE TABLE ta_consumo_auditoria
(
  id numeric(10,0) NOT NULL,
  lectura numeric(10,0),
  lectura_corregida numeric(10,0),
  consumo numeric(10,0),
  consumo_corregido numeric(10,0),
  ciclo numeric(10,0),
  nminstalacion numeric(10,0),
  observacion character varying(500),
  usuario character varying(10),
  fecha timestamp without time zone,
  CONSTRAINT pk_ta_consumo_auditoria PRIMARY KEY (id)
);


CREATE SEQUENCE sq_ta_consumo_auditoria
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;