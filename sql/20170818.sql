ALTER TABLE ta_usuario_sistema ADD email character varying(50);

INSERT INTO ta_recursos(nmrecurso, titulo, snsubmenu, nmrelacion, nmgrupo)
    VALUES (290, 'Servicio al cliente', 'S', 290, 2);
    
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 290);
    
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 2, 290);
    
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (294, 'PQR', 'pqr', 'N', 290, 2);
    
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 294);

INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 2, 294);
    
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'PQR_ESTADO', 1,'ABIERTO');
INSERT INTO ta_datosmaestros(id, grupo, cdcodigo, dsvalor) VALUES ((SELECT MAX(d.id) FROM ta_datosmaestros d)+1, 'PQR_ESTADO', 2,'CERRADO');

ALTER TABLE ta_usuario_sistema ADD CONSTRAINT uk_ta_usuario_sistema UNIQUE (nombreusuario);

CREATE TABLE ta_pqr_maestro
(
  nmpqr numeric(10,0) NOT NULL,
  nminstalacion numeric(10,0) NOT NULL,
  fechainicio date NOT NULL,
  fechafin date,
  estado numeric(2,0) NOT NULL,
  descripcion character varying(100) NOT NULL,
  usuario character varying(10) NOT NULL,
  CONSTRAINT pk_ta_pqr_maestro PRIMARY KEY (nmpqr),
  CONSTRAINT ta_pqr_maestro_nminstalacion_fkey FOREIGN KEY (nminstalacion)
      REFERENCES ta_instalacion (nminstalacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ta_pqr_maestro_usuario_fkey FOREIGN KEY (usuario)
      REFERENCES ta_usuario_sistema (nombreusuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE sq_ta_pqr_maestro
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_pqr_maestro
  OWNER TO postgres;

CREATE TABLE ta_pqr_detalle
(
  id numeric(10,0) NOT NULL,
  nmpqr numeric(10,0) NOT NULL,
  fechaaccion date NOT NULL,
  usuario character varying(10) NOT NULL,
  accion character varying(500) NOT NULL,
  CONSTRAINT pk_ta_pqr_detalle PRIMARY KEY (id),
  CONSTRAINT ta_pqr_detalle_nmpqr_fkey FOREIGN KEY (nmpqr)
      REFERENCES ta_pqr_maestro (nmpqr) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ta_pqr_detalle_usuario_fkey FOREIGN KEY (usuario)
      REFERENCES ta_usuario_sistema (nombreusuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE sq_ta_pqr_detalle
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_pqr_detalle
  OWNER TO postgres;