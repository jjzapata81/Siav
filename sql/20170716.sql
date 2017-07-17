
CREATE TABLE ta_nota_credito_auditoria
(
  id numeric(10,0) NOT NULL,
  nmciclo numeric(10,0) NOT NULL,
  nminstalacion numeric(10,0) NOT NULL,
  nmvalor numeric(10,0) NOT NULL,
  usuario character varying(10) NOT NULL,
  observacion character varying(500) NOT NULL,
  fecha date NOT NULL,

  CONSTRAINT pk_ta_nota_credito_auditoria PRIMARY KEY (id)
);

CREATE SEQUENCE sq_ta_nota_credito_auditoria
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_nota_credito_auditoria
  OWNER TO postgres;

 INSERT INTO ta_tarifas(cdconcepto, tipo, descripcion, estrato0, estrato1, estrato2, 
            estrato3, estrato4, estrato5, estrato6, nmorden)
    VALUES ('888888', '2', 'NOTA CRÉDITO', 0, 0, 0, 0, 0, 0, 0,99); 