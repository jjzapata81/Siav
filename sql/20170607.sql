INSERT INTO ta_perfiles(nmperfil, dsperfil) VALUES (4, 'Gerencia');
INSERT INTO ta_perfiles(nmperfil, dsperfil) VALUES (5, 'Contable');

INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (107, 'Estructura acueducto', 'estructura-acueducto', 'N', 107, 1);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 107);
	
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (205, 'Macromedidores', 'macromedidor', 'N', 205, 2);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 205);
	
CREATE TABLE ta_macro_maestro
(
  nmmacro numeric(10,0) NOT NULL,
  nombre character varying(50) NOT NULL,
  marca character varying(50),
  direccion character varying(100),
  CONSTRAINT pk_ta_macro_maestro PRIMARY KEY (nmmacro)
);

CREATE SEQUENCE sq_ta_macro_maestro
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sq_ta_macro_maestro
  OWNER TO postgres;
  
INSERT INTO ta_recursos(nmrecurso, titulo, accion, snsubmenu, nmrelacion, nmgrupo)
    VALUES (233, 'Ramales', 'ramales', 'N', 233, 2);
INSERT INTO ta_recurso_perfil(nmrecursoperfil, nmperfil, nmrecurso)
    VALUES ((SELECT MAX(r.nmrecursoperfil) FROM ta_recurso_perfil r)+1, 1, 233);