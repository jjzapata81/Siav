ALTER TABLE ta_credito_maestro ADD ref_refinanciado numeric(10,0);
ALTER TABLE ta_credito_maestro ADD ciclo numeric(10,0);

UPDATE ta_log_pagos set snerror = 'N' where snerror is null;
alter table ta_log_pagos ALTER COLUMN snerror SET not null;
alter table ta_log_pagos ALTER COLUMN snerror SET  default 'N';

UPDATE ta_log_pagos set sncredito = 'N' where sncredito is null;
alter table ta_log_pagos ALTER COLUMN sncredito SET not null;
alter table ta_log_pagos ALTER COLUMN sncredito SET  default 'N';

ALTER TABLE ta_credito_maestro ADD ciclo numeric(10,0);

CREATE TABLE ta_macro_lectura
(
  nmlectura numeric(10,0) NOT NULL,
  nmmacro numeric(10,0) NOT NULL,
  ciclo numeric(10,0) NOT NULL,
  fedesde timestamp without time zone,
  fehasta timestamp without time zone,
  leanterior numeric(10,0),
  leactual numeric(10,0),
  usuario_lectura character varying(55),
  snsincronizado character varying(1) NOT NULL DEFAULT 'N'::character varying,
  CONSTRAINT pk_ta_macro_lectura PRIMARY KEY (nmlectura),
  CONSTRAINT pk_ta_macro_lectura_nmmacro_fkey FOREIGN KEY (nmmacro)
      REFERENCES ta_macro_maestro (nmmacro) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE sq_ta_macro_lectura
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_macro_lectura
  OWNER TO postgres;

DELETE FROM ta_recurso_perfil;

DELETE FROM ta_recursos WHERE nmrecurso in (410,420,430,440,450,455,456,457,458,460,470,459);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (410, 'Instalaciones', 'S', 410, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (413, 'Cuentas vencidas', 'reporte-cuentas-vencidas', 'N', 410, 4);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (416, 'Instalaciones por usuario', 'instalaciones-usuarios', 'N', 410, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (419, 'Instalaciones por ruta', 'instalaciones-ruta', 'N', 410, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (420, 'Consumos', 'S', 420, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (422, 'Variación consumo', 'variacion-consumo', 'N', 420, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (424, 'Lecturas y consumos', 'lecturas-consumos', 'N', 420, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (430, 'Facturación', 'S', 430, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (432, 'Prefactura', 'reporte-prefactura', 'N', 430, 4);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (434, 'Factura', 'reporte-factura', 'N', 430, 4);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (436, 'Consolidado por concepto', 'consolidado-concepto', 'N', 430, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (440, 'Recaudo', 'S', 440, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (443, 'Recaudo detallado por pagos', 'detalle-recaudo', 'N', 440, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (446, 'Consumos no facturados', 'consumo-no-facturado', 'N', 440, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (450, 'Cartera', 'S', 450, 4);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (454, 'Cartera por usuario', 'cartera', 'N', 450, 4);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (464, 'Estadísticas', 'estadisticas', 'N', 454, 4);

	
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES (1, 1, 105);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 107);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 110);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 120);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 130);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 140);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 150);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 160);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 170);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 205);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 210);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 220);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 230);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 233);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 235);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 240);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 250);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 260);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 270);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 310);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 320);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 325);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 330);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 340);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 342);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 345);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 346);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 347);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 350);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 355);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 360);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 365);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 370);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 380);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 410);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 413);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 416);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 419);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 420);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 422);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 424);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 430);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 432);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 434);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 436);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 440);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 443);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 446);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 450);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 454);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 464);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 105);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 107);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 110);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 120);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 130);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 140);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 150);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 160);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 170);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 205);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 210);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 220);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 230);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 233);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 235);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 240);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 250);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 260);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 270);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 310);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 320);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 330);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 340);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 342);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 345);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 346);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 347);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 350);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 355);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 360);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 365);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 370);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 380);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 410);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 413);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 416);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 419);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 420);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 422);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 424);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 430);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 432);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 434);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 436);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 440);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 443);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 446);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 450);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 454);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 464);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 3, 170);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 3, 235);


INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (280, 'Auditoria', 'S', 280, 2);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (284, 'Pagos', 'pagos-consulta', 'N', 280, 2);

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (288, 'Inconsistencias', 'inconsistencias-consulta', 'N', 280, 2);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 280);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 284);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 1, 288);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 280);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 284);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso) VALUES ((SELECT MAX(nmrecursoperfil) + 1 from ta_recurso_perfil), 2, 288);