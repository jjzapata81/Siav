DROP TABLE ta_login;
DROP TABLE ta_roles;

CREATE TABLE ta_comprobante_pago
(
  nmcomprobante numeric(6,0) NOT NULL,
  nminstalacion numeric(10,0),
  cedula character varying(50),
  valor numeric(10,0),
  usuario character varying(10) NOT NULL,
  fecha timestamp without time zone,
  nmcredito numeric(10,0),
  snmatricula character varying(1),
  sncancelado character varying(1),
  CONSTRAINT pk_ta_comprobante_pago PRIMARY KEY (nmcomprobante),
  CONSTRAINT ta_comprobante_pago_cedula_fkey FOREIGN KEY (cedula)
      REFERENCES ta_usuarios (cedula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);