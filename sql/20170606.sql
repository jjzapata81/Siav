INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES (7, 'CODIGO_CARGO', '1', 'PRESIDENTE');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES (8, 'CODIGO_CARGO', '2', 'SECRETARIO');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES (9, 'CODIGO_CARGO', '3', 'VOCAL');

CREATE TABLE ta_bancos
(
  nmbanco numeric(2,0) NOT NULL,
  nombre character varying(100),
  CONSTRAINT pk_ta_bancos PRIMARY KEY (nmbanco)
);

INSERT INTO ta_bancos(nmbanco, nombre) VALUES (1, 'COTRAFA');

CREATE SEQUENCE sq_ta_bancos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE sq_ta_bancos
  OWNER TO postgres;

DROP TABLE ta_cuentas;

CREATE TABLE ta_cuentas
(
  codigo numeric(10,0) NOT NULL,
  nmbanco numeric(10,0) NOT NULL,
  nombre character varying(100),
  numerocuenta character varying(20),
  CONSTRAINT pk_ta_cuentas PRIMARY KEY (codigo),
  CONSTRAINT ta_cuentas_fkey FOREIGN KEY (nmbanco)
      REFERENCES ta_bancos (nmbanco) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO ta_cuentas(codigo, nmbanco, nombre, numerocuenta)
    VALUES (1, 1, 'COTRAFA RECAUDO', '0051-000020714');
INSERT INTO ta_cuentas(codigo, nmbanco, nombre, numerocuenta)
    VALUES (2, 1, 'COTRAFA AUXILIAR CONSIGNACION', '0051-000021858');

CREATE TABLE ta_estructura
(
  nmempresa numeric(10,0) NOT NULL,
  cedula character varying(50) NOT NULL,
  cdcargo character varying(2) NOT NULL,
  snactivo character varying(1),
  acta character varying (100),
  fecha date NOT NULL,
  fechabaja date,
  CONSTRAINT pk_ta_estructura PRIMARY KEY (nmempresa, cedula, cdcargo, fecha),
  CONSTRAINT ta_estructura_cedula_fkey FOREIGN KEY (cedula)
      REFERENCES ta_usuarios (cedula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (105, 'Empresa', 'empresa', 'N', 105, 1);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 105);