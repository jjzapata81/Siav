CREATE TABLE ta_articulo
(
  nmarticulo numeric(10,0) NOT NULL,
  nombre character varying(100),
  unidad character varying(3),
  preciounitario numeric(10,2),
  precioinventario numeric(10,2),
  porcentajeganancia numeric(5,2),
  preciocomercial numeric(10,2),
  observacion character varying(200),
  snactivo character varying(1) NOT NULL DEFAULT 'S'::character varying,
  CONSTRAINT pk_ta_articulo PRIMARY KEY (nmarticulo)
);


CREATE TABLE ta_proveedor
(
  nmproveedor numeric(10,0) NOT NULL,
  nit numeric(10,0),
  razonsocial character varying(100),
  direccion character varying(100),
  telefono character varying(20),
  correo character varying(80),
  observacion character varying(200),
  CONSTRAINT pk_ta_proveedor PRIMARY KEY (nmproveedor)
);

CREATE TABLE ta_entrada_maestro
(
  nmentrada numeric(10,0) NOT NULL,
  ciclo numeric(10,0) NOT NULL,
  nmproveedor numeric(10,0) NOT NULL,
  nmfacturacompra numeric(10,0),
  fefacturacompra date,
  CONSTRAINT pk_ta_entrada_maestro PRIMARY KEY (nmentrada),
  CONSTRAINT ta_entrada_maestro_ciclo_fkey FOREIGN KEY (ciclo)
    REFERENCES ta_ciclos (ciclo) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ta_entrada_maestro_nmproveedor_fkey FOREIGN KEY (nmproveedor)
    REFERENCES ta_proveedor (nmproveedor) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ta_entrada_detalle
(
  nmentrada numeric(10,0) NOT NULL,
  nmarticulo numeric(10,0) NOT NULL,
  cantidad numeric(10,0),
  preciocompra numeric (10,2),
  CONSTRAINT pk_ta_entrada_detalle PRIMARY KEY (nmentrada, nmarticulo),
  CONSTRAINT ta_entrada_detalle_nmentrada_fkey FOREIGN KEY (nmentrada)
    REFERENCES ta_entrada_maestro (nmentrada) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ta_entrada_detalle_nmarticulo_fkey FOREIGN KEY (nmarticulo)
      REFERENCES ta_articulo (nmarticulo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ta_salida_maestro
(
  nmsalida numeric(10,0) NOT NULL,
  ciclo numeric(10,0) NOT NULL,
  nmdestino numeric(10,0),
  nmordensalida numeric (10,0),
  feordensalida date,
  CONSTRAINT pk_ta_salida_maestro PRIMARY KEY (nmsalida),
  CONSTRAINT ta_SALIDA_maestro_ciclo_fkey FOREIGN KEY (ciclo)
    REFERENCES ta_ciclos (ciclo) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ta_salida_detalle
(
  nmsalida numeric(10,0) NOT NULL,
  nmarticulo numeric(10,0) NOT NULL,
  cantidad numeric(10,0),
  preciocomercial numeric (10,2),
  CONSTRAINT pk_ta_salida_detalle PRIMARY KEY (nmsalida, nmarticulo),
  CONSTRAINT ta_salida_detalle_nmsalida_fkey FOREIGN KEY (nmsalida)
    REFERENCES ta_salida_maestro (nmsalida) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ta_salida_detalle_nmarticulo_fkey FOREIGN KEY (nmarticulo)
      REFERENCES ta_articulo (nmarticulo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ta_kardex
(
  nmkardex numeric(10,0) NOT NULL,
  nmarticulo numeric(10,0),
  nmentrada numeric(10,0),
  nmsalida numeric(10,0),
  tipo character varying(3),
  nmdocumento numeric(10,0),
  saldoanterior numeric(12,0),
  cantidadentrada numeric (10,0),
  cantidadsalida numeric (10,0),
  saldoactual numeric (12,0),
  valorsaldoanterior numeric (10,2),
  preciocompra numeric (10,2),
  preciocomercial numeric (10,2),
  valorsaldoactual numeric (10,2),
  CONSTRAINT pk_ta_kardex PRIMARY KEY (nmkardex),
  CONSTRAINT ta_kardex_nmarticulo_fkey FOREIGN KEY (nmarticulo)
      REFERENCES ta_articulo (nmarticulo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT ta_kardex_nmentrada_fkey FOREIGN KEY (nmentrada)
      REFERENCES ta_entrada_maestro (nmentrada) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT ta_kardex_nmsalida_fkey FOREIGN KEY (nmsalida)
      REFERENCES ta_salida_maestro (nmsalida) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'TIPO_KARDEX', 1,'ENTRADA');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'TIPO_KARDEX', 2,'SALIDA');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'TIPO_KARDEX', 3,'DEVOLUCIÓN ENTRADA');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'TIPO_KARDEX', 4,'DEVOLUCIÓN SALIDA');

INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'UNIDAD_ARTICULO', 1,'UND');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'UNIDAD_ARTICULO', 2,'MT');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'UNIDAD_ARTICULO', 3,'MT3');





